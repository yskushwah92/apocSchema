package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ModelInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ModelInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModelInfoRepository extends JpaRepository<ModelInfo, Long> {
}
