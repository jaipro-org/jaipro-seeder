package com.bindord.eureka.seeder.domain.specialist;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class SpecialistCommission {

    @Id
    @Column(name = "SpecialistCommissionId")
    private UUID id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", nullable = false, updatable = false)
    private Specialist specialist;

    @Schema(description = "The fee that has to pay the specialist to the platform for each service he gives")
    @NotNull
    @Min(0)
    @Max(21)
    @Digits(integer = 2, fraction = 2)
    @Column(precision = 2, scale = 2, nullable = false)
    private Double comission;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    public SpecialistCommission() {
    }

    public SpecialistCommission(UUID id) {
        this.id = id;
    }
}
