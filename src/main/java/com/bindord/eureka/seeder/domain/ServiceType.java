package com.bindord.eureka.seeder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceTypeId")
    private Integer id;

    private String code;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false)
    private String name;

    @Size(max = 60)
    @Column(nullable = true, length = 60)
    private String description;

    @Column(nullable = false, updatable = false)
    private boolean comissionAdvanced;

    @Column
    private boolean enabled;

    @JsonBackReference
    @OneToMany(mappedBy = "serviceType", fetch = FetchType.LAZY)
    private List<Service> lstService;

    public ServiceType() {
    }

    public ServiceType(String name) {
        this.name = name;
    }

    public ServiceType(Integer id) {
        this.id = id;
    }
}
