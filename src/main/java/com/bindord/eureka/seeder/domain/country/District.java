package com.bindord.eureka.seeder.domain.country;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class District {

    @Id
    @Column(name = "DistrictId")
    private Integer id;

    @Size(min = 2, max = 50)
    @Column(nullable = false, updatable = false, length = 50)
    private String name;

    @Min(1)
    @Max(10)
    @Column(nullable = true, length = 2)
    private Integer zone;

    @Schema(description = "Could be ubigeo code for Peru or another similar code for other countries")
    @NotBlank
    @Size(min = 3, max = 9)
    @Column(nullable = false, updatable = false, length = 9)
    private String numericCode;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryId")
    private Country country;

    public District() {
    }

}
