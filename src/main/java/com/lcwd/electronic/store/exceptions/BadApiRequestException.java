package com.lcwd.electronic.store.exceptions;

public class BadApiRequestException extends  RuntimeException{
    public BadApiRequestException(String s) {
        super(s);
    }
    public BadApiRequestException() {
        super("Bad API Request");
    }

}
