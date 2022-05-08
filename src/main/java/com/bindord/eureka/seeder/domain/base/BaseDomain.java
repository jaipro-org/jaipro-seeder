package com.bindord.eureka.seeder.domain.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class BaseDomain {

    @CreatedBy
    @Column(name = "createdBy", nullable = false, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedBy
    @Column(name = "modifiedBy")
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "modifiedDate")
    private LocalDateTime modifiedDate;

    public BaseDomain() {
    }

    public BaseDomain(String createdBy, LocalDateTime creationDate, String modifiedBy, LocalDateTime modifiedDate) {
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
