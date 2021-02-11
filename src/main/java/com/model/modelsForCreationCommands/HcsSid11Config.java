package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class HcsSid11Config implements CreationCommand {


  /**
   * name : RncFunction=1,UtranCell=CK0108I,UtranRelation=CK0001K
   * hcsSib11Config : {"hcsPrio":0,"qHcs":0,"penaltyTime":0,"temporaryOffset1":0,"temporaryOffset2":0}
   * loadSharingCandidate : 0
   * mobilityRelationType : 0
   * qOffset1sn : 0
   * qOffset2sn : 0
   * selectionPriority : 1
   * utranCellRef : {"UtranCell":"CK0001K"}
   */

  private String name;
  private HcsSib11ConfigBean hcsSib11Config;
  private int loadSharingCandidate;
  private int mobilityRelationType;
  private int qOffset1sn;
  private int qOffset2sn;
  private int selectionPriority;
  private UtranCellRefBean utranCellRef;
  private String[] source;

  public HcsSid11Config() {
  }

  public HcsSid11Config(String[] source) {
    this.source = source;
    name = source[0];
    hcsSib11Config = (HcsSib11ConfigBean)FieldExtractor.getFieldObject(HcsSib11ConfigBean.class, source, "hcsSib11Config");
    loadSharingCandidate = FieldExtractor.getFieldIntPrimitive(source, "loadSharingCandidate");
    mobilityRelationType = FieldExtractor.getFieldIntPrimitive(source, "mobilityRelationType");
    qOffset1sn = FieldExtractor.getFieldIntPrimitive(source, "qOffset1sn");
    qOffset2sn = FieldExtractor.getFieldIntPrimitive(source, "qOffset2sn");
    selectionPriority = FieldExtractor.getFieldIntPrimitive(source, "selectionPriority");
    utranCellRef = (UtranCellRefBean)FieldExtractor.getFieldObject(UtranCellRefBean.class, source, "utranCellRef");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public HcsSib11ConfigBean getHcsSib11Config() {
    return hcsSib11Config;
  }

  public void setHcsSib11Config(HcsSib11ConfigBean hcsSib11Config) {
    this.hcsSib11Config = hcsSib11Config;
  }

  public int getLoadSharingCandidate() {
    return loadSharingCandidate;
  }

  public void setLoadSharingCandidate(int loadSharingCandidate) {
    this.loadSharingCandidate = loadSharingCandidate;
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

  public int getQOffset2sn() {
    return qOffset2sn;
  }

  public void setQOffset2sn(int qOffset2sn) {
    this.qOffset2sn = qOffset2sn;
  }

  public int getSelectionPriority() {
    return selectionPriority;
  }

  public void setSelectionPriority(int selectionPriority) {
    this.selectionPriority = selectionPriority;
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
    return Patterns.HCS_SIB_11_CONFIG;
  }

  public static class HcsSib11ConfigBean {
    /**
     * hcsPrio : 0
     * qHcs : 0
     * penaltyTime : 0
     * temporaryOffset1 : 0
     * temporaryOffset2 : 0
     */

    private int hcsPrio;
    private int qHcs;
    private int penaltyTime;
    private int temporaryOffset1;
    private int temporaryOffset2;
    private String allRow;

    public HcsSib11ConfigBean() {
    }

    public HcsSib11ConfigBean(String source) {
//      hcsPrio = source[0].split("=").length == 2 ? Integer.parseInt(source[0].split("=")[1]) : null;
//      qHcs = source[0].split("=").length == 2 ? Integer.parseInt(source[1].split("=")[1]) : null;
//      penaltyTime = source[0].split("=").length == 2 ? Integer.parseInt(source[2].split("=")[1]) : null;
//      temporaryOffset1 = source[0].split("=").length == 2 ? Integer.parseInt(source[3].split("=")[1]) : null;
//      temporaryOffset2 = source[0].split("=").length == 2 ? Integer.parseInt(source[4].split("=")[1]) : null;
      allRow = source;
    }

    public int getHcsPrio() {
      return hcsPrio;
    }

    public void setHcsPrio(int hcsPrio) {
      this.hcsPrio = hcsPrio;
    }

    public int getQHcs() {
      return qHcs;
    }

    public void setQHcs(int qHcs) {
      this.qHcs = qHcs;
    }

    public int getPenaltyTime() {
      return penaltyTime;
    }

    public void setPenaltyTime(int penaltyTime) {
      this.penaltyTime = penaltyTime;
    }

    public int getTemporaryOffset1() {
      return temporaryOffset1;
    }

    public void setTemporaryOffset1(int temporaryOffset1) {
      this.temporaryOffset1 = temporaryOffset1;
    }

    public int getTemporaryOffset2() {
      return temporaryOffset2;
    }

    public void setTemporaryOffset2(int temporaryOffset2) {
      this.temporaryOffset2 = temporaryOffset2;
    }

    @Override
    public String toString() {
      return allRow;
    }
  }

  public static class UtranCellRefBean {
    /**
     * UtranCell : CK0001K
     */

    private String prefix;
    private String UtranCell;

    public UtranCellRefBean() {
    }

    public UtranCellRefBean(String src) {
      final String[] source = src.split(",");
      if(source[0].contains("IurLink")) {
        prefix = "IurLink=";
        UtranCell = source[0].split("=")[1] + "," + source[1];
      } else {
        prefix = "UtranCell=";
        UtranCell = source[1];
      }

    }

    public String getUtranCell() {
      return UtranCell;
    }

    public void setUtranCell(String UtranCell) {
      this.UtranCell = UtranCell;
    }

    @Override
    public String toString() {
      return prefix + UtranCell;
    }
  }

  @Override
  public String toString() {
    return "crn " + name + "\n" +
        hcsSib11Config + "\n" +
        "loadSharingCandidate " + loadSharingCandidate + "\n" +
        "mobilityRelationType " + mobilityRelationType + "\n" +
        "qOffset1sn " + qOffset1sn + "\n" +
        "qOffset2sn " + qOffset2sn + "\n" +
        "selectionPriority " + selectionPriority + "\n" +
        "utranCellRef " + utranCellRef + "\n" +
        "end\n\n";
  }
}
