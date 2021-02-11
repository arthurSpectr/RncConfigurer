package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Collections;
import java.util.Map;

public class AichTransmissionTiming implements CreationCommand {

  /**
   * name : RncFunction=1,UtranCell=CK0001K,Rach=1
   * administrativeState : 1
   * aichPower : -6
   * aichTransmissionTiming : 4
   * constantValueCprach : -24
   * increasedRachCoverageEnabled : 0
   * maxPreambleCycle : 8
   * nb01Max : 0
   * nb01Min : 0
   * powerOffsetP0 : 1
   * powerOffsetPpm : -2
   * preambleRetransMax : 32
   * preambleSignatures : 65535
   * scramblingCodeWordNo : 0
   * spreadingFactor : 32
   * subChannelNo : 4095
   * userLabel :
   */

  private String name;
  private int administrativeState;
  private int aichPower;
  private int aichTransmissionTiming;
  private int constantValueCprach;
  private int increasedRachCoverageEnabled;
  private int maxPreambleCycle;
  private int nb01Max;
  private int nb01Min;
  private int powerOffsetP0;
  private int powerOffsetPpm;
  private int preambleRetransMax;
  private int preambleSignatures;
  private int scramblingCodeWordNo;
  private int spreadingFactor;
  private int subChannelNo;
  private String userLabel;
  private String[] source;

  private int i = 0;

  public AichTransmissionTiming() {
  }

