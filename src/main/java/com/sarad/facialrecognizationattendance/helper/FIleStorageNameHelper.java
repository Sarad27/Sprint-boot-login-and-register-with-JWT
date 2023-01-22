package com.sarad.facialrecognizationattendance.helper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class FIleStorageNameHelper {

    public String fileNameTimestamp(String originalName){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime() + "-" + originalName;
    }

}
