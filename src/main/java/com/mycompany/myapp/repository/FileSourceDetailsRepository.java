package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.FileSourceDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FileSourceDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileSourceDetailsRepository extends JpaRepository<FileSourceDetails, Long> {
}
