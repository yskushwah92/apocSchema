package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.InvoiceHeaderTaxDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvoiceHeaderTaxDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceHeaderTaxDetailsRepository extends JpaRepository<InvoiceHeaderTaxDetails, Long> {
}
