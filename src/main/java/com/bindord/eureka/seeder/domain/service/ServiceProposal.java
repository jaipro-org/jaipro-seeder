package com.bindord.eureka.seeder.domain.service;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.service.ids.ServiceProposalId;
import com.bindord.eureka.seeder.domain.specialist.Specialist;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceProposal extends BaseDomain {

    @EmbeddedId
    private ServiceProposalId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ServiceRequestId", referencedColumnName = "ServiceRequestId", insertable = false, updatable = false),
            @JoinColumn(name = "CustomerId", referencedColumnName = "CustomerId", insertable = false, updatable = false),
            @JoinColumn(name = "ProfessionId", referencedColumnName = "ProfessionId", insertable = false, updatable = false)
    })
    private ServiceRequest serviceRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", insertable = false, updatable = false, nullable = false)
    private Specialist specialist;

    @Schema(description = "Proposal status. 1: Awaiting, 2: Declined, 3: Approved")
    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    @Column(nullable = false)
    private Integer statusProposal;

    @Schema(description = "Proposal made by the professional to the service request")
    @NotBlank
    @Size(min = 60, max = 500)
    @Column(nullable = false, length = 500)
    private String proposal;

    @Schema(description = "Professional name")
    @NotBlank
    @Size(min = 2, max = 36)
    @Column(nullable = false, length = 36)
    private String profName;

    @Schema(description = "Professional lastname")
    @NotBlank
    @Size(min = 2, max = 36)
    @Column(nullable = false, length = 36)
    private String profLastName;

    @Schema(description = "Professional public url")
    @NotBlank
    @Size(min = 7, max = Byte.MAX_VALUE)
    @Column(nullable = false, length = Byte.MAX_VALUE)
    private String profPublicUrl;

    @NotNull
    @Min(20)
    @Max(9999)
    @Column(nullable = false)
    private Integer minCost;

    @NotNull
    @Min(20)
    @Max(9999)
    @Column(nullable = false)
    private Integer maxCost;

    public ServiceProposal() {

    }

    public ServiceProposal(ServiceProposalId id) {
        this.id = id;
    }
}
