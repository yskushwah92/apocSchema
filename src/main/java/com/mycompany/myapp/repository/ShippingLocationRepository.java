package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ShippingLocation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ShippingLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShippingLocationRepository extends JpaRepository<ShippingLocation, Long> {
}