  public AichTransmissionTiming(String[] source) {
    this.source = source;
    name = source[0];
    administrativeState = FieldExtractor.getFieldIntPrimitive(source, "administrativeState");
    aichPower = FieldExtractor.getFieldIntPrimitive(source, "aichPower");
    aichTransmissionTiming = FieldExtractor.getFieldIntPrimitive(source, "aichTransmissionTiming");
    constantValueCprach = FieldExtractor.getFieldIntPrimitive(source, "constantValueCprach");
    increasedRachCoverageEnabled = FieldExtractor.getFieldIntPrimitive(source, "increasedRachCoverageEnabled");
    maxPreambleCycle = FieldExtractor.getFieldIntPrimitive(source, "maxPreambleCycle");
    nb01Max = FieldExtractor.getFieldIntPrimitive(source, "nb01Max");
    nb01Min = FieldExtractor.getFieldIntPrimitive(source, "nb01Min");
    powerOffsetP0 = FieldExtractor.getFieldIntPrimitive(source, "powerOffsetP0");
    powerOffsetPpm = FieldExtractor.getFieldIntPrimitive(source, "powerOffsetPpm");
    preambleRetransMax = FieldExtractor.getFieldIntPrimitive(source, "preambleRetransMax");
    preambleSignatures = FieldExtractor.getFieldIntPrimitive(source, "preambleSignatures");
    scramblingCodeWordNo = FieldExtractor.getFieldIntPrimitive(source, "scramblingCodeWordNo");
    spreadingFactor = FieldExtractor.getFieldIntPrimitive(source, "spreadingFactor");
    subChannelNo = FieldExtractor.getFieldIntPrimitive(source, "subChannelNo");
    userLabel = FieldExtractor.getFieldString(source, "userLabel");
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

  public int getAichPower() {
    return aichPower;
  }

  public void setAichPower(int aichPower) {
    this.aichPower = aichPower;
  }

  public int getAichTransmissionTiming() {
    return aichTransmissionTiming;
  }

  public void setAichTransmissionTiming(int aichTransmissionTiming) {
    this.aichTransmissionTiming = aichTransmissionTiming;
  }

  public int getConstantValueCprach() {
    return constantValueCprach;
  }

  public void setConstantValueCprach(int constantValueCprach) {
    this.constantValueCprach = constantValueCprach;
  }

  public int getIncreasedRachCoverageEnabled() {
    return increasedRachCoverageEnabled;
  }

  public void setIncreasedRachCoverageEnabled(int increasedRachCoverageEnabled) {
    this.increasedRachCoverageEnabled = increasedRachCoverageEnabled;
  }

  public int getMaxPreambleCycle() {
    return maxPreambleCycle;
  }

  public void setMaxPreambleCycle(int maxPreambleCycle) {
    this.maxPreambleCycle = maxPreambleCycle;
  }

  public int getNb01Max() {
    return nb01Max;
  }

  public void setNb01Max(int nb01Max) {
    this.nb01Max = nb01Max;
  }

  public int getNb01Min() {
    return nb01Min;
  }

  public void setNb01Min(int nb01Min) {
    this.nb01Min = nb01Min;
  }

  public int getPowerOffsetP0() {
    return powerOffsetP0;
  }

  public void setPowerOffsetP0(int powerOffsetP0) {
    this.powerOffsetP0 = powerOffsetP0;
  }

  public int getPowerOffsetPpm() {
    return powerOffsetPpm;
  }

  public void setPowerOffsetPpm(int powerOffsetPpm) {
    this.powerOffsetPpm = powerOffsetPpm;
  }

  public int getPreambleRetransMax() {
    return preambleRetransMax;
  }

  public void setPreambleRetransMax(int preambleRetransMax) {
    this.preambleRetransMax = preambleRetransMax;
  }

  public int getPreambleSignatures() {
    return preambleSignatures;
  }

  public void setPreambleSignatures(int preambleSignatures) {
    this.preambleSignatures = preambleSignatures;
  }

  public int getScramblingCodeWordNo() {
    return scramblingCodeWordNo;
  }

  public void setScramblingCodeWordNo(int scramblingCodeWordNo) {
    this.scramblingCodeWordNo = scramblingCodeWordNo;
  }

  public int getSpreadingFactor() {
    return spreadingFactor;
  }

  public void setSpreadingFactor(int spreadingFactor) {
    this.spreadingFactor = spreadingFactor;
  }

  public int getSubChannelNo() {
    return subChannelNo;
  }

  public void setSubChannelNo(int subChannelNo) {
    this.subChannelNo = subChannelNo;
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
    Map<String, String> values;
//    values.put("crn", name);
//    values.put("administrativeState", administrativeState);
//    values.put("aichPower", aichPower);
//    values.put("aichTransmissionTiming", aichTransmissionTiming);
//    values.put("constantValueCprach", constantValueCprach);
//    values.put("increasedRachCoverageEnabled", increasedRachCoverageEnabled);
//    values.put("maxPreambleCycle", maxPreambleCycle);
//    values.put("nb01Max", nb01Max);
//    values.put("nb01Min", nb01Min);
//    values.put("powerOffsetP0", powerOffsetP0);
//    values.put("powerOffsetPpm", powerOffsetPpm);
//    values.put("preambleRetransMax", preambleRetransMax);
//    values.put("preambleSignatures", preambleSignatures);
//    values.put("spreadingFactor", spreadingFactor);
//    values.put("subChannelNo", subChannelNo);
//    values.put("userLabel", (userLabel == null ? "" : userLabel));

    values = ModelUtils.createMapProperties(source);

    return values == null ? Collections.emptyMap() : values;
  }

  @Override
  public Patterns getType() {
    return Patterns.AICH_TRANSMISSION_TIMING;
  }

  @Override
  public String toString() {
    return "crn " + name + "\n" +
        "administrativeState " + administrativeState + "\n" +
        "aichPower " + aichPower + "\n" +
        "aichTransmissionTiming " + aichTransmissionTiming + "\n" +
        "constantValueCprach " + constantValueCprach + "\n" +
        "increasedRachCoverageEnabled " + increasedRachCoverageEnabled + "\n" +
        "maxPreambleCycle " + maxPreambleCycle + "\n" +
        "nb01Max " + nb01Max + "\n" +
        "nb01Min " + nb01Min + "\n" +
        "powerOffsetP0 " + powerOffsetP0 + "\n" +
        "powerOffsetPpm " + powerOffsetPpm + "\n" +
        "preambleRetransMax " + preambleRetransMax + "\n" +
        "preambleSignatures " + preambleSignatures + "\n" +
        "scramblingCodeWordNo " + scramblingCodeWordNo + "\n" +
        "spreadingFactor " + spreadingFactor + "\n" +
        "subChannelNo " + subChannelNo + "\n" +
        "userLabel " + (userLabel == null ? "" : userLabel) + "\n" +
        "end\n\n";
  }


}
