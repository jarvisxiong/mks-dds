package com.mks.network.http.response;


/**
 * Response Entity of JSON. 
 */
public class JsonResponse {

    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    
    private int statusCode = SUCCESS;
    private String fail;
    private Object data;
    
    public JsonResponse(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public JsonResponse(Object data) {
        this.data = data;
    }

    public JsonResponse(int statusCode, String fail) {
        this.statusCode = statusCode;
        this.fail = fail;
    }

    public JsonResponse(int statusCode, String fail, Object data) {
        this.statusCode = statusCode;
        this.fail = fail;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public String getFail() {
        return fail;
    }
    
    public void setFail(String fail) {
        this.fail = fail;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
}
