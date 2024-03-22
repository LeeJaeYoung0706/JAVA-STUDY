package com.example.patterns.factory_method;

public class SimpleFactory {

    public Object createCar(String name){
        if (name.equals("쏘나타")){
            return new Sonata();
        } else if(name.equals("그랜져")) {
            return new Gran();
        }
        throw new IllegalArgumentException();
    }
}
