package com.example.security_study.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Embeddable
public class EmbeddedIdTest implements Serializable {

    private long orderId;
    @Column(name="template_id")
    private long templateId;


}
