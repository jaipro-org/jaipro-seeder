package com.bindord.eureka.seeder.domain.specialist;

import com.bindord.eureka.seeder.domain.jsonb.Rating;
import com.bindord.eureka.seeder.validation.ExtendedEmailValidator;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@TypeDefs({
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Specialist {

    @Id
    @Column(name = "SpecialistId")
    private UUID id;

    @NotBlank
    @Size(min = 2, max = 36)
    @Column(nullable = false, length = 36)
    private String name;

    @NotBlank
    @Size(min = 2, max = 36)
    @Column(nullable = false, length = 36)
    private String lastName;

    @Min(value = 0)
    @Column(nullable = true)
    private Integer identity;

    @Min(value = 1)
    @Max(value = 2)
    @Column(nullable = false)
    private Integer gender;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String district;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String country;

    @NotBlank
    @Size(min = 2, max = Byte.MAX_VALUE)
    @Column(nullable = false, length = Byte.MAX_VALUE)
    private String address;

    @ExtendedEmailValidator
    @NotBlank
    @Size(min = 7, max = 60)
    @Column(nullable = false, length = 60)
    private String email;

    @NotBlank
    @Size(min = 8, max = 12)
    @Column(nullable = false, length = 12, unique = true)
    private String document;

    @NotBlank
    @Size(min = 9, max = 15)
    @Column(nullable = false, length = 15)
    private String phone;

    @NotBlank
    @Size(min = 7, max = Byte.MAX_VALUE)
    @Column(nullable = false, length = Byte.MAX_VALUE)
    private String publicUrl;

    @Column(nullable = false)
    private boolean verified;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private List<Rating> ratings;

}
