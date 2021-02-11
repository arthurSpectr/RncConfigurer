package com.model.modelsForCreationCommands;

import com.model.modelsForCreationCommands.util.CreationCommand;
import com.model.Patterns;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LdLset implements CreationCommand {

  private final String ld;
  List<String> lsLsets;

  public LdLset(String[] source) {
    ld = source[0];
    lsLsets = source.length > 0 ? Arrays.asList(Arrays.copyOfRange(source, 1, source.length)) : null;
  }

  public List<String> getLsLsets() {
    return lsLsets;
  }

  public void setLsLsets(List<String> lsLsets) {
    this.lsLsets = lsLsets;
  }

  public String getLd() {
    return ld;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    if(lsLsets != null && lsLsets.size() > 0) {
      for (String lsLset : lsLsets) {
        sb.append(lsLset + "\n");
      }
    }
    sb.append("\n");

    return ld + "\n" +
        sb.toString();
  }

  @Override
  public CreationCommand getCreationCommand(Patterns pattern, String source) {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public Map<String, String> getValues() {
    Map<String, String> values = new TreeMap<>();
    values.put("ld", ld.replaceAll("ld ", ""));
    StringBuilder sb = new StringBuilder();

    if(lsLsets != null && lsLsets.size() > 0){
      for (String lsLset : lsLsets) {
        sb.append(lsLset + "\n");
      }
      values.put("lset", sb.toString().replaceAll("lset lset RncFunction", "RncFunction"));
    }

    return values;
  }

  @Override
  public Patterns getType() {
    return Patterns.LD_LSET;
  }
}
