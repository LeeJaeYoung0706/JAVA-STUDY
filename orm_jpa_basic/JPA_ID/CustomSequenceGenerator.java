package com.example.security_study.domain;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;
import java.util.stream.Stream;

public class CustomSequenceGenerator implements IdentifierGenerator {

    private String prefix;

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        IdentifierGenerator.super.configure(type, parameters, serviceRegistry);
        prefix = parameters.getProperty("prefix");

    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        String query = String.format("select %s from %s",
                session.getEntityPersister(object.getClass().getName(), object)
                        .getIdentifierPropertyName(),
                object.getClass().getSimpleName());

//        Stream ids = session.createQuery(query).stream();
        Stream<String> ids = session.createQuery(query, String.class).stream();

        Long max = ids.map(o -> o.replace(prefix + "-", ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);

        return prefix + "-" + (max + 1);
    }
}
