package com.model;

import java.util.regex.Pattern;

public enum Patterns {

  E_NODEB_ID(Pattern.compile("RncFunction=[0-9]*," + "EutraNetwork=[a-zA-Z]*,EutranFrequency=[\\d]*,ExternalEutranCell=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  BAND_INDICATOR(Pattern.compile("RncFunction=[0-9]*," + "ExternalGsmNetwork=[a-zA-Z]*,ExternalGsmCell=[\\w]*[\\s\\n]*bandIndicator" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  EXTERNAL_GSM_CELL_REF(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,GsmRelation=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  ATM_USER_PLANE_TERM_SUBRACK_REF(Pattern.compile("RncFunction=[0-9]*," + "IubLink=[\\w]*[\\s\\n]*administrativeState" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  HCS_SIB_11_CONFIG(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,UtranRelation=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  EXTERNAL_EUTRAN_CELL_REF(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,EutranFreqRelation=[\\w]*,EutranCellRelation=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  HS_PATH_LOSS_TRESHOLD(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,CoverageRelation=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  EU_DCH_BALANCING_ENABLED(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,Hsdsch=[\\w]*,Eul=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  CODE_THRESHOLD_PDU_656(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,Hsdsch=[\\w]*[\\s\\n]*administrativeState" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  MAX_FACH_1_POWER(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,Fach=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  PCH_POWER(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,Pch=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  AICH_TRANSMISSION_TIMING(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,Rach=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  ASE_LOAD_THRESHOLD_UI_SPEECH(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*[\\s\\n]*absPrioCellRes" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  SAC(Pattern.compile("RncFunction=[0-9]*," + "LocationArea=[\\w]*,[\\s\\n]*ServiceArea=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  EDCH_DATA_FRAME_DELAY_THRESHOLD(Pattern.compile("RncFunction=[0-9]*," + "IubLink=[\\w]*,[\\s\\n]*IubEdch=[\\w]*" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  BARRED_CN_OPERATOR_REF(Pattern.compile("RncFunction=[0-9]*," + "UtranCell=[\\w]*,EutranFreqRelation=[\\w]*[\\s\\n]*barredCnOperatorRef" + "[\\s\\n\\w\\.,=-]*[\\s\\n]*")),
  LD_LSET(Pattern.compile("ld RncFunction=[\\w]*[,$#\\s\\n\\w\\.,=-]*[,$#\\s\\n]*")),
  HEADER(Pattern.compile("#DoNotEditThisLine:[\\s]*UndoCommandFile[\\s]*[\\w.]*[\\s]*[\\w.]*[\\s]*RNC_NODE_MODEL[\\w_]*[\\s]*stopfile=[\\w/]*[\\s\\n\\w\\.,=-]*[\\s\\n]*"));

  private Pattern pattern;

  Patterns(Pattern pattern) {
    this.pattern = pattern;
  }

  public Pattern getPattern() {
    return pattern;
  }

  public void setPattern(Pattern pattern) {
    this.pattern = pattern;
  }
}
