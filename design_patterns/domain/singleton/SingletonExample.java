package com.example.patterns.domain.singleton;

import lombok.Data;

import java.io.Serializable;


public enum SingletonExample  {
    INSTANCE;
}



//    public static int count;
//    private static SingletonExample instance;
//
//    private SingletonExample() {
//        count+= 1;
//    }
//
//    public static SingletonExample getInstance(){
//        if(instance == null){
//            instance = new SingletonExample();
//        }
//        return instance;
//    }
//
//    protected Object readResolve() {
//        return getInstance();
//    }