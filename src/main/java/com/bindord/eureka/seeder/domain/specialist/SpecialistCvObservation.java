package com.bindord.eureka.seeder.domain.specialist;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
@Data
public class SpecialistCvObservation extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SpecialistCvObservationId")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", referencedColumnName = "SpecialistId", updatable = false, nullable = false, columnDefinition = "uuid")
    private Specialist specialist;

    @Schema(description = "Observation of the specialist's profile.")
    @Size(min = 8, max = 250)
    @Column(nullable = false, length = 250)
    private String observation;

    public SpecialistCvObservation() {

    }

    public SpecialistCvObservation(Integer id) {
        this.id = id;
    }
}
