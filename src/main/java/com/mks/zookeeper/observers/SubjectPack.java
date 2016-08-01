package com.mks.zookeeper.observers;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.zookeeper.CreateMode;

import com.google.common.collect.Maps;
import com.mks.utils.JsonHelper;
import com.mks.utils.StringHelper;
import com.mks.zookeeper.ZkClientHolder;
import com.mks.zookeeper.observers.exceptions.AlreadyExistException;
import com.mks.zookeeper.observers.exceptions.CharacterException;
import com.mks.zookeeper.observers.exceptions.ObserverException;
 
public class SubjectPack {
    
    private final ZkClientHolder zkClientHolder;
    private final Map<String, Subject> SUBJECTS = Maps.newConcurrentMap();
    private SubjectConfig config = SubjectConfig.DEFAULT;
    
    public SubjectPack(ZkClientHolder zkClientHolder) {
        this.zkClientHolder = zkClientHolder;
    }
    
    public SubjectPack(ZkClientHolder zkClientHolder, Map<String, Subject> subjects) {
        this.zkClientHolder = zkClientHolder;
        if (MapUtils.isNotEmpty(subjects)) {
            for (Entry<String, Subject> subject : subjects.entrySet()) {
                addSubject(subject.getKey(), subject.getValue());
            }
        }
    }

    public void addSubject(String subjectType, Subject subject) {
        if (SUBJECTS.containsKey(subjectType)) {
            throw new AlreadyExistException("Subject type '" + subjectType + "' is already exist!");
        }
        if (StringHelper.contains(subjectType, "/")) {
            throw new CharacterException("subjectType cannot contain character '" + SubjectConfig.PREFIX + "'");
        }
        
        String path = config.getSubjectPath(subjectType);
        
        try {
            if (!exists(path)) {
                zkClientHolder.get().create().creatingParentsIfNeeded().forPath(path);
            }
            
            SUBJECTS.put(subjectType, subject);
        }
        catch (Exception e) {
            throw new ObserverException("addSubject:", e);
        }
    }

    private boolean exists(String path) throws Exception {
        return zkClientHolder.get().checkExists().forPath(path) != null;
    }
    
    public void notify(String subjectType, Object message) {
        String path = config.getPath(subjectType);
        try {
            String json = JsonHelper.convert(message);
            byte[] content = StringHelper.convert(json);
            zkClientHolder.get().create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(path, content);
        }
        catch (Exception e) {
            throw new ObserverException("notify:", e);
        }
    }

    public void setConfig(SubjectConfig config) {
        this.config = config;
    }
}
