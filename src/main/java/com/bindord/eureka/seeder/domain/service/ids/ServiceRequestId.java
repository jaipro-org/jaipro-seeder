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
public class ServiceRequestId implements Serializable {

    @Column(name = "ServiceRequestId", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID serviceRequestId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRequestId that = (ServiceRequestId) o;
        return serviceRequestId.equals(that.serviceRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceRequestId);
    }

    public ServiceRequestId() {
    }

    public ServiceRequestId(UUID serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }
}
