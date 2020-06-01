package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.InvoiceStatusDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvoiceStatusDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceStatusDetailsRepository extends JpaRepository<InvoiceStatusDetails, Long> {
}
