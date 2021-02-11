package com.service;

/*
public class StructureWithModelsTest {

  public static void main(String[] args) throws IOException {

//    testE_NODEB_ID("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testBAND_INDICATOR("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testEXTERNAL_GSM_CELL_REF("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testATM_USER_PLANE_TERM_SUBRACK_REF("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testHCS_SIB_11_CONFIG("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testEXTERNAL_EUTRAN_CELL_REF("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testHS_PATH_LOSS_TRESHOLD("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testEU_DCH_BALANCING_ENABLED("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testCODE_THRESHOLD_PDU_656("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testMAX_FACH_1_POWER("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testPCH_POWER("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testAICH_TRANSMISSION_TIMING("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testASE_LOAD_THRESHOLD_UI_SPEECH("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testSAC("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
//    testEDCH_DATA_FRAME_DELAY_THRESHOLD("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");
    testBARRED_CN_OPERATOR_REF("/home/atian/Documents/arturProjects/RncConfigurerParser/src/main/resources/undo_KIER7_191118-105157.mos");

  }

  public static void testE_NODEB_ID(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.E_NODEB_ID, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.E_NODEB_ID.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.E_NODEB_ID, createObject);
        StructureWithModels.commands.get(Patterns.E_NODEB_ID).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.E_NODEB_ID)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testBAND_INDICATOR(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.BAND_INDICATOR, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.BAND_INDICATOR.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.BAND_INDICATOR, createObject);
        StructureWithModels.commands.get(Patterns.BAND_INDICATOR).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.BAND_INDICATOR)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testEXTERNAL_GSM_CELL_REF(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.EXTERNAL_GSM_CELL_REF, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.EXTERNAL_GSM_CELL_REF.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.EXTERNAL_GSM_CELL_REF, createObject);
        StructureWithModels.commands.get(Patterns.EXTERNAL_GSM_CELL_REF).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.EXTERNAL_GSM_CELL_REF)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testATM_USER_PLANE_TERM_SUBRACK_REF(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.ATM_USER_PLANE_TERM_SUBRACK_REF, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.ATM_USER_PLANE_TERM_SUBRACK_REF.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.ATM_USER_PLANE_TERM_SUBRACK_REF, createObject);
        StructureWithModels.commands.get(Patterns.ATM_USER_PLANE_TERM_SUBRACK_REF).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.ATM_USER_PLANE_TERM_SUBRACK_REF)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testEXTERNAL_EUTRAN_CELL_REF(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.EXTERNAL_EUTRAN_CELL_REF, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.EXTERNAL_EUTRAN_CELL_REF.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.EXTERNAL_EUTRAN_CELL_REF, createObject);
        StructureWithModels.commands.get(Patterns.EXTERNAL_EUTRAN_CELL_REF).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.EXTERNAL_EUTRAN_CELL_REF)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testHS_PATH_LOSS_TRESHOLD(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.HS_PATH_LOSS_TRESHOLD, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.HS_PATH_LOSS_TRESHOLD.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.HS_PATH_LOSS_TRESHOLD, createObject);
        StructureWithModels.commands.get(Patterns.HS_PATH_LOSS_TRESHOLD).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.HS_PATH_LOSS_TRESHOLD)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testHCS_SIB_11_CONFIG(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.HCS_SIB_11_CONFIG, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.HCS_SIB_11_CONFIG.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.HCS_SIB_11_CONFIG, createObject);
        StructureWithModels.commands.get(Patterns.HCS_SIB_11_CONFIG).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.HCS_SIB_11_CONFIG)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testEU_DCH_BALANCING_ENABLED(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.EU_DCH_BALANCING_ENABLED, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.EU_DCH_BALANCING_ENABLED.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.EU_DCH_BALANCING_ENABLED, createObject);
        StructureWithModels.commands.get(Patterns.EU_DCH_BALANCING_ENABLED).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.EU_DCH_BALANCING_ENABLED)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testCODE_THRESHOLD_PDU_656(String fileName) throws IOException {

    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");

    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());


    StructureWithModels.commands.put(Patterns.EU_DCH_BALANCING_ENABLED, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.EU_DCH_BALANCING_ENABLED.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.EU_DCH_BALANCING_ENABLED, createObject);
        StructureWithModels.commands.get(Patterns.EU_DCH_BALANCING_ENABLED).add(creationCommand);

      }
    }


    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.EU_DCH_BALANCING_ENABLED)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testMAX_FACH_1_POWER(String fileName) throws IOException {
    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");
    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());
    StructureWithModels.commands.put(Patterns.EU_DCH_BALANCING_ENABLED, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.EU_DCH_BALANCING_ENABLED.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.EU_DCH_BALANCING_ENABLED, createObject);
        StructureWithModels.commands.get(Patterns.EU_DCH_BALANCING_ENABLED).add(creationCommand);

      }
    }

    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.EU_DCH_BALANCING_ENABLED)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testPCH_POWER(String fileName) throws IOException {
    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");
    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());
    StructureWithModels.commands.put(Patterns.PCH_POWER, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.PCH_POWER.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.PCH_POWER, createObject);
        StructureWithModels.commands.get(Patterns.PCH_POWER).add(creationCommand);

      }
    }

    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.PCH_POWER)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testAICH_TRANSMISSION_TIMING(String fileName) throws IOException {
    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");
    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());
    StructureWithModels.commands.put(Patterns.PCH_POWER, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.PCH_POWER.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.PCH_POWER, createObject);
        StructureWithModels.commands.get(Patterns.PCH_POWER).add(creationCommand);

      }
    }

    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.PCH_POWER)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);

  }

  public static void testASE_LOAD_THRESHOLD_UI_SPEECH(String fileName) throws IOException {
    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");
    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());
    StructureWithModels.commands.put(Patterns.ASE_LOAD_THRESHOLD_UI_SPEECH, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.ASE_LOAD_THRESHOLD_UI_SPEECH.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.ASE_LOAD_THRESHOLD_UI_SPEECH, createObject);
        StructureWithModels.commands.get(Patterns.ASE_LOAD_THRESHOLD_UI_SPEECH).add(creationCommand);

      }
    }

    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.ASE_LOAD_THRESHOLD_UI_SPEECH)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);
  }

  public static void testSAC(String fileName) throws IOException {
    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");
    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());
    StructureWithModels.commands.put(Patterns.SAC, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.SAC.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.SAC, createObject);
        StructureWithModels.commands.get(Patterns.SAC).add(creationCommand);

      }
    }

    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.SAC)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);
  }

  public static void testEDCH_DATA_FRAME_DELAY_THRESHOLD(String fileName) throws IOException {
    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");
    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());
    StructureWithModels.commands.put(Patterns.EDCH_DATA_FRAME_DELAY_THRESHOLD, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.EDCH_DATA_FRAME_DELAY_THRESHOLD.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.EDCH_DATA_FRAME_DELAY_THRESHOLD, createObject);
        StructureWithModels.commands.get(Patterns.EDCH_DATA_FRAME_DELAY_THRESHOLD).add(creationCommand);

      }
    }

    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.EDCH_DATA_FRAME_DELAY_THRESHOLD)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);
  }

  public static void testBARRED_CN_OPERATOR_REF(String fileName) throws IOException {
    String[] split = Files.readAllLines(new File(fileName).toPath()).stream()
        .filter(e -> !e.contains("DoNotEditThisLine") && !e.contains("gs+") && !e.contains("gs-"))
        .map(e -> e.isEmpty() ? "&" : e+"\n")
        .collect(Collectors.joining())
        .split("&");
    List<String> createObjects = Arrays.stream(split)
        .filter(e -> e.contains("crn"))
        .map(e -> e.replace("crn ", ""))
        .map(e -> e.replace("end", ""))
        .collect(Collectors.toList());
    StructureWithModels.commands.put(Patterns.BARRED_CN_OPERATOR_REF, new ArrayList<>());
    for (String createObject : createObjects) {
      Matcher matcher = Patterns.BARRED_CN_OPERATOR_REF.getPattern().matcher(createObject);

      if(matcher.matches()) {
        CreationCommand creationCommand = CreationCommandFabric.createObject(Patterns.BARRED_CN_OPERATOR_REF, createObject);
        StructureWithModels.commands.get(Patterns.BARRED_CN_OPERATOR_REF).add(creationCommand);

      }
    }

    int i = 0;

    for (CreationCommand creationCommand : StructureWithModels.commands.get(Patterns.BARRED_CN_OPERATOR_REF)) {
      i++;
//      System.out.println(creationCommand);
    }

    System.out.println(i);
  }

}
*/