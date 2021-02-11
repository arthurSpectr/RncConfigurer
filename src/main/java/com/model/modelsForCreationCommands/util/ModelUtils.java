package com.model.modelsForCreationCommands.util;

import com.Constants;
import com.model.Patterns;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class ModelUtils {

  public static Map<String, String> createMapProperties(String[] creationCommand) {
    Map<String, String> props = new LinkedHashMap<>();

    for (String s : creationCommand) {

      if(s.matches("#DoNotEditThisLine:[\\w\\s-./=]+")) {
        String key = s.split(" ")[0];
        props.put(key, s.replace(key+" ", ""));
        continue;
      }

      if(s.matches("lset\\s(\\w+=\\w+,)+(\\w+=\\w+)+[\\w\\s\\n$]+")) {

        props.put(s, "");
        continue;
      }

      if(s.matches("crn[\\w\\s-=,\\n\\.]+")) {
        props.put("crn", s.replace("crn ", ""));
        continue;
      }

      if(s.matches("\\w+\\s+[\\w-]*")) {
        String[] keyValue = s.split(" ");
        props.put(s.split(" ")[0], keyValue.length >= 2 ? keyValue[1] : "");
        continue;
      }
      if(s.matches("\\w+\\s\\w+[=\\d\\s,]+(\\w+=\\w+,)+(\\w+=\\w+)+")) {
        String key = s.split(" ")[0];
        props.put(key, s.replace(key+" ", ""));
        continue;
      }


      if(s.matches("\\w+\\s+(\\w+=\\w+,)+[\\w=,-]+\\s?(#SystemCreated)?")) {
        String[] keyValue = s.split(" ");
        String systemProp = keyValue.length >= 3 ? keyValue[2] : "";
        props.put(s.split(" ")[0], keyValue[1] + " " +systemProp);
        continue;
      }

      if(s.matches("\\w+\\s+(\\d+,)+(\\d+)+")) {
        String[] keyValue = s.split(" ");
        props.put(s.split(" ")[0], keyValue[1]);
        continue;
      }

      if(s.matches("\\w+\\s\\w+=\\w+")) {
        String[] keyValue = s.split(" ");
        props.put(s.split(" ")[0], keyValue[1]);
        continue;
      }

    }

    return props;
  }

  public static String extractSiteName(String fileName) {

    String[] split = new String[0];
    try {
      split = Files.readAllLines(new File(fileName).toPath()).stream()
              .filter(e -> !e.contains("gs+") && !e.contains("gs-"))
              .map(e -> e.isEmpty() ? "&" : e + "\n")
              .collect(Collectors.joining())
              .split("&");
    } catch (IOException e) {
      e.printStackTrace();
    }

    String name = "";

    if(split.length <= 1) throw new RuntimeException("file downloaded from rnc - " + fileName + ", is empty");
    if(split[2].contains("eNodeBId")) {
      name = split[2].split("[=,_]")[7].split("[\nL]")[0];
    } else {
      name = split[3].split("[=,_]")[3];
    }
    return name;
  }

  public static Map<String, List<String>> getCreationCommandsFiles() {

    Map<String, List<String>> nameAndFiles = new TreeMap<>();

    File creationCommandsDir = new File(Constants.RAW_RNC_CREATION_COMMANDS);

    if(creationCommandsDir.exists() && creationCommandsDir.list() != null && creationCommandsDir.list().length != 0) {

      String[] list = creationCommandsDir.list();

      for (String s : list) {
        String s1 = extractSiteName(Constants.RAW_RNC_CREATION_COMMANDS + s);

        if(nameAndFiles.containsKey(s1)) {
          nameAndFiles.get(s1).add(s);
        } else {
          List<String> fileNames = new ArrayList<>();
          fileNames.add(s);
          nameAndFiles.put(s1, fileNames);
        }
      }

      return nameAndFiles;
    }

    return Collections.emptyMap();
  }

  public static List<CreationCommand> extractCreationCommands(String fileOfCreationCommands) {
    List<CreationCommand> creationCommands = new ArrayList<>();

    try {
      String[] split = Files.readAllLines(new File(fileOfCreationCommands).toPath()).stream()
              .filter(e -> !e.contains("gs+") && !e.contains("gs-"))
              .map(e -> e.isEmpty() ? "&" : e + "\n")
              .collect(Collectors.joining())
              .split("&");

      List<String> createObjects = Arrays.stream(split)
              .map(e -> e.replace("crn ", ""))
              .map(e -> e.replace("end", ""))
              .collect(Collectors.toList());


      for (String createObject : createObjects) {
        for (Patterns pattern : Patterns.values()) {
          Matcher matcher = pattern.getPattern().matcher(createObject);

          if (matcher.matches()) {
            CreationCommand creationCommand = CreationCommandFabric.createObject(pattern, createObject);
            creationCommands.add(creationCommand);
            break;
          }
        }
      }
    } catch (IOException e) {
//      LOG.error("file not found or something else ", e);
      return null;
    }
    return creationCommands;
  }

}
