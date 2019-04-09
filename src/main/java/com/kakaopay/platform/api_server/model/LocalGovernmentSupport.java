package com.kakaopay.platform.api_server.model;

import com.kakaopay.platform.api_server.framework.PlatformEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "local_government_support")
public class LocalGovernmentSupport extends BaseAuditEntity implements PlatformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "local_government_code")
    private LocalGovernment localGovernment;

    @Column(name = "target")
    private String target;

    @Column(name = "support_usage")
    private String supportUsage;

    @Column(name = "support_limit")
    private Long supportLimit;

    @Column(name = "rate_min")
    private BigDecimal rateMin;

    @Column(name = "rate_max")
    private BigDecimal rateMax;

    @Column(name = "institute")
    private String institute;

    @Column(name = "mgmt")
    private String mgmt;

    @Column(name = "reception")
    private String reception;

}
