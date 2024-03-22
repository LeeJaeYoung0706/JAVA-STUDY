package jpabook.japshop.domain.value.basicvalue;

public class ValueMain {
    public static void main(String[] args) {
        int a = 10;
        int b = a;
        
        b = 20;
        System.out.println("b = " + b);
        System.out.println("a = " + a);

        /**
         * 값의 공유가 안 일어난다.
         * b = 20
         * a = 10
         * b의 값만 바꾸면 b 만 변경 됨.
         * 값을 복사하는 것
         * 단, wrapper Class 의 경우는 다르다.
         */

        Integer IntegerA = new Integer(10);
        Integer IntegerB = a;

        /**
         * IntegerA = IntegerA.setValue(20);
         * 라는 메소드가 존재할 경우
         * 값이 공유되서
         * IntegerA , IntegerB 둘 다 20으로 출력
         * 하지만 값을 변경할 메소드가 없으니까, 불가능함.
         * 그래서 공유는 가능하지만, 변경은 불가능함.
         *
         * 사이드 이펙트 방지.
         */
        System.out.println("IntegerA = " + IntegerA);
        System.out.println("IntegerB = " + IntegerB);
    }
}
