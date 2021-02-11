package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.modelsForCreationCommands.util.FieldExtractor;
import com.model.modelsForCreationCommands.util.ModelUtils;
import com.model.Patterns;

import java.util.Map;

public class EdchDataFrameDelayThreshold implements CreationCommand {

  /**
   * name : RncFunction=1,IubLink=CK0001,IubEdch=1
   * edchDataFrameDelayThreshold : 60
   * userLabel :
   */

  private String name;
  private int edchDataFrameDelayThreshold;
  private String userLabel;
  private String[] source;

  public EdchDataFrameDelayThreshold() {
  }

  public EdchDataFrameDelayThreshold(String[] source) {
    this.source = source;
    name = source[0];
    edchDataFrameDelayThreshold = FieldExtractor.getFieldIntPrimitive(source, "edchDataFrameDelayThreshold");
    userLabel = FieldExtractor.getFieldString(source, "userLabel");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getEdchDataFrameDelayThreshold() {
    return edchDataFrameDelayThreshold;
  }

  public void setEdchDataFrameDelayThreshold(int edchDataFrameDelayThreshold) {
    this.edchDataFrameDelayThreshold = edchDataFrameDelayThreshold;
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
    return Patterns.EDCH_DATA_FRAME_DELAY_THRESHOLD;
  }

  @Override
  public String toString() {
    return "crn " + name + "\n" +
        "edchDataFrameDelayThreshold " + edchDataFrameDelayThreshold + "\n" +
        "userLabel " + (userLabel == null ? "" : userLabel) + "\n" +
        "end\n\n";
  }
}
