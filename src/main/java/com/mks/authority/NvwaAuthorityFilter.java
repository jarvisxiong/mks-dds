package com.mks.authority;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
@Deprecated
public class NvwaAuthorityFilter extends PathMatchingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(NvwaAuthorityFilter.class);
    
    @Override
    public boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        if (request instanceof HttpServletRequest) {
            String uri = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Access url: {}", uri);
            }
        }
        Subject subject = SecurityUtils.getSubject();
        subject.checkRole("admin");
        return true;
    }
}
