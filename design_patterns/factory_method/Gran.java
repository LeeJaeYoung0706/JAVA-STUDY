package com.example.patterns.factory_method;

import lombok.Data;


public class Gran extends Car{

    public Gran() {
         setName("그랜져");
         setKind("중대형 승용차");
         setPrice("3000만");
    }
}
