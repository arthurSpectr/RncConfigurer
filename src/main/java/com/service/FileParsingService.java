package com.service;

import com.exceptions.NotFoundRncException;
import com.model.FileOfChanges;
import com.model.FileOfChanges2;
import com.model.RncModification;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileParsingService {

  @Autowired
  private SshService sshService;

  private static final Logger LOG = LogManager.getLogger(FileParsingService.class);

  public List<List<String>> readCsv(String fileName) {
    List<List<String>> lines = new ArrayList<>();
    String csvFile;
    if (null == fileName || fileName.isEmpty() || isValidateFileName(fileName)) {
      csvFile = "/home/atian/Documents/uploads/RncMaximoTable.csv";
    } else {
      csvFile = "/home/atian/Documents/uploads/" + fileName;
    }

    CSVReader reader = null;

    try {

      Files.readAllLines(new File(csvFile).toPath()).stream().filter(e -> e.length() > 0).forEach(e -> lines.add(Arrays.asList(e)));

//      reader = new CSVReader(new FileReader(csvFile));
//
//      String[] line;
//      while ((line = reader.readNext()) != null) {
//        if(line.length > 0) {
//          lines.add(Arrays.asList(line));
//        }
//      }

    } catch (IOException e) {
      LOG.error("something went wrong ", e);
    }

    return lines;
  }

  public List<Map<String, String>> readMapCsv(String file) {
    String fileName = "filesOfChanges/" + file;
    List<Map<String, String>> lines = new ArrayList<>();
    String csvFile;

    if (null == file || file.isEmpty() || !isValidateFileName(fileName)) {
      csvFile = fileName;
    } else {
      return null;
    }

    CSVReader reader = null;

    try {
      reader = new CSVReader(new FileReader(csvFile));

      String[] line;
      List<String> headers = Arrays.asList(reader.readNext());

      while ((line = reader.readNext()) != null) {
        Map<String, String> values = new TreeMap<>();
        if (line.length > 0) {
          for (int i = 0; i < line.length; i++) {
            values.put(headers.get(i), line[i]);
          }
        }

        if (!values.isEmpty()) {
          lines.add(values);
        }
      }

    } catch (IOException e) {
      LOG.error("something went wrong ", e);
    }

    return lines;
  }

//  public FileOfChanges2 validateFileOfChanges() {
//    FileOfChanges2 fileOfChanges2 = new FileOfChanges2();
//
//    List<String> headers = Arrays.asList("Rehom.Order", "BSC", "Site", "Cell", "LON", "LAT", "LAC", "CI", "New RNC", "New LAC", "New CI", "New Ura", "New_RBSID_1", "New_RBSID_2");
//    fileOfChanges2.setHeaders(headers);
//
//    List<List<String>> values = new ArrayList<>();
//    List<String> valueRow = Arrays.asList("", "KITR1", "KITI01", "KITI01A", "", "", "55555", "10001", "KIER7", "23456", "20313", "44444", "31", "");
//    List<String> valueRow2 = Arrays.asList("", "KITR1", "KITI01", "KITI01E", "", "", "44444", "10002", "KIER7", "23456", "50313", "44444", "31", "");
//    List<String> valueRow3 = Arrays.asList("", "KITR1", "KITI01", "KITI01I", "", "", "55555", "10003", "KIER7", "23456", "60313", "44444", "31", "");
//    values.add(valueRow);
//    values.add(valueRow2);
//    values.add(valueRow3);
//    fileOfChanges2.setValues(values);
//
//    return fileOfChanges2;
//  }

  public FileOfChanges2 loadFileOfChanges(String file) throws NotFoundRncException {

    String fileName = "filesOfChanges/" + file;
    String csvFile;
    FileOfChanges2 fileOfChanges2 = new FileOfChanges2();

    if (null == file || file.isEmpty() || !isValidateFileName(fileName)) {
      csvFile = fileName;
    } else {
      return null;
    }

    try {
      CSVReader reader = new CSVReader(new FileReader(csvFile));

      String[] line;

      List<List<Object>> values = new ArrayList<>();

      while ((line = reader.readNext()) != null) {

        if (Arrays.asList(line).contains("BSC") || Arrays.asList(line).contains("RNC")) {
          fileOfChanges2.setHeaders(Arrays.asList(line));
          continue;
        }

        if (line.length > 0) {
          values.add(Arrays.asList(line));
        }

      }

      fileOfChanges2.setValues(values);

    } catch (IOException e) {
      LOG.error("something went wrong ", e);
    }

    FileOfChanges2 init = null;

    while (null == init) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      init = sshService.init(fileOfChanges2);
    }


    return init;
  }


  public String extractNeededFile(String directory) {
    File file1 = new File(directory);

    try {
      for (File file : Objects.requireNonNull(file1.listFiles())) {
        final List<String> strings = Files.readAllLines(file.toPath());

        for (String string : strings) {
          if (string.contains("IubLink")) {
            return file.getName();
          }
        }
      }
    } catch (IOException e) {
      LOG.error("directory is empty or file broken", e);
    }

    return null;
  }

  public List<RncModification> getAllFileChanges(String fileName) {
    List<RncModification> modifications = new ArrayList<>();

    try {
      List<String> strings = Files.readAllLines(Paths.get(fileName));
      strings.remove(0);
      Set<String> rncNames = strings.stream().map(e -> e.split(",")[2]).collect(Collectors.toSet());

      for (String rncName : rncNames) {
        List<FileOfChanges> lines = new ArrayList<>();

        for (String string : strings) {
          if (string.split(",")[2].equals(rncName)) {
            lines.add(new FileOfChanges(string));
          }
        }

        RncModification rncModification = new RncModification(lines);
        modifications.add(rncModification);
      }


    } catch (IOException e) {
      LOG.error("something went wrong ", e);
    }

    return modifications;
  }

  public Set<String> getRncNames(String fileName) {
    List<String> strings = null;
    try {
      strings = Files.readAllLines(Paths.get(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (strings != null) {
      strings.remove(0);

      return strings.stream().map(e -> e.split(",")[2]).collect(Collectors.toSet());
    }

    return null;
  }

  public Set<String> checkedFields(String... columns) {

    Set<String> rncNames = new HashSet<>();

    List<Map<String, String>> maps = readMapCsv(columns[0]);

    for (Map<String, String> map : maps) {
      for (String param : Arrays.copyOfRange(columns, 1, columns.length)) {
        rncNames.add(map.get(param));
      }
    }

    return rncNames;
  }

  private boolean isValidateFileName(String file) {
    String filename = "filesOfChanges/" + file;

    if (new File(filename).exists()) {
      return true;
    }

    return false;
  }

}
