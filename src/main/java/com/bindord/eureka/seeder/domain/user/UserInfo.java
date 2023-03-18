package com.bindord.eureka.seeder.domain.user;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
public class UserInfo extends BaseDomain {

    @Id
    @Column(name = "UserInfoId")
    private UUID id;

    @NotNull
    @Min(value = 0)
    @Max(value = 99)
    @Column(nullable = false, updatable = false)
    private Integer profileType;

    @NotBlank
    @Size(min = 2, max = 36)
    @Column(nullable = false, updatable = false, length = 36)
    private String profileName;

    public UserInfo() {
    }

}
