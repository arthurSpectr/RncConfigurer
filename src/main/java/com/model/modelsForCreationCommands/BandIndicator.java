package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class BandIndicator implements CreationCommand {


  /**
   * name : RncFunction=1,ExternalGsmNetwork=Astelit,ExternalGsmCell=CK00013
   * bandIndicator : 0
   * bcc : 6
   * bcchFrequency : 525
   * cellIdentity : 9643
   * individualOffset : 0
   * lac : 12710
   * maxTxPowerUl : 30
   * ncc : 1
   * qRxLevMin : -105
   * userLabel : CK00013
   */

  private String name;
  private int bandIndicator;
  private int bcc;
  private int bcchFrequency;
  private int cellIdentity;
  private int individualOffset;
  private int lac;
  private int maxTxPowerUl;
  private int ncc;
  private int qRxLevMin;
  private String userLabel;
  private String[] source;

  public BandIndicator() {
  }

  public BandIndicator(String[] source) {
    this.source = source;
    name = source[0];
    bandIndicator = FieldExtractor.getFieldIntPrimitive(source, "bandIndicator");
    bcc = FieldExtractor.getFieldIntPrimitive(source, "bcc");
    bcchFrequency = FieldExtractor.getFieldIntPrimitive(source, "bcchFrequency");
    cellIdentity = FieldExtractor.getFieldIntPrimitive(source, "cellIdentity");
    individualOffset = FieldExtractor.getFieldIntPrimitive(source, "individualOffset");
    lac = FieldExtractor.getFieldIntPrimitive(source, "lac");
    maxTxPowerUl = FieldExtractor.getFieldIntPrimitive(source, "maxTxPowerUl");
    ncc = FieldExtractor.getFieldIntPrimitive(source, "ncc");
    qRxLevMin = FieldExtractor.getFieldIntPrimitive(source, "qRxLevMin");
    userLabel = FieldExtractor.getFieldString(source, "userLabel");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBandIndicator() {
    return bandIndicator;
  }

  public void setBandIndicator(int bandIndicator) {
    this.bandIndicator = bandIndicator;
  }

  public int getBcc() {
    return bcc;
  }

  public void setBcc(int bcc) {
    this.bcc = bcc;
  }

  public int getBcchFrequency() {
    return bcchFrequency;
  }

  public void setBcchFrequency(int bcchFrequency) {
    this.bcchFrequency = bcchFrequency;
  }

  public int getCellIdentity() {
    return cellIdentity;
  }

  public void setCellIdentity(int cellIdentity) {
    this.cellIdentity = cellIdentity;
  }

  public int getIndividualOffset() {
    return individualOffset;
  }

  public void setIndividualOffset(int individualOffset) {
    this.individualOffset = individualOffset;
  }

  public int getLac() {
    return lac;
  }

  public void setLac(int lac) {
    this.lac = lac;
  }

  public int getMaxTxPowerUl() {
    return maxTxPowerUl;
  }

  public void setMaxTxPowerUl(int maxTxPowerUl) {
    this.maxTxPowerUl = maxTxPowerUl;
  }

  public int getNcc() {
    return ncc;
  }

  public void setNcc(int ncc) {
    this.ncc = ncc;
  }

  public int getQRxLevMin() {
    return qRxLevMin;
  }

  public void setQRxLevMin(int qRxLevMin) {
    this.qRxLevMin = qRxLevMin;
  }

  public String getUserLabel() {
    return userLabel;
  }

  public void setUserLabel(String userLabel) {
    this.userLabel = userLabel;
  }

  @Override
  public CreationCommand getCreationCommand(Patterns pattern, String source) {
    return null;
  }

  @Override
  public Map<String,String> getValues() {
    Map<String, String> values = ModelUtils.createMapProperties(source);

    return values;
  }

  @Override
  public Patterns getType() {
    return Patterns.BAND_INDICATOR;
  }

  @Override
  public String toString() {
    return "crn " + name +  "\n" +
        "bandIndicator " + bandIndicator + "\n" +
        "bcc " + bcc + "\n" +
        "bcchFrequency " + bcchFrequency + "\n" +
        "cellIdentity " + cellIdentity + "\n" +
        "individualOffset " + individualOffset + "\n" +
        "lac " + lac + "\n" +
        "maxTxPowerUl " + maxTxPowerUl + "\n" +
        "ncc " + ncc + "\n" +
        "qRxLevMin " + qRxLevMin + "\n" +
        "userLabel " + userLabel +  "\n" +
        "end\n\n";
  }
}
