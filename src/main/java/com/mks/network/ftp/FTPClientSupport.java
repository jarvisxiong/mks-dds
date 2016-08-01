package com.mks.network.ftp;
 
public abstract class FTPClientSupport {

    public static final int DEFAULT_PORT = 21;
    public static final String DEFAULT_CONTROL_ENCODING = "GBK";
    protected static final int BUFF_SIZE = 2048;

    protected static final int RETRY_NUM = 3;
    protected String hostname;
    protected int port = DEFAULT_PORT;
    protected String username;
    protected String password;
    protected boolean autoDisconnect = true;
    protected String controlEncoding = DEFAULT_CONTROL_ENCODING;

    public FTPClientSupport(String hostname) {
        this.hostname = hostname;
    }

    public FTPClientSupport(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public FTPClientSupport(String hostname, int port, String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAutoDisconnect() {
        return autoDisconnect;
    }

    public void setAutoDisconnect(boolean autoDisconnect) {
        this.autoDisconnect = autoDisconnect;
    }

    public String getControlEncoding() {
        return controlEncoding;
    }

    public void setControlEncoding(String controlEncoding) {
        this.controlEncoding = controlEncoding;
    }
}
