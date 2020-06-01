package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OCRExtractionEngineInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OCRExtractionEngineInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OCRExtractionEngineInfoRepository extends JpaRepository<OCRExtractionEngineInfo, Long> {
}
