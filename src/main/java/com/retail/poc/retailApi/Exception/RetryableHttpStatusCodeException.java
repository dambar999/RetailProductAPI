package com.retail.poc.retailApi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class RetryableHttpStatusCodeException extends HttpStatusCodeException {

    public RetryableHttpStatusCodeException(HttpStatus statusCode, String message){
        super(statusCode,message);
    }
}
