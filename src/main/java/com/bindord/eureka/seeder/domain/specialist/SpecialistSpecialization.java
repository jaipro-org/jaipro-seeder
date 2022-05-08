package com.bindord.eureka.seeder.domain.specialist;

import com.bindord.eureka.seeder.domain.profession.Specialization;
import com.bindord.eureka.seeder.domain.specialist.ids.SpecialistSpecializationId;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
@Data
public class SpecialistSpecialization {

    @EmbeddedId
    private SpecialistSpecializationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", insertable = false, updatable = false)
    private Specialist specialist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "SpecializationId", referencedColumnName = "SpecializationId", insertable = false, updatable = false),
            @JoinColumn(name = "ProfessionId", referencedColumnName = "ProfessionId", insertable = false, updatable = false)
    })
    private Specialization specialization;

}
