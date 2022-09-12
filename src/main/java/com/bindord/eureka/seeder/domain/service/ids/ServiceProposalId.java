package com.bindord.eureka.seeder.domain.service.ids;


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
public class ServiceProposalId implements Serializable {

    @Column(name = "ServiceProposalId", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID serviceProposalId;

    @Column(name = "ServiceRequestId")
    private UUID serviceRequestId;

    @Column(name = "CustomerId")
    private UUID customerId;

    @Column(name = "ProfessionId")
    private Integer professionId;

    public ServiceProposalId() {
    }

    public ServiceProposalId(UUID serviceProposalId, UUID serviceRequestId, UUID customerId, Integer professionId) {
        this.serviceProposalId = serviceProposalId;
        this.serviceRequestId = serviceRequestId;
        this.customerId = customerId;
        this.professionId = professionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceProposalId that = (ServiceProposalId) o;
        return serviceProposalId.equals(that.serviceProposalId) &&
                serviceRequestId.equals(that.serviceRequestId) &&
                customerId.equals(that.customerId) &&
                professionId.equals(that.professionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceProposalId, serviceRequestId, customerId, professionId);
    }
}
