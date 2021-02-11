package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class Sac implements CreationCommand {

  /**
   * name : RncFunction=1,LocationArea=12710,ServiceArea=30563
   * sac : 30563
   * userLabel :
   */

  private String name;
  private String locationArea;
  private String serviceArea;
  private int sac;
  private String userLabel;
  private String[] source;

  public Sac() {
  }

  public Sac(String[] source) {
    this.source = source;
    name = source[0];
    locationArea = source[0].split(",|=")[3];
    serviceArea = source[0].split(",|=")[5];
    sac = FieldExtractor.getFieldIntPrimitive(source, "sac");
    userLabel = FieldExtractor.getFieldString(source, "userLabel");
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSac() {
    return sac;
  }

  public void setSac(int sac) {
    this.sac = sac;
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

  public String getLocationArea() {
    return locationArea;
  }

  public void setLocationArea(String locationArea) {
    this.locationArea = locationArea;
  }

  public String getServiceArea() {
    return serviceArea;
  }

  public void setServiceArea(String serviceArea) {
    this.serviceArea = serviceArea;
  }

  @Override
  public Patterns getType() {
    return Patterns.SAC;
  }

  @Override
  public String toString() {
    return "crn " + name.split(",")[0] + ",LocationArea=" + locationArea + ",ServiceArea=" + sac + "\n" +
        "sac " + sac + "\n" +
        "userLabel " + (userLabel == null ? "" : userLabel) + "\n" +
        "end\n\n";
  }
}
