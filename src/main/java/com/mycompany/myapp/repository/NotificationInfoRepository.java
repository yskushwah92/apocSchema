package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.NotificationInfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NotificationInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationInfoRepository extends JpaRepository<NotificationInfo, Long> {
}
