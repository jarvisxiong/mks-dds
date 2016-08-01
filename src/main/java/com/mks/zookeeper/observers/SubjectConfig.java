package com.mks.zookeeper.observers;

import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ZKPaths;
 
public final class SubjectConfig {

    public static final String SUBJECT_PATH = "/observers/subject";
    public static final String PREFIX = "P-";
    
    public static final SubjectConfig DEFAULT = new SubjectConfig();
    
    private String subjectRootPath = SUBJECT_PATH;
    
    public String getSubjectPath(String subjectType) {
        String path = ZKPaths.makePath(subjectRootPath, subjectType);
        PathUtils.validatePath(path);
        return path;
    }
    
    public String getPath(String subjectType) {
        String path = getSubjectPath(subjectType);
        path = ZKPaths.makePath(path, PREFIX);
        PathUtils.validatePath(path);
        return path;
    }

    public void setSubjectRootPath(String subjectRootPath) {
        this.subjectRootPath = subjectRootPath;
    }
}
