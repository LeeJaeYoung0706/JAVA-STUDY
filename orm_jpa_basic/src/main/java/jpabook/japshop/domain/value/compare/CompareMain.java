//package jpabook.japshop.domain.value.compare;
//
//import jpabook.japshop.domain.value.embedded.Address;
//
//public class CompareMain {
//
//    public static void main(String[] args) {
//        int a = 10;
//        int b = 10;
//
//        System.out.println("(b == a) = " + (b == a));
//        // true
//
//        Address address1 = new Address("11" , "22" , "33");
//        Address address2 = new Address("12" , "22" , "33");
//
//        System.out.println("(address2 == address1 = " + (address2 == address1));
//        // false
//        System.out.println("(address2 == address1 equals = " + (address2.equals(address1)));
//        // false // 값이 같으면 true , 안에 String equals 도 하기 때문에,
//
//        System.out.println("11" + new String().equals(new String()));
//        System.out.println("22" + new Address().equals(new Address()));
//    }
//}
