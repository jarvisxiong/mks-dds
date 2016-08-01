package com.mks.spring.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.google.common.collect.Maps;
import com.mks.utils.BaseLifeCycleSupport;
import com.mks.utils.PathUtils;


/**
 * 文件配置管理 
 */
public final class FileConfigurator extends BaseLifeCycleSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileConfigurator.class);
    private static final Map<String, byte[]> CONFS = Maps.newLinkedHashMap();
    private final Resource confDirectory;

    public FileConfigurator(Resource confDirectory) {
        this.confDirectory = confDirectory;
    }

    @Override
    protected void doInit() {
        if (null != confDirectory) {
            try {
                File dir = confDirectory.getFile();
                if (!dir.isDirectory()) {
                    LOGGER.warn("File [{}] does not directory path.", dir.getPath());
                    return;
                }
                
                Collection<File> files = FileUtils.listFiles(dir, null, true);
                for (File file : files) {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("Load config file: {}", file.getPath());
                    }
                    String key = file.getAbsolutePath();
                    FileInputStream is = new FileInputStream(file);
                    byte[] buffer = new byte[is.available()];
                    IOUtils.readFully(is, buffer);
                    CONFS.put(convertKey(key), buffer);
                }
            }
            catch (IOException e) {
                LOGGER.error("init:", e);
            }
        }
    }

    @Override
    protected void doDestroy() {
    }
    
    private static String convertKey(String filePath) {
        filePath = PathUtils.replacePathSeparator(filePath);
        return StringUtils.replace(filePath, "/", "_");
    }
    
    public static byte[] getFile(String path) {
        path = convertKey(path);
        for (Entry<String, byte[]> entry : CONFS.entrySet()) {
            String key = entry.getKey();
            if (StringUtils.endsWith(key, path)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
