package com.bindord.eureka.seeder.domain.json;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@Setter
@Getter
public class MailInput {

    @Size(max = 64)
    private String field;

    @Size(max = 200)
    private String description;
}

