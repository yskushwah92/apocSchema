package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MailBox;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MailBox entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MailBoxRepository extends JpaRepository<MailBox, Long> {
}
