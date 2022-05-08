package com.bindord.eureka.seeder.domain.profession;

import com.bindord.eureka.seeder.domain.specialist.SpecialistSpecialization;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SpecializationId")
    private Integer id;

    @Size(min = 2, max = 50)
    @Column(nullable = false, updatable = false, length = 50)
    private String name;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProfessionId")
    private Profession profession;

    @JsonBackReference
    @OneToMany(mappedBy = "specialization")
    private List<SpecialistSpecialization> specialistSpecializations;

    public Specialization() {
    }

}
