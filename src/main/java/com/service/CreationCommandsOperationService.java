package com.service;

import com.model.FileOfChanges;
import com.model.LacRacUra;
import com.model.RncModification;
import com.model.modelsForCreationCommands.*;
import com.model.modelsForCreationCommands.Header;
import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.CreationCommandFabric;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Logger;

import static com.Constants.*;

@Service
public class CreationCommandsOperationService {

  private static final Logger LOG = LogManager.getLogger(CreationCommandsOperationService.class);

  private String site;

  private String pathToSite;


//  public static void main(String[] args) throws IOException {
//    long start = System.nanoTime();
//
//    methodForTest();
//
//    long end = System.nanoTime();
//
//    LOG.debug("time of execution = " + (end-start) + " nanoseconds");
//  }

//  public static void methodForTest() {
//
//    Map<String, List<String>> creationCommandsFiles = ModelUtils.getCreationCommandsFiles();
//
//    CreationCommandsOperationService parser = new CreationCommandsOperationService();
//
//    List<RncModification> modifications = extractAllRehomeInformation(TEST_FILE_OF_CHANGES4);
//
//    for (RncModification changeCommand : modifications) {
//      String site = changeCommand.getModifications().get(0).getSite();
//      parser.setPathToSite(PREPARED_CREATION_COMMANDS + site + "/");
//      parser.setSite(site + "/");
//      List<String> fileNames = creationCommandsFiles.get(site);
//
//      List<CreationCommand> updatedCreationCommands = parser.performModification(changeCommand, fileNames, TEST_FILE_OF_RESULT);
//
//      parser.placeCommandsBySeparatedFiles(updatedCreationCommands);
//      parser.createLacRacUra(changeCommand);
//      parser.write2GNeighbors();
//      parser.write3GNeighbors();
//    }
//
//  }


  public void execute(String fileOfChanges) {

    Map<String, List<String>> creationCommandsFiles = ModelUtils.getCreationCommandsFiles();

    CreationCommandsOperationService parser = new CreationCommandsOperationService();

    List<RncModification> modifications = extractAllRehomeInformation(fileOfChanges);

    for (RncModification changeCommand : modifications) {
      String site = changeCommand.getModifications().get(0).getSite();
      parser.setPathToSite(PREPARED_CREATION_COMMANDS + site + "/");
      parser.setSite(site + "/");
      List<String> fileNames = creationCommandsFiles.get(site);

      List<CreationCommand> updatedCreationCommands = parser.performModification(changeCommand, fileNames, PREPARED_CREATION_COMMANDS + "/updated.mos");

      parser.placeCommandsBySeparatedFiles(updatedCreationCommands);
      parser.createLacRacUra(changeCommand);
      parser.write2GNeighbors();
      parser.write3GNeighbors();
    }

  }

  public void createLacRacUra(RncModification modification) {
    LacRacUra lacRacUra = new LacRacUra(modification.getModifications().get(0).getNew_LAC(), modification.getModifications().get(0).getNew_Ura());

    try (OutputStream lacRacUraOutputStream = Files.newOutputStream(Paths.get(pathToSite + LAC_RAC_URA), StandardOpenOption.APPEND);) {
      new File(pathToSite + LAC_RAC_URA).createNewFile();

      writeToFile(lacRacUra,  lacRacUraOutputStream);
    } catch (IOException e) {
      LOG.error("can't create file LacRacUra, because of {}", e);
    }

  }

  public void write2GNeighbors() {
    Set<String> neighbors = new TreeSet<>();
    List<String> siteAndNeighbors = new ArrayList<>();
    try {
      Path path = Paths.get(pathToSite + GSM_REL);
      List<String> strings = Files.readAllLines(path);

      for (String string : strings) {
        if(string.contains("GsmRelation")) {
          String site = string.split("[,=]")[3];
          String neighbor = string.split("[,=_]").length == 6 ? string.split("[,=_]")[5] : string.split("[,=_]")[6];
          neighbors.add(neighbor);
          siteAndNeighbors.add(site+" "+neighbor);
        }
      }

      for (String neighbor : neighbors) {
        Files.write(Paths.get(pathToSite + SITES_NEIGHBORS_2G), (neighbor+"\n").getBytes(), StandardOpenOption.APPEND);
      }

      for (String siteAndNeighbor : siteAndNeighbors) {
        Files.write(Paths.get(pathToSite + SITES_AND_NEIGHBORS_2G), (siteAndNeighbor+"\n").getBytes(), StandardOpenOption.APPEND);
      }

    } catch (IOException e) {
      LOG.error("can't write to file, " + e);
    }

  }

