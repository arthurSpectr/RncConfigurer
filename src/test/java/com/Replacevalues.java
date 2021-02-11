package com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Replacevalues {

  public static void main(String[] args) throws IOException {
    replacenumbers();
  }

  static void compareKeys() throws IOException {
    String[] split = Files.readAllLines(new File("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos").toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e + "\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());

    List<String> keys = new ArrayList<>();

    for (String createObject : createObjects) {
      keys.add(createObject.split("\n")[0]);
    }

    /*
    for (Patterns pattern : Patterns.values()) {
      StructureWithModels.commands.put(pattern, new ArrayList<>());
      for (String createObject : createObjects) {
        Matcher matcher = pattern.getPattern().matcher(createObject);

        if (matcher.matches()) {
          CreationCommand creationCommand = CreationCommandFabric.createObject(pattern, createObject);
          StructureWithModels.commands.get(pattern).add(creationCommand);

        }
      }
    }

    List<String> names = new ArrayList<>();


    for (Map.Entry<Patterns, List<CreationCommand>> patternsListEntry : StructureWithModels.commands.entrySet()) {
      for (CreationCommand creationCommand : patternsListEntry.getValue()) {
        names.add(creationCommand.getName());
      }
    }
*/

//    for (String name : names) {
//      System.out.println(name);
//    }
//    System.out.println(names.size());

    List<String> skippedKeys = new ArrayList<>();

    /*
    for (String key : keys) {
      if (!names.contains(key)) {
        skippedKeys.add(key);
      }
    }
*/


    for (String skippedKey : skippedKeys) {
      System.out.println(skippedKey);
    }
    System.out.println(skippedKeys.size());

  }

  static List<String> replacenumbers() throws IOException {
    List<String> strings = Files.readAllLines(new File("/home/atian/Documents/toCopy.txt").toPath());
    List<String> refreshed = new ArrayList<>();
    List<String> finalString = new ArrayList<>();

    for (String string : strings) {
      String newString = string.replaceAll("source\\[[0-9]*\\]", "source[0]");
      refreshed.add(newString);
    }

    int i = 0;

    for (String s : refreshed) {
      String s1 = s.replaceAll("source\\[[0-9]*\\]", "source[" + i++ + "]");
      finalString.add(s1);
    }

    for (String s : finalString) {
      System.out.println(s);
    }

    return finalString;
  }

  void findExceptedKeys() {

  }

}
