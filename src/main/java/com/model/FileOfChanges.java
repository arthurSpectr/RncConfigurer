package com.model;


public class FileOfChanges {

  private String Rehom_Order;
  private String BSC;
  private String Site;
  private String Cell;
  private String LON;
  private String LAT;
  private String LAC;
  private String CI;
  private String New_Bsc;
  private String New_LAC;
  private String New_Ci;
  private String New_Ura;
  private String New_RBSID_1;
  private String New_RBSID_2;

  public FileOfChanges() {
  }

  public FileOfChanges(String rehom_Order, String BSC, String site,
                       String cell, String LON, String LAT, String LAC, String CI,
                       String new_Bsc, String new_LAC, String new_Ci, String new_Ura, String new_RBSID_1, String new_RBSID_2) {
    this.Rehom_Order = rehom_Order;
    this.BSC = BSC;
    this.Site = site;
    this.Cell = cell;
    this.LON = LON;
    this.LAT = LAT;
    this.LAC = LAC;
    this.CI = CI;
    this.New_Bsc = new_Bsc;
    this.New_LAC = new_LAC;
    this.New_Ci = new_Ci;
    this.New_Ura = new_Ura;
    this.New_RBSID_1 = new_RBSID_1;
    this.New_RBSID_2 = new_RBSID_2;
  }

  public FileOfChanges(String[] values) {
    this.Rehom_Order = values[0];
    this.BSC = values[1];
    this.Site = values[2];
    this.Cell = values[3];
    this.LON = values[4];
    this.LAT = values[5];
    this.LAC = values[6];
    this.CI = values[7];
    this.New_Bsc = values[8];
    this.New_LAC = values[9];
    this.New_Ci = values[10];
    this.New_Ura = values[11];
    this.New_RBSID_1 = values[12];
    this.New_RBSID_2 = values[13];
  }

  public FileOfChanges(String entity) {
    String[] split = entity.split(",");
    this.Rehom_Order = split[0];
    this.BSC = split[1];
    this.Site = split[2];
    this.Cell = split[3];
    this.LON = split[4];
    this.LAT = split[5];
    this.LAC = split[6];
    this.CI = split[7];
    this.New_Bsc = split[8];
    this.New_LAC = split[9];
    this.New_Ci = split[10];
    this.New_Ura = split[11];
    this.New_RBSID_1 = split[12];
    this.New_RBSID_2 = split.length > 13 ? split[13] : "";
  }

  public String getRehom_Order() {
    return Rehom_Order;
  }

  public void setRehom_Order(String rehom_Order) {
    Rehom_Order = rehom_Order;
  }

  public String getBSC() {
    return BSC;
  }

  public void setBSC(String BSC) {
    this.BSC = BSC;
  }

  public String getSite() {
    return Site;
  }

  public void setSite(String site) {
    Site = site;
  }

  public String getCell() {
    return Cell;
  }

  public void setCell(String cell) {
    Cell = cell;
  }

  public String getLON() {
    return LON;
  }

  public void setLON(String LON) {
    this.LON = LON;
  }

  public String getLAT() {
    return LAT;
  }

  public void setLAT(String LAT) {
    this.LAT = LAT;
  }

  public String getLAC() {
    return LAC;
  }

  public void setLAC(String LAC) {
    this.LAC = LAC;
  }

  public String getCI() {
    return CI;
  }

  public void setCI(String CI) {
    this.CI = CI;
  }

  public String getNew_Bsc() {
    return New_Bsc;
  }

  public void setNew_Bsc(String new_Bsc) {
    New_Bsc = new_Bsc;
  }

  public String getNew_LAC() {
    return New_LAC;
  }

  public void setNew_LAC(String new_LAC) {
    New_LAC = new_LAC;
  }

  public String getNew_Ci() {
    return New_Ci;
  }

  public void setNew_Ci(String new_Ci) {
    New_Ci = new_Ci;
  }

  public String getNew_Ura() {
    return New_Ura;
  }

  public void setNew_Ura(String new_Ura) {
    New_Ura = new_Ura;
  }

  public String getNew_RBSID_1() {
    return New_RBSID_1;
  }

  public void setNew_RBSID_1(String new_RBSID_1) {
    New_RBSID_1 = new_RBSID_1;
  }

  public String getNew_RBSID_2() {
    return New_RBSID_2;
  }

  public void setNew_RBSID_2(String new_RBSID_2) {
    New_RBSID_2 = new_RBSID_2;
  }

  @Override
  public String toString() {
    return "Rnc{" +
        ", Rehom_Order='" + Rehom_Order + '\'' +
        ", BSC='" + BSC + '\'' +
        ", Site='" + Site + '\'' +
        ", Cell='" + Cell + '\'' +
        ", LON='" + LON + '\'' +
        ", LAT='" + LAT + '\'' +
        ", LAC='" + LAC + '\'' +
        ", CI='" + CI + '\'' +
        ", New_Bsc='" + New_Bsc + '\'' +
        ", New_LAC='" + New_LAC + '\'' +
        ", New_Ci='" + New_Ci + '\'' +
        ", New_Ura='" + New_Ura + '\'' +
        ", New_RSBSD_1='" + New_RBSID_1 + '\'' +
        ", New_RSBSD_2='" + New_RBSID_2 + '\'' +
        '}';
  }
}
