package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PaymentInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PaymentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {
}
