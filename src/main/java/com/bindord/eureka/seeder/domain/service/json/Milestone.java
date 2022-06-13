package com.bindord.eureka.seeder.domain.service.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Milestone implements Serializable {

    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private Integer previousStatus;

    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private Integer currentStatus;

    @FutureOrPresent
    private LocalDateTime date;
}
