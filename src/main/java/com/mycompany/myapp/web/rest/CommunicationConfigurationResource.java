package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CommunicationConfiguration;
import com.mycompany.myapp.repository.CommunicationConfigurationRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CommunicationConfiguration}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CommunicationConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(CommunicationConfigurationResource.class);

    private static final String ENTITY_NAME = "communicationConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommunicationConfigurationRepository communicationConfigurationRepository;

    public CommunicationConfigurationResource(CommunicationConfigurationRepository communicationConfigurationRepository) {
        this.communicationConfigurationRepository = communicationConfigurationRepository;
    }

    /**
     * {@code POST  /communication-configurations} : Create a new communicationConfiguration.
     *
     * @param communicationConfiguration the communicationConfiguration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new communicationConfiguration, or with status {@code 400 (Bad Request)} if the communicationConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/communication-configurations")
    public ResponseEntity<CommunicationConfiguration> createCommunicationConfiguration(@RequestBody CommunicationConfiguration communicationConfiguration) throws URISyntaxException {
        log.debug("REST request to save CommunicationConfiguration : {}", communicationConfiguration);
        if (communicationConfiguration.getId() != null) {
            throw new BadRequestAlertException("A new communicationConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommunicationConfiguration result = communicationConfigurationRepository.save(communicationConfiguration);
        return ResponseEntity.created(new URI("/api/communication-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /communication-configurations} : Updates an existing communicationConfiguration.
     *
     * @param communicationConfiguration the communicationConfiguration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated communicationConfiguration,
     * or with status {@code 400 (Bad Request)} if the communicationConfiguration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the communicationConfiguration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/communication-configurations")
    public ResponseEntity<CommunicationConfiguration> updateCommunicationConfiguration(@RequestBody CommunicationConfiguration communicationConfiguration) throws URISyntaxException {
        log.debug("REST request to update CommunicationConfiguration : {}", communicationConfiguration);
        if (communicationConfiguration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommunicationConfiguration result = communicationConfigurationRepository.save(communicationConfiguration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, communicationConfiguration.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /communication-configurations} : get all the communicationConfigurations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of communicationConfigurations in body.
     */
    @GetMapping("/communication-configurations")
    public List<CommunicationConfiguration> getAllCommunicationConfigurations() {
        log.debug("REST request to get all CommunicationConfigurations");
        return communicationConfigurationRepository.findAll();
    }

    /**
     * {@code GET  /communication-configurations/:id} : get the "id" communicationConfiguration.
     *
     * @param id the id of the communicationConfiguration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the communicationConfiguration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/communication-configurations/{id}")
    public ResponseEntity<CommunicationConfiguration> getCommunicationConfiguration(@PathVariable Long id) {
        log.debug("REST request to get CommunicationConfiguration : {}", id);
        Optional<CommunicationConfiguration> communicationConfiguration = communicationConfigurationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(communicationConfiguration);
    }

    /**
     * {@code DELETE  /communication-configurations/:id} : delete the "id" communicationConfiguration.
     *
     * @param id the id of the communicationConfiguration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/communication-configurations/{id}")
    public ResponseEntity<Void> deleteCommunicationConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete CommunicationConfiguration : {}", id);

        communicationConfigurationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
