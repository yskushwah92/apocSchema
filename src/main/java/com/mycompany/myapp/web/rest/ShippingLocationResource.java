package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ShippingLocation;
import com.mycompany.myapp.repository.ShippingLocationRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ShippingLocation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ShippingLocationResource {

    private final Logger log = LoggerFactory.getLogger(ShippingLocationResource.class);

    private static final String ENTITY_NAME = "shippingLocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShippingLocationRepository shippingLocationRepository;

    public ShippingLocationResource(ShippingLocationRepository shippingLocationRepository) {
        this.shippingLocationRepository = shippingLocationRepository;
    }

    /**
     * {@code POST  /shipping-locations} : Create a new shippingLocation.
     *
     * @param shippingLocation the shippingLocation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new shippingLocation, or with status {@code 400 (Bad Request)} if the shippingLocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/shipping-locations")
    public ResponseEntity<ShippingLocation> createShippingLocation(@RequestBody ShippingLocation shippingLocation) throws URISyntaxException {
        log.debug("REST request to save ShippingLocation : {}", shippingLocation);
        if (shippingLocation.getId() != null) {
            throw new BadRequestAlertException("A new shippingLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShippingLocation result = shippingLocationRepository.save(shippingLocation);
        return ResponseEntity.created(new URI("/api/shipping-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /shipping-locations} : Updates an existing shippingLocation.
     *
     * @param shippingLocation the shippingLocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated shippingLocation,
     * or with status {@code 400 (Bad Request)} if the shippingLocation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the shippingLocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/shipping-locations")
    public ResponseEntity<ShippingLocation> updateShippingLocation(@RequestBody ShippingLocation shippingLocation) throws URISyntaxException {
        log.debug("REST request to update ShippingLocation : {}", shippingLocation);
        if (shippingLocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ShippingLocation result = shippingLocationRepository.save(shippingLocation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, shippingLocation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /shipping-locations} : get all the shippingLocations.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of shippingLocations in body.
     */
    @GetMapping("/shipping-locations")
    public List<ShippingLocation> getAllShippingLocations(@RequestParam(required = false) String filter) {
        if ("invoicelineitem-is-null".equals(filter)) {
            log.debug("REST request to get all ShippingLocations where invoiceLineItem is null");
            return StreamSupport
                .stream(shippingLocationRepository.findAll().spliterator(), false)
                .filter(shippingLocation -> shippingLocation.getInvoiceLineItem() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all ShippingLocations");
        return shippingLocationRepository.findAll();
    }

    /**
     * {@code GET  /shipping-locations/:id} : get the "id" shippingLocation.
     *
     * @param id the id of the shippingLocation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the shippingLocation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/shipping-locations/{id}")
    public ResponseEntity<ShippingLocation> getShippingLocation(@PathVariable Long id) {
        log.debug("REST request to get ShippingLocation : {}", id);
        Optional<ShippingLocation> shippingLocation = shippingLocationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(shippingLocation);
    }

    /**
     * {@code DELETE  /shipping-locations/:id} : delete the "id" shippingLocation.
     *
     * @param id the id of the shippingLocation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/shipping-locations/{id}")
    public ResponseEntity<Void> deleteShippingLocation(@PathVariable Long id) {
        log.debug("REST request to delete ShippingLocation : {}", id);

        shippingLocationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
