package com.bindord.eureka.seeder.domain.notification;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@Entity
@Data
public class Notification extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationId")
    private Integer id;

    @Size(min = 6, max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String notificationType;

    @NotBlank
    @Size(min = 3, max = 600)
    @Column(nullable = false, length = 600)
    private String content;

    @NotBlank
    @Size(min = 3, max = 600)
    @Column(nullable = false, length = 600)
    private String description;

    private Boolean flagTriggerEmail;

    private Integer toProfileType;

    @Type(type = "string-array")
    @Column(
            name = "inputs",
            columnDefinition = "text[]"
    )
    private String[] inputs;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }
}
