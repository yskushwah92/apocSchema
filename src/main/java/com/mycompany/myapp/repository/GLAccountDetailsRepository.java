package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.GLAccountDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GLAccountDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GLAccountDetailsRepository extends JpaRepository<GLAccountDetails, Long> {
}
