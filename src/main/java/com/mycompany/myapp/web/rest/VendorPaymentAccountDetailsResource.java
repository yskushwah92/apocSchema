package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.VendorPaymentAccountDetails;
import com.mycompany.myapp.repository.VendorPaymentAccountDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.VendorPaymentAccountDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VendorPaymentAccountDetailsResource {

    private final Logger log = LoggerFactory.getLogger(VendorPaymentAccountDetailsResource.class);

    private static final String ENTITY_NAME = "vendorPaymentAccountDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendorPaymentAccountDetailsRepository vendorPaymentAccountDetailsRepository;

    public VendorPaymentAccountDetailsResource(VendorPaymentAccountDetailsRepository vendorPaymentAccountDetailsRepository) {
        this.vendorPaymentAccountDetailsRepository = vendorPaymentAccountDetailsRepository;
    }

    /**
     * {@code POST  /vendor-payment-account-details} : Create a new vendorPaymentAccountDetails.
     *
     * @param vendorPaymentAccountDetails the vendorPaymentAccountDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendorPaymentAccountDetails, or with status {@code 400 (Bad Request)} if the vendorPaymentAccountDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vendor-payment-account-details")
    public ResponseEntity<VendorPaymentAccountDetails> createVendorPaymentAccountDetails(@RequestBody VendorPaymentAccountDetails vendorPaymentAccountDetails) throws URISyntaxException {
        log.debug("REST request to save VendorPaymentAccountDetails : {}", vendorPaymentAccountDetails);
        if (vendorPaymentAccountDetails.getId() != null) {
            throw new BadRequestAlertException("A new vendorPaymentAccountDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendorPaymentAccountDetails result = vendorPaymentAccountDetailsRepository.save(vendorPaymentAccountDetails);
        return ResponseEntity.created(new URI("/api/vendor-payment-account-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vendor-payment-account-details} : Updates an existing vendorPaymentAccountDetails.
     *
     * @param vendorPaymentAccountDetails the vendorPaymentAccountDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendorPaymentAccountDetails,
     * or with status {@code 400 (Bad Request)} if the vendorPaymentAccountDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendorPaymentAccountDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vendor-payment-account-details")
    public ResponseEntity<VendorPaymentAccountDetails> updateVendorPaymentAccountDetails(@RequestBody VendorPaymentAccountDetails vendorPaymentAccountDetails) throws URISyntaxException {
        log.debug("REST request to update VendorPaymentAccountDetails : {}", vendorPaymentAccountDetails);
        if (vendorPaymentAccountDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendorPaymentAccountDetails result = vendorPaymentAccountDetailsRepository.save(vendorPaymentAccountDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vendorPaymentAccountDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vendor-payment-account-details} : get all the vendorPaymentAccountDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendorPaymentAccountDetails in body.
     */
    @GetMapping("/vendor-payment-account-details")
    public List<VendorPaymentAccountDetails> getAllVendorPaymentAccountDetails() {
        log.debug("REST request to get all VendorPaymentAccountDetails");
        return vendorPaymentAccountDetailsRepository.findAll();
    }

    /**
     * {@code GET  /vendor-payment-account-details/:id} : get the "id" vendorPaymentAccountDetails.
     *
     * @param id the id of the vendorPaymentAccountDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendorPaymentAccountDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vendor-payment-account-details/{id}")
    public ResponseEntity<VendorPaymentAccountDetails> getVendorPaymentAccountDetails(@PathVariable Long id) {
        log.debug("REST request to get VendorPaymentAccountDetails : {}", id);
        Optional<VendorPaymentAccountDetails> vendorPaymentAccountDetails = vendorPaymentAccountDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vendorPaymentAccountDetails);
    }

    /**
     * {@code DELETE  /vendor-payment-account-details/:id} : delete the "id" vendorPaymentAccountDetails.
     *
     * @param id the id of the vendorPaymentAccountDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vendor-payment-account-details/{id}")
    public ResponseEntity<Void> deleteVendorPaymentAccountDetails(@PathVariable Long id) {
        log.debug("REST request to delete VendorPaymentAccountDetails : {}", id);

        vendorPaymentAccountDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
