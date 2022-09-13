package com.bindord.eureka.seeder.domain.country;

import com.bindord.eureka.seeder.domain.base.BaseDomain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class Country extends BaseDomain {

    @Id
    @Size(min = 3, max = 3)
    @Column(name = "CountryId", length = 3)
    @Schema(description = "Codes from ISO 3166-1, Alpha-3 code")
    private String id;

    @Size(min = 2, max = 50)
    @Column(nullable = false, updatable = false, length = 60)
    private String name;

    @Schema(description = "Numeric code from ISO 3166-1")
    @NotBlank
    @Size(min = 3, max = 3)
    @Column(nullable = false, updatable = false, length = 3)
    private String numericCode;

    public Country() {
    }

}
