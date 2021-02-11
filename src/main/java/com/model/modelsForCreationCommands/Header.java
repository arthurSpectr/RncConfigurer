package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.Patterns;

import java.util.HashMap;
import java.util.Map;

public class Header implements CreationCommand {
  private final String header;

  public Header(String header) {
    this.header = header;
  }

  public String getHeader() {
    return header;
  }

  @Override
  public CreationCommand getCreationCommand(Patterns pattern, String source) {
    return null;
  }

  @Override
  public String getName() {
    return header;
  }

  @Override
  public Map<String, String> getValues() {
    Map<String, String> values = new HashMap<>();
    values.put("#DoNotEditThisLine:", header.replace("#DoNotEditThisLine: ", ""));
    return values;
  }

  @Override
  public Patterns getType() {
    return Patterns.HEADER;
  }

  @Override
  public String toString() {
    return header;
  }
}
