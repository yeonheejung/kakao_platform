package com.kakaopay.platform.api_server.repository;

import com.kakaopay.platform.api_server.model.LocalGovernment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalGovernmentRepository extends JpaRepository<LocalGovernment, String> {

    LocalGovernment findByLocalGovernmentName(String localGovernmentName);

}
