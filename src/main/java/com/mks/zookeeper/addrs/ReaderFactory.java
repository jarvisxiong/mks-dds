package com.mks.zookeeper.addrs;

import org.apache.commons.lang.StringUtils;

import com.mks.zookeeper.addrs.file.FileAddressReader;
import com.mks.zookeeper.addrs.fixedly.FixedlyAddressReader;
import com.mks.zookeeper.addrs.url.UrlAddressReader;

public final class ReaderFactory {

    private ReaderFactory() {
    }

    /**
     * 根据参数值匹配来构造一个读取器
     * 
     * @param value 参数值。可以是一个URL、文件路径，甚至可以为<code>null</code>
     * @return 如果参数值为<code>null</code>，那么构造{@linkplain FileAddressReader 文件默认路径读取器}。<br> 
     *        如果不为<code>null</code>，且全部匹配失败，则会构造一个{@linkplain FixedlyAddressReader 不变的地址读取器}
     */
    public static AddressReader matching(String value) {
        if (StringUtils.isNotBlank(value)) {
            if (value.matches(UrlAddressReader.REGX))
                return buildUrl(value);
            if (value.matches(FileAddressReader.PREFIX_REGX))
                return buildFile(value);

            return buildFixedly(value);
        }
        return buildFile();
    }

    /**
     * 构造一个指定类型的读取器并返回
     * 
     * @param type 读取器类型
     * @param value 参数值。可以是一个URL、文件路径，甚至可以为<code>null</code>
     * @return
     */
    public static AddressReader build(ReaderType type, String value) {
        switch (type) {
            case URL:
                return new UrlAddressReader(value);
            case FILE:
                return new FileAddressReader(value);
            case FIXEDLY:
                return new FixedlyAddressReader(value);
            default:
                throw new RuntimeException("Does not supported type: " + type);
        }
    }

    public static AddressReader buildUrl(String url) {
        return new UrlAddressReader(url);
    }

    public static AddressReader buildFile() {
        return new FileAddressReader();
    }

    public static AddressReader buildFile(String path) {
        return new FileAddressReader(path);
    }

    public static AddressReader buildFixedly(String value) {
        return new FixedlyAddressReader(value);
    }
}
