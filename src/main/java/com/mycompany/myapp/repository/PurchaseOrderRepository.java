package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PurchaseOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the PurchaseOrder entity.
 */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query(value = "select distinct purchaseOrder from PurchaseOrder purchaseOrder left join fetch purchaseOrder.vendors",
        countQuery = "select count(distinct purchaseOrder) from PurchaseOrder purchaseOrder")
    Page<PurchaseOrder> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct purchaseOrder from PurchaseOrder purchaseOrder left join fetch purchaseOrder.vendors")
    List<PurchaseOrder> findAllWithEagerRelationships();

    @Query("select purchaseOrder from PurchaseOrder purchaseOrder left join fetch purchaseOrder.vendors where purchaseOrder.id =:id")
    Optional<PurchaseOrder> findOneWithEagerRelationships(@Param("id") Long id);
}
