package com.bindord.eureka.seeder.domain.service;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.service.ids.ServiceRatingId;
import com.bindord.eureka.seeder.domain.specialist.Specialist;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceRating extends BaseDomain {

    @EmbeddedId
    private ServiceRatingId id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", columnDefinition = "uuid", updatable = false)
    private Specialist specialist;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ServiceRequestId", referencedColumnName = "ServiceRequestId", columnDefinition = "uuid", updatable = false),
            @JoinColumn(name = "ProfessionId", referencedColumnName = "ProfessionId", updatable = false)
    })
    private ServiceRequest serviceRequest;

    @Schema(description = "Qualification submitted by the customer")
    @NotNull
    @Min(1)
    @Digits(integer = 1, fraction = 1)
    @Column(precision = 1, scale = 1, nullable = false)
    private Float qualification;

    @Schema(description = "The comment associated with the qualification submitted by the customer")
    @Size(min = 9, max = 250)
    private String comment;


    @Schema(description = "Rating option selected by the customer in the qualification")
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "RatingOptionId", updatable = false, nullable = true)
    private RatingOption ratingOption;

    public ServiceRating() {

    }

    public ServiceRating(ServiceRatingId id) {
        this.id = id;
    }
}
