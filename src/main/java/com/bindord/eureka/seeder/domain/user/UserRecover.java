package com.bindord.eureka.seeder.domain.user;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class UserRecover extends BaseDomain {

    @Id
    @Column(name = "UserRecoverId", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(name = "UserId")
    private UUID userId;

    @Column(nullable = false)
    private String verificationCode;

    @Column(nullable = false)
    private boolean flagRecover;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime limitDate;

    public UserRecover() {
    }
}
