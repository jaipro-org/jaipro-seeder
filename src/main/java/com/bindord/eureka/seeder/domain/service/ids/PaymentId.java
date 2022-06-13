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
public class PaymentId implements Serializable {

    @Column(name = "PaymentId")
    private UUID paymentId;

    @Column(name = "ServiceRequestId")
    private UUID serviceRequestId;

    @Column(name = "CustomerId")
    private UUID customerId;

    @Column(name = "ProfessionId")
    private Integer professionId;

    public PaymentId() {
    }

    public PaymentId(UUID paymentId, UUID serviceRequestId, UUID customerId, Integer professionId) {
        this.paymentId = paymentId;
        this.serviceRequestId = serviceRequestId;
        this.customerId = customerId;
        this.professionId = professionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentId that = (PaymentId) o;
        return paymentId.equals(that.paymentId) &&
                serviceRequestId.equals(that.serviceRequestId) &&
                customerId.equals(that.customerId) &&
                professionId.equals(that.professionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId, serviceRequestId, customerId, professionId);
    }
}
