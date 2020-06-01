package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CaseStatusDetails;
import com.mycompany.myapp.repository.CaseStatusDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CaseStatusDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CaseStatusDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CaseStatusDetailsResource.class);

    private static final String ENTITY_NAME = "caseStatusDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseStatusDetailsRepository caseStatusDetailsRepository;

    public CaseStatusDetailsResource(CaseStatusDetailsRepository caseStatusDetailsRepository) {
        this.caseStatusDetailsRepository = caseStatusDetailsRepository;
    }

    /**
     * {@code POST  /case-status-details} : Create a new caseStatusDetails.
     *
     * @param caseStatusDetails the caseStatusDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseStatusDetails, or with status {@code 400 (Bad Request)} if the caseStatusDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-status-details")
    public ResponseEntity<CaseStatusDetails> createCaseStatusDetails(@RequestBody CaseStatusDetails caseStatusDetails) throws URISyntaxException {
        log.debug("REST request to save CaseStatusDetails : {}", caseStatusDetails);
        if (caseStatusDetails.getId() != null) {
            throw new BadRequestAlertException("A new caseStatusDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseStatusDetails result = caseStatusDetailsRepository.save(caseStatusDetails);
        return ResponseEntity.created(new URI("/api/case-status-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-status-details} : Updates an existing caseStatusDetails.
     *
     * @param caseStatusDetails the caseStatusDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseStatusDetails,
     * or with status {@code 400 (Bad Request)} if the caseStatusDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseStatusDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-status-details")
    public ResponseEntity<CaseStatusDetails> updateCaseStatusDetails(@RequestBody CaseStatusDetails caseStatusDetails) throws URISyntaxException {
        log.debug("REST request to update CaseStatusDetails : {}", caseStatusDetails);
        if (caseStatusDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseStatusDetails result = caseStatusDetailsRepository.save(caseStatusDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseStatusDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-status-details} : get all the caseStatusDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseStatusDetails in body.
     */
    @GetMapping("/case-status-details")
    public List<CaseStatusDetails> getAllCaseStatusDetails() {
        log.debug("REST request to get all CaseStatusDetails");
        return caseStatusDetailsRepository.findAll();
    }

    /**
     * {@code GET  /case-status-details/:id} : get the "id" caseStatusDetails.
     *
     * @param id the id of the caseStatusDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseStatusDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-status-details/{id}")
    public ResponseEntity<CaseStatusDetails> getCaseStatusDetails(@PathVariable Long id) {
        log.debug("REST request to get CaseStatusDetails : {}", id);
        Optional<CaseStatusDetails> caseStatusDetails = caseStatusDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(caseStatusDetails);
    }

    /**
     * {@code DELETE  /case-status-details/:id} : delete the "id" caseStatusDetails.
     *
     * @param id the id of the caseStatusDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-status-details/{id}")
    public ResponseEntity<Void> deleteCaseStatusDetails(@PathVariable Long id) {
        log.debug("REST request to delete CaseStatusDetails : {}", id);

        caseStatusDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
