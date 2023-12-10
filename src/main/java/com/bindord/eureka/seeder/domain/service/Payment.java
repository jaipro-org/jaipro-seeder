package com.bindord.eureka.seeder.domain.service;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.json.Photo;
import com.bindord.eureka.seeder.domain.service.ids.PaymentId;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@EqualsAndHashCode(callSuper = false)
public class Payment extends BaseDomain {

    @EmbeddedId
    private PaymentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ServiceRequestId", referencedColumnName = "ServiceRequestId", updatable = false)
    private ServiceRequest serviceRequest;

    @Schema(description = "In evaluation, maybe will be removed this field")
    @Min(value = 0)
    @Max(value = 1)
    @Column(nullable = false)
    private Integer status;

    @Schema(description = "Payment modality. Options are 1: online, 2: cash")
    @NotNull
    @Min(value = 1)
    @Max(value = 2)
    @Column(nullable = false)
    private Integer modality;

    @Schema(description = "Types of payments. " +
            "commission_pay(1, specialist pay -> cash), " +
            "service_pay(2, customer pay pay -> online), " +
            "service_wout_comm_pay(3, backoffice pay -> online), " +
            "ficticious_pay(4, customerpay -> cash)." +
            "Ficticious pay is a record that is generated when a customer choose the option cash pay")
    @NotNull
    @Min(value = 1)
    @Max(value = 4)
    @Column(nullable = false, updatable = false)
    private Integer type;

    @Column(nullable = true)
    private Integer bankId;

    @Schema(description = "Voucher image of the bank operation")
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private Photo voucher;

    @Schema(description = "Operation number related to the bank transaction of the payment")
    @Size(min = 4, max = 40)
    @Column(nullable = true, length = 40)
    private String operationNumber;

    @NotNull
    @Min(1)
    @Digits(integer = 4, fraction = 2)
    @Column(precision = 4, scale = 2, nullable = false)
    private Double amount;

    @Column
    @Min(value = 1)
    @Max(value = 4)
    private Integer methodId;

    @Schema(description = "Indicates if the operation number of the bank transaction was successfully verified")
    @Column(nullable = false)
    private boolean verified;

    @Schema(description = "Limit time to make effective the pay of the platform's fee, this field is generated when the status pass to created status")
    @Column(name = "expirationDate")
    private LocalDateTime expirationDate;

    public Payment() {

    }

    public Payment(PaymentId id) {
        this.id = id;
    }
}
