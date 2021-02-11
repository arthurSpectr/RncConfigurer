package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class BarredCnOperatorRef implements CreationCommand {

  /**
   * name : RncFunction=1,UtranCell=CK0001K,EutranFreqRelation=1300
   * barredCnOperatorRef :
   * blacklistedCell :
   * cellReselectionPriority : 5
   * coSitedCellAvailable : 1
   * eutranFrequencyRef : {"EutraNetwork":"Astelit","EutranFrequency":1300}
   * qQualMin : 100
   * qRxLevMin : -120
   * redirectionOrder : 1
   * thresh2dRwr : -85
   * threshHigh : 10
   * threshHigh2 : 10
   * threshLow : 6
   * threshLow2 : 10
   * userLabel :
   */

  private String name;
  private String barredCnOperatorRef;
  private String blacklistedCell;
  private int cellReselectionPriority;
  private int coSitedCellAvailable;
  private EutranFrequencyRefBean eutranFrequencyRef;
  private int qQualMin;
  private int qRxLevMin;
  private int redirectionOrder;
  private int thresh2dRwr;
  private int threshHigh;
  private int threshHigh2;
  private int threshLow;
  private int threshLow2;
  private String userLabel;
  private String[] source;

  public BarredCnOperatorRef() {
  }

  public BarredCnOperatorRef(String[] source) {
    this.source = source;
    name = source[0];
    barredCnOperatorRef = FieldExtractor.getFieldString(source, "barredCnOperatorRef");
    blacklistedCell = FieldExtractor.getFieldString(source, "blacklistedCell");
    cellReselectionPriority = FieldExtractor.getFieldIntPrimitive(source, "cellReselectionPriority");
    coSitedCellAvailable = FieldExtractor.getFieldIntPrimitive(source, "coSitedCellAvailable");
    eutranFrequencyRef = (EutranFrequencyRefBean)FieldExtractor.getFieldObject(EutranFrequencyRefBean.class, source, "eutranFrequencyRef");
    qQualMin = FieldExtractor.getFieldIntPrimitive(source, "qQualMin");
    qRxLevMin = FieldExtractor.getFieldIntPrimitive(source, "qRxLevMin");
    redirectionOrder = FieldExtractor.getFieldIntPrimitive(source, "redirectionOrder");
    thresh2dRwr = FieldExtractor.getFieldIntPrimitive(source, "thresh2dRwr");
    threshHigh = FieldExtractor.getFieldIntPrimitive(source, "threshHigh");
    threshHigh2 = FieldExtractor.getFieldIntPrimitive(source, "threshHigh2");
    threshLow = FieldExtractor.getFieldIntPrimitive(source, "threshLow");
    threshLow2 = FieldExtractor.getFieldIntPrimitive(source, "threshLow2");
    userLabel = FieldExtractor.getFieldString(source, "userLabel");
  }

  @Override
  public CreationCommand getCreationCommand(Patterns pattern, String source) {
    return null;
  }

  public String getName() {
    return name;
  }

  @Override
  public Map<String, String> getValues() {
    Map<String, String> values = ModelUtils.createMapProperties(source);

    return values;
  }

  @Override
  public Patterns getType() {
    return Patterns.BARRED_CN_OPERATOR_REF;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBarredCnOperatorRef() {
    return barredCnOperatorRef;
  }

  public void setBarredCnOperatorRef(String barredCnOperatorRef) {
    this.barredCnOperatorRef = barredCnOperatorRef;
  }

  public String getBlacklistedCell() {
    return blacklistedCell;
  }

  public void setBlacklistedCell(String blacklistedCell) {
    this.blacklistedCell = blacklistedCell;
  }

  public int getCellReselectionPriority() {
    return cellReselectionPriority;
  }

  public void setCellReselectionPriority(int cellReselectionPriority) {
    this.cellReselectionPriority = cellReselectionPriority;
  }

  public int getCoSitedCellAvailable() {
    return coSitedCellAvailable;
  }

  public void setCoSitedCellAvailable(int coSitedCellAvailable) {
    this.coSitedCellAvailable = coSitedCellAvailable;
  }

  public EutranFrequencyRefBean getEutranFrequencyRef() {
    return eutranFrequencyRef;
  }

  public void setEutranFrequencyRef(EutranFrequencyRefBean eutranFrequencyRef) {
    this.eutranFrequencyRef = eutranFrequencyRef;
  }

  public int getQQualMin() {
    return qQualMin;
  }

  public void setQQualMin(int qQualMin) {
    this.qQualMin = qQualMin;
  }

  public int getQRxLevMin() {
    return qRxLevMin;
  }

  public void setQRxLevMin(int qRxLevMin) {
    this.qRxLevMin = qRxLevMin;
  }

  public int getRedirectionOrder() {
    return redirectionOrder;
  }

  public void setRedirectionOrder(int redirectionOrder) {
    this.redirectionOrder = redirectionOrder;
  }

  public int getThresh2dRwr() {
    return thresh2dRwr;
  }

  public void setThresh2dRwr(int thresh2dRwr) {
    this.thresh2dRwr = thresh2dRwr;
  }

  public int getThreshHigh() {
    return threshHigh;
  }

  public void setThreshHigh(int threshHigh) {
    this.threshHigh = threshHigh;
  }

  public int getThreshHigh2() {
    return threshHigh2;
  }

  public void setThreshHigh2(int threshHigh2) {
    this.threshHigh2 = threshHigh2;
  }

  public int getThreshLow() {
    return threshLow;
  }

  public void setThreshLow(int threshLow) {
    this.threshLow = threshLow;
  }

  public int getThreshLow2() {
    return threshLow2;
  }

  public void setThreshLow2(int threshLow2) {
    this.threshLow2 = threshLow2;
  }

  public String getUserLabel() {
    return userLabel;
  }

  public void setUserLabel(String userLabel) {
    this.userLabel = userLabel;
  }

  public static class EutranFrequencyRefBean {
    /**
     * EutraNetwork : Astelit
     * EutranFrequency : 1300
     */

    private String EutraNetwork;
    private int EutranFrequency;

    public EutranFrequencyRefBean() {
    }

    public EutranFrequencyRefBean(String src) {
      final String[] source = src.split(",");
      EutraNetwork = source[0].split("=").length == 2 ? source[0].split("=")[1] : null;
      EutranFrequency = source[0].split("=").length == 2 ? Integer.parseInt(source[1].split("=")[1]) : null;
    }

    public String getEutraNetwork() {
      return EutraNetwork;
    }

    public void setEutraNetwork(String EutraNetwork) {
      this.EutraNetwork = EutraNetwork;
    }

    public int getEutranFrequency() {
      return EutranFrequency;
    }

    public void setEutranFrequency(int EutranFrequency) {
      this.EutranFrequency = EutranFrequency;
    }

    @Override
    public String toString() {
      return "EutraNetwork=" + EutraNetwork + ",EutranFrequency=" + EutranFrequency;
    }
  }

  @Override
  public String toString() {
    return "crn " + name +  "\n" +
        "barredCnOperatorRef " + (barredCnOperatorRef == null ? "" : barredCnOperatorRef) + "\n" +
        "blacklistedCell " + (blacklistedCell == null ? "" : blacklistedCell) + "\n" +
        "cellReselectionPriority " + cellReselectionPriority + "\n" +
        "coSitedCellAvailable " + coSitedCellAvailable + "\n" +
        "eutranFrequencyRef " + eutranFrequencyRef + "\n" +
        "qQualMin " + qQualMin + "\n" +
        "qRxLevMin " + qRxLevMin + "\n" +
        "redirectionOrder " + redirectionOrder + "\n" +
        "thresh2dRwr " + thresh2dRwr + "\n" +
        "threshHigh " + threshHigh + "\n" +
        "threshHigh2 " + threshHigh2 + "\n" +
        "threshLow " + threshLow + "\n" +
        "threshLow2 " + threshLow2 + "\n" +
        "userLabel " + (userLabel == null ? "" : userLabel) + "\n" +
        "end\n\n";
  }
}
