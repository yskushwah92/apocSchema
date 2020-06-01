package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CommunicationConfiguration;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CommunicationConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommunicationConfigurationRepository extends JpaRepository<CommunicationConfiguration, Long> {
}
