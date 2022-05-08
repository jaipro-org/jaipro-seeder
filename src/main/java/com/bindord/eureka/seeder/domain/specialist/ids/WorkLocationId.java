package com.bindord.eureka.seeder.domain.specialist.ids;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
public class WorkLocationId implements Serializable {

    @Column(name = "SpecialistId")
    private UUID specialistId;

    @Column(name = "DistrictId")
    private Integer districtId;

    @Column(name = "CountryId")
    private String countryId;

    public WorkLocationId() {
    }

    public WorkLocationId(UUID specialistId, Integer districtId, String countryId) {
        this.specialistId = specialistId;
        this.districtId = districtId;
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkLocationId that = (WorkLocationId) o;
        return getSpecialistId().equals(that.getSpecialistId()) &&
                getDistrictId().equals(that.getDistrictId()) &&
                getCountryId().equals(that.getCountryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecialistId(), getDistrictId(), getCountryId());
    }
}
