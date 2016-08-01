package com.mks.network.ftp.server;

import java.net.URL;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mks.utils.BaseLifeCycleSupport;

public class FTPServer extends BaseLifeCycleSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(FTPServer.class);
    private static final int PORT = 21;
    private final String host;
    private int port = PORT;
    
    private FtpServer server;
    private URL properties;
    
    public FTPServer(String host) {
        this(host, PORT);
    }

    public FTPServer(String host, int port) {
        this(host, port, FTPServer.class.getResource("/ftp-server.properties"));
    }
    
    public FTPServer(String host, int port, URL properties) {
        this.host = host;
        this.port = port;
        this.properties = properties;
    }

    @Override
    protected void doInit() {
        try {
            FtpServerFactory factory = new FtpServerFactory();
            PropertiesUserManagerFactory managerFactory = new PropertiesUserManagerFactory();
            
            ListenerFactory listenerFactory = new ListenerFactory();
            listenerFactory.setServerAddress(host);
            listenerFactory.setPort(port);
            factory.addListener("default", listenerFactory.createListener());
            
            if ( null == properties )
                throw new NullPointerException("properties");
            
            managerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
            managerFactory.setUrl(properties);
            factory.setUserManager(managerFactory.createUserManager());
            server = factory.createServer();
            server.start();
            String addr = getAddr();
            LOGGER.info("FTP Server {} has started successfully!", addr);
        }
        catch (Exception e) {
            throw new RuntimeException("FTP Server start failured!", e);
        }
    }

    private String getAddr() {
        return "ftp://" + host + ":" + port;
    }

    @Override
    protected void doDestroy() {
        if (null != server) {
            server.stop();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setServer(FtpServer server) {
        this.server = server;
    }

    public void setProperties(URL properties) {
        this.properties = properties;
    }
}
