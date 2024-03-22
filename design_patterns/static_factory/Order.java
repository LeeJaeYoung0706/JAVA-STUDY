package com.example.security_study.static_factory;

public class Order {

    private ClassificationOrderEnum classificationOrder;

    private Product product;

    private Order(Product product, ClassificationOrderEnum classificationOrder) {
        this.product = product;
        this.classificationOrder = classificationOrder;
    }

    public static Order createOrder(Product product , ClassificationOrderEnum classificationOrder) {
        return new Order(product , classificationOrder);
    }

    public static Order defaultOrder(Product product) {
        return new Order(product , ClassificationOrderEnum.PRIME);
    }

    public ClassificationOrderEnum getClassificationOrder() {
        return classificationOrder;
    }

    private void setClassificationOrder(ClassificationOrderEnum classificationOrder) {
        this.classificationOrder = classificationOrder;
    }

    public static Order transOrderClassification(Order order , ClassificationOrderEnum classificationOrder){
        order.setClassificationOrder(classificationOrder);
        return order;
    }

    public static void main(String[] args) {
        Product product = new Product();

//        Order order = Order.defaultOrder(product);
//        System.out.println( " default Order = " + order.getClassificationOrder());
//        Order lazyOrder = Order.createOrder(product, ClassificationOrderEnum.LAZY);
//        System.out.println( " lazy Order = " + lazyOrder.getClassificationOrder());
//        Order urgentOrder = Order.createOrder(product, ClassificationOrderEnum.URGENT);
//        System.out.println( "urgent Order = " + urgentOrder.getClassificationOrder());

        Order order = Order.defaultOrder(product);
        System.out.println(order);
        System.out.println(order.getClassificationOrder());
        // 긴급으로 변경
        Order order1 = Order.transOrderClassification(order, ClassificationOrderEnum.URGENT);
        System.out.println(order1);
        System.out.println(order1.getClassificationOrder());

    }

//    public Order(boolean prime, Product product) {
//        this.prime = prime;
//        this.product = product;
//    }
//
//    public Order(Product product , boolean urgent) {
//        this.urgent = urgent;
//        this.product = product;
//    }

//    // 기본 오더 생성
//    public static Order primeOrder(Product product) {
//        Order order = new Order();
//        order.prime = true;
//        order.product = product;
//        return order;
//    }
//
//    // 긴급 오더 생성
//    public static Order urgentOrder(Product product) {
//        Order order = new Order();
//        order.urgent = true;
//        order.product = product;
//        return order;
//    }



}
