package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AuditLogDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AuditLogDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditLogDetailsRepository extends JpaRepository<AuditLogDetails, Long> {
}
