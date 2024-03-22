package com.example.patterns.factory_method;

public interface CarFactory {

    default Car orderCar(String name, String email) {
        validate( name ,email);
        prepareFor(name);
        Car car = createCar();
//        sendEmailTo(email, car);
        return car;
    }


//    private static void sendEmailTo(String email, Car car) {
//        System.out.println(car.getName() + " 다 만들었습니다.");
//    }

    Car createCar();
    private void validate(String name , String email){
        // validate
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("차 이름을 지어주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }
    }

    private static void prepareFor(String name) {
        System.out.println(name + " 만들 준비 중");
    }

}
