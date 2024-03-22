package com.example.security_study.static_factory;

import java.util.Optional;
import java.util.ServiceLoader;

public class ServiceFactory {

    public static void main(String[] args) {
        //LangService ko = LangService.of("ko");
        // KoreanService 호출

        // 만약 인터페이스만 존재하고 구현체가 없다면
        ServiceLoader<LangService> loader = ServiceLoader.load(LangService.class);
        // ServiceLoader -> implements Iterable<S>
        // 구현체를 가져옴
        Optional<LangService> langService = loader.findFirst();
        // 찾아올 수 있고 없을 수 있음.
        langService.ifPresent(h -> h.hello());

    }
}
