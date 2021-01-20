package com.controller;

//import com.dao.RncListRepository;
//import com.dao.RncRepository;

import com.model.FileOfChanges2;
import com.responses.Response;
import com.responses.UploadFileException;
import com.responses.UploadFileResponse;
import com.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "Content-Disposition")
@RestController
@RequestMapping("/api/v1/rnc")
public class FileController {

  private final Logger LOG = LoggerFactory.getLogger(FileController.class);
  private static String lastFileName;

  private FileStorageService fileStorageService;
  private FileParsingService fileParsingService;
  private FileService fileService;

  @Autowired
  public FileController(
    FileStorageService fileStorageService,
    FileParsingService parseCsvFile,
    FileService fileService
  ) {
    this.fileStorageService = fileStorageService;
    this.fileParsingService = parseCsvFile;
    this.fileService = fileService;
  }

  @PostMapping("/upload")
  public Response uploadRncMaximoTable(@RequestParam("file") MultipartFile file) {
    if(!fileStorageService.validateFile(file)) {
      return new UploadFileException("validation is unsuccessfully");
    }

    String fileName = fileStorageService.storeFile(file);

    FileController.lastFileName = fileName;
    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("fileOfChanges/")
        .path(fileName)
        .toUriString();

    return new UploadFileResponse(fileName, fileDownloadUri,
        file.getContentType(), file.getSize(), Collections.emptyMap());
  }

  @PostMapping(path = "/uploadMultipleFiles")
  public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    return Arrays.stream(files)
        .map(this::uploadRncMaximoTable)
        .collect(Collectors.toList());
  }

  @GetMapping("/downloadFile/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

    Resource resource = fileStorageService.loadFileAsResource(fileName);

    String contentType = null;
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      LOG.info("Could not determine file type.");
    }

    if(contentType == null) {
      contentType = "application/octet-stream";
    }

    LOG.info("i am logger, log of application");

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }


  @GetMapping(value = "/fileNames", produces = "application/json")
  public List<String> getFileNames() {
    List<String> fileNames = Arrays.asList("RncMaximoTable.csv", "oldFileOfChanges/RncMaximoTable1.csv", "RncMaximoTable2.csv");
    return fileNames;
  }

  @GetMapping("/fileMap/{id}")
  public List<Map<String, String>> getFileMapNames(@PathVariable String id) {
    return fileParsingService.readMapCsv(id);
  }

  @GetMapping("get-file-of-changes/{id}")
  public FileOfChanges2 getFileOfChanges(@PathVariable String id) {
    return fileParsingService.createFileOfChanges(id);
  }

  @GetMapping("validate-file-of-changes")
  public FileOfChanges2 validateFileOfChanges() {
    return fileParsingService.validateFileOfChanges();
  }

  @GetMapping(value = "/download/files", produces="application/zip")
  public ResponseEntity<Resource> downloadPreparedCreationCommandsFiles(HttpServletRequest request) throws Exception {

    final Resource resource = fileService.getFileFromRemote();
    LOG.info("from method downloadResultFiles");

    String contentType = null;
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      LOG.info("Could not determine file type.");
    }

    if(contentType == null) {
      contentType = "application/zip";
    }

    return ResponseEntity.ok()
      .contentLength(resource.contentLength())
      .contentType(MediaType.parseMediaType(contentType))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
      .body(resource);
  }

  /*
  // TODO its code is important
  @GetMapping("/modifyFile/{filename}")
  public List<List<Map<String, String>>> preformModification(@PathVariable("filename") String fileOfChanges) {

    System.out.println(fileOfChanges);

    List<List<Map<String, String>>> listChangesAndListResults = new ArrayList<>();

    parser.execute(fileOfChanges);

    for (CreationCommand creationCommand : creationCommands) {
      valuesAfter.add(creationCommand.getValues());
    }

    for (CreationCommand creationCommand : creationCommandsBefore) {
      valuesBefore.add(creationCommand.getValues());
    }

    listChangesAndListResults.add(valuesBefore);
    listChangesAndListResults.add(valuesAfter);

    return listChangesAndListResults;
  }
   */
}
