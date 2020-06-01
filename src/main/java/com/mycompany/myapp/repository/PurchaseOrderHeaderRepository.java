package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PurchaseOrderHeader;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PurchaseOrderHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderHeaderRepository extends JpaRepository<PurchaseOrderHeader, Long> {
}
