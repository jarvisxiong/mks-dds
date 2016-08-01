package com.mks.network.http;

import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpServletRequestLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServletRequestLogger.class);

    public void info(final HttpServletRequest request) {
        if (null == request)
            return;
        if (LOGGER.isInfoEnabled()) {
            StringBuilder url = new StringBuilder(256);
            StringBuilder parameter = new StringBuilder(256);
            StringBuilder headers = new StringBuilder(256);
            StringBuilder body = new StringBuilder();
            String uri = request.getRequestURI();
            String address = request.getRemoteAddr();
            Map<?, ?> map = request.getParameterMap();
            if (MapUtils.isNotEmpty(map)) {
                parameter.append("?");
            }
            for (Entry<?, ?> entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                parameter.append(key);
                if (value instanceof String[]) {
                    parameter.append("=");
                    String[] values = (String[]) value;
                    for (int i = 0; i < values.length; i++) {
                        parameter.append(values[i]);
                    }
                }
                parameter.append("&");
            }
            url.append(uri).append(StringUtils.removeEnd(parameter.toString(), "&"));
            String method = request.getMethod();
            int contentLength = request.getContentLength();
            Enumeration<?> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                Object nextElement = headerNames.nextElement();
                String value = request.getHeader(String.valueOf(nextElement));
                headers.append(nextElement).append(": ").append(value).append(";");
            }
            HttpServletRequestLog log = new HttpServletRequestLog();
            log.setAddress(address);
            log.setContentLength(contentLength);
            log.setHeaders(StringUtils.removeEnd(headers.toString(), ";"));
            log.setMethod(method);
            log.setUri(url.toString());
            log.setBody(body.toString());
            LOGGER.info(log.toString());
        }
    }
}
