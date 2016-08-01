package com.mks.pay;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;

public interface PayDefaults {

    ContentType APPLICATION_XML = ContentType.create("application/xml", Consts.UTF_8);
    
    String SUCCESS = "SUCCESS";
    String FAIL = "FAIL";
    
    interface DigestType {
        
        String DSA = "DSA";
        String RSA = "RSA";
        String MD5 = "MD5";
    }
}
