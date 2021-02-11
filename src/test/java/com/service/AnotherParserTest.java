package com.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class AnotherParserTest {

  public static void main(String[] args) throws IOException {


    String[] split = Files.readAllLines(new File("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos").toPath()).stream()
//        .filter(e -> !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e + "\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
//        .map(e -> e.replace("crn ", ""))
        .filter(e -> !e.isEmpty())
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());

    List<Map<String, String>> result = new ArrayList<>();

    for (String createObject : createObjects) {
      Map<String, String> mapProperties = AnotherParserTest.createMapProperties(createObject);

      if(!mapProperties.isEmpty()) {
        result.add(mapProperties);
      }
    }

    System.out.println("parsed objects: "+createObjects.size() + ", objects parsed directly: " +result.size());

    for (Map<String, String> stringStringMap : result) {
      for (Map.Entry<String, String> stringStringEntry : stringStringMap.entrySet()) {
        System.out.println(stringStringEntry.getKey() + " " + stringStringEntry.getValue());
      }
      System.out.println();
    }
  }

  public static boolean updateValues(List<Map<String, String>> parsedObjects, List<Map<String, String>> changeCommands, String fileOfResult) {

    if(parsedObjects == null || changeCommands == null) return false;

    for (Map<String, String> parsedObject : parsedObjects) {

      for (Map.Entry<String, String> stringStringEntry : parsedObject.entrySet()) {

//        if(stringStringEntry.getKey().equals())

      }

    }

//    boolean b = writeResult(parsedObjects, fileOfResult);
//
//    if (b) {
//      LOG.info("writing successfully\n");
//    } else {
//      LOG.error("error during writing\n");
//      return false;
//    }

    return true;
  }

  static Map<String, String> createMapProperties(String creationCommand) {
    Map<String, String> props = new LinkedHashMap<>();

    String[] split = creationCommand.split("\n");

    for (String s : split) {

      if(s.matches("#DoNotEditThisLine:[\\w\\s-./=]+")) {
        String key = s.split(" ")[0];
        props.put(key, s.replace(key+" ", ""));
        continue;
      }

      if(s.matches("lset\\s(\\w+=\\w+,)+(\\w+=\\w+)+[\\w\\s\\n$]+")) {

        props.put(s, "");
        continue;
      }

//      if(creationCommand.contains("gs+") || creationCommand.contains("gs-")) {
//        props.put(creationCommand, "");
//        continue;
//      }

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

    String source = String.join(" ", split);
    if(!source.contains("#SystemCreated") && !source.contains("DoNotEditThisLine") && !source.contains("gs+") && !source.contains("gs-")) {
      props.put("end", "");
    }
    return props;
  }

}
