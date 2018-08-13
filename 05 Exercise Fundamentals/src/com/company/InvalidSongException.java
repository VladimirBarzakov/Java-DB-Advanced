package com.company;

public class InvalidSongException extends RuntimeException{
    private  String message;

    public InvalidSongException(){
        super();
    }

    public InvalidSongException(String message){
        super("Invalid song.");
        this.message=message;
    }

    public String getMessage(){
        return this.message;
    }

}
