package com.example.patterns.factory_method;

public class SonataCarFactory implements CarFactory{

    @Override
    public Car createCar() {
        return new Sonata();
    }
}

