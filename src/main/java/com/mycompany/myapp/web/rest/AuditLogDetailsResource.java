package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.AuditLogDetails;
import com.mycompany.myapp.repository.AuditLogDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.AuditLogDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AuditLogDetailsResource {

    private final Logger log = LoggerFactory.getLogger(AuditLogDetailsResource.class);

    private static final String ENTITY_NAME = "auditLogDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditLogDetailsRepository auditLogDetailsRepository;

    public AuditLogDetailsResource(AuditLogDetailsRepository auditLogDetailsRepository) {
        this.auditLogDetailsRepository = auditLogDetailsRepository;
    }

    /**
     * {@code POST  /audit-log-details} : Create a new auditLogDetails.
     *
     * @param auditLogDetails the auditLogDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditLogDetails, or with status {@code 400 (Bad Request)} if the auditLogDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/audit-log-details")
    public ResponseEntity<AuditLogDetails> createAuditLogDetails(@RequestBody AuditLogDetails auditLogDetails) throws URISyntaxException {
        log.debug("REST request to save AuditLogDetails : {}", auditLogDetails);
        if (auditLogDetails.getId() != null) {
            throw new BadRequestAlertException("A new auditLogDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuditLogDetails result = auditLogDetailsRepository.save(auditLogDetails);
        return ResponseEntity.created(new URI("/api/audit-log-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /audit-log-details} : Updates an existing auditLogDetails.
     *
     * @param auditLogDetails the auditLogDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditLogDetails,
     * or with status {@code 400 (Bad Request)} if the auditLogDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditLogDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/audit-log-details")
    public ResponseEntity<AuditLogDetails> updateAuditLogDetails(@RequestBody AuditLogDetails auditLogDetails) throws URISyntaxException {
        log.debug("REST request to update AuditLogDetails : {}", auditLogDetails);
        if (auditLogDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuditLogDetails result = auditLogDetailsRepository.save(auditLogDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auditLogDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /audit-log-details} : get all the auditLogDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditLogDetails in body.
     */
    @GetMapping("/audit-log-details")
    public List<AuditLogDetails> getAllAuditLogDetails() {
        log.debug("REST request to get all AuditLogDetails");
        return auditLogDetailsRepository.findAll();
    }

    /**
     * {@code GET  /audit-log-details/:id} : get the "id" auditLogDetails.
     *
     * @param id the id of the auditLogDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditLogDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/audit-log-details/{id}")
    public ResponseEntity<AuditLogDetails> getAuditLogDetails(@PathVariable Long id) {
        log.debug("REST request to get AuditLogDetails : {}", id);
        Optional<AuditLogDetails> auditLogDetails = auditLogDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auditLogDetails);
    }

    /**
     * {@code DELETE  /audit-log-details/:id} : delete the "id" auditLogDetails.
     *
     * @param id the id of the auditLogDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/audit-log-details/{id}")
    public ResponseEntity<Void> deleteAuditLogDetails(@PathVariable Long id) {
        log.debug("REST request to delete AuditLogDetails : {}", id);

        auditLogDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
