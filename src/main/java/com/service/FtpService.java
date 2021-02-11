package com.service;


import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FtpService {

    private static Logger LOG = LoggerFactory.getLogger(FtpService.class);

    public static final String TEST_PATH_TO_FILE_FROM_SERVER = "/ericsson/log/amos/moshell_logfiles/dpleskac/logs_moshell/undo/undo_KITR1_200805-154825.mos";
    public static final String TEST_PATH_TO_FILE_ON_MY_PC = "C:\\Users\\atian\\Desktop\\file.mos";

//    public static void main(String[] args) {
//
//        try {
//            connectViaFtp(TEST_PATH_TO_FILE_FROM_SERVER, TEST_PATH_TO_FILE_ON_MY_PC);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public void connectViaFtp(String fileFromServer, String pathToFileOnMyMyPc) {
        String knownHostsFilename = "C:\\Users\\atian\\.ssh\\known_hosts";

        String user = "dpleskac";            //CHANGE ME
        String host = "10.4.164.21"; //CHANGE ME
        String passwd = "Oles73Oles73";
        int port = 22;

        JSch jsch = new JSch();
        ChannelSftp sftpChannel = null;
        Session session = null;

        try {
            jsch.setKnownHosts(knownHostsFilename);
            session = jsch.getSession(user, host, port);
            session.setPassword(passwd);

            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftpChannel = (ChannelSftp) channel;

            sftpChannel.get(fileFromServer, pathToFileOnMyMyPc);
        } catch (JSchException e) {
            LOG.error("connect via sftp or to rnc host failed", e);
        } catch (SftpException e) {
            LOG.error("sftp connection exception when download", e);
        } finally {
            if(sftpChannel != null) {
                sftpChannel.exit();
            }

            if(session != null) {
                session.disconnect();
            }
        }

    }
}
