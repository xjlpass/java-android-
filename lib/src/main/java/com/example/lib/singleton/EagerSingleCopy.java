package com.example.lib.singleton;

public class EagerSingleCopy {
    private static final  EagerSingleCopy INSTANCE= new EagerSingleCopy();

    private EagerSingleCopy(){

    }

    public  EagerSingleCopy getInstance(){
        return INSTANCE;
    }
}
