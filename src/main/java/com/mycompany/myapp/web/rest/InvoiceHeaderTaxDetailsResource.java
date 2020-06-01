package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.InvoiceHeaderTaxDetails;
import com.mycompany.myapp.repository.InvoiceHeaderTaxDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.InvoiceHeaderTaxDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InvoiceHeaderTaxDetailsResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceHeaderTaxDetailsResource.class);

    private static final String ENTITY_NAME = "invoiceHeaderTaxDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceHeaderTaxDetailsRepository invoiceHeaderTaxDetailsRepository;

    public InvoiceHeaderTaxDetailsResource(InvoiceHeaderTaxDetailsRepository invoiceHeaderTaxDetailsRepository) {
        this.invoiceHeaderTaxDetailsRepository = invoiceHeaderTaxDetailsRepository;
    }

    /**
     * {@code POST  /invoice-header-tax-details} : Create a new invoiceHeaderTaxDetails.
     *
     * @param invoiceHeaderTaxDetails the invoiceHeaderTaxDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceHeaderTaxDetails, or with status {@code 400 (Bad Request)} if the invoiceHeaderTaxDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-header-tax-details")
    public ResponseEntity<InvoiceHeaderTaxDetails> createInvoiceHeaderTaxDetails(@RequestBody InvoiceHeaderTaxDetails invoiceHeaderTaxDetails) throws URISyntaxException {
        log.debug("REST request to save InvoiceHeaderTaxDetails : {}", invoiceHeaderTaxDetails);
        if (invoiceHeaderTaxDetails.getId() != null) {
            throw new BadRequestAlertException("A new invoiceHeaderTaxDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceHeaderTaxDetails result = invoiceHeaderTaxDetailsRepository.save(invoiceHeaderTaxDetails);
        return ResponseEntity.created(new URI("/api/invoice-header-tax-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-header-tax-details} : Updates an existing invoiceHeaderTaxDetails.
     *
     * @param invoiceHeaderTaxDetails the invoiceHeaderTaxDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceHeaderTaxDetails,
     * or with status {@code 400 (Bad Request)} if the invoiceHeaderTaxDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceHeaderTaxDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-header-tax-details")
    public ResponseEntity<InvoiceHeaderTaxDetails> updateInvoiceHeaderTaxDetails(@RequestBody InvoiceHeaderTaxDetails invoiceHeaderTaxDetails) throws URISyntaxException {
        log.debug("REST request to update InvoiceHeaderTaxDetails : {}", invoiceHeaderTaxDetails);
        if (invoiceHeaderTaxDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoiceHeaderTaxDetails result = invoiceHeaderTaxDetailsRepository.save(invoiceHeaderTaxDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invoiceHeaderTaxDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invoice-header-tax-details} : get all the invoiceHeaderTaxDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceHeaderTaxDetails in body.
     */
    @GetMapping("/invoice-header-tax-details")
    public List<InvoiceHeaderTaxDetails> getAllInvoiceHeaderTaxDetails() {
        log.debug("REST request to get all InvoiceHeaderTaxDetails");
        return invoiceHeaderTaxDetailsRepository.findAll();
    }

    /**
     * {@code GET  /invoice-header-tax-details/:id} : get the "id" invoiceHeaderTaxDetails.
     *
     * @param id the id of the invoiceHeaderTaxDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceHeaderTaxDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-header-tax-details/{id}")
    public ResponseEntity<InvoiceHeaderTaxDetails> getInvoiceHeaderTaxDetails(@PathVariable Long id) {
        log.debug("REST request to get InvoiceHeaderTaxDetails : {}", id);
        Optional<InvoiceHeaderTaxDetails> invoiceHeaderTaxDetails = invoiceHeaderTaxDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(invoiceHeaderTaxDetails);
    }

    /**
     * {@code DELETE  /invoice-header-tax-details/:id} : delete the "id" invoiceHeaderTaxDetails.
     *
     * @param id the id of the invoiceHeaderTaxDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-header-tax-details/{id}")
    public ResponseEntity<Void> deleteInvoiceHeaderTaxDetails(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceHeaderTaxDetails : {}", id);

        invoiceHeaderTaxDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