  public void write3GNeighbors() {
    Set<String> neighbors = new TreeSet<>();
    List<String> siteAndNeighbors = new ArrayList<>();
    try {
      Path path = Paths.get(pathToSite + UTRAN_REl);
      List<String> strings = Files.readAllLines(path);

      for (String string : strings) {
        if(string.contains("UtranRelation")) {
          String site = string.split("[,=]")[3];
          String neighbor = "";
          if (string.contains("Femto_cell4")) {
            neighbor = string.split("[,=]")[5];
          } else {
            neighbor = string.split("[,=_]").length == 6 ? string.split("[,=_]")[5] : string.split("[,=_]")[6];
          }
          neighbors.add(neighbor);
          siteAndNeighbors.add(site+" "+neighbor);
        }
      }

      for (String neighbor : neighbors) {
        Files.write(Paths.get(pathToSite + SITES_NEIGHBORS_3G), (neighbor+"\n").getBytes(), StandardOpenOption.APPEND);
      }

      for (String siteAndNeighbor : siteAndNeighbors) {
        Files.write(Paths.get(pathToSite + SITES_AND_NEIGHBORS_3G), (siteAndNeighbor+"\n").getBytes(), StandardOpenOption.APPEND);
      }

    } catch (IOException e) {
      LOG.error("can't write to file, " + e);
    }

  }

  public boolean placeCommandsBySeparatedFiles(List<CreationCommand> creationCommands) {
    List<CreationCommand> skippedCommands = new ArrayList<>();

    validateFiles();

    try (OutputStream utranRel = Files.newOutputStream(Paths.get(pathToSite + UTRAN_REl), StandardOpenOption.APPEND);
         OutputStream gsmRel = Files.newOutputStream(Paths.get(pathToSite + GSM_REL), StandardOpenOption.APPEND);
         OutputStream eutran = Files.newOutputStream(Paths.get(pathToSite + EUTRAN), StandardOpenOption.APPEND);
         OutputStream iubCell = Files.newOutputStream(Paths.get(pathToSite + IUB_CELL), StandardOpenOption.APPEND)) {

      for (CreationCommand creationCommand : creationCommands) {
        if (creationCommand instanceof HcsSid11Config || creationCommand instanceof HsPathLossTreshold) {
          writeToFile(creationCommand,  utranRel);
          continue;
        }

        if (creationCommand instanceof ExternalGsmCellRef || creationCommand instanceof BandIndicator) {
          writeToFile(creationCommand,  gsmRel);
          continue;
        }

        if (creationCommand instanceof BarredCnOperatorRef || creationCommand instanceof ExternalEutranCellRef || creationCommand instanceof ENodeBId) {
          writeToFile(creationCommand,  eutran);
          continue;
        }

        if (!(creationCommand instanceof Header)) {
          writeToFile(creationCommand,  iubCell);
          continue;
        }

        skippedCommands.add(creationCommand);

      }
    } catch (IOException e) {
      LOG.error("can't access to file", e);
    }

//    System.out.println(skippedCommands.size());

    for (CreationCommand skippedCommand : skippedCommands) {
      System.out.println("\n" + skippedCommand + "\n");
    }

    return true;
  }

