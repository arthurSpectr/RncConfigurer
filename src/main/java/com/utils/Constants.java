package com.utils;

import java.util.Objects;

public class Constants {
    public static final String configString = "=================================================================================================================\n" +
            "119458                               RncFunction=1,UtranCell=CK2019A\n" +
            "=================================================================================================================\n" +
            "UtranCellId                          CK2019A\n" +
            "absPrioCellRes                       Struct{5}\n" +
            " >>> 1.sPrioritySearch1 = 10\n" +
            " >>> 2.sPrioritySearch2 = 4\n" +
            " >>> 3.measIndFach = 0 (DISABLED)\n" +
            " >>> 4.cellReselectionPriority = 3\n" +
            " >>> 5.threshServingLow = 16\n" +
            "accessClassNBarred                   i[16] = 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 (FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE)\n" +
            "accessClassesBarredCs                i[16] = 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 (FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE)\n" +
            "accessClassesBarredPs                i[16] = 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 (FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE)\n" +
            "admBlockRedirection                  Struct{3}\n" +
            " >>> 1.gsmRrc = 1 (ON)\n" +
            " >>> 2.speech = 1 (ON)\n" +
            " >>> 3.rrc = 0 (OFF)\n" +
            "administrativeState                  1 (UNLOCKED)\n" +
            "agpsEnabled                          1 (TRUE)\n" +
            "amrNbSelector                        0 (SINGLE_RATE)\n" +
            "amrWbRateDlMax                       12650\n" +
            "reservedBy                           [14] = \n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2008A,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2011A,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019B,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019C,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019E,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019F,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019G,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019I,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019J,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019K,UtranRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019E,CoverageRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019I,CoverageRelation=CK2019A\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019I,Hsdsch=1,Eul=1,MultiCarrier=1\n" +
            " >>> reservedBy = RncFunction=1,UtranCell=CK2019E,Hsdsch=1,Eul=1,MultiCarrier=1";

    public static final String anotherConfigString = "=================================================================================================================\n" +
        "113011                               RncFunction=1,IubLink=CK2019\n" +
        "=================================================================================================================\n" +
        "IubLinkId                            CK2019\n" +
        "administrativeState                  1 (UNLOCKED)\n" +
        "atmUserPlaneTermSubrackRef           \n" +
        "availabilityStatus                   0 (NO_STATUS)\n" +
        "cachedRemoteCpIpAddress1             \n" +
        "cachedRemoteCpIpAddress2             \n" +
        "controlPlaneTransportOption          Struct{2}\n" +
        " >>> 1.atm = 0 (FALSE)\n" +
        " >>> 2.ipv4 = 1 (TRUE)\n" +
        "dlHwAdm                              95\n" +
        "l2EstReqRetryTimeNbapC               5\n" +
        "l2EstReqRetryTimeNbapD               5\n" +
        "linkType                             0 (TERRESTRIAL)\n" +
        "operationalState                     1 (ENABLED)\n" +
        "poolRedundancy                       0 (NO_POOL_REDUN)\n" +
        "rSiteRef                             \n" +
        "rbsId                                7019\n" +
        "remoteCpIpAddress1                   10.80.149.179\n" +
        "remoteCpIpAddress2                   \n" +
        "remoteSctpPortNbapC                  5113\n" +
        "remoteSctpPortNbapD                  5114\n" +
        "reservedBy                           [9] = \n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019A\n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019B\n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019C\n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019E\n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019F\n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019G\n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019I\n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019J\n" +
        " >>> reservedBy = RncFunction=1,UtranCell=CK2019K\n" +
        "rncModuleAllocWeight                 10\n" +
        "rncModulePreferredRef                \n" +
        "rncModuleRef                         RncModule=ES-2-3-1\n" +
        "sctpRef                              Sctp=ES-2-3_Iub\n" +
        "softCongThreshGbrBwDl                100\n" +
        "softCongThreshGbrBwUl                100\n" +
        "spare                                0\n" +
        "spareA                               i[10] = 0 0 0 0 0 0 0 0 0 7019 \n" +
        "ulHwAdm                              95\n" +
        "userLabel                            CK2019\n" +
        "userPlaneGbrAdmBandwidthDl           10000\n" +
        "userPlaneGbrAdmBandwidthUl           10000\n" +
        "userPlaneGbrAdmEnabled               0 (FALSE)\n" +
        "userPlaneGbrAdmMarginDl              0\n" +
        "userPlaneGbrAdmMarginUl              0\n" +
        "userPlaneIpResourceRef               IpAccessHostPool=IUB\n" +
        "userPlaneTransportOption             Struct{2}\n" +
        " >>> 1.atm = 0 (FALSE)\n" +
        " >>> 2.ipv4 = 1 (TRUE)";

    public static final ClassLoader classLoader = Constants.class.getClassLoader();

    public static final String OLD_ORIGINAL_FILE_OF_COMMANDS = Objects.requireNonNull(classLoader.getResource("undo_KIER7_200126-183751.mos")).getFile();

    public static final String ORIGINAL_FILE_OF_COMMANDS = Objects.requireNonNull(classLoader.getResource("undo_KIER7_191118-105157.mos")).getFile();
    public static final String FILE_OF_COMMANDS_WITH_MARKERS = Objects.requireNonNull(classLoader.getResource("undo_KIER7_200126-183751_div.mos")).getFile();

    public static final String TEST_FILE_OF_COMMANDS1 = Objects.requireNonNull(classLoader.getResource("undo_KIER7_200126-183751.mos")).getFile();
    public static final String TEST_FILE_OF_COMMANDS2 = Objects.requireNonNull(classLoader.getResource("undo_KIER7_200226-150547.mos")).getFile();
    public static final String TEST_FILE_OF_COMMANDS3 = Objects.requireNonNull(classLoader.getResource("undo_KIER7_200226-152555.mos")).getFile();

    public static final String TEST_FILE_OF_COMMANDS4 = Objects.requireNonNull(classLoader.getResource("undo_KIER3_200409-161332.mos")).getFile();
    public static final String TEST_FILE_OF_COMMANDS5 = Objects.requireNonNull(classLoader.getResource("undo_KIER3_200409-162849.mos")).getFile();

    public static final String TEST_FILE_OF_CHANGES1 = Objects.requireNonNull(classLoader.getResource("RncMaximoTable1.csv")).getFile();
    public static final String TEST_FILE_OF_CHANGES2 = Objects.requireNonNull(classLoader.getResource("RncMaximoTable2.csv")).getFile();
    public static final String TEST_FILE_OF_CHANGES3 = Objects.requireNonNull(classLoader.getResource("RncMaximoTable3.csv")).getFile();

    public static final String RESULT_FILES = "src/main/resources/resultFiles/";
    public static final String UTRAN_REl = "utran_rel.mos";
    public static final String GSM_REL = "gsm_rel.mos";
    public static final String EUTRAN = "Eutran_rel.mos";
    public static final String IUB_CELL = "iub_cell.mos";

    public static final String SITES_NEIGHBORS_FILE = "src/main/resources/resultFiles/";
    public static final String SITES_NEIGHBORS = "sites_neighbors.mos";
    public static final String SITES_AND_NEIGHBORS_FILE = "src/main/resources/resultFiles/";
    public static final String SITES_AND_NEIGHBORS = "sites_and_neighbors.mos";
    public static final String LAC_RAC_URA = "LAC_RAC_URA.txt";

    public static final String CREATION_COMMANDS_DIRECTORY = "src/main/resources/rncCreationCommands/";
    public static final String FILES_OF_CHANGES_DIRECTORY = "src/main/resources/rncCreationCommands/";
}
