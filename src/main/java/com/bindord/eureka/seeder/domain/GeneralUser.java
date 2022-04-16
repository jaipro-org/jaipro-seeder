package com.bindord.eureka.seeder.domain;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;


@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class)
})
@Entity
@Data
public class GeneralUser implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @GenericGenerator(
            name = "pg-uuid",
            strategy = "uuid2",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "com.bindord.eureka.seeder.component.PostgreSQLUUIDGenerationStrategy"
            )
    )
    @Column(name = "GeneralUserId")
    private UUID id;

    @NotBlank
    @Column(nullable = false, updatable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @Min(value = 1)
    @Max(value = 10)
    @Column(nullable = false, updatable = false, length = 10)
    private Integer profile;

    @Type(type = "string-array")
    @Column(columnDefinition = "text[]", nullable = false)
    private String[] roles;

    public GeneralUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username.toLowerCase();
    }

    public GeneralUser(UUID id) {
        this.id = id;
    }
}
