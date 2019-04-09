package com.kakaopay.platform.api_server.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseAuditEntity {

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedDate;
}
