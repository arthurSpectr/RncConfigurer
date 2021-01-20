package com.service;

import com.Constants;
import com.controller.FileController;
import com.model.RncModification;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static com.Constants.RAW_RNC_CREATION_COMMANDS;

@Service
public class FileService {

  private static final Logger LOG = LoggerFactory.getLogger(FileService.class);

  private FileStorageService fileStorageService;
  private FileParsingService parseCsvFileService;
  private CreationCommandsOperationService creationCommandsOperationService;

  private SshService sshService;
  private FtpService ftpService;

  public FileService(FileStorageService fileStorageService, FileParsingService parseCsvFileService,
                     CreationCommandsOperationService creationCommandsOperationService,
                     SshService sshService, FtpService ftpService) {
    this.fileStorageService = fileStorageService;
    this.parseCsvFileService = parseCsvFileService;
    this.creationCommandsOperationService = creationCommandsOperationService;
    this.sshService = sshService;
    this.ftpService = ftpService;
  }

  public Resource getFileFromRemote() {

    String pathToCreationCommands = "filesOfChanges/fileOfChanges.csv";
    String pathToCreationCommandsFile1 = "rawRncCreationCommands/file.mos";
    String pathToCreationCommandsFile2 = "rawRncCreationCommands/file-del.mos";
    long start = System.nanoTime();

    final List<RncModification> filesChanges = parseCsvFileService.getAllFileChanges(pathToCreationCommands);

    LOG.info("in getFileFromRemote");

    LOG.info("is directory in path - {} - {}", RAW_RNC_CREATION_COMMANDS, new File(RAW_RNC_CREATION_COMMANDS).isDirectory());

    if (new File(RAW_RNC_CREATION_COMMANDS).isDirectory()) {
      try {
        FileUtils.cleanDirectory(new File(RAW_RNC_CREATION_COMMANDS));

      } catch (IOException e) {
        LOG.error("can not find directory ", e);
      }

      final List<String> strings = sshService.createFilesInRemote(filesChanges);

      LOG.info("after creating files through ssh - {}", strings);

      ftpService.connectViaFtp(strings.get(0), pathToCreationCommandsFile1);
      ftpService.connectViaFtp(strings.get(1), pathToCreationCommandsFile2);

      try {
        org.apache.commons.io.FileUtils.copyDirectoryToDirectory(new File(RAW_RNC_CREATION_COMMANDS), new File(Constants.PREPARED_CREATION_COMMANDS));
      } catch (IOException e) {
        LOG.error("copy files - {} {}, in directory is failed", pathToCreationCommandsFile1, pathToCreationCommandsFile2);
      }

      long end = System.nanoTime();

      creationCommandsOperationService.execute(pathToCreationCommands);

      LOG.debug("time of execution = " + (end - start) + " nanoseconds");
      return fileStorageService.loadUpdatedFilesAsResource();
    }

    // TODO throw exception or status code 5** that means directory not found
    return null;
  }

}
