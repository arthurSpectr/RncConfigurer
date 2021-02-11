package com.service;

import com.exceptions.NotFoundRncException;
import com.jcraft.jsch.*;
import com.model.FileOfChanges2;
import com.model.RncModification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class SshService {

    private static final Logger LOG = LogManager.getLogger(SshService.class);

    @Value("${user}")
    private String user;

    @Value("${host}")
    private String host;

    @Value("${password}")
    private String password;

    @Value("${port}")
    private int port;

    private JSch jsch = new JSch();

    private final int DELAY = 20;
    private final int SMALL_WAIT = 50;
    private final int BIG_WAIT = 4000;

    private Stack<String> hosts = new Stack<String>(){{push("10.4.164.21"); push("10.4.164.22"); push("10.4.164.24"); push("10.4.164.23"); }};

    public Map<String, SessionResource> rncSession = new HashMap<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    private boolean isInitialized = false;

    public FileOfChanges2 init(FileOfChanges2 fileOfChanges2) throws NotFoundRncException {
        if(!isInitialized) {
            List<String> poppedHosts = new ArrayList<>();
            Set<String> allRnc = fileOfChanges2.getValues().stream().map(elem -> elem.get(8)).map(elem -> (String) elem).collect(Collectors.toSet());

            List<Exception> exceptions = new ArrayList<>();
            List<Future<?>> futureList = new ArrayList<>();

            for (String rnc : allRnc) {
                final String host = hosts.pop();
                poppedHosts.add(host);
                final Future<?> future = getExecutorService().submit(new InitThread(rnc, host, getRncSession(), jsch, exceptions));
                futureList.add(future);
            }

            boolean allDone = false;

            while (!allDone) {
                boolean atLeastOneFalse = true;
                for (Future<?> future : futureList) {
                    if(!future.isDone()) {
                        atLeastOneFalse = false;
                        break;
                    }
                }

                if(atLeastOneFalse) {
                    allDone = true;
                }
            }

            if(!exceptions.isEmpty()) {
                // TODO rewrite this bullshit
                for (String host : poppedHosts) {
                    hosts.push(host);
                }
                throw new NotFoundRncException("Rnc(s) that does not exist ", exceptions);
            }


//            if(!executorService.isShutdown()) {
//                executorService.shutdown();
//            }
            isInitialized = true;

        }
        return fileOfChanges2;
    }

    public void validateFilesInRemote(FileOfChanges2 fileOfChanges2) {
        Set<String> allRnc = fileOfChanges2.getValues().stream().map(elem -> elem.get(8)).map(elem -> (String) elem).collect(Collectors.toSet());

        for (String rnc : allRnc) {
            final SessionResource sessionResources = getRncSession().get(rnc);

            if(sessionResources == null) throw new RuntimeException("no available session for rnc - " + rnc);
            if(!sessionResources.getChannel().isConnected()) throw new RuntimeException("channel is not connected.");

            final PrintStream ps = sessionResources.getPrintStream();
            final InputStream input = sessionResources.getInputStream();

            for (List<Object> row : fileOfChanges2.getValues()) {

                performOperation(ps, "pr UtranCell=", 3, row, input);
                performOperation(ps, "pr LocationArea=", 9, row, input);
                performOperation(ps, "get . localCellId=", 10, row, input);
                performOperation(ps, "pr Ura=", 11, row, input);
                performOperationWithSuffix(ps, "get . rbsid ^", 12, row, input, "$");

            }


        }

    }

    public void performOperationWithSuffix(PrintStream ps, String operation, int columnNumber, List<Object> row, InputStream input, String suffix) {
        ps.println(operation + row.get(columnNumber) + suffix);
        LOG.info(operation + row.get(columnNumber) + suffix);

        try {
            printResult(input, "Total: 0 MOs", SMALL_WAIT);
            row.set(columnNumber, true);
        } catch (Exception e) {
            // TODO if exception than change value in list on false

            row.set(columnNumber, false);
        }
    }

    public void performOperation(PrintStream ps, String operation, int columnNumber, List<Object> row, InputStream input) {
        ps.println(operation + row.get(columnNumber));
        LOG.info(operation + row.get(columnNumber));

        try {
            printResult(input, "Total: 0 MOs", SMALL_WAIT);
            row.set(columnNumber, true);
        } catch (Exception e) {
            // TODO if exception than change value in list on false

            row.set(columnNumber, false);
        }
    }

    public List<String> createFilesInRemote(List<RncModification> rncNames) {
        List<String> files = new ArrayList<>();

        Session session = null;
        Channel channel = null;
        OutputStream ops = null;
        PrintStream ps = null;

        try {
            JSch jsch = new JSch();     //CHAN
            session = jsch.getSession(user, host, port);
            session.setPassword(password);

            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            channel = session.openChannel("shell");

            ops = channel.getOutputStream();

            String utf8 = StandardCharsets.UTF_8.name();

            ps = new PrintStream(ops, true, utf8);

            channel.connect();
            InputStream input = channel.getInputStream();

            for (RncModification rncModification : rncNames) {
                //commands
                ps.println("amos " + rncModification.getModifications().get(0).getBSC());
                LOG.info("amos " + rncModification.getModifications().get(0).getBSC());

                printResult(input, rncModification.getModifications().get(0).getBSC(), BIG_WAIT);

                ps.println("LT ALL");
                LOG.info("LT ALL");

                printResult(input, "Total:", BIG_WAIT);

                ps.println("us+");
                LOG.info("us+");

                printResult(input, "Starting the simulated undo mode", SMALL_WAIT);

                ps.println("us?");
                LOG.info("us?");

                printResult(input, "Simulated Undo Mode is active", SMALL_WAIT);

                agreeWithConditions(ps, input, new StringBuilder("rdel iublink=" + rncModification.getModifications().get(0).getSite()));

                ps.println("us-");
                LOG.info("us-");

                final List<String> strings = printResult(input, "To undo, execute command: run /ericsson/log/amos/moshell_logfiles/dpleskac/logs_moshell/undo/", BIG_WAIT);

                if (!strings.isEmpty()) {
                    final String pathToFile = extractPathToFiles(strings.get(0).split("\\s"));
                    System.out.println(pathToFile);
                    files.add(pathToFile);
                }

                ps.println("us+");
                LOG.info("us+");

                printResult(input, "Starting the simulated undo mode", SMALL_WAIT);

                ps.println("us?");
                LOG.info("us?");

                printResult(input, "Simulated Undo Mode is active", SMALL_WAIT);

                agreeWithConditions(ps, input, new StringBuilder("rdel ExternalEutranCell=" + rncModification.getModifications().get(0).getSite()));

                ps.println("us-");
                LOG.info("us-");

                final List<String> pathToFile = printResult(input, "To undo, execute command: run /ericsson/log/amos/moshell_logfiles/dpleskac/logs_moshell/undo/", BIG_WAIT);

                if (!pathToFile.isEmpty()) {
                    final String file = extractPathToFiles(pathToFile.get(0).split("\\s"));
                    System.out.println(file);
                    files.add(file);
                }
            }

        } catch (JSchException e) {
            LOG.error("can't connect via ssh  ", e);
        } catch (IOException e) {
            LOG.error("can't get output from rnc host  ", e);
        } catch (InterruptedException e) {
            LOG.error("can't read or perform commands in rnc host ", e);
        } finally {

            try {
                if (ops != null) {
                    ops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (ps != null) ps.close();
            if (channel != null) channel.disconnect();
            if (session != null) session.disconnect();

        }


        return files;
    }

    List<String> printResult(InputStream input, String comparingString, int retry) throws IOException, InterruptedException {
        List<String> commands = new ArrayList<>();

        for (int i = 0; i < retry; i++) {
            if (input.available() == 0) {
                Thread.sleep(DELAY);
            } else break;
        }

        while (input.available() > 0) {
            byte[] bytes = new byte[1024];
            int i = input.read(bytes, 0, 1024);
            if (i < 0) break;

            commands.add(new String(bytes, 0, i));
            if (new String(bytes, 0, i).contains(comparingString)) break;

            for (int j = 0; j < retry; j++) {
                if (input.available() == 0) {
                    Thread.sleep(DELAY);
                } else break;
            }
        }

        if (!commands.isEmpty()) {
            final Optional<String> s = commands.stream().filter(e -> e.contains(comparingString)).findAny();
            if (!s.isPresent()) {
                LOG.error("comparing string doesn't found, something went wrong");

                throw new IOException("");
            } else {
                LOG.info(commands);
            }
        }

//        LOG.info(commands);

        return commands;
    }

    static String extractPathToFiles(String[] commands) {

        final String pathToCreationCommands = Arrays.stream(commands)
                .filter(el -> el.contains(".mos"))
                .filter(el -> !el.contains("del"))
                .findFirst().get();

        return pathToCreationCommands;
    }

    void agreeWithConditions(PrintStream printStream, InputStream inputStream, StringBuilder sb) throws IOException, InterruptedException {
        String checkPhrase = "Are you Sure [y/n]";
        String previousCheckPhrase = "Are you Sure [y/n] ? y";

        for (; ; ) {
            printStream.println(sb.toString());

            final List<String> strings = printResult(inputStream, checkPhrase, BIG_WAIT);

            List<String> stringSubList;
            long count = 0;
            if (strings.size() > 1) {
                stringSubList = new ArrayList<>(strings.subList(1, strings.size()));
                count = stringSubList.stream().filter(e -> e.contains(checkPhrase)).filter(e -> !e.contains(previousCheckPhrase)).count();
            } else {
                count = strings.stream().filter(e -> e.contains(checkPhrase)).count();
            }

            if (count == 0) {
                return;
            }

            boolean contains = false;

            for (int i = 1; i <= strings.size(); i++) {

                contains = strings.get(strings.size() - i).contains(checkPhrase);
                if (contains) break;
            }

            if (contains) {
                sb.append("\ny");
            } else {
                LOG.info("did strings read completely? {}", strings.get(strings.size() - 1).contains("Total"));
                printStream.println("\n");
                return;
            }
        }

    }

    public SessionResource getAvailableSessionResources(String rnc) {
        if(getRncSession().size() == 0) throw new RuntimeException("no available connected session on oss");

        return getRncSession().get(rnc);
    }

    public Map<String, SessionResource> getRncSession() {
        return rncSession;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}

class InitThread implements Runnable {

    private final Logger LOG = LogManager.getLogger(InitThread.class);

    private final String rnc;
    private final String host;
    private final JSch jSch;

    private final Map<String, SessionResource> rncSessions;
    private final List<Exception> exceptions;

    public InitThread(String rnc, String host, Map<String, SessionResource> rncSessions, JSch jSch, List<Exception> exceptions) {
        this.rnc = rnc;
        this.host = host;
        this.rncSessions = rncSessions;
        this.jSch = jSch;
        this.exceptions = exceptions;
    }

    @Override
    public void run() {

        SessionResource sessionResource = new SessionResource(rnc, host, jSch);

        try {
            sessionResource.init();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            exceptions.add(e);
            Thread.currentThread().interrupt();
        }
        rncSessions.put(rnc, sessionResource);

    }

}

class SessionResource {

    private final org.slf4j.Logger LOG = LoggerFactory.getLogger(SessionResource.class);

    private final int DELAY = 100;
    private final int SMALL_WAIT = 100;
    private final int BIG_WAIT = 800;

    private Session session;

    private Channel channel;

    private OutputStream outputStream;

    private InputStream inputStream;

    private PrintStream printStream;

    private final String rnc;
    private final String host;
    private final JSch jSch;

    public SessionResource(String rnc,  String host, JSch jSch) {
        this.rnc = rnc;
        this.host = host;
        this.jSch = jSch;
    }

    public void init() throws Exception {

        try {
            session = jSch.getSession("dpleskac", host, 22);
            session.setPassword("Oles73Oles73");

            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            channel = session.openChannel("shell");

            outputStream = channel.getOutputStream();

            String utf8 = StandardCharsets.UTF_8.name();

            printStream = new PrintStream(outputStream, true, utf8);

            channel.connect();
            inputStream = channel.getInputStream();

            //commands
            printStream.println("amos " + rnc);
            LOG.info("amos " + rnc);

            checkTerminalOutput(inputStream, rnc+">", "Cannot connect to", BIG_WAIT);

            printStream.println("LT ALL");
            LOG.info("LT ALL");

            checkTerminalOutput(inputStream, "Total:", BIG_WAIT);

        } catch (JSchException e) {
            LOG.error("can't connect via ssh  ", e);
        } catch (IOException e) {
            LOG.error("Specified rnc does not exist or can'tget output from rnc host  ", e);
            throw new Exception(rnc);
        } catch (InterruptedException e) {
            LOG.error("can't read or perform commands in rnc host ", e);
        }
    }

    List<String> checkTerminalOutput(InputStream input, String successString, String errorString, int retry) throws IOException, InterruptedException {
        List<String> commands = new ArrayList<>();

        for (int i = 0; i < retry; i++) {
            if (input.available() <= 0) {
                Thread.sleep(DELAY);
            } else break;
        }

        while (input.available() > 0) {
            byte[] bytes = new byte[1024];
            int i = input.read(bytes, 0, 1024);
            if (i < 0) break;

            commands.add(new String(bytes, 0, i));
            if (new String(bytes, 0, i).contains(successString)) break;
            if (new String(bytes, 0, i).contains(errorString)) break;

            for (int j = 0; j < retry; j++) {
                if (input.available() <= 0) {
                    Thread.sleep(DELAY);
                } else break;
            }
        }

        if (!commands.isEmpty()) {
            final Optional<String> success = commands.stream().filter(e -> e.contains(successString)).findAny();
            final Optional<String> error = commands.stream().filter(e -> e.contains(errorString)).findAny();
            if (!success.isPresent() || error.isPresent()) {
                LOG.error("comparing string doesn't found, something went wrong");

                throw new IOException("specified rnc does not exist");
            } else {
                LOG.info(String.valueOf(commands));
            }
        }

        LOG.info(String.valueOf(commands));

        return commands;
    }

    List<String> checkTerminalOutput(InputStream input, String successString, int retry) throws IOException, InterruptedException {
        List<String> commands = new ArrayList<>();

        for (int i = 0; i < retry; i++) {
            if (input.available() <= 0) {
                Thread.sleep(DELAY);
            } else break;
        }

        while (input.available() > 0) {
            byte[] bytes = new byte[1024];
            int i = input.read(bytes, 0, 1024);
            if (i < 0) break;

            commands.add(new String(bytes, 0, i));
            if (new String(bytes, 0, i).contains(successString)) break;

            for (int j = 0; j < retry; j++) {
                if (input.available() <= 0) {
                    Thread.sleep(DELAY);
                } else break;
            }
        }

        if (!commands.isEmpty()) {
            final Optional<String> success = commands.stream().filter(e -> e.contains(successString)).findAny();
            if (!success.isPresent()) {
                LOG.error("comparing string doesn't found, something went wrong");

                throw new IOException("specified rnc does not exist");
            } else {
                LOG.info(String.valueOf(commands));
            }
        }

        LOG.info(String.valueOf(commands));

        return commands;
    }

    public Session getSession() {
        return session;
    }

    public Channel getChannel() {
        return channel;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }
}