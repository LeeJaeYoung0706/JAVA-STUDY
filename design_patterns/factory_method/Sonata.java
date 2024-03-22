package com.example.patterns.factory_method;

import lombok.Data;


public class Sonata extends Car{

    public Sonata () {
         setName("쏘나타");
         setKind("중형 승용차");
         setPrice("2500만");
    }
}
