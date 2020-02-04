package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.utils.Patterns;

import java.util.Map;

public class EuDchBalancingEnabled implements CreationCommand {

  /**
   * name : RncFunction=1,UtranCell=CK0001K,Hsdsch=1,Eul=1
   * administrativeState : 1
   * eulDchBalancingEnabled : 0
   * eulDchBalancingLoad : 6
   * eulDchBalancingOverload : 0
   * eulDchBalancingReportPeriod : 60
   * eulDchBalancingSuspendDownSw : 0
   * eulDchBalancingTimerNg : 1000
   * eulLoadTriggeredSoftCong : 0
   * eulMaxTargetRtwp : -499
   * numEagchCodes : 3
   * numEhichErgchCodes : 4
   * pathlossThresholdEulTti2 : 130
   * releaseAseUlNg : 3
   * threshEulTti2Ecno : -18
   * userLabel :
   */

  private String name;
  private int administrativeState;
  private int eulDchBalancingEnabled;
  private int eulDchBalancingLoad;
  private int eulDchBalancingOverload;
  private int eulDchBalancingReportPeriod;
  private int eulDchBalancingSuspendDownSw;
  private int eulDchBalancingTimerNg;
  private int eulLoadTriggeredSoftCong;
  private int eulMaxTargetRtwp;
  private int numEagchCodes;
  private int numEhichErgchCodes;
  private int pathlossThresholdEulTti2;
  private int releaseAseUlNg;
  private int threshEulTti2Ecno;
  private String userLabel;
  private String[] source;

  public EuDchBalancingEnabled() {
  }

