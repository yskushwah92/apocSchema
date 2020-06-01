package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.FileSourceDetails;
import com.mycompany.myapp.repository.FileSourceDetailsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.FileSourceDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FileSourceDetailsResource {

    private final Logger log = LoggerFactory.getLogger(FileSourceDetailsResource.class);

    private static final String ENTITY_NAME = "fileSourceDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileSourceDetailsRepository fileSourceDetailsRepository;

    public FileSourceDetailsResource(FileSourceDetailsRepository fileSourceDetailsRepository) {
        this.fileSourceDetailsRepository = fileSourceDetailsRepository;
    }

    /**
     * {@code POST  /file-source-details} : Create a new fileSourceDetails.
     *
     * @param fileSourceDetails the fileSourceDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileSourceDetails, or with status {@code 400 (Bad Request)} if the fileSourceDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-source-details")
    public ResponseEntity<FileSourceDetails> createFileSourceDetails(@RequestBody FileSourceDetails fileSourceDetails) throws URISyntaxException {
        log.debug("REST request to save FileSourceDetails : {}", fileSourceDetails);
        if (fileSourceDetails.getId() != null) {
            throw new BadRequestAlertException("A new fileSourceDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileSourceDetails result = fileSourceDetailsRepository.save(fileSourceDetails);
        return ResponseEntity.created(new URI("/api/file-source-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-source-details} : Updates an existing fileSourceDetails.
     *
     * @param fileSourceDetails the fileSourceDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileSourceDetails,
     * or with status {@code 400 (Bad Request)} if the fileSourceDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileSourceDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-source-details")
    public ResponseEntity<FileSourceDetails> updateFileSourceDetails(@RequestBody FileSourceDetails fileSourceDetails) throws URISyntaxException {
        log.debug("REST request to update FileSourceDetails : {}", fileSourceDetails);
        if (fileSourceDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileSourceDetails result = fileSourceDetailsRepository.save(fileSourceDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileSourceDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-source-details} : get all the fileSourceDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileSourceDetails in body.
     */
    @GetMapping("/file-source-details")
    public List<FileSourceDetails> getAllFileSourceDetails() {
        log.debug("REST request to get all FileSourceDetails");
        return fileSourceDetailsRepository.findAll();
    }

    /**
     * {@code GET  /file-source-details/:id} : get the "id" fileSourceDetails.
     *
     * @param id the id of the fileSourceDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileSourceDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-source-details/{id}")
    public ResponseEntity<FileSourceDetails> getFileSourceDetails(@PathVariable Long id) {
        log.debug("REST request to get FileSourceDetails : {}", id);
        Optional<FileSourceDetails> fileSourceDetails = fileSourceDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fileSourceDetails);
    }

    /**
     * {@code DELETE  /file-source-details/:id} : delete the "id" fileSourceDetails.
     *
     * @param id the id of the fileSourceDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-source-details/{id}")
    public ResponseEntity<Void> deleteFileSourceDetails(@PathVariable Long id) {
        log.debug("REST request to delete FileSourceDetails : {}", id);

        fileSourceDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
