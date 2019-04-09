package com.kakaopay.platform.api_server.repository;

import com.kakaopay.platform.api_server.model.LocalGovernment;
import com.kakaopay.platform.api_server.model.LocalGovernmentSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalGovernmentSupportRepository extends JpaRepository<LocalGovernmentSupport, Long> {

    LocalGovernmentSupport findByLocalGovernment(LocalGovernment localGovernment);

    @Query(value = "select * " +
            "from local_government_support " +
            "order by support_limit desc, (rate_min+rate_max)/2 asc " +
            "limit :limit ", nativeQuery = true)
    List<LocalGovernmentSupport> findByOrderBySupportLimitAndAverageRate(@Param("limit") Integer Limit);

    @Query(value = "select * " +
            "from local_government_support " +
            "where rate_min is not null " +
            "order by (rate_min+rate_max)/2 asc " +
            "limit 1 ", nativeQuery = true)
    LocalGovernmentSupport findTopByOrderByAverageRate();

}
