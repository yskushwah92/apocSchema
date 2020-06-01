package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OCRRawExtraction;
import com.mycompany.myapp.repository.OCRRawExtractionRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OCRRawExtraction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OCRRawExtractionResource {

    private final Logger log = LoggerFactory.getLogger(OCRRawExtractionResource.class);

    private static final String ENTITY_NAME = "oCRRawExtraction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OCRRawExtractionRepository oCRRawExtractionRepository;

    public OCRRawExtractionResource(OCRRawExtractionRepository oCRRawExtractionRepository) {
        this.oCRRawExtractionRepository = oCRRawExtractionRepository;
    }

    /**
     * {@code POST  /ocr-raw-extractions} : Create a new oCRRawExtraction.
     *
     * @param oCRRawExtraction the oCRRawExtraction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new oCRRawExtraction, or with status {@code 400 (Bad Request)} if the oCRRawExtraction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ocr-raw-extractions")
    public ResponseEntity<OCRRawExtraction> createOCRRawExtraction(@RequestBody OCRRawExtraction oCRRawExtraction) throws URISyntaxException {
        log.debug("REST request to save OCRRawExtraction : {}", oCRRawExtraction);
        if (oCRRawExtraction.getId() != null) {
            throw new BadRequestAlertException("A new oCRRawExtraction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OCRRawExtraction result = oCRRawExtractionRepository.save(oCRRawExtraction);
        return ResponseEntity.created(new URI("/api/ocr-raw-extractions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ocr-raw-extractions} : Updates an existing oCRRawExtraction.
     *
     * @param oCRRawExtraction the oCRRawExtraction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oCRRawExtraction,
     * or with status {@code 400 (Bad Request)} if the oCRRawExtraction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the oCRRawExtraction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ocr-raw-extractions")
    public ResponseEntity<OCRRawExtraction> updateOCRRawExtraction(@RequestBody OCRRawExtraction oCRRawExtraction) throws URISyntaxException {
        log.debug("REST request to update OCRRawExtraction : {}", oCRRawExtraction);
        if (oCRRawExtraction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OCRRawExtraction result = oCRRawExtractionRepository.save(oCRRawExtraction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, oCRRawExtraction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ocr-raw-extractions} : get all the oCRRawExtractions.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oCRRawExtractions in body.
     */
    @GetMapping("/ocr-raw-extractions")
    public List<OCRRawExtraction> getAllOCRRawExtractions(@RequestParam(required = false) String filter) {
        if ("modelinfo-is-null".equals(filter)) {
            log.debug("REST request to get all OCRRawExtractions where modelInfo is null");
            return StreamSupport
                .stream(oCRRawExtractionRepository.findAll().spliterator(), false)
                .filter(oCRRawExtraction -> oCRRawExtraction.getModelInfo() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all OCRRawExtractions");
        return oCRRawExtractionRepository.findAll();
    }

    /**
     * {@code GET  /ocr-raw-extractions/:id} : get the "id" oCRRawExtraction.
     *
     * @param id the id of the oCRRawExtraction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the oCRRawExtraction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ocr-raw-extractions/{id}")
    public ResponseEntity<OCRRawExtraction> getOCRRawExtraction(@PathVariable Long id) {
        log.debug("REST request to get OCRRawExtraction : {}", id);
        Optional<OCRRawExtraction> oCRRawExtraction = oCRRawExtractionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(oCRRawExtraction);
    }

    /**
     * {@code DELETE  /ocr-raw-extractions/:id} : delete the "id" oCRRawExtraction.
     *
     * @param id the id of the oCRRawExtraction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ocr-raw-extractions/{id}")
    public ResponseEntity<Void> deleteOCRRawExtraction(@PathVariable Long id) {
        log.debug("REST request to delete OCRRawExtraction : {}", id);

        oCRRawExtractionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
