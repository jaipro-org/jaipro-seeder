package com.bindord.eureka.seeder.domain.notification;

import com.bindord.eureka.seeder.domain.customer.Customer;
import com.bindord.eureka.seeder.domain.specialist.Specialist;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Notification {

    @Id
    @Column(name = "NotificationId")
    private UUID id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", nullable = false, updatable = false)
    private Specialist specialist;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false, updatable = false)
    private Customer customer;

    @NotBlank
    @Size(min = 3, max = 72)
    @Column(nullable = false, length = 72)
    private String title;

    @NotBlank
    @Size(min = 3, max = 600)
    @Column(nullable = false, length = 600)
    private String message;

    @NotEmpty
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

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    public Notification() {
    }

    public Notification(UUID id) {
        this.id = id;
    }
}
