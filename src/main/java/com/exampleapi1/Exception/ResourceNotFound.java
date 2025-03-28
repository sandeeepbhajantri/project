package com.exampleapi1.Exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String massage){
        super(massage);
    }
}
