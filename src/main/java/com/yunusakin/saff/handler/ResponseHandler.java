package com.yunusakin.saff.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(boolean success,String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success",success);
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<Object>(map,status);
    }
    public static ResponseEntity<Object> successResponse() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success",true);
        map.put("status", HttpStatus.OK.value());
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
    public static ResponseEntity<Object> returnErrorResponse(Exception e){
        return ResponseHandler.generateResponse(false,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}
