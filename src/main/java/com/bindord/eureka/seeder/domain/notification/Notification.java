package com.bindord.eureka.seeder.domain.notification;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.customer.Customer;
import com.bindord.eureka.seeder.domain.specialist.Specialist;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
public class Notification extends BaseDomain {

    @Id
    @Column(name = "NotificationId", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", updatable = false, columnDefinition = "uuid")
    private Specialist specialist;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", updatable = false, columnDefinition = "uuid")
    private Customer customer;

    @NotBlank
    @Size(min = 3, max = 72)
    @Column(nullable = false, length = 72)
    private String title;

    @NotBlank
    @Size(min = 3, max = 600)
    @Column(nullable = false, length = 600)
    private String message;

    @Size(min = 3, max = 72)
    @Column(nullable = true, length = 72)
    private String icon;

    private Integer type;

    @Column(nullable = false)
    private boolean toSpecialist;

    @Column(nullable = false)
    private boolean toCustomer;

    @Column(nullable = false)
    private boolean read;

    @Column(nullable = false)
    private boolean deleted;

    public Notification() {
    }

    public Notification(UUID id) {
        this.id = id;
    }
}
