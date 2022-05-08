package com.bindord.eureka.seeder.domain.specialist.ids;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class SpecialistSpecializationId implements Serializable {

    @Column(name = "SpecialistId")
    private UUID specialistId;

    @Column(name = "SpecializationId")
    private Integer specializationId;

    @Column(name = "ProfessionId")
    private Integer professionId;

    public SpecialistSpecializationId() {
    }

    public SpecialistSpecializationId(UUID specialistId, Integer specializationId, Integer professionId) {
        this.specialistId = specialistId;
        this.specializationId = specializationId;
        this.professionId = professionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialistSpecializationId that = (SpecialistSpecializationId) o;
        return getSpecialistId().equals(that.getSpecialistId()) &&
                getSpecializationId().equals(that.getSpecializationId()) &&
                getProfessionId().equals(that.getProfessionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecialistId(), getSpecializationId(), getProfessionId());
    }
}
