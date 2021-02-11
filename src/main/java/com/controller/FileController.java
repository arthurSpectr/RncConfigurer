package com.controller;

//import com.dao.RncListRepository;
//import com.dao.RncRepository;

import com.exceptions.NotFoundRncException;
import com.model.FileOfChanges2;
import com.responses.Response;
import com.exceptions.UploadFileException;
import com.responses.StringResponse;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://10.1.34.94:80", "http://localhost:4200"}, exposedHeaders = "Content-Disposition")
//@CrossOrigin(origins = "http://10.1.34.94:80", exposedHeaders = "Content-Disposition")
@RestController
@RequestMapping("/api/v1/rnc")
public class FileController {

  private final Logger LOG = LoggerFactory.getLogger(FileController.class);
  private static String lastFileName;

  private FileStorageService fileStorageService;
  private FileParsingService fileParsingService;
  private FileService fileService;
  private ValidationService validateFileOfChanges;

  @Autowired
  public FileController(
    FileStorageService fileStorageService,
    FileParsingService parseCsvFile,
    FileService fileService,
    ValidationService validateFileOfChanges
  ) {
    this.fileStorageService = fileStorageService;
    this.fileParsingService = parseCsvFile;
    this.fileService = fileService;
    this.validateFileOfChanges = validateFileOfChanges;
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

  @GetMapping("recreate-file-of-changes")
  public Object getLastFileChanges(HttpSession session) {
    String filename = (String)session.getAttribute("filename");
    if(null == filename) return new FileOfChanges2();

    FileOfChanges2 fileOfChanges2 = null;

    try {
      fileOfChanges2 = fileParsingService.loadFileOfChanges(filename);
    } catch (NotFoundRncException e) {
      return e.getNotFoundedExceptions().stream().map(Throwable::getMessage).collect(Collectors.toList());
    }

    return fileOfChanges2;

  }

  @GetMapping(value = "get-file-of-changes/{id}")
  public Object getFileOfChanges(@PathVariable String id, HttpServletRequest request, HttpSession session) {
    request.getSession().setAttribute("filename", id);

    FileOfChanges2 fileOfChanges2 = null;

    try {
      fileOfChanges2 = fileParsingService.loadFileOfChanges(id);
    } catch (NotFoundRncException e) {
      return new StringResponse(e.getMessage() + " — " + e.getNotFoundedExceptions().stream().map(Throwable::getMessage).collect(Collectors.joining(", ")));
    }

    return fileOfChanges2;
  }

  @PostMapping("validate-file-of-changes")
  public FileOfChanges2 validateFileOfChanges(@RequestBody FileOfChanges2 fileOfChanges2) {
    return validateFileOfChanges.validateFileOfChanges(fileOfChanges2);
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
