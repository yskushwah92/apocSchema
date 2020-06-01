package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PurchaseOrderHeader;
import com.mycompany.myapp.repository.PurchaseOrderHeaderRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.PurchaseOrderHeader}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PurchaseOrderHeaderResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderHeaderResource.class);

    private static final String ENTITY_NAME = "purchaseOrderHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderHeaderRepository purchaseOrderHeaderRepository;

    public PurchaseOrderHeaderResource(PurchaseOrderHeaderRepository purchaseOrderHeaderRepository) {
        this.purchaseOrderHeaderRepository = purchaseOrderHeaderRepository;
    }

    /**
     * {@code POST  /purchase-order-headers} : Create a new purchaseOrderHeader.
     *
     * @param purchaseOrderHeader the purchaseOrderHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrderHeader, or with status {@code 400 (Bad Request)} if the purchaseOrderHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-order-headers")
    public ResponseEntity<PurchaseOrderHeader> createPurchaseOrderHeader(@RequestBody PurchaseOrderHeader purchaseOrderHeader) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrderHeader : {}", purchaseOrderHeader);
        if (purchaseOrderHeader.getId() != null) {
            throw new BadRequestAlertException("A new purchaseOrderHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderHeader result = purchaseOrderHeaderRepository.save(purchaseOrderHeader);
        return ResponseEntity.created(new URI("/api/purchase-order-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-order-headers} : Updates an existing purchaseOrderHeader.
     *
     * @param purchaseOrderHeader the purchaseOrderHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderHeader,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-order-headers")
    public ResponseEntity<PurchaseOrderHeader> updatePurchaseOrderHeader(@RequestBody PurchaseOrderHeader purchaseOrderHeader) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrderHeader : {}", purchaseOrderHeader);
        if (purchaseOrderHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PurchaseOrderHeader result = purchaseOrderHeaderRepository.save(purchaseOrderHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, purchaseOrderHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /purchase-order-headers} : get all the purchaseOrderHeaders.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrderHeaders in body.
     */
    @GetMapping("/purchase-order-headers")
    public List<PurchaseOrderHeader> getAllPurchaseOrderHeaders(@RequestParam(required = false) String filter) {
        if ("purchaseorder-is-null".equals(filter)) {
            log.debug("REST request to get all PurchaseOrderHeaders where purchaseOrder is null");
            return StreamSupport
                .stream(purchaseOrderHeaderRepository.findAll().spliterator(), false)
                .filter(purchaseOrderHeader -> purchaseOrderHeader.getPurchaseOrder() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all PurchaseOrderHeaders");
        return purchaseOrderHeaderRepository.findAll();
    }

    /**
     * {@code GET  /purchase-order-headers/:id} : get the "id" purchaseOrderHeader.
     *
     * @param id the id of the purchaseOrderHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrderHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-order-headers/{id}")
    public ResponseEntity<PurchaseOrderHeader> getPurchaseOrderHeader(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrderHeader : {}", id);
        Optional<PurchaseOrderHeader> purchaseOrderHeader = purchaseOrderHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrderHeader);
    }

    /**
     * {@code DELETE  /purchase-order-headers/:id} : delete the "id" purchaseOrderHeader.
     *
     * @param id the id of the purchaseOrderHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-order-headers/{id}")
    public ResponseEntity<Void> deletePurchaseOrderHeader(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrderHeader : {}", id);

        purchaseOrderHeaderRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
