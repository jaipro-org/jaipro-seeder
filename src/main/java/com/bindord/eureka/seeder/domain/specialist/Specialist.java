package com.bindord.eureka.seeder.domain.specialist;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.bindord.eureka.seeder.domain.country.District;
import com.bindord.eureka.seeder.domain.json.Rating;
import com.bindord.eureka.seeder.validation.ExtendedEmailValidator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Specialist extends BaseDomain {

    @Id
    @Column(name = "SpecialistId", columnDefinition = "uuid DEFAULT gen_random_uuid()")
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
    @Size(min = 2, max = Byte.MAX_VALUE)
    @Column(nullable = false, length = Byte.MAX_VALUE)
    private String address;

    @ExtendedEmailValidator
    @NotBlank
    @Size(min = 7, max = 60)
    @Column(nullable = false, length = 60, unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 12)
    @Column(nullable = false, length = 12, unique = true)
    private String document;

    @NotBlank
    @Size(min = 9, max = 15)
    @Column(nullable = false, length = 15)
    private String phone;

    @Size(min = 9, max = 15)
    @Column(nullable = true, length = 15)
    private String secondaryPhone;

    @NotBlank
    @Size(min = 7, max = Byte.MAX_VALUE)
    @Column(nullable = false, length = Byte.MAX_VALUE)
    private String publicUrl;

    @Column(nullable = false)
    private boolean verified;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<Rating> ratings;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DistrictId", nullable = true)
    private District district;

    @JsonBackReference
    @OneToMany(mappedBy = "specialist")
    private List<WorkLocation> workLocations;

    @JsonBackReference
    @OneToMany(mappedBy = "specialist")
    private List<SpecialistSpecialization> specialistSpecializations;

}
