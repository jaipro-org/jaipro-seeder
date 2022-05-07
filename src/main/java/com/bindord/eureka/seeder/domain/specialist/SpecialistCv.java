package com.bindord.eureka.seeder.domain.specialist;

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
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@TypeDefs({
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class SpecialistCv {

    @Id
    private UUID id;

    @Null
    @Size(min = 30, max = 1300)
    @Column(nullable = true, length = 1200)
    private String about;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private String country;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private String workLocations;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private String socialNetworks;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private String gallery;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private String profileImage;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private String experienceTimes;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "SpecialistId")
    private Specialist specialist;

}
