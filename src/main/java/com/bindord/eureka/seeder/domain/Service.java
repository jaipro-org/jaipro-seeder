package com.bindord.eureka.seeder.domain;

import com.bindord.eureka.seeder.domain.base.AuditingEntity;
import com.bindord.eureka.seeder.domain.json.SvcVal;
import com.bindord.eureka.seeder.json.JsonMoneyDoubleSimpleSerializer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@EqualsAndHashCode(callSuper = false)
public class Service extends AuditingEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid")
    @GenericGenerator(
            name = "pg-uuid",
            strategy = "uuid2",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "com.itsight.epay.rsrc.component.PostgreSQLUUIDGenerationStrategy")
    )
    @Column(name = "ServiceId")
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    @Size(min = 3)
    private String description;

    private Integer currency;

    @Column(nullable = false, length = 128)
    private String logo;

    @Column(nullable = false, length = 4)
    private String extension;

    @JsonSerialize(using = JsonMoneyDoubleSimpleSerializer.class)
    @Column(precision = 4, scale = 2, nullable = false, updatable = false)
    @Digits(integer = 4, fraction = 2)
    private Double cost;

    @Column(nullable = false)
    private boolean comissionUnique;

    @Column(nullable = true)
    private Integer comissionType;

    @Column(precision = 2, scale = 2, nullable = true)
    @Digits(integer = 2, fraction = 2)
    private Double comissionValue;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false, updatable = false)
    private boolean comissionAdvanced;

    @Column(nullable = false)
    private boolean fixedCost;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ServiceTypeId", updatable = false, nullable = false)
    private ServiceType serviceType;

    @Column
    private boolean flagPhone;

    @Column
    private boolean flagTelephone;

    @Column
    private boolean flagCode;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private List<SvcVal> validations;

    @Transient
    private UUID supplierId;

    @Transient
    private Integer serviceTypeId;

    @Transient
    private Integer serviceCategoryId;

    public Service() {

    }

    public Service(UUID id) {
        this.id = id;
    }
}
