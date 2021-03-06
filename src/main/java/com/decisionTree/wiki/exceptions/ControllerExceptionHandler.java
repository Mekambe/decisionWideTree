package com.decisionTree.wiki.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "com.decisionTree.wiki.controllers")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(value = {IdNotFound.class})
public ResponseEntity<Object> handlerError(Exception ex, WebRequest webRequest) {
    String bodyError = "No ID found";

    return handleExceptionInternal(ex, bodyError, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);

}

    @ExceptionHandler(value = {GroupNotFound.class})
    public ResponseEntity<Object> handle2Error(Exception ex, WebRequest webRequest) {
        String bodyError = "You need to create new Group";

        return handleExceptionInternal(ex, bodyError, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);

    }


}