  private void validateFiles() {

    try {

      if (!new File(pathToSite).exists()) {
        new File(pathToSite).mkdirs();
      }

      if(!new File(RAW_RNC_CREATION_COMMANDS).exists()) {
        new File(RAW_RNC_CREATION_COMMANDS).mkdir();
      }

      if(!new File(FILES_OF_CHANGES_DIRECTORY).exists()) {
        new File(FILES_OF_CHANGES_DIRECTORY).mkdir();
      }

      if(!new File(OLD_COMMANDS_DIRECTORY).exists()) {
        new File(OLD_COMMANDS_DIRECTORY).mkdir();
      }

      if (new File(pathToSite + IUB_CELL).exists()) {
        Path path = Paths.get(pathToSite + IUB_CELL);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + IUB_CELL));

      if (new File(pathToSite + GSM_REL).exists()) {
        Path path = Paths.get(pathToSite + GSM_REL);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + GSM_REL));


      if (new File(pathToSite + UTRAN_REl).exists()) {
        Path path = Paths.get(pathToSite + UTRAN_REl);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + UTRAN_REl));


      if (new File(pathToSite + EUTRAN).exists()) {
        Path path = Paths.get(pathToSite + EUTRAN);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + EUTRAN));


      if (new File(pathToSite + LAC_RAC_URA).exists()) {
        Path path = Paths.get(pathToSite + LAC_RAC_URA);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + LAC_RAC_URA));


      if (new File(pathToSite + SITES_NEIGHBORS_2G).exists()) {
        Path path = Paths.get(pathToSite + SITES_NEIGHBORS_2G);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + SITES_NEIGHBORS_2G));


      if (new File(pathToSite + SITES_AND_NEIGHBORS_2G).exists()) {
        Path path = Paths.get(pathToSite + SITES_AND_NEIGHBORS_2G);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + SITES_AND_NEIGHBORS_2G));


      if (new File(pathToSite + SITES_NEIGHBORS_3G).exists()) {
        Path path = Paths.get(pathToSite + SITES_NEIGHBORS_3G);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + SITES_NEIGHBORS_3G));


      if (new File(pathToSite + SITES_AND_NEIGHBORS_3G).exists()) {
        Path path = Paths.get(pathToSite + SITES_AND_NEIGHBORS_3G);
        Files.delete(path);
      }
      Files.createFile(Paths.get(pathToSite + SITES_AND_NEIGHBORS_3G));


    } catch (IOException e) {
      LOG.error("can't delete file", e);
    }

  }

  private void writeToFile(CreationCommand creationCommand, OutputStream outputStream) {

    try {

      byte[] bytes = creationCommand.toString().getBytes();
      int len = bytes.length;
      int rem = len;
      while (rem > 0) {
        int n = Math.min(rem, 8192);
        outputStream.write(bytes, (len-rem), n);
        rem -= n;
      }

    } catch (IOException e) {
      LOG.error("can't write objects to File ", e);
    }

  }

  public List<CreationCommand> performModification(RncModification modification, List<String> fileOfCreationCommands, String fileOfResult) {
    List<CreationCommand> creationCommands = extractCreationCommands(fileOfCreationCommands);

    List<CreationCommand> resultCommands = new ArrayList<>();
    try {
      resultCommands = updateValues(creationCommands, modification, fileOfResult);
    } catch (IllegalArgumentException e) {
      LOG.error("transfered empty list of changes", e);
    }

    return resultCommands;
  }

  public List<CreationCommand> extractCreationCommands(List<String> fileOfCreationCommands) {
    List<CreationCommand> creationCommands = new ArrayList<>();

    for (String fileOfCreationCommand : fileOfCreationCommands) {

      try {
        String[] split = Files.readAllLines(new File(RAW_RNC_CREATION_COMMANDS + fileOfCreationCommand).toPath()).stream()
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
        LOG.error("file not found or something else ", e);
        return null;
      }
    }
    return creationCommands;
  }

  public static List<CreationCommand> updateValues(List<CreationCommand> parsedObjects, RncModification modification, String fileOfResult) {

    if(parsedObjects == null || modification == null) throw new IllegalArgumentException();

    // only for debug
    List<CreationCommand> changedCreationCommands = new ArrayList<>();

    List<CreationCommand> onlyAseLoadThresholdUiSpeech = parsedObjects.stream().filter(e -> e.getType().equals(Patterns.ASE_LOAD_THRESHOLD_UI_SPEECH)).collect(Collectors.toList());
    List<CreationCommand> onlyAtmUserPlaneTermSubrackRef = parsedObjects.stream().filter(e -> e.getType().equals(Patterns.ATM_USER_PLANE_TERM_SUBRACK_REF)).collect(Collectors.toList());
    List<CreationCommand> onlySac = parsedObjects.stream().filter(e -> e.getType().equals(Patterns.SAC)).collect(Collectors.toList());
    List<CreationCommand> onlyEnodeB = parsedObjects.stream().filter(e -> e.getType().equals(Patterns.E_NODEB_ID)).collect(Collectors.toList());


    // TODO change as faster as you can
    boolean new_RBSID_1 = false;

    Map<String, Integer> forNewRbsid = new HashMap<>();

    for (int i = 0; i < onlyAtmUserPlaneTermSubrackRef.size(); i++) {
      if(onlyAtmUserPlaneTermSubrackRef.get(i).getIubLink().contains("_1")) {
        forNewRbsid.put("NewRbsid_1", i);
        new_RBSID_1 = true;
      }
      if(onlyAtmUserPlaneTermSubrackRef.get(i).getIubLink().contains("_2")) {
        forNewRbsid.put("NewRbsid_2", i);
      }
    }

    for (FileOfChanges fileOfChanges : modification.getModifications()) {

      if(!new_RBSID_1) {
        ((AtmUserPlaneTermSubrackRef)onlyAtmUserPlaneTermSubrackRef.get(0)).setRbsId(Integer.parseInt(fileOfChanges.getNew_RBSID_1()));
      }

      if(!forNewRbsid.isEmpty()) {
        for (Map.Entry<String, Integer> stringIntegerEntry : forNewRbsid.entrySet()) {
          if(stringIntegerEntry.getKey().equals("NewRbsid_1")) {
            ((AtmUserPlaneTermSubrackRef) onlyAtmUserPlaneTermSubrackRef.get(stringIntegerEntry.getValue())).setRbsId(Integer.parseInt(fileOfChanges.getNew_RBSID_1()));
          }
          if(stringIntegerEntry.getKey().equals("NewRbsid_2")) {
            ((AtmUserPlaneTermSubrackRef) onlyAtmUserPlaneTermSubrackRef.get(stringIntegerEntry.getValue())).setRbsId(Integer.parseInt(fileOfChanges.getNew_RBSID_2()));
          }
        }
      }

      for (CreationCommand creationCommand : onlyEnodeB) {
        ((ENodeBId) creationCommand).setTac(Integer.parseInt(fileOfChanges.getNew_LAC()));
      }

    }

    for (FileOfChanges changeCommand : modification.getModifications()) {
      for (CreationCommand creationCommand : onlyAseLoadThresholdUiSpeech) {
        if (((AseLoadThresholdUiSpeech) creationCommand).getCId() == Integer.parseInt(changeCommand.getCI())
            && ((AseLoadThresholdUiSpeech) creationCommand).getLocationAreaRef().getLocationArea() == Integer.parseInt(changeCommand.getLAC())) {
          ((AseLoadThresholdUiSpeech) creationCommand).setCId(Integer.parseInt(changeCommand.getNew_Ci()));
          ((AseLoadThresholdUiSpeech) creationCommand).getLocationAreaRef().setLocationArea(Integer.parseInt(changeCommand.getNew_LAC()));
          ((AseLoadThresholdUiSpeech) creationCommand).setLocalCellId(Integer.parseInt(changeCommand.getNew_Ci()));
          ((AseLoadThresholdUiSpeech) creationCommand).getRoutingAreaRef().setLocationArea(Integer.parseInt(changeCommand.getNew_LAC()));
          ((AseLoadThresholdUiSpeech) creationCommand).getServiceAreaRef().setLocationArea(Integer.parseInt(changeCommand.getNew_LAC()));
          ((AseLoadThresholdUiSpeech) creationCommand).getServiceAreaRef().setServiceArea(Integer.parseInt(changeCommand.getNew_Ci()));
          ((AseLoadThresholdUiSpeech) creationCommand).getUraRef().setUra(Integer.parseInt(changeCommand.getNew_Ura()));

          // only for debug
          changedCreationCommands.add(creationCommand);
        }
      }

      for (CreationCommand creationCommand : onlySac) {
        if (((Sac) creationCommand).getSac() == Integer.parseInt(changeCommand.getCI())
                && ((Sac) creationCommand).getLocationArea().equals(changeCommand.getLAC())) {
          ((Sac) creationCommand).setSac(Integer.parseInt(changeCommand.getNew_Ci()));
          ((Sac) creationCommand).setLocationArea(changeCommand.getNew_LAC());

          // only for debug
          changedCreationCommands.add(creationCommand);
        }
      }
    }

    boolean b = writeResult(parsedObjects, fileOfResult);

    if (b) {
      LOG.info("writing successfully\n");
    } else {
      LOG.error("error during writing\n");
      throw new RuntimeException("error during exception");
    }

    return parsedObjects;
  }

  public List<CreationCommand> prepareObjectsToChange(List<CreationCommand> parsedObjects, List<Map<String, String>> changeCommands) {

    List<CreationCommand> changedCreationCommands = new ArrayList<>();
    List<CreationCommand> onlyAseLoadThresholdUiSpeech = parsedObjects.stream().filter(e -> e.getType().equals(Patterns.ASE_LOAD_THRESHOLD_UI_SPEECH)).collect(Collectors.toList());

    for (Map<String, String> map : changeCommands) {
      for (CreationCommand creationCommand : onlyAseLoadThresholdUiSpeech) {
        if (((AseLoadThresholdUiSpeech) creationCommand).getCId() == Integer.parseInt(map.get("CI"))
            && ((AseLoadThresholdUiSpeech) creationCommand).getLocationAreaRef().getLocationArea() == Integer.parseInt(map.get("LAC"))) {

          changedCreationCommands.add(creationCommand);
        }
      }
    }

    return changedCreationCommands;
  }

  public static List<Map<String, String>> extractRehomeInformation(String fileOfChanges) {
    FileParsingService service = new FileParsingService();
    return service.readMapCsv(fileOfChanges);
  }

  public static List<RncModification> extractAllRehomeInformation(String fileOfChanges) {
    FileParsingService service = new FileParsingService();
    return service.getAllFileChanges(fileOfChanges);
  }

  static boolean writeResult(List<CreationCommand> creationCommands, String fileOfResult) {
    final String header = creationCommands.get(0) == null ? "" : creationCommands.get(0) + "\ngs+\n\n";

    if(creationCommands.get(0) != null) creationCommands.remove(0);

    final String footer = "gs-\n";

    List<Byte> allBytes = new ArrayList<>();

    for (byte aByte : header.getBytes()) {
      allBytes.add(aByte);
    }

    for (CreationCommand creationCommand : creationCommands) {
      for (byte aByte : creationCommand.toString().getBytes()) {
        allBytes.add(aByte);
      }
    }

    for (byte aByte : footer.getBytes()) {
      allBytes.add(aByte);
    }

    try {
      new File(fileOfResult).delete();
      boolean isFileCreated = new File(fileOfResult).createNewFile();
      LOG.info("is file in afterModification created - {}", isFileCreated);
    } catch (IOException e) {
      LOG.error("file not found or can not acces to file");
    }

    try (OutputStream out = Files.newOutputStream(new File(fileOfResult).toPath(), StandardOpenOption.APPEND)) {

      if (new File(fileOfResult).exists()) {

        Byte[] prev = allBytes.toArray(new Byte[allBytes.size()]);
        byte[] bytes = ArrayUtils.toPrimitive(prev);
        int len = bytes.length;
        int rem = len;
        while (rem > 0) {
          int n = Math.min(rem, 8192);
          out.write(bytes, (len-rem), n);
          rem -= n;
        }

      }
    } catch (IOException e) {
      LOG.error("something went wrong, ", e);
      return false;
    }

    return true;
  }

  public String getPathToSite() {
    return pathToSite;
  }

  public void setPathToSite(String pathToSite) {
    this.pathToSite = pathToSite;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }
}
