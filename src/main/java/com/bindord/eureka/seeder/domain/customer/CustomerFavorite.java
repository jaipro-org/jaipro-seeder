package com.bindord.eureka.seeder.domain.customer;

import com.bindord.eureka.seeder.domain.customer.ids.CustomerSpecialistId;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;

@Entity
@Data
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class CustomerFavorite {

    @EmbeddedId
    private CustomerSpecialistId id;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialistId", insertable = false, updatable = false)
    private Specialist specialist;*/

    @Column(nullable = false)
    private boolean enabled;

    @Column
    @FutureOrPresent
    private LocalDateTime date;

}
