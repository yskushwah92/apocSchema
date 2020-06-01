package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ContactDetails;
import com.mycompany.myapp.repository.ContactDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ContactDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ContactDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ContactDetailsResource.class);

    private static final String ENTITY_NAME = "contactDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContactDetailsRepository contactDetailsRepository;

    public ContactDetailsResource(ContactDetailsRepository contactDetailsRepository) {
        this.contactDetailsRepository = contactDetailsRepository;
    }

    /**
     * {@code POST  /contact-details} : Create a new contactDetails.
     *
     * @param contactDetails the contactDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contactDetails, or with status {@code 400 (Bad Request)} if the contactDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contact-details")
    public ResponseEntity<ContactDetails> createContactDetails(@RequestBody ContactDetails contactDetails) throws URISyntaxException {
        log.debug("REST request to save ContactDetails : {}", contactDetails);
        if (contactDetails.getId() != null) {
            throw new BadRequestAlertException("A new contactDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContactDetails result = contactDetailsRepository.save(contactDetails);
        return ResponseEntity.created(new URI("/api/contact-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contact-details} : Updates an existing contactDetails.
     *
     * @param contactDetails the contactDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contactDetails,
     * or with status {@code 400 (Bad Request)} if the contactDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contactDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contact-details")
    public ResponseEntity<ContactDetails> updateContactDetails(@RequestBody ContactDetails contactDetails) throws URISyntaxException {
        log.debug("REST request to update ContactDetails : {}", contactDetails);
        if (contactDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContactDetails result = contactDetailsRepository.save(contactDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contactDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contact-details} : get all the contactDetails.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contactDetails in body.
     */
    @GetMapping("/contact-details")
    public List<ContactDetails> getAllContactDetails(@RequestParam(required = false) String filter) {
        if ("vendor-is-null".equals(filter)) {
            log.debug("REST request to get all ContactDetailss where vendor is null");
            return StreamSupport
                .stream(contactDetailsRepository.findAll().spliterator(), false)
                .filter(contactDetails -> contactDetails.getVendor() == null)
                .collect(Collectors.toList());
        }
        if ("organization-is-null".equals(filter)) {
            log.debug("REST request to get all ContactDetailss where organization is null");
            return StreamSupport
                .stream(contactDetailsRepository.findAll().spliterator(), false)
                .filter(contactDetails -> contactDetails.getOrganization() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all ContactDetails");
        return contactDetailsRepository.findAll();
    }

    /**
     * {@code GET  /contact-details/:id} : get the "id" contactDetails.
     *
     * @param id the id of the contactDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contact-details/{id}")
    public ResponseEntity<ContactDetails> getContactDetails(@PathVariable Long id) {
        log.debug("REST request to get ContactDetails : {}", id);
        Optional<ContactDetails> contactDetails = contactDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(contactDetails);
    }

    /**
     * {@code DELETE  /contact-details/:id} : delete the "id" contactDetails.
     *
     * @param id the id of the contactDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contact-details/{id}")
    public ResponseEntity<Void> deleteContactDetails(@PathVariable Long id) {
        log.debug("REST request to delete ContactDetails : {}", id);

        contactDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
