package com;

import com.model.RncModification;
import com.service.FileParsingService;

import java.util.List;

public class Test2 {

  public static void main(String[] args) {
    FileParsingService fileService = new FileParsingService();

    final List<RncModification> allFileChanges = fileService.getAllFileChanges("filesOfChanges/fileOfChanges.csv");

    System.out.println(allFileChanges);
  }
}
