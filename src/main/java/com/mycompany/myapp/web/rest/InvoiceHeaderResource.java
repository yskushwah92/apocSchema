package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.InvoiceHeader;
import com.mycompany.myapp.repository.InvoiceHeaderRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.InvoiceHeader}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InvoiceHeaderResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceHeaderResource.class);

    private static final String ENTITY_NAME = "invoiceHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceHeaderRepository invoiceHeaderRepository;

    public InvoiceHeaderResource(InvoiceHeaderRepository invoiceHeaderRepository) {
        this.invoiceHeaderRepository = invoiceHeaderRepository;
    }

    /**
     * {@code POST  /invoice-headers} : Create a new invoiceHeader.
     *
     * @param invoiceHeader the invoiceHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceHeader, or with status {@code 400 (Bad Request)} if the invoiceHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-headers")
    public ResponseEntity<InvoiceHeader> createInvoiceHeader(@RequestBody InvoiceHeader invoiceHeader) throws URISyntaxException {
        log.debug("REST request to save InvoiceHeader : {}", invoiceHeader);
        if (invoiceHeader.getId() != null) {
            throw new BadRequestAlertException("A new invoiceHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceHeader result = invoiceHeaderRepository.save(invoiceHeader);
        return ResponseEntity.created(new URI("/api/invoice-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-headers} : Updates an existing invoiceHeader.
     *
     * @param invoiceHeader the invoiceHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceHeader,
     * or with status {@code 400 (Bad Request)} if the invoiceHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-headers")
    public ResponseEntity<InvoiceHeader> updateInvoiceHeader(@RequestBody InvoiceHeader invoiceHeader) throws URISyntaxException {
        log.debug("REST request to update InvoiceHeader : {}", invoiceHeader);
        if (invoiceHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoiceHeader result = invoiceHeaderRepository.save(invoiceHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invoiceHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invoice-headers} : get all the invoiceHeaders.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceHeaders in body.
     */
    @GetMapping("/invoice-headers")
    public List<InvoiceHeader> getAllInvoiceHeaders(@RequestParam(required = false) String filter) {
        if ("invoice-is-null".equals(filter)) {
            log.debug("REST request to get all InvoiceHeaders where invoice is null");
            return StreamSupport
                .stream(invoiceHeaderRepository.findAll().spliterator(), false)
                .filter(invoiceHeader -> invoiceHeader.getInvoice() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all InvoiceHeaders");
        return invoiceHeaderRepository.findAll();
    }

    /**
     * {@code GET  /invoice-headers/:id} : get the "id" invoiceHeader.
     *
     * @param id the id of the invoiceHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-headers/{id}")
    public ResponseEntity<InvoiceHeader> getInvoiceHeader(@PathVariable Long id) {
        log.debug("REST request to get InvoiceHeader : {}", id);
        Optional<InvoiceHeader> invoiceHeader = invoiceHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(invoiceHeader);
    }

    /**
     * {@code DELETE  /invoice-headers/:id} : delete the "id" invoiceHeader.
     *
     * @param id the id of the invoiceHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-headers/{id}")
    public ResponseEntity<Void> deleteInvoiceHeader(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceHeader : {}", id);

        invoiceHeaderRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
