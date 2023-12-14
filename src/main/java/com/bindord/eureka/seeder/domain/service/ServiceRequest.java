package com.bindord.eureka.seeder.domain.service;

import com.bindord.eureka.seeder.domain.ServiceType;
import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.country.District;
import com.bindord.eureka.seeder.domain.customer.Customer;
import com.bindord.eureka.seeder.domain.json.Photo;
import com.bindord.eureka.seeder.domain.profession.Profession;
import com.bindord.eureka.seeder.domain.service.ids.ServiceRequestId;
import com.bindord.eureka.seeder.domain.service.json.Milestone;
import com.bindord.eureka.seeder.domain.specialist.Specialist;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@EqualsAndHashCode(callSuper = false)
public class ServiceRequest extends BaseDomain {

    @EmbeddedId
    private ServiceRequestId id;

    @Schema(description = "Via where come from the request. 1: particular, 2: public")
    @NotNull
    @Min(value = 1)
    @Max(value = 2)
    @Column(nullable = false, updatable = false)
    private Integer type;

    @Schema(description = "Status that describes the current situation of the service request")
    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    @Column(nullable = false)
    private Integer status;

    @NotBlank
    @Size(min = 60, max = 600)
    @Column(nullable = true, length = 600)
    private String detail;

    @Schema(description = "Counter that saves the total of proposals received in the request")
    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    @Column(nullable = false)
    private Integer proposalsCounter;

    @Schema(description = "To show the rating option in the service request(In the web)")
    @Column(nullable = false)
    private boolean enabledRating;

    @Schema(description = "To disabled rating options because it was already done(In the web)")
    @Column(nullable = false)
    private boolean ratingDone;

    @Schema(description = "For V2, in V1 all requests will registered with condition regular")
    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    @Column(nullable = false)
    private Integer condition;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<Milestone> milestones;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<Photo> gallery;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false, updatable = false)
    private Customer customer;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProfessionId", nullable = false, updatable = false)
    private Profession profession;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", nullable = true, columnDefinition = "uuid")
    private Specialist specialist;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ServiceTypeId", updatable = false, nullable = false)
    private ServiceType serviceType;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DistrictId", nullable = false)
    private District district;

    public ServiceRequest() {

    }

    public ServiceRequest(ServiceRequestId id) {
        this.id = id;
    }
}
