package com.bindord.eureka.seeder.domain;

import com.bindord.eureka.seeder.domain.base.AuditingEntity;
import com.bindord.eureka.seeder.domain.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Bank extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BankId")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortName;

    @Column
    private boolean enabled;

    public Bank() {
    }

    public Bank(String name, String shortName, boolean enabled) {
        this.name = name;
        this.shortName = shortName;
        this.enabled = enabled;
    }

    public Bank(Integer id) {
        this.id = id;
    }
}
