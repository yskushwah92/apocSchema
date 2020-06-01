package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.InvoiceStatusDetails;
import com.mycompany.myapp.repository.InvoiceStatusDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.InvoiceStatusDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InvoiceStatusDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceStatusDetailsResource.class);

    private static final String ENTITY_NAME = "invoiceStatusDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceStatusDetailsRepository invoiceStatusDetailsRepository;

    public InvoiceStatusDetailsResource(InvoiceStatusDetailsRepository invoiceStatusDetailsRepository) {
        this.invoiceStatusDetailsRepository = invoiceStatusDetailsRepository;
    }

    /**
     * {@code POST  /invoice-status-details} : Create a new invoiceStatusDetails.
     *
     * @param invoiceStatusDetails the invoiceStatusDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceStatusDetails, or with status {@code 400 (Bad Request)} if the invoiceStatusDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-status-details")
    public ResponseEntity<InvoiceStatusDetails> createInvoiceStatusDetails(@RequestBody InvoiceStatusDetails invoiceStatusDetails) throws URISyntaxException {
        log.debug("REST request to save InvoiceStatusDetails : {}", invoiceStatusDetails);
        if (invoiceStatusDetails.getId() != null) {
            throw new BadRequestAlertException("A new invoiceStatusDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceStatusDetails result = invoiceStatusDetailsRepository.save(invoiceStatusDetails);
        return ResponseEntity.created(new URI("/api/invoice-status-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-status-details} : Updates an existing invoiceStatusDetails.
     *
     * @param invoiceStatusDetails the invoiceStatusDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceStatusDetails,
     * or with status {@code 400 (Bad Request)} if the invoiceStatusDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceStatusDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-status-details")
    public ResponseEntity<InvoiceStatusDetails> updateInvoiceStatusDetails(@RequestBody InvoiceStatusDetails invoiceStatusDetails) throws URISyntaxException {
        log.debug("REST request to update InvoiceStatusDetails : {}", invoiceStatusDetails);
        if (invoiceStatusDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoiceStatusDetails result = invoiceStatusDetailsRepository.save(invoiceStatusDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invoiceStatusDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invoice-status-details} : get all the invoiceStatusDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceStatusDetails in body.
     */
    @GetMapping("/invoice-status-details")
    public List<InvoiceStatusDetails> getAllInvoiceStatusDetails() {
        log.debug("REST request to get all InvoiceStatusDetails");
        return invoiceStatusDetailsRepository.findAll();
    }

    /**
     * {@code GET  /invoice-status-details/:id} : get the "id" invoiceStatusDetails.
     *
     * @param id the id of the invoiceStatusDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceStatusDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-status-details/{id}")
    public ResponseEntity<InvoiceStatusDetails> getInvoiceStatusDetails(@PathVariable Long id) {
        log.debug("REST request to get InvoiceStatusDetails : {}", id);
        Optional<InvoiceStatusDetails> invoiceStatusDetails = invoiceStatusDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(invoiceStatusDetails);
    }

    /**
     * {@code DELETE  /invoice-status-details/:id} : delete the "id" invoiceStatusDetails.
     *
     * @param id the id of the invoiceStatusDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-status-details/{id}")
    public ResponseEntity<Void> deleteInvoiceStatusDetails(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceStatusDetails : {}", id);

        invoiceStatusDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
