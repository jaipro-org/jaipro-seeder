package com.bindord.eureka.seeder.domain.mail;

import com.bindord.eureka.seeder.domain.json.MailInput;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MailId")
    private Integer id;

    @Column(length = 128, nullable = false)
    private String subject;

    @Column(length = 2000, nullable = false)
    private String body;

    @Column(length = 128, nullable = true)
    private String description;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = true)
    private List<MailInput> inputs;

    public Mail() {
    }
}

