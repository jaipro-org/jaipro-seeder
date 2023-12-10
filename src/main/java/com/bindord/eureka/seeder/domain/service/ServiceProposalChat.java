package com.bindord.eureka.seeder.domain.service;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.customer.Customer;
import com.bindord.eureka.seeder.domain.specialist.Specialist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceProposalChat extends BaseDomain {

    @Id
    @Column(name = "ServiceProposalChatId", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ServiceProposalId", referencedColumnName = "ServiceProposalId", updatable = false)
    private ServiceProposal serviceProposal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", updatable = false, nullable = false, columnDefinition = "uuid")
    private Specialist specialist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", updatable = false, nullable = false, columnDefinition = "uuid")
    private Customer customer;

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

    public ServiceProposalChat(UUID id) {
        this.id = id;
    }
}
