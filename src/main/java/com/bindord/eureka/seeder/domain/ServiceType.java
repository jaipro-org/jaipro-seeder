package com.bindord.eureka.seeder.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceTypeId")
    private Integer id;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String name;

    @Size(max = 60)
    @Column(nullable = true, length = 60)
    private String description;

    @Column
    private boolean enabled;

    public ServiceType() {
    }

    public ServiceType(String name) {
        this.name = name;
    }

    public ServiceType(Integer id) {
        this.id = id;
    }
}
