package com.bindord.eureka.seeder.domain.specialist;

import com.bindord.eureka.seeder.domain.country.District;
import com.bindord.eureka.seeder.domain.specialist.ids.WorkLocationId;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
@Data
public class WorkLocation {

    @EmbeddedId
    private WorkLocationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", insertable = false, updatable = false, nullable = false)
    private Specialist specialist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DistrictId", referencedColumnName = "DistrictId", insertable = false, updatable = false),
            @JoinColumn(name = "CountryId", referencedColumnName = "CountryId", insertable = false, updatable = false)
    })
    private District district;

}
