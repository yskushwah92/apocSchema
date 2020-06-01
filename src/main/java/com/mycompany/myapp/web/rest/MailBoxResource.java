package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MailBox;
import com.mycompany.myapp.repository.MailBoxRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MailBox}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MailBoxResource {

    private final Logger log = LoggerFactory.getLogger(MailBoxResource.class);

    private static final String ENTITY_NAME = "mailBox";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MailBoxRepository mailBoxRepository;

    public MailBoxResource(MailBoxRepository mailBoxRepository) {
        this.mailBoxRepository = mailBoxRepository;
    }

    /**
     * {@code POST  /mail-boxes} : Create a new mailBox.
     *
     * @param mailBox the mailBox to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mailBox, or with status {@code 400 (Bad Request)} if the mailBox has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mail-boxes")
    public ResponseEntity<MailBox> createMailBox(@RequestBody MailBox mailBox) throws URISyntaxException {
        log.debug("REST request to save MailBox : {}", mailBox);
        if (mailBox.getId() != null) {
            throw new BadRequestAlertException("A new mailBox cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MailBox result = mailBoxRepository.save(mailBox);
        return ResponseEntity.created(new URI("/api/mail-boxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mail-boxes} : Updates an existing mailBox.
     *
     * @param mailBox the mailBox to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mailBox,
     * or with status {@code 400 (Bad Request)} if the mailBox is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mailBox couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mail-boxes")
    public ResponseEntity<MailBox> updateMailBox(@RequestBody MailBox mailBox) throws URISyntaxException {
        log.debug("REST request to update MailBox : {}", mailBox);
        if (mailBox.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MailBox result = mailBoxRepository.save(mailBox);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mailBox.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mail-boxes} : get all the mailBoxes.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mailBoxes in body.
     */
    @GetMapping("/mail-boxes")
    public List<MailBox> getAllMailBoxes(@RequestParam(required = false) String filter) {
        if ("communicationconfiguration-is-null".equals(filter)) {
            log.debug("REST request to get all MailBoxs where communicationConfiguration is null");
            return StreamSupport
                .stream(mailBoxRepository.findAll().spliterator(), false)
                .filter(mailBox -> mailBox.getCommunicationConfiguration() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all MailBoxes");
        return mailBoxRepository.findAll();
    }

    /**
     * {@code GET  /mail-boxes/:id} : get the "id" mailBox.
     *
     * @param id the id of the mailBox to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mailBox, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mail-boxes/{id}")
    public ResponseEntity<MailBox> getMailBox(@PathVariable Long id) {
        log.debug("REST request to get MailBox : {}", id);
        Optional<MailBox> mailBox = mailBoxRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mailBox);
    }

    /**
     * {@code DELETE  /mail-boxes/:id} : delete the "id" mailBox.
     *
     * @param id the id of the mailBox to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mail-boxes/{id}")
    public ResponseEntity<Void> deleteMailBox(@PathVariable Long id) {
        log.debug("REST request to delete MailBox : {}", id);

        mailBoxRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
