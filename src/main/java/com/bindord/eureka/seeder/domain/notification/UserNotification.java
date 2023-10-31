package com.bindord.eureka.seeder.domain.notification;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.user.UserInfo;
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
public class UserNotification extends BaseDomain {

    @Id
    @Column(name = "UserNotificationId", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", updatable = false, columnDefinition = "uuid")
    private UserInfo userInfo;

    @NotBlank
    @Size(min = 3, max = 600)
    @Column(nullable = false, length = 600)
    private String content;

    @Size(min = 32, max = 256)
    @Column(nullable = true, length = 256)
    private String imageUrl;

    @Size(min = 16, max = 512)
    @Column(nullable = true, length = 512)
    private String link;

    private Integer type;

    @Column(nullable = false)
    private boolean read;

    @Column(nullable = false)
    private boolean deleted;

    public UserNotification() {
    }

    public UserNotification(UUID id) {
        this.id = id;
    }
}
