package com.exampleapi1;

public class LowBalance extends RuntimeException {
    public LowBalance(String massage){
        super(massage);
    }
}
