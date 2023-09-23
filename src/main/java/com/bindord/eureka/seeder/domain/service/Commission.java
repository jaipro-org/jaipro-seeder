package com.bindord.eureka.seeder.domain.service;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Commission {

    @Id
    @Column(name = "CommissionId")
    private Integer id;

    @Schema(description = "Commission type")
    @NotNull
    @Min(1)
    @Max(10)
    private Integer commissionType;

    @Schema(description = "The comission value can be a number expressed in percentage or a integer number, it depends on comm")
    @Min(1)
    @Max(20)
    @Column(precision = 4, scale = 2, nullable = false)
    @Digits(integer = 4, fraction = 2)
    private Double comissionValue;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    public Commission() {
    }

    public Commission(Integer id) {
        this.id = id;
    }
}
