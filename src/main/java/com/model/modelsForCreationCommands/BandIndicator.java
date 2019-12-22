package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.utils.Patterns;

import java.util.List;
import java.util.regex.Pattern;

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

  final static String begin = "RncFunction=[0-9]*,";
  final static String end = "[\\s\\n\\w\\.,=-]*[\\s\\n]*";
  final Pattern pattern = Pattern.compile(begin + "ExternalGsmNetwork=[a-zA-Z]*,ExternalGsmCell=[\\w]*[\\s\\n]*bandIndicator" + end);

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
  public List<?> getValues() {
    return null;
  }

  @Override
  public String getType() {
    return null;
  }
}
