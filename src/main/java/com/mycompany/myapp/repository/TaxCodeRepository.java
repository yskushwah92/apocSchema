package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TaxCode;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TaxCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxCodeRepository extends JpaRepository<TaxCode, Long> {
}
