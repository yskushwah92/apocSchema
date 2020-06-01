package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ContactDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ContactDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {
}
