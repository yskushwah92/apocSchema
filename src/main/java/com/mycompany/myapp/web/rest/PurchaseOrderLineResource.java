package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PurchaseOrderLine;
import com.mycompany.myapp.repository.PurchaseOrderLineRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.PurchaseOrderLine}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PurchaseOrderLineResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderLineResource.class);

    private static final String ENTITY_NAME = "purchaseOrderLine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderLineRepository purchaseOrderLineRepository;

    public PurchaseOrderLineResource(PurchaseOrderLineRepository purchaseOrderLineRepository) {
        this.purchaseOrderLineRepository = purchaseOrderLineRepository;
    }

    /**
     * {@code POST  /purchase-order-lines} : Create a new purchaseOrderLine.
     *
     * @param purchaseOrderLine the purchaseOrderLine to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrderLine, or with status {@code 400 (Bad Request)} if the purchaseOrderLine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-order-lines")
    public ResponseEntity<PurchaseOrderLine> createPurchaseOrderLine(@RequestBody PurchaseOrderLine purchaseOrderLine) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrderLine : {}", purchaseOrderLine);
        if (purchaseOrderLine.getId() != null) {
            throw new BadRequestAlertException("A new purchaseOrderLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderLine result = purchaseOrderLineRepository.save(purchaseOrderLine);
        return ResponseEntity.created(new URI("/api/purchase-order-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-order-lines} : Updates an existing purchaseOrderLine.
     *
     * @param purchaseOrderLine the purchaseOrderLine to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderLine,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderLine is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderLine couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-order-lines")
    public ResponseEntity<PurchaseOrderLine> updatePurchaseOrderLine(@RequestBody PurchaseOrderLine purchaseOrderLine) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrderLine : {}", purchaseOrderLine);
        if (purchaseOrderLine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PurchaseOrderLine result = purchaseOrderLineRepository.save(purchaseOrderLine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, purchaseOrderLine.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /purchase-order-lines} : get all the purchaseOrderLines.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrderLines in body.
     */
    @GetMapping("/purchase-order-lines")
    public List<PurchaseOrderLine> getAllPurchaseOrderLines(@RequestParam(required = false) String filter) {
        if ("invoicelineitem-is-null".equals(filter)) {
            log.debug("REST request to get all PurchaseOrderLines where invoiceLineItem is null");
            return StreamSupport
                .stream(purchaseOrderLineRepository.findAll().spliterator(), false)
                .filter(purchaseOrderLine -> purchaseOrderLine.getInvoiceLineItem() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all PurchaseOrderLines");
        return purchaseOrderLineRepository.findAll();
    }

    /**
     * {@code GET  /purchase-order-lines/:id} : get the "id" purchaseOrderLine.
     *
     * @param id the id of the purchaseOrderLine to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrderLine, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-order-lines/{id}")
    public ResponseEntity<PurchaseOrderLine> getPurchaseOrderLine(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrderLine : {}", id);
        Optional<PurchaseOrderLine> purchaseOrderLine = purchaseOrderLineRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrderLine);
    }

    /**
     * {@code DELETE  /purchase-order-lines/:id} : delete the "id" purchaseOrderLine.
     *
     * @param id the id of the purchaseOrderLine to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-order-lines/{id}")
    public ResponseEntity<Void> deletePurchaseOrderLine(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrderLine : {}", id);

        purchaseOrderLineRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
