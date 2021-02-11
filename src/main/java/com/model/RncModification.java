package com.model;

import java.util.List;

public class RncModification {

    private List<FileOfChanges> modifications;

    public RncModification(List<FileOfChanges> modifications) {
        this.modifications = modifications;
    }

    public List<FileOfChanges> getModifications() {
        return modifications;
    }

    public void setModifications(List<FileOfChanges> modifications) {
        this.modifications = modifications;
    }

  @Override
  public String toString() {
    return "RncModification{" +
      "modifications=" + modifications +
      '}';
  }
}
