package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class HsPathLossTreshold implements CreationCommand {

  /**
   * name : RncFunction=1,UtranCell=CK0001K,CoverageRelation=CK0001G
   * coverageIndicator : 1
   * hsIflsDownswitch : 1
   * hsPathlossThreshold : 0
   * relationCapability : {"dchLoadSharing":1,"hsCellSelection":0,"hsLoadSharing":1,"powerSave":0}
   * utranCellRef : {"UtranCell":"CK0001G"}
   */

  private String name;
  private int coverageIndicator;
  private int hsIflsDownswitch;
  private int hsPathlossThreshold;
  private RelationCapabilityBean relationCapability;
  private UtranCellRefBean utranCellRef;
  private String[] source;

  public HsPathLossTreshold() {
  }

  public HsPathLossTreshold(String[] source) {
    this.source = source;
    name = source[0];
    coverageIndicator = FieldExtractor.getFieldIntPrimitive(source, "coverageIndicator");
    hsIflsDownswitch = FieldExtractor.getFieldIntPrimitive(source, "hsIflsDownswitch");
    hsPathlossThreshold = FieldExtractor.getFieldIntPrimitive(source, "hsPathlossThreshold");
    relationCapability = (RelationCapabilityBean)FieldExtractor.getFieldObject(RelationCapabilityBean.class, source, "relationCapability");
    utranCellRef = (UtranCellRefBean)FieldExtractor.getFieldObject(UtranCellRefBean.class, source, "utranCellRef");

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCoverageIndicator() {
    return coverageIndicator;
  }

  public void setCoverageIndicator(int coverageIndicator) {
    this.coverageIndicator = coverageIndicator;
  }

  public int getHsIflsDownswitch() {
    return hsIflsDownswitch;
  }

  public void setHsIflsDownswitch(int hsIflsDownswitch) {
    this.hsIflsDownswitch = hsIflsDownswitch;
  }

  public int getHsPathlossThreshold() {
    return hsPathlossThreshold;
  }

  public void setHsPathlossThreshold(int hsPathlossThreshold) {
    this.hsPathlossThreshold = hsPathlossThreshold;
  }

  public RelationCapabilityBean getRelationCapability() {
    return relationCapability;
  }

  public void setRelationCapability(RelationCapabilityBean relationCapability) {
    this.relationCapability = relationCapability;
  }

  public UtranCellRefBean getUtranCellRef() {
    return utranCellRef;
  }

  public void setUtranCellRef(UtranCellRefBean utranCellRef) {
    this.utranCellRef = utranCellRef;
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
    return Patterns.HS_PATH_LOSS_TRESHOLD;
  }

  public static class RelationCapabilityBean {
    /**
     * dchLoadSharing : 1
     * hsCellSelection : 0
     * hsLoadSharing : 1
     * powerSave : 0
     */

    private int dchLoadSharing;
    private int hsCellSelection;
    private int hsLoadSharing;
    private int powerSave;

    public RelationCapabilityBean() {
    }

    public RelationCapabilityBean(String src) {
      final String[] source = src.split(",");
      dchLoadSharing = source[0].split("=").length == 2 ? Integer.parseInt(source[0].split("=")[1]) : null;
      hsCellSelection = source[1].split("=").length == 2 ? Integer.parseInt(source[1].split("=")[1]) : null;
      hsLoadSharing = source[2].split("=").length == 2 ? Integer.parseInt(source[2].split("=")[1]) : null;
      powerSave = source[3].split("=").length == 2 ? Integer.parseInt(source[3].split("=")[1]) : null;
    }

    public int getDchLoadSharing() {
      return dchLoadSharing;
    }

    public void setDchLoadSharing(int dchLoadSharing) {
      this.dchLoadSharing = dchLoadSharing;
    }

    public int getHsCellSelection() {
      return hsCellSelection;
    }

    public void setHsCellSelection(int hsCellSelection) {
      this.hsCellSelection = hsCellSelection;
    }

    public int getHsLoadSharing() {
      return hsLoadSharing;
    }

    public void setHsLoadSharing(int hsLoadSharing) {
      this.hsLoadSharing = hsLoadSharing;
    }

    public int getPowerSave() {
      return powerSave;
    }

    public void setPowerSave(int powerSave) {
      this.powerSave = powerSave;
    }

    @Override
    public String toString() {
      return "dchLoadSharing=" + dchLoadSharing + ",hsCellSelection=" + hsCellSelection + ",hsLoadSharing=" + hsLoadSharing + ",powerSave=" + powerSave;
    }
  }

  public static class UtranCellRefBean {
    /**
     * UtranCell : CK0001G
     */

    private String UtranCell;

    public UtranCellRefBean() {
    }

    public UtranCellRefBean(String source) {
      UtranCell = source;
    }

    public String getUtranCell() {
      return UtranCell;
    }

    public void setUtranCell(String UtranCell) {
      this.UtranCell = UtranCell;
    }

    @Override
    public String toString() {
      return "UtranCell=" + UtranCell;
    }
  }

  @Override
  public String toString() {
    return "crn " + name + "\n" +
        "coverageIndicator " + coverageIndicator + "\n" +
        "hsIflsDownswitch " + hsIflsDownswitch + "\n" +
        "hsPathlossThreshold " + hsPathlossThreshold + "\n" +
        "relationCapability " + relationCapability + "\n" +
        "utranCellRef " + utranCellRef + "\n" +
        "end\n\n";
  }
}
