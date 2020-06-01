package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.VendorPaymentAccountDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VendorPaymentAccountDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorPaymentAccountDetailsRepository extends JpaRepository<VendorPaymentAccountDetails, Long> {
}
