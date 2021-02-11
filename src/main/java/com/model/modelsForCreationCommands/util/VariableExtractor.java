package com.model.modelsForCreationCommands.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class VariableExtractor {

  public static int extractInteger(String sourceString) {
    String[] source = sourceString.split("\\s");
    return source[1] == null ? null : Integer.parseInt(source[1]);
  }

  public static int extractIntegerFromObject(String source) {
    return source.split("=").length == 2 ? Integer.parseInt(source.split("=")[1]) : null;
  }

  public static String extractString(String sourceString) {
    String[] source = sourceString.split("\\s");
    return source.length >= 2 ? source[1] : null;
  }

  public static List<Integer> extractArray(String sourceString) {
    String[] source = sourceString.split("\\s");
    try {
      return source.length >= 2 ? new ObjectMapper().readValue("["+source[1] +"]", List.class) : null;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static String extractVariablesForObject(String sourceString) {
    String[] source = sourceString.split("\\s");

    if(source.length >= 2) {
      return source[1];
    }

    return null;
  }

}
