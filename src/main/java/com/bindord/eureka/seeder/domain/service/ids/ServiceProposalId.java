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

    public ServiceProposalId() {
    }

    public ServiceProposalId(UUID serviceProposalId) {
        this.serviceProposalId = serviceProposalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceProposalId that = (ServiceProposalId) o;
        return serviceProposalId.equals(that.serviceProposalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceProposalId);
    }
}
