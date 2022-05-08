package com.bindord.eureka.seeder.domain.specialist;

import com.bindord.eureka.seeder.domain.Bank;
import com.bindord.eureka.seeder.domain.base.BaseDomain;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
public class SpecialistBankAccount extends BaseDomain {

    @Id
    @Column(name = "SpecialistBankAccountId")
    private UUID id;

    @NotBlank
    @Size(min = 12, max = 24)
    @Column(nullable = false, length = 24)
    private String accountNumber;

    @NotBlank
    @Size(min = 12, max = 30)
    @Column(nullable = false, length = 30)
    private String cci;

    @Schema(description = "Currency of the account. E.g: PEN(1), USD(2), CNY(3), etc")
    @NotNull
    @Min(1)
    @Max(13)
    @Column(nullable = false, updatable = false)
    private Integer currency;

    @Column
    private boolean preferred;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", nullable = false, updatable = false)
    private Specialist specialist;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BankId", nullable = false, updatable = false)
    private Bank bank;

}
