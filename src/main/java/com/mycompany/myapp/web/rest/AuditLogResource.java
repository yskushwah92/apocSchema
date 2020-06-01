package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.AuditLog;
import com.mycompany.myapp.repository.AuditLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.AuditLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AuditLogResource {

    private final Logger log = LoggerFactory.getLogger(AuditLogResource.class);

    private static final String ENTITY_NAME = "auditLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditLogRepository auditLogRepository;

    public AuditLogResource(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    /**
     * {@code POST  /audit-logs} : Create a new auditLog.
     *
     * @param auditLog the auditLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditLog, or with status {@code 400 (Bad Request)} if the auditLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/audit-logs")
    public ResponseEntity<AuditLog> createAuditLog(@RequestBody AuditLog auditLog) throws URISyntaxException {
        log.debug("REST request to save AuditLog : {}", auditLog);
        if (auditLog.getId() != null) {
            throw new BadRequestAlertException("A new auditLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuditLog result = auditLogRepository.save(auditLog);
        return ResponseEntity.created(new URI("/api/audit-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /audit-logs} : Updates an existing auditLog.
     *
     * @param auditLog the auditLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditLog,
     * or with status {@code 400 (Bad Request)} if the auditLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/audit-logs")
    public ResponseEntity<AuditLog> updateAuditLog(@RequestBody AuditLog auditLog) throws URISyntaxException {
        log.debug("REST request to update AuditLog : {}", auditLog);
        if (auditLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuditLog result = auditLogRepository.save(auditLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auditLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /audit-logs} : get all the auditLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditLogs in body.
     */
    @GetMapping("/audit-logs")
    public List<AuditLog> getAllAuditLogs() {
        log.debug("REST request to get all AuditLogs");
        return auditLogRepository.findAll();
    }

    /**
     * {@code GET  /audit-logs/:id} : get the "id" auditLog.
     *
     * @param id the id of the auditLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/audit-logs/{id}")
    public ResponseEntity<AuditLog> getAuditLog(@PathVariable Long id) {
        log.debug("REST request to get AuditLog : {}", id);
        Optional<AuditLog> auditLog = auditLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auditLog);
    }

    /**
     * {@code DELETE  /audit-logs/:id} : delete the "id" auditLog.
     *
     * @param id the id of the auditLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/audit-logs/{id}")
    public ResponseEntity<Void> deleteAuditLog(@PathVariable Long id) {
        log.debug("REST request to delete AuditLog : {}", id);

        auditLogRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
