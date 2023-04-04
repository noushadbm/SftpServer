package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.subsystem.sftp.SftpSubsystemFactory;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws InterruptedException, IOException {
        logger.info("starting");
        SshServer sshServer = SshServer.setUpDefaultServer();
        sshServer.setHost("127.0.0.1");
        sshServer.setPort(22);
        sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
        sshServer.setPasswordAuthenticator((username, password, session) -> {
            return true;
        });

        sshServer.setSubsystemFactories(Arrays.asList(new SftpSubsystemFactory()));

        sshServer.start();
        System.out.println("Hello world!");
        while (true) {
            Thread.sleep(10000);
        }
    }
}