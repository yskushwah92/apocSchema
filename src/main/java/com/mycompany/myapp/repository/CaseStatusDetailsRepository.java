package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CaseStatusDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CaseStatusDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseStatusDetailsRepository extends JpaRepository<CaseStatusDetails, Long> {
}
