package com.bindord.eureka.seeder.domain.service;

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
public class PaymentObservation extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentObservationId")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PaymentId", referencedColumnName = "PaymentId", updatable = false, nullable = false)
    private Payment payment;

    @Schema(description = "Observation of the payment.")
    @Size(min = 8, max = 250)
    @Column(nullable = false, length = 250)
    private String observation;

    public PaymentObservation() {

    }

    public PaymentObservation(Integer id) {
        this.id = id;
    }
}
