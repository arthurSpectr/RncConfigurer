package com.model.modelsForCreationCommands.util;

import com.model.Patterns;

import java.util.Map;


public interface CreationCommand {
  CreationCommand getCreationCommand(Patterns pattern, String source);

  String getName();

  Map<String, String> getValues();

  Patterns getType();

  default String getIubLink() {
    return "";
  }
}
