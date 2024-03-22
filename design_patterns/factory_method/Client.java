package com.example.patterns.factory_method;

public class Client {

    public static void main(String[] args) {
        Car sonata = new SonataCarFactory().orderCar("쏘나타" , "qaz774422@naver.com");
        System.out.println("sonata = " + sonata);
        Car gran= new GranCarFactory().orderCar("그랜져" , "qaz774422@naver.com");
        System.out.println("gran = " + gran);
    }

    public static void print(CarFactory carFactory , String name , String email){
        System.out.println(carFactory.orderCar(name , email));
    }


}
