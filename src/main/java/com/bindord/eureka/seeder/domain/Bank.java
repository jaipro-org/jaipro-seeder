package com.bindord.eureka.seeder.domain;

import com.bindord.eureka.seeder.domain.base.AuditingEntity;
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
public class Bank extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BankId")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String abbrv;

    @Column
    private String countryCode;

    @Column
    private boolean enabled;

    public Bank() {
    }

    public Bank(String name, String abbrv, String countryCode, boolean enabled) {
        this.name = name;
        this.abbrv = abbrv;
        this.countryCode = countryCode;
        this.enabled = enabled;
    }

    public Bank(Integer id) {
        this.id = id;
    }
}
