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

    @Column(name = "PaymentId", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID paymentId;

    public PaymentId() {
    }

    public PaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentId that = (PaymentId) o;
        return paymentId.equals(that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentId);
    }
}
