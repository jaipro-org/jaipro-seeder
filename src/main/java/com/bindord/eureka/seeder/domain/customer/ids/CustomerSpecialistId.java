package com.bindord.eureka.seeder.domain.customer.ids;


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
public class CustomerSpecialistId implements Serializable {

    @Column(name = "CustomerId")
    private UUID customerId;

    @Column(name = "SpecialistId")
    private UUID specialistId;

    public CustomerSpecialistId() {
    }

    public CustomerSpecialistId(UUID specialistId, UUID customerId) {
        this.specialistId = specialistId;
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSpecialistId that = (CustomerSpecialistId) o;
        return getSpecialistId().equals(that.getSpecialistId()) &&
                getCustomerId().equals(that.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecialistId(), getCustomerId());
    }
}
