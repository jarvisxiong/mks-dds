package com.mks.network.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class HttpServletResponseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServletResponseHelper.class);
    private static final String CONTENT_TYPE = "application/octet-stream";
    
    private HttpServletResponseHelper() {
    }
    
    public static boolean doRespond(HttpServletResponse response, String data) {
        if (null == response)
            return false;
        if (StringUtils.isNotBlank(data))
        {
            PrintWriter out = null;
            try {
                response.setContentType(CONTENT_TYPE);
                out = response.getWriter();
                out.println(data);
                out.flush();
                return true;
            }
            catch (IOException e) {
                LOGGER.error("println:", e);
            }
            finally {
                IOUtils.closeQuietly(out);
            }
        }
        return false;
    }
    
    public static boolean doRespond(HttpServletResponse response, byte[] data) {
        if (null == response)
            return false;
        if (ArrayUtils.isNotEmpty(data))
        {
            PrintWriter out = null;
            try {
                response.setContentType(CONTENT_TYPE);
                out = response.getWriter();
                out.println(data);
                out.flush();
                return true;
            }
            catch (IOException e) {
                LOGGER.error("println:", e);
            }
            finally {
                IOUtils.closeQuietly(out);
            }
        }
        return false;
    }
}
