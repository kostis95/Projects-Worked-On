/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * HTTP responses that the API uses
 *
 * @author Youri
 */
public enum HttpResponse {

    SUCCES(HttpStatus.ACCEPTED, "succes"),
    ALREADY_EXIST(HttpStatus.CONFLICT, "object already exists"),
    UNKNOWN_ERROR(HttpStatus.CONFLICT, "there was a unknown error"),
    WRONG_FORMAT(HttpStatus.BAD_REQUEST, "missing parameters"),
    DOESNT_EXIST(HttpStatus.NO_CONTENT, "object cannot be found");

    private HttpStatus status;

    private String debugMessage;

    private HttpResponse(HttpStatus status, String debugMessage) {
        this.status = status;
        this.debugMessage = debugMessage;
    }

    public ResponseEntity getResponseEntity(Object object) {
        return new ResponseEntity<Object>(object, getHeader(), status);
    }

    public ResponseEntity getResponseEntity(String... debugMessage) {
        return new ResponseEntity<Void>(getHeader(debugMessage), status);
    }

    public HttpHeaders getHeader(String... debugMessage) {
        HttpHeaders header = new HttpHeaders();
        header.add("message", this.name());

        if (debugMessage.length > 0) {
            header.add("debug", debugMessage[0]);
        } else {
            header.add("debug", this.debugMessage);
        }

        return header;
    }

}
