package com.example.patterns.factory_method;

public class GranCarFactory implements CarFactory{

    @Override
    public Car createCar() {
        return new Gran();
    }
}


/**
 * public class SonataCarFactory{
 *
 *
 *     public static Car orderCar(String name,String email) {
 * // validate
 * if (name== null ||name.isBlank()) {
 *             throw new IllegalArgumentException("차 이름을 지어주세요.");
 *         }
 *         if (email== null ||email.isBlank()) {
 *             throw new IllegalArgumentException("연락처를 남겨주세요.");
 *         }
 *
 * prepareFor(name);
 *
 * Car car = new Car();
 *         car.setName(name);
 *
 * // Customizing for specific name
 * if (name.equalsIgnoreCase("그랜져")) {
 *             car.setKind("중대형 승용차");
 *         } else if (name.equalsIgnoreCase("쏘나타")) {
 *             car.setKind("중형 승용차");
 *         }
 *
 * // price
 * if (name.equalsIgnoreCase("그랜져")) {
 *             car.setPrice("3000만");
 *         } else if (name.equalsIgnoreCase("쏘나타")) {
 *             car.setPrice("2500만");
 *         }
 *
 * // notify
 * 				sendEmailTo(email, car);
 *
 *         return car;
 *     }
 *
 *     private static void prepareFor(Stringname) {
 * 				System.out.println(name+ " 만들 준비 중");
 *     }
 *
 *     private static void sendEmailTo(Stringemail,Carcar) {
 * 				System.out.println(car.getName() + " 다 만들었습니다.");
 *     }
 *
 * }
 *
 *
 *
 *
 *
 * ```java
 *
 * public class SonataCarFactory{
 *
 *     public static Car orderCar(String name,String email) {
 * // validate
 * if (name== null ||name.isBlank()) {
 *             throw new IllegalArgumentException("차 이름을 지어주세요.");
 *         }
 *         if (email== null ||email.isBlank()) {
 *             throw new IllegalArgumentException("연락처를 남겨주세요.");
 *         }
 *
 * prepareFor(name);
 *
 * Car car = new Car();
 *         car.setName(name);
 *
 * // Customizing for specific name
 * if (name.equalsIgnoreCase("그랜져")) {
 *             car.setKind("중대형 승용차");
 *         } else if (name.equalsIgnoreCase("쏘나타")) {
 *             car.setKind("중형 승용차");
 *         }
 *
 * // price
 * if (name.equalsIgnoreCase("그랜져")) {
 *             car.setPrice("3000만");
 *         } else if (name.equalsIgnoreCase("쏘나타")) {
 *             car.setPrice("2500만");
 *         }
 *
 * // notify
 * 				sendEmailTo(email, car);
 *
 *         return car;
 *     }
 *
 *     private static void prepareFor(Stringname) {
 * 				System.out.println(name+ " 만들 준비 중");
 *     }
 *
 *     private static void sendEmailTo(Stringemail,Carcar) {
 * 				System.out.println(car.getName() + " 다 만들었습니다.");
 *     }
 *
 * }
 * ```
 */