package com.service;

import com.jcraft.jsch.*;
import com.model.RncModification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class SshService {

  @Value("${user}")
  static String user = "dpleskac";

  @Value("${host}")
  static String host = "10.4.164.21";

  @Value("${password}")
  static String password = "Oles73Oles73";

  @Value("${port}")
  static int port = 22;

  private static final Logger LOG = LogManager.getLogger(SshService.class);

//  public static void main(String[] args) throws Exception {
//    long start = System.nanoTime();
//
//    FileParsingService parseCsvFileService = new FileParsingService();
//    String pathToCreationCommands = "filesOfChanges/fileOfChanges.csv";
//    createFilesInRemote(parseCsvFileService.getAllFileChanges(pathToCreationCommands));
//
//    long end = System.nanoTime();
//
//    LOG.debug("time of execution = " + (end - start) + " nanoseconds");
//  }

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

        printResult(input, rncModification.getModifications().get(0).getBSC());

        ps.println("LT ALL");
        LOG.info("LT ALL");

        printResult(input, "Total:");

        ps.println("us+");
        LOG.info("us+");

        printResult(input, "Starting the simulated undo mode");

        ps.println("us?");
        LOG.info("us?");

        printResult(input, "Simulated Undo Mode is active");

        agreeWithConditions(ps, input, new StringBuilder("rdel iublink=" + rncModification.getModifications().get(0).getSite()));

        ps.println("us-");
        LOG.info("us-");

        final List<String> strings = printResult(input, "To undo, execute command: run /ericsson/log/amos/moshell_logfiles/dpleskac/logs_moshell/undo/");

        if (!strings.isEmpty()) {
          final String pathToFile = extractPathToFiles(strings.get(0).split("\\s"));
          System.out.println(pathToFile);
          files.add(pathToFile);
        }

        ps.println("us+");
        LOG.info("us+");

        printResult(input, "Starting the simulated undo mode", 3000);

        ps.println("us?");
        LOG.info("us?");

        printResult(input, "Simulated Undo Mode is active");

        agreeWithConditions(ps, input, new StringBuilder("rdel ExternalEutranCell=" + rncModification.getModifications().get(0).getSite()));

        ps.println("us-");
        LOG.info("us-");

        final List<String> pathToFile = printResult(input, "To undo, execute command: run /ericsson/log/amos/moshell_logfiles/dpleskac/logs_moshell/undo/");

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
        if(ops != null) {
          ops.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }

      if(ps != null) ps.close();
      if(channel != null) channel.disconnect();
      if(session != null) session.disconnect();

    }


    return files;
  }

  static List<String> printResult(InputStream input, String comparingString) throws IOException, InterruptedException {
    Thread.sleep(2000);
    List<String> commands = new ArrayList<>();

    while (input.available() > 0) {
      Thread.sleep(300);
      byte[] bytes = new byte[1024];
      int i = input.read(bytes, 0, 1024);
      if (i < 0) break;

      commands.add(new String(bytes, 0, i));
    }

    if (!commands.isEmpty()) {
      final Optional<String> s = commands.stream().filter(e -> e.contains(comparingString)).findAny();
      if (!s.isPresent()) {
        LOG.error("comparing string doesn't found, something went wrong");

        // TODO disconnect from server or perform command again
      } else {
        LOG.info(commands);
      }
    }

    LOG.info(commands);

    return commands;
  }

  public static List<String> printResult(InputStream input, String comparingString, int waitTime) throws IOException, InterruptedException {
    Thread.sleep(waitTime);

    return printResult(input, comparingString);
  }

  static String extractPathToFiles(String[] commands) {

    final String pathToCreationCommands = Arrays.stream(commands)
      .filter(el -> el.contains(".mos"))
      .filter(el -> !el.contains("del"))
      .findFirst().get();

    return pathToCreationCommands;
  }

  static void agreeWithConditions(PrintStream printStream, InputStream inputStream, StringBuilder sb) throws IOException, InterruptedException {
    String checkPhrase = "Are you Sure [y/n]";
    String previousCheckPhrase = "Are you Sure [y/n] ? y";

    for (; ; ) {
      printStream.println(sb.toString());

      final List<String> strings = SshService.printResult(inputStream, checkPhrase);

      List<String> stringSubList;
      long count = 0;
      if(strings.size() > 1) {
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

}
