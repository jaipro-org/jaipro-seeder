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
public class ServiceRatingId implements Serializable {

    @Column(name = "ServiceRequestId", columnDefinition = "uuid")
    private UUID serviceRequestId;

    public ServiceRatingId(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRatingId that = (ServiceRatingId) o;
        return serviceRequestId.equals(that.serviceRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceRequestId);
    }
}
