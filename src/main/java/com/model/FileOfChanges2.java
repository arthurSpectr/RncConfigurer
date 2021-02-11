package com.model;

import java.util.ArrayList;
import java.util.List;

public class FileOfChanges2 {

  private List<String> headers = new ArrayList<>();
  private List<List<Object>> values = new ArrayList<>();

  public FileOfChanges2() {
  }

  public FileOfChanges2(List<String> headers, List<List<Object>> values) {
    this.headers = headers;
    this.values = values;
  }

  public List<String> getHeaders() {
    return headers;
  }

  public void setHeaders(List<String> headers) {
    this.headers = headers;
  }

  public List<List<Object>> getValues() {
    return values;
  }

  public void setValues(List<List<Object>> values) {
    this.values = values;
  }


}
