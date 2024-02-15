package com.bindord.eureka.seeder.domain.profession;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Data
public class Profession extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProfessionId")
    private Integer id;

    @Size(min = 2, max = 50)
    @Column(nullable = false, updatable = false, length = 50)
    private String name;

    @Column(nullable = true)
    private boolean enabled;

    public Profession() {
    }

}
