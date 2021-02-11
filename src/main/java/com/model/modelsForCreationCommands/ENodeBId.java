package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class ENodeBId implements CreationCommand {


  /**
   * name : RncFunction=1,EutraNetwork=Astelit,EutranFrequency=1300,ExternalEutranCell=CK0001L31
   * cellId : 31
   * eNodeBId : 940001
   * physicalCellIdentity : -1
   * tac : 12710
   * userLabel :
   */

  private String name;
  private int cellId;
  private int eNodeBId;
  private int physicalCellIdentity;
  private int tac;
  private String userLabel;
  private String[] source;

  public ENodeBId() {
  }

  public ENodeBId(String[] values) {
    this.source = values;
    name = values[0];
    cellId = FieldExtractor.getFieldIntPrimitive(source, "cellId");
    eNodeBId = FieldExtractor.getFieldIntPrimitive(source, "eNodeBId");
    physicalCellIdentity = FieldExtractor.getFieldIntPrimitive(source, "physicalCellIdentity");
    tac = FieldExtractor.getFieldIntPrimitive(source, "tac");
    userLabel = FieldExtractor.getFieldString(source, "userLabel");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCellId() {
    return cellId;
  }

  public void setCellId(int cellId) {
    this.cellId = cellId;
  }

  public int getENodeBId() {
    return eNodeBId;
  }

  public void setENodeBId(int eNodeBId) {
    this.eNodeBId = eNodeBId;
  }

  public int getPhysicalCellIdentity() {
    return physicalCellIdentity;
  }

  public void setPhysicalCellIdentity(int physicalCellIdentity) {
    this.physicalCellIdentity = physicalCellIdentity;
  }

  public int getTac() {
    return tac;
  }

  public void setTac(int tac) {
    this.tac = tac;
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
    return Patterns.E_NODEB_ID;
  }

  @Override
  public String toString() {
    return "crn " + name + "\n" +
        "cellId " + cellId + "\n" +
        "eNodeBId " + eNodeBId + "\n" +
        "physicalCellIdentity " + physicalCellIdentity + "\n" +
        "tac " + tac + "\n" +
        "userLabel " + (userLabel == null ? "" : userLabel) + "\n" +
        "end\n\n";
  }
}
