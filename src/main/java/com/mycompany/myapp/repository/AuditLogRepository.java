package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AuditLog;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
