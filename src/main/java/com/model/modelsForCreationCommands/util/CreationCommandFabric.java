package com.model.modelsForCreationCommands.util;

import com.model.modelsForCreationCommands.*;
import com.model.Patterns;

import java.io.IOException;

public class CreationCommandFabric {

  public static CreationCommand createObject(Patterns pattern, String source) throws IOException {

    switch (pattern) {
      case SAC: return new Sac(source.split("\n"));
      case HEADER: return new Header(source);
      case LD_LSET: return new LdLset(source.split("\n"));
      case PCH_POWER: return new PchPower(source.split("\n"));
      case E_NODEB_ID: return new ENodeBId(source.split("\n"));
      case BAND_INDICATOR:return new BandIndicator(source.split("\n"));
      case MAX_FACH_1_POWER: return new MaxFach1Power(source.split("\n"));
      case HCS_SIB_11_CONFIG: return new HcsSid11Config(source.split("\n"));
      case EXTERNAL_GSM_CELL_REF: return new ExternalGsmCellRef(source.split("\n"));
      case HS_PATH_LOSS_TRESHOLD: return new HsPathLossTreshold(source.split("\n"));
      case BARRED_CN_OPERATOR_REF: return new BarredCnOperatorRef(source.split("\n"));
      case CODE_THRESHOLD_PDU_656: return new CodeThresholdPdu656(source.split("\n"));
      case AICH_TRANSMISSION_TIMING: return new AichTransmissionTiming(source.split("\n"));
      case EU_DCH_BALANCING_ENABLED: return new EuDchBalancingEnabled(source.split("\n"));
      case EXTERNAL_EUTRAN_CELL_REF: return new ExternalEutranCellRef(source.split("\n"));
      case ASE_LOAD_THRESHOLD_UI_SPEECH: return new AseLoadThresholdUiSpeech(source.split("\n"));
      case ATM_USER_PLANE_TERM_SUBRACK_REF: return new AtmUserPlaneTermSubrackRef(source.split("\n"));
      case EDCH_DATA_FRAME_DELAY_THRESHOLD: return new EdchDataFrameDelayThreshold(source.split("\n"));


      default: return null;
    }

  }

}
