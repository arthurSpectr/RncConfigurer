package com.responses;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UploadFileResponse implements Response{
  private String fileName;
  private String fileDownloadUri;
  private String fileType;
  private long size;
  private boolean status;

  public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, Map<String,List<String>> validation) {
    this.fileName = fileName;
    this.fileDownloadUri = fileDownloadUri;
    this.fileType = fileType;
    this.size = size;
    this.status = true;
  }


}
