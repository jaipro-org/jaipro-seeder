package com.bindord.eureka.seeder.domain.service;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.service.ids.ServiceProposalChatId;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceProposalChat extends BaseDomain {

    @EmbeddedId
    private ServiceProposalChatId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ServiceProposalId", referencedColumnName = "ServiceProposalId", insertable = false, updatable = false),
            @JoinColumn(name = "ServiceRequestId", referencedColumnName = "ServiceRequestId", insertable = false, updatable = false),
            @JoinColumn(name = "CustomerId", referencedColumnName = "CustomerId", insertable = false, updatable = false),
            @JoinColumn(name = "ProfessionId", referencedColumnName = "ProfessionId", insertable = false, updatable = false)
    })
    private ServiceProposal serviceRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", insertable = false, updatable = false, nullable = false)
    private Specialist specialist;

    @NotBlank
    @Size(min = 1, max = 280)
    @Column(nullable = false, length = 280)
    private String message;

    @Schema(description = "Indicates if the specialist sent this message")
    @Column(nullable = false)
    private boolean fromSpecialist;

    @Schema(description = "Indicates if the customer sent this message")
    @Column(nullable = false)
    private boolean fromCustomer;

    public ServiceProposalChat() {

    }

    public ServiceProposalChat(ServiceProposalChatId id) {
        this.id = id;
    }
}
