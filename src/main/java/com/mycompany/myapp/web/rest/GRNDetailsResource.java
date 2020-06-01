package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.GRNDetails;
import com.mycompany.myapp.repository.GRNDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.GRNDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GRNDetailsResource {

    private final Logger log = LoggerFactory.getLogger(GRNDetailsResource.class);

    private static final String ENTITY_NAME = "gRNDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GRNDetailsRepository gRNDetailsRepository;

    public GRNDetailsResource(GRNDetailsRepository gRNDetailsRepository) {
        this.gRNDetailsRepository = gRNDetailsRepository;
    }

    /**
     * {@code POST  /grn-details} : Create a new gRNDetails.
     *
     * @param gRNDetails the gRNDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gRNDetails, or with status {@code 400 (Bad Request)} if the gRNDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grn-details")
    public ResponseEntity<GRNDetails> createGRNDetails(@RequestBody GRNDetails gRNDetails) throws URISyntaxException {
        log.debug("REST request to save GRNDetails : {}", gRNDetails);
        if (gRNDetails.getId() != null) {
            throw new BadRequestAlertException("A new gRNDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GRNDetails result = gRNDetailsRepository.save(gRNDetails);
        return ResponseEntity.created(new URI("/api/grn-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grn-details} : Updates an existing gRNDetails.
     *
     * @param gRNDetails the gRNDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gRNDetails,
     * or with status {@code 400 (Bad Request)} if the gRNDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gRNDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grn-details")
    public ResponseEntity<GRNDetails> updateGRNDetails(@RequestBody GRNDetails gRNDetails) throws URISyntaxException {
        log.debug("REST request to update GRNDetails : {}", gRNDetails);
        if (gRNDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GRNDetails result = gRNDetailsRepository.save(gRNDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gRNDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /grn-details} : get all the gRNDetails.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gRNDetails in body.
     */
    @GetMapping("/grn-details")
    public List<GRNDetails> getAllGRNDetails(@RequestParam(required = false) String filter) {
        if ("invoiceheader-is-null".equals(filter)) {
            log.debug("REST request to get all GRNDetailss where invoiceHeader is null");
            return StreamSupport
                .stream(gRNDetailsRepository.findAll().spliterator(), false)
                .filter(gRNDetails -> gRNDetails.getInvoiceHeader() == null)
                .collect(Collectors.toList());
        }
        if ("purchaseorder-is-null".equals(filter)) {
            log.debug("REST request to get all GRNDetailss where purchaseOrder is null");
            return StreamSupport
                .stream(gRNDetailsRepository.findAll().spliterator(), false)
                .filter(gRNDetails -> gRNDetails.getPurchaseOrder() == null)
                .collect(Collectors.toList());
        }
        if ("invoicelineitem-is-null".equals(filter)) {
            log.debug("REST request to get all GRNDetailss where invoiceLineItem is null");
            return StreamSupport
                .stream(gRNDetailsRepository.findAll().spliterator(), false)
                .filter(gRNDetails -> gRNDetails.getInvoiceLineItem() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all GRNDetails");
        return gRNDetailsRepository.findAll();
    }

    /**
     * {@code GET  /grn-details/:id} : get the "id" gRNDetails.
     *
     * @param id the id of the gRNDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gRNDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grn-details/{id}")
    public ResponseEntity<GRNDetails> getGRNDetails(@PathVariable Long id) {
        log.debug("REST request to get GRNDetails : {}", id);
        Optional<GRNDetails> gRNDetails = gRNDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gRNDetails);
    }

    /**
     * {@code DELETE  /grn-details/:id} : delete the "id" gRNDetails.
     *
     * @param id the id of the gRNDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grn-details/{id}")
    public ResponseEntity<Void> deleteGRNDetails(@PathVariable Long id) {
        log.debug("REST request to delete GRNDetails : {}", id);

        gRNDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
