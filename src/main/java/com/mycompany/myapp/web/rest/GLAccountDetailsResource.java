package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.GLAccountDetails;
import com.mycompany.myapp.repository.GLAccountDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.GLAccountDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GLAccountDetailsResource {

    private final Logger log = LoggerFactory.getLogger(GLAccountDetailsResource.class);

    private static final String ENTITY_NAME = "gLAccountDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GLAccountDetailsRepository gLAccountDetailsRepository;

    public GLAccountDetailsResource(GLAccountDetailsRepository gLAccountDetailsRepository) {
        this.gLAccountDetailsRepository = gLAccountDetailsRepository;
    }

    /**
     * {@code POST  /gl-account-details} : Create a new gLAccountDetails.
     *
     * @param gLAccountDetails the gLAccountDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gLAccountDetails, or with status {@code 400 (Bad Request)} if the gLAccountDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gl-account-details")
    public ResponseEntity<GLAccountDetails> createGLAccountDetails(@RequestBody GLAccountDetails gLAccountDetails) throws URISyntaxException {
        log.debug("REST request to save GLAccountDetails : {}", gLAccountDetails);
        if (gLAccountDetails.getId() != null) {
            throw new BadRequestAlertException("A new gLAccountDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GLAccountDetails result = gLAccountDetailsRepository.save(gLAccountDetails);
        return ResponseEntity.created(new URI("/api/gl-account-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gl-account-details} : Updates an existing gLAccountDetails.
     *
     * @param gLAccountDetails the gLAccountDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gLAccountDetails,
     * or with status {@code 400 (Bad Request)} if the gLAccountDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gLAccountDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gl-account-details")
    public ResponseEntity<GLAccountDetails> updateGLAccountDetails(@RequestBody GLAccountDetails gLAccountDetails) throws URISyntaxException {
        log.debug("REST request to update GLAccountDetails : {}", gLAccountDetails);
        if (gLAccountDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GLAccountDetails result = gLAccountDetailsRepository.save(gLAccountDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gLAccountDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gl-account-details} : get all the gLAccountDetails.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gLAccountDetails in body.
     */
    @GetMapping("/gl-account-details")
    public List<GLAccountDetails> getAllGLAccountDetails(@RequestParam(required = false) String filter) {
        if ("vendor-is-null".equals(filter)) {
            log.debug("REST request to get all GLAccountDetailss where vendor is null");
            return StreamSupport
                .stream(gLAccountDetailsRepository.findAll().spliterator(), false)
                .filter(gLAccountDetails -> gLAccountDetails.getVendor() == null)
                .collect(Collectors.toList());
        }
        if ("purchaseorderline-is-null".equals(filter)) {
            log.debug("REST request to get all GLAccountDetailss where purchaseOrderLine is null");
            return StreamSupport
                .stream(gLAccountDetailsRepository.findAll().spliterator(), false)
                .filter(gLAccountDetails -> gLAccountDetails.getPurchaseOrderLine() == null)
                .collect(Collectors.toList());
        }
        if ("invoicelineitem-is-null".equals(filter)) {
            log.debug("REST request to get all GLAccountDetailss where invoiceLineItem is null");
            return StreamSupport
                .stream(gLAccountDetailsRepository.findAll().spliterator(), false)
                .filter(gLAccountDetails -> gLAccountDetails.getInvoiceLineItem() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all GLAccountDetails");
        return gLAccountDetailsRepository.findAll();
    }

    /**
     * {@code GET  /gl-account-details/:id} : get the "id" gLAccountDetails.
     *
     * @param id the id of the gLAccountDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gLAccountDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gl-account-details/{id}")
    public ResponseEntity<GLAccountDetails> getGLAccountDetails(@PathVariable Long id) {
        log.debug("REST request to get GLAccountDetails : {}", id);
        Optional<GLAccountDetails> gLAccountDetails = gLAccountDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gLAccountDetails);
    }

    /**
     * {@code DELETE  /gl-account-details/:id} : delete the "id" gLAccountDetails.
     *
     * @param id the id of the gLAccountDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gl-account-details/{id}")
    public ResponseEntity<Void> deleteGLAccountDetails(@PathVariable Long id) {
        log.debug("REST request to delete GLAccountDetails : {}", id);

        gLAccountDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
