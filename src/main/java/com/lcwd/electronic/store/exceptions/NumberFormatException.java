package com.lcwd.electronic.store.exceptions;

public class NumberFormatException extends RuntimeException{
    public NumberFormatException(){
        super("Something Wrong with Number format ");
    }
    public NumberFormatException(String message){
        super(message);
    }
}
