package com.kakaopay.platform.api_server.model;

import com.kakaopay.platform.api_server.framework.PlatformEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "local_government")
public class LocalGovernment extends BaseAuditEntity implements PlatformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "local_government_code")
    private Long localGovernmentCode;

    @Column(name = "local_government_name")
    private String localGovernmentName;

}
