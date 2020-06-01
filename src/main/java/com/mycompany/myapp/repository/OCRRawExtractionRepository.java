package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OCRRawExtraction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OCRRawExtraction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OCRRawExtractionRepository extends JpaRepository<OCRRawExtraction, Long> {
}
