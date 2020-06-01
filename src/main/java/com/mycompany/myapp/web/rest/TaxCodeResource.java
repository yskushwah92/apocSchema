package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TaxCode;
import com.mycompany.myapp.repository.TaxCodeRepository;
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

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TaxCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TaxCodeResource {

    private final Logger log = LoggerFactory.getLogger(TaxCodeResource.class);

    private static final String ENTITY_NAME = "taxCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxCodeRepository taxCodeRepository;

    public TaxCodeResource(TaxCodeRepository taxCodeRepository) {
        this.taxCodeRepository = taxCodeRepository;
    }

    /**
     * {@code POST  /tax-codes} : Create a new taxCode.
     *
     * @param taxCode the taxCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxCode, or with status {@code 400 (Bad Request)} if the taxCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tax-codes")
    public ResponseEntity<TaxCode> createTaxCode(@RequestBody TaxCode taxCode) throws URISyntaxException {
        log.debug("REST request to save TaxCode : {}", taxCode);
        if (taxCode.getId() != null) {
            throw new BadRequestAlertException("A new taxCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaxCode result = taxCodeRepository.save(taxCode);
        return ResponseEntity.created(new URI("/api/tax-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tax-codes} : Updates an existing taxCode.
     *
     * @param taxCode the taxCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxCode,
     * or with status {@code 400 (Bad Request)} if the taxCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tax-codes")
    public ResponseEntity<TaxCode> updateTaxCode(@RequestBody TaxCode taxCode) throws URISyntaxException {
        log.debug("REST request to update TaxCode : {}", taxCode);
        if (taxCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaxCode result = taxCodeRepository.save(taxCode);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taxCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tax-codes} : get all the taxCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxCodes in body.
     */
    @GetMapping("/tax-codes")
    public List<TaxCode> getAllTaxCodes() {
        log.debug("REST request to get all TaxCodes");
        return taxCodeRepository.findAll();
    }

    /**
     * {@code GET  /tax-codes/:id} : get the "id" taxCode.
     *
     * @param id the id of the taxCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tax-codes/{id}")
    public ResponseEntity<TaxCode> getTaxCode(@PathVariable Long id) {
        log.debug("REST request to get TaxCode : {}", id);
        Optional<TaxCode> taxCode = taxCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(taxCode);
    }

    /**
     * {@code DELETE  /tax-codes/:id} : delete the "id" taxCode.
     *
     * @param id the id of the taxCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tax-codes/{id}")
    public ResponseEntity<Void> deleteTaxCode(@PathVariable Long id) {
        log.debug("REST request to delete TaxCode : {}", id);

        taxCodeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