  public EuDchBalancingEnabled(String[] source) {
    this.source = source;
    name = source[0];
    administrativeState = source[1].split("\\s").length == 2 ? Integer.parseInt(source[1].split("\\s")[1]) : null;
    eulDchBalancingEnabled = source[2].split("\\s").length == 2 ? Integer.parseInt(source[2].split("\\s")[1]) : null;
    eulDchBalancingLoad = source[3].split("\\s").length == 2 ? Integer.parseInt(source[3].split("\\s")[1]) : null;
    eulDchBalancingOverload = source[4].split("\\s").length == 2 ? Integer.parseInt(source[4].split("\\s")[1]) : null;
    eulDchBalancingReportPeriod = source[5].split("\\s").length == 2 ? Integer.parseInt(source[5].split("\\s")[1]) : null;
    eulDchBalancingSuspendDownSw = source[6].split("\\s").length == 2 ? Integer.parseInt(source[6].split("\\s")[1]) : null;
    eulDchBalancingTimerNg = source[7].split("\\s").length == 2 ? Integer.parseInt(source[7].split("\\s")[1]) : null;
    eulLoadTriggeredSoftCong = source[8].split("\\s").length == 2 ? Integer.parseInt(source[8].split("\\s")[1]) : null;
    eulMaxTargetRtwp = source[9].split("\\s").length == 2 ? Integer.parseInt(source[9].split("\\s")[1]) : null;
    numEagchCodes = source[10].split("\\s").length == 2 ? Integer.parseInt(source[10].split("\\s")[1]) : null;
    numEhichErgchCodes = source[11].split("\\s").length == 2 ? Integer.parseInt(source[11].split("\\s")[1]) : null;
    pathlossThresholdEulTti2 = source[12].split("\\s").length == 2 ? Integer.parseInt(source[12].split("\\s")[1]) : null;
    releaseAseUlNg = source[13].split("\\s").length == 2 ? Integer.parseInt(source[13].split("\\s")[1]) : null;
    threshEulTti2Ecno = source[14].split("\\s").length == 2 ? Integer.parseInt(source[14].split("\\s")[1]) : null;
    userLabel = source[15].split("\\s").length == 2 ? source[15].split("\\s")[1] : null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAdministrativeState() {
    return administrativeState;
  }

  public void setAdministrativeState(int administrativeState) {
    this.administrativeState = administrativeState;
  }

  public int getEulDchBalancingEnabled() {
    return eulDchBalancingEnabled;
  }

  public void setEulDchBalancingEnabled(int eulDchBalancingEnabled) {
    this.eulDchBalancingEnabled = eulDchBalancingEnabled;
  }

  public int getEulDchBalancingLoad() {
    return eulDchBalancingLoad;
  }

  public void setEulDchBalancingLoad(int eulDchBalancingLoad) {
    this.eulDchBalancingLoad = eulDchBalancingLoad;
  }

  public int getEulDchBalancingOverload() {
    return eulDchBalancingOverload;
  }

  public void setEulDchBalancingOverload(int eulDchBalancingOverload) {
    this.eulDchBalancingOverload = eulDchBalancingOverload;
  }

  public int getEulDchBalancingReportPeriod() {
    return eulDchBalancingReportPeriod;
  }

  public void setEulDchBalancingReportPeriod(int eulDchBalancingReportPeriod) {
    this.eulDchBalancingReportPeriod = eulDchBalancingReportPeriod;
  }

  public int getEulDchBalancingSuspendDownSw() {
    return eulDchBalancingSuspendDownSw;
  }

  public void setEulDchBalancingSuspendDownSw(int eulDchBalancingSuspendDownSw) {
    this.eulDchBalancingSuspendDownSw = eulDchBalancingSuspendDownSw;
  }

  public int getEulDchBalancingTimerNg() {
    return eulDchBalancingTimerNg;
  }

  public void setEulDchBalancingTimerNg(int eulDchBalancingTimerNg) {
    this.eulDchBalancingTimerNg = eulDchBalancingTimerNg;
  }

  public int getEulLoadTriggeredSoftCong() {
    return eulLoadTriggeredSoftCong;
  }

  public void setEulLoadTriggeredSoftCong(int eulLoadTriggeredSoftCong) {
    this.eulLoadTriggeredSoftCong = eulLoadTriggeredSoftCong;
  }

  public int getEulMaxTargetRtwp() {
    return eulMaxTargetRtwp;
  }

  public void setEulMaxTargetRtwp(int eulMaxTargetRtwp) {
    this.eulMaxTargetRtwp = eulMaxTargetRtwp;
  }

  public int getNumEagchCodes() {
    return numEagchCodes;
  }

  public void setNumEagchCodes(int numEagchCodes) {
    this.numEagchCodes = numEagchCodes;
  }

  public int getNumEhichErgchCodes() {
    return numEhichErgchCodes;
  }

  public void setNumEhichErgchCodes(int numEhichErgchCodes) {
    this.numEhichErgchCodes = numEhichErgchCodes;
  }

  public int getPathlossThresholdEulTti2() {
    return pathlossThresholdEulTti2;
  }

  public void setPathlossThresholdEulTti2(int pathlossThresholdEulTti2) {
    this.pathlossThresholdEulTti2 = pathlossThresholdEulTti2;
  }

  public int getReleaseAseUlNg() {
    return releaseAseUlNg;
  }

  public void setReleaseAseUlNg(int releaseAseUlNg) {
    this.releaseAseUlNg = releaseAseUlNg;
  }

  public int getThreshEulTti2Ecno() {
    return threshEulTti2Ecno;
  }

  public void setThreshEulTti2Ecno(int threshEulTti2Ecno) {
    this.threshEulTti2Ecno = threshEulTti2Ecno;
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
    return Patterns.EU_DCH_BALANCING_ENABLED;
  }

  @Override
  public String toString() {
    return "crn " + name + "\n" +
        "administrativeState " + administrativeState + "\n" +
        "eulDchBalancingEnabled " + eulDchBalancingEnabled + "\n" +
        "eulDchBalancingLoad " + eulDchBalancingLoad + "\n" +
        "eulDchBalancingOverload " + eulDchBalancingOverload + "\n" +
        "eulDchBalancingReportPeriod " + eulDchBalancingReportPeriod + "\n" +
        "eulDchBalancingSuspendDownSw " + eulDchBalancingSuspendDownSw + "\n" +
        "eulDchBalancingTimerNg " + eulDchBalancingTimerNg + "\n" +
        "eulLoadTriggeredSoftCong " + eulLoadTriggeredSoftCong + "\n" +
        "eulMaxTargetRtwp " + eulMaxTargetRtwp + "\n" +
        "numEagchCodes " + numEagchCodes + "\n" +
        "numEhichErgchCodes " + numEhichErgchCodes + "\n" +
        "pathlossThresholdEulTti2 " + pathlossThresholdEulTti2 + "\n" +
        "releaseAseUlNg " + releaseAseUlNg + "\n" +
        "threshEulTti2Ecno " + threshEulTti2Ecno + "\n" +
        "userLabel " + (userLabel == null ? "" : userLabel) + "\n" +
        "end\n";
  }
}
