package com.mks.zookeeper.addrs.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.mks.utils.PathUtils;
import com.mks.zookeeper.addrs.AddressReader;
import com.mks.zookeeper.addrs.ReaderException;

/**
 * 从文件中读取地址
 * 
 * <pre>
 * e.g.
 * 
 * 第一种方式：指定路径（路径必须以file:开头）
 * ----- Code -----
 * AddressReader reader = new FileAddressReader("file:/opt/bigmouth/zkaddrs.cfg");
 * System.out.println(reader.read());
 * 
 * ----- Result -----
 * "192.168.1.100:2181,192.168.1.101:2181,192.168.1.102:2181"
 * 
 * 第二种方式：默认路径（"%USER_HOME%/zookeeper-address"）
 * ----- Code -----
 * AddressReader reader = new FileAddressReader();
 * System.out.println(reader.read());
 * 
 * ----- Result -----
 * "192.168.1.100:2181,192.168.1.101:2181,192.168.1.102:2181"
 * </pre>
 * 
 * @author Allen Hu 2016-3-17
 */
public class FileAddressReader implements AddressReader {

    private static final String PREFIX = "file:";
    public static final String PREFIX_REGX = PREFIX + ".*";
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String DEFAULT_FILENAME = "zookeeper-address";
    
    private final String path;

    public FileAddressReader() {
        this(null);
    }

    public FileAddressReader(String path) {
        if (StringUtils.isNotBlank(path) && !path.matches(PREFIX_REGX)) {
            throw new RuntimeException("The file path must be begin with '" + PREFIX + "'");
        }
        this.path = path;
    }

    @Override
    public String read() throws ReaderException {
        String filename = path;
        if (StringUtils.isBlank(filename)) {
            filename = StringUtils.join(new String[] { PathUtils.appendEndFileSeparator(USER_HOME), DEFAULT_FILENAME });
        }
        try {
            filename = StringUtils.removeStart(filename, PREFIX);
            String addrs = FileUtils.readFileToString(new File(filename));
            if (StringUtils.isBlank(addrs))
                throw new ReaderException("empty!");
            return StringUtils.trim(addrs);
        }
        catch (FileNotFoundException e) {
            return null;
        }
        catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
