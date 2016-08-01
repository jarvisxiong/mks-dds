package com.mks.zkadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mks.utils.StringHelper;
import com.mks.zkadmin.dao.NodeDao;
import com.mks.zkadmin.entity.Node;


@Service
public class NodeService {

    @Autowired
    private NodeDao nodeDao
    ;
    
    public Node getNode(String path) throws Exception {
      return nodeDao.getNode(path);   
    }
    
    public void save(String path, String content) throws Exception {
        nodeDao.save(path, StringHelper.convert(content));
    }
    
    public void delete(String path) throws Exception {
        nodeDao.remove(path);
    }

    public void setNodeDao(NodeDao nodeDao) {
        this.nodeDao = nodeDao;
    }
}
