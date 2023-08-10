package com.bindord.eureka.seeder.domain.service;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class RatingOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RatingOptionId")
    private Integer id;

    @NotBlank
    @Size(max = 256)
    @Column(nullable = false, length = 256)
    private String name;

    public RatingOption() {

    }
}
