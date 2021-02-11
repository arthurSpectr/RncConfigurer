package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class ExternalEutranCellRef implements CreationCommand {


  /**
   * name : RncFunction=1,UtranCell=CK0001K,EutranFreqRelation=1300,EutranCellRelation=1
   * coSited : 1
   * externalEutranCellRef : {"EutraNetwork":"Astelit","EutranFrequency":1300,"ExternalEutranCell":"CK0001L31"}
   * userLabel :
   */

  private String name;
  private int coSited;
  private ExternalEutranCellRefBean externalEutranCellRef;
  private String userLabel;
  private String[] source;

  public ExternalEutranCellRef() {
  }

  public ExternalEutranCellRef(String[] source) {
    this.source = source;
    name = source[0];
    coSited = FieldExtractor.getFieldIntPrimitive(source, "coSited");
    externalEutranCellRef = (ExternalEutranCellRefBean)FieldExtractor.getFieldObject(ExternalEutranCellRefBean.class, source, "externalEutranCellRef");
    userLabel = FieldExtractor.getFieldString(source, "coSited");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCoSited() {
    return coSited;
  }

  public void setCoSited(int coSited) {
    this.coSited = coSited;
  }

  public ExternalEutranCellRefBean getExternalEutranCellRef() {
    return externalEutranCellRef;
  }

  public void setExternalEutranCellRef(ExternalEutranCellRefBean externalEutranCellRef) {
    this.externalEutranCellRef = externalEutranCellRef;
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
    return Patterns.EXTERNAL_EUTRAN_CELL_REF;
  }

  public static class ExternalEutranCellRefBean {
    /**
     * EutraNetwork : Astelit
     * EutranFrequency : 1300
     * ExternalEutranCell : CK0001L31
     */

    private String EutraNetwork;
    private int EutranFrequency;
    private String ExternalEutranCell;

    public ExternalEutranCellRefBean() {
    }

    public ExternalEutranCellRefBean(String[] source) {
      EutraNetwork = source[0].split("=").length == 2 ? source[0].split("=")[1] : null;
      EutranFrequency = source[1].split("=").length == 2 ? Integer.parseInt(source[1].split("=")[1]) : null;
      ExternalEutranCell = source[2].split("=").length == 2 ? source[2].split("=")[1] : null;
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

    public String getExternalEutranCell() {
      return ExternalEutranCell;
    }

    public void setExternalEutranCell(String ExternalEutranCell) {
      this.ExternalEutranCell = ExternalEutranCell;
    }

    @Override
    public String toString() {
      return "EutraNetwork=" + EutraNetwork + ",EutranFrequency=" + EutranFrequency + ",ExternalEutranCell=" + ExternalEutranCell;
    }
  }

  @Override
  public String toString() {
    return "crn " + name + "\n" +
        "coSited " + coSited + "\n" +
        "externalEutranCellRef " + externalEutranCellRef + "\n" +
        "userLabel " + (userLabel == null ? "" : userLabel) + "\n" +
        "end\n\n";
  }
}
