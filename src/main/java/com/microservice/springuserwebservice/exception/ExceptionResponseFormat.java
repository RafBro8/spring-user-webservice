package com.microservice.springuserwebservice.exception;

import java.util.Date;

public class ExceptionResponseFormat {

    private Date timestamp;
    private String message;
    private String detail;

    public ExceptionResponseFormat(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}


//Creating Custom Exception and specifying the format of the body
//the specified format and error message would now display in PostMan