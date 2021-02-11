package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class ExternalGsmCellRef implements CreationCommand {


  /**
   * name : RncFunction=1,UtranCell=CK0029J,GsmRelation=CK00013
   * externalGsmCellRef : {"ExternalGsmNetwork":"Astelit","ExternalGsmCell":"CK00013"}
   * mobilityRelationType : 0
   * qOffset1sn : 7
   * selectionPriority : 1
   */

  private String name;
  private ExternalGsmCellRefBean externalGsmCellRef;
  private int mobilityRelationType;
  private int qOffset1sn;
  private int selectionPriority;
  private String[] source;

  public ExternalGsmCellRef() {
  }

  public ExternalGsmCellRef(String[] source) {
    this.source = source;
    name =source[0];
    externalGsmCellRef = (ExternalGsmCellRefBean)FieldExtractor.getFieldObject(ExternalGsmCellRefBean.class, source, "externalGsmCellRef");
    mobilityRelationType = FieldExtractor.getFieldIntPrimitive(source, "mobilityRelationType");
    qOffset1sn = FieldExtractor.getFieldIntPrimitive(source, "qOffset1sn");
    selectionPriority = FieldExtractor.getFieldIntPrimitive(source, "selectionPriority");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ExternalGsmCellRefBean getExternalGsmCellRef() {
    return externalGsmCellRef;
  }

  public void setExternalGsmCellRef(ExternalGsmCellRefBean externalGsmCellRef) {
    this.externalGsmCellRef = externalGsmCellRef;
  }

  public int getMobilityRelationType() {
    return mobilityRelationType;
  }

  public void setMobilityRelationType(int mobilityRelationType) {
    this.mobilityRelationType = mobilityRelationType;
  }

  public int getQOffset1sn() {
    return qOffset1sn;
  }

  public void setQOffset1sn(int qOffset1sn) {
    this.qOffset1sn = qOffset1sn;
  }

  public int getSelectionPriority() {
    return selectionPriority;
  }

  public void setSelectionPriority(int selectionPriority) {
    this.selectionPriority = selectionPriority;
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
    return Patterns.EXTERNAL_GSM_CELL_REF;
  }

  public static class ExternalGsmCellRefBean {
    /**
     * ExternalGsmNetwork : Astelit
     * ExternalGsmCell : CK00013
     */

    private String ExternalGsmNetwork;
    private String ExternalGsmCell;

    public ExternalGsmCellRefBean() {
    }

    public ExternalGsmCellRefBean(String src) {
      final String[] source = src.split(",");
      if(source.length != 0) {
        ExternalGsmNetwork = source[0].split("=").length == 2 ? source[0].split("=")[1] : null;
        ExternalGsmCell = source[1].split("=").length == 2 ? source[1].split("=")[1] : null;
      }
    }

    public String getExternalGsmNetwork() {
      return ExternalGsmNetwork;
    }

    public void setExternalGsmNetwork(String ExternalGsmNetwork) {
      this.ExternalGsmNetwork = ExternalGsmNetwork;
    }

    public String getExternalGsmCell() {
      return ExternalGsmCell;
    }

    public void setExternalGsmCell(String ExternalGsmCell) {
      this.ExternalGsmCell = ExternalGsmCell;
    }

    @Override
    public String toString() {
      return "ExternalGsmNetwork=" + ExternalGsmNetwork + ",ExternalGsmCell=" + (ExternalGsmCell == null ? "" : ExternalGsmCell);
    }
  }

  @Override
  public String toString() {
    return "crn " + name + "\n" +
        "externalGsmCellRef " + externalGsmCellRef + "\n" +
        "mobilityRelationType " + mobilityRelationType + "\n" +
        "qOffset1sn " + qOffset1sn + "\n" +
        "selectionPriority " + selectionPriority + "\n" +
        "end\n\n";
  }
}
