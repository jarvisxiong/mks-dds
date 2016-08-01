package com.mks.network.ftp;

import java.io.File;
import java.io.IOException;

 
public interface FTPClient {
    
    /**
     * Download file
     * 
     * @param remotePath
     * @param remoteFileName
     * @return
     * @throws IOException
     */
    File download(String remotePath, String remoteFileName) throws IOException;

    /**
     * 创建一个目录，如果目标目录已经存在则不会对其有任何改动
     * 
     * @param path
     * @throws IOException
     */
    void mkdir(String path) throws IOException;

    /**
     * 指定目录下文件是否存在
     * 
     * @param path
     * @param fileName
     * @return
     * @throws IOException
     */
    @Deprecated
    boolean exists(String path, String fileName) throws IOException;

    /**
     * 上传文件到指定目录下。
     * 
     * @param path 目标目录，将文件保存在此目录下
     * @param file 上传的文件
     * @param fileName 目标文件名称，如果为<code>null</code>的话，那么则与本地上传的文件名称保持一致。
     * @throws IOException
     */
    void upload(String path, File file, String fileName) throws IOException;

    /**
     * 测试是否可正常访问
     */
    void test() throws IOException;

    /**
     * 断开连接
     */
    void disconnect();

    /**
     * 设置是否自动关闭连接
     * 
     * @param autoDisconnect
     */
    void setAutoDisconnect(boolean autoDisconnect);
}
