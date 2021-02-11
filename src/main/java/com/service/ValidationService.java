package com.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.model.FileOfChanges2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ValidationService {

    private final Logger LOG = LoggerFactory.getLogger(ValidationService.class);

    @Autowired
    private SshService sshService;

    public FileOfChanges2 validateFileOfChanges(FileOfChanges2 fileOfChanges2) {
        System.out.println(fileOfChanges2);

        final long l = System.nanoTime();

        sshService.validateFilesInRemote(fileOfChanges2);
        final long l1 = System.nanoTime();
        final long l2 = l1 - l;
        System.out.println("result in nanosecs - " + l2);

        return fileOfChanges2;


    }

    public FileOfChanges2 copyFileOfChanges(FileOfChanges2 fileOfChanges) {
        List<String> headers = new ArrayList<>();
        List<List<Object>> values = new ArrayList<>();

        for (String header : fileOfChanges.getHeaders()) {
            headers.add(header);
        }

        for (List<Object> value : fileOfChanges.getValues()) {
            values.add(value);
        }

        FileOfChanges2 fileOfChanges2 = new FileOfChanges2();
        fileOfChanges2.setHeaders(headers);
        fileOfChanges2.setValues(values);

        return fileOfChanges2;
    }

}

class DataTread implements Runnable {

    private final FileOfChanges2 file;

    private final Logger LOG = LoggerFactory.getLogger(DataTread.class);

    private final int SMALL_WAIT = 100;
    private int BIG_WAIT = 800;

    private final String OPERATION;

    private final int COLUMN_NUMBER;

    public DataTread(FileOfChanges2 file, String OPERATION, int COLUMN_NUMBER) {
        this.file = file;
        this.OPERATION = OPERATION;
        this.COLUMN_NUMBER = COLUMN_NUMBER;
    }

    @Override
    public void run() {
        Set<String> allRnc = file.getValues().stream().map(elem -> elem.get(8)).map(elem -> (String) elem).collect(Collectors.toSet());

        Session session = null;
        Channel channel = null;
        OutputStream ops = null;
        PrintStream ps = null;

        try {
            JSch jsch = new JSch();     //CHAN
            session = jsch.getSession("dpleskac", "10.4.164.23", 22);
            session.setPassword("Oles73Oles73");

            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            channel = session.openChannel("shell");

            ops = channel.getOutputStream();

            String utf8 = StandardCharsets.UTF_8.name();

            ps = new PrintStream(ops, true, utf8);

            channel.connect();
            InputStream input = channel.getInputStream();

            for (String rnc : allRnc) {
                //commands
                ps.println("amos " + rnc);
                LOG.info("amos " + rnc);

                printResult(input, rnc, BIG_WAIT);

                ps.println("LT ALL");
                LOG.info("LT ALL");

                printResult(input, "Total:", BIG_WAIT);

                for (List<Object> row : file.getValues()) {

                    performOperation(ps, OPERATION, COLUMN_NUMBER, row, input);

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

    }

    List<String> printResult(InputStream input, String comparingString, int retry) throws IOException, InterruptedException {
        List<String> commands = new ArrayList<>();

        for (int i = 0; i < retry; i++) {
            if(input.available() <= 0) Thread.sleep(50);
        }

        while (input.available() > 0) {
            byte[] bytes = new byte[1024];
            int i = input.read(bytes, 0, 1024);
            if (i < 0) break;

            for (int j = 0; j < retry; j++) {
                if(input.available() <= 0) Thread.sleep(50);
            }

            commands.add(new String(bytes, 0, i));
            if(new String(bytes, 0, i).contains(comparingString)) break;
        }

        if (!commands.isEmpty()) {
            final Optional<String> s = commands.stream().filter(e -> e.contains(comparingString)).findAny();
            if (!s.isPresent()) {
                LOG.error("comparing string doesn't found, something went wrong");

                throw new IOException("");
            } else {
                LOG.info(String.valueOf(commands));
            }
        }

        LOG.info(String.valueOf(commands));

        return commands;
    }

    public void performOperation(PrintStream ps, String operation, int columnNumber,  List<Object> row, InputStream input) {
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

}


class DataTread2 implements Runnable {

    private final FileOfChanges2 file;

    private final Logger LOG = LoggerFactory.getLogger(DataTread2.class);

    private final int SMALL_WAIT = 100;
    private int BIG_WAIT = 800;

    private final String OPERATION;

    private final int COLUMN_NUMBER;

    public DataTread2(FileOfChanges2 file, String OPERATION, int COLUMN_NUMBER) {
        this.file = file;
        this.OPERATION = OPERATION;
        this.COLUMN_NUMBER = COLUMN_NUMBER;
    }

    @Override
    public void run() {
        Set<String> allRnc = file.getValues().stream().map(elem -> elem.get(8)).map(elem -> (String) elem).collect(Collectors.toSet());

        Session session = null;
        Channel channel = null;
        OutputStream ops = null;
        PrintStream ps = null;

        try {
            JSch jsch = new JSch();     //CHAN
            session = jsch.getSession("dpleskac", "10.4.164.23", 22);
            session.setPassword("Oles73Oles73");

            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            channel = session.openChannel("shell");

            ops = channel.getOutputStream();

            String utf8 = StandardCharsets.UTF_8.name();

            ps = new PrintStream(ops, true, utf8);

            channel.connect();
            InputStream input = channel.getInputStream();

            for (String rnc : allRnc) {
                //commands
                ps.println("amos " + rnc);
                LOG.info("amos " + rnc);

                printResult(input, rnc, BIG_WAIT);

                ps.println("LT ALL");
                LOG.info("LT ALL");

                printResult(input, "Total:", BIG_WAIT);

                for (List<Object> row : file.getValues()) {
                    performOperationWithSuffix(ps, OPERATION, COLUMN_NUMBER, row, input, "$");
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

    }

    List<String> printResult(InputStream input, String comparingString, int retry) throws IOException, InterruptedException {
        List<String> commands = new ArrayList<>();

        for (int i = 0; i < retry; i++) {
            if(input.available() <= 0) Thread.sleep(50);
        }

        while (input.available() > 0) {
            byte[] bytes = new byte[1024];
            int i = input.read(bytes, 0, 1024);
            if (i < 0) break;

            for (int j = 0; j < retry; j++) {
                if(input.available() <= 0) Thread.sleep(50);
            }

            commands.add(new String(bytes, 0, i));
            if(new String(bytes, 0, i).contains(comparingString)) break;
        }

        if (!commands.isEmpty()) {
            final Optional<String> s = commands.stream().filter(e -> e.contains(comparingString)).findAny();
            if (!s.isPresent()) {
                LOG.error("comparing string doesn't found, something went wrong");

                throw new IOException("");
            } else {
                LOG.info(String.valueOf(commands));
            }
        }

        LOG.info(String.valueOf(commands));

        return commands;
    }

    public void performOperationWithSuffix(PrintStream ps, String operation, int columnNumber,  List<Object> row, InputStream input, String suffix) {
        ps.println(operation + row.get(columnNumber) + suffix);
        LOG.info(operation + row.get(columnNumber));

        try {
            printResult(input, "Total: 0 MOs", SMALL_WAIT);
            row.set(columnNumber, true);
        } catch (Exception e) {
            // TODO if exception than change value in list on false

            row.set(columnNumber, false);
        }
    }

}