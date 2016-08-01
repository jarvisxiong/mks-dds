package com.mks.network.http.utils;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.http.Header;

import com.google.common.collect.Lists;


public class HttpResponse {

    private List<Header> headers = Lists.newArrayList();
    private byte[] entity;
    private int statusCode;
    private String entityString;
    
    public void addAllHeaders(Header[] headers) {
        for (Header e : headers) {
            this.headers.add(e);
        }
    }
    
    public List<Header> getHeaders() {
        return headers;
    }
    
    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }
    
    public byte[] getEntity() {
        return entity;
    }
    
    public void setEntity(byte[] entity) {
        this.entity = entity;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getEntityString() {
        return entityString;
    }

    public void setEntityString(String entityString) {
        this.entityString = entityString;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
