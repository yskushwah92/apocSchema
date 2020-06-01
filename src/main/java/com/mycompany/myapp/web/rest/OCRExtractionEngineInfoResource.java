package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OCRExtractionEngineInfo;
import com.mycompany.myapp.repository.OCRExtractionEngineInfoRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OCRExtractionEngineInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OCRExtractionEngineInfoResource {

    private final Logger log = LoggerFactory.getLogger(OCRExtractionEngineInfoResource.class);

    private static final String ENTITY_NAME = "oCRExtractionEngineInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OCRExtractionEngineInfoRepository oCRExtractionEngineInfoRepository;

    public OCRExtractionEngineInfoResource(OCRExtractionEngineInfoRepository oCRExtractionEngineInfoRepository) {
        this.oCRExtractionEngineInfoRepository = oCRExtractionEngineInfoRepository;
    }

    /**
     * {@code POST  /ocr-extraction-engine-infos} : Create a new oCRExtractionEngineInfo.
     *
     * @param oCRExtractionEngineInfo the oCRExtractionEngineInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new oCRExtractionEngineInfo, or with status {@code 400 (Bad Request)} if the oCRExtractionEngineInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ocr-extraction-engine-infos")
    public ResponseEntity<OCRExtractionEngineInfo> createOCRExtractionEngineInfo(@RequestBody OCRExtractionEngineInfo oCRExtractionEngineInfo) throws URISyntaxException {
        log.debug("REST request to save OCRExtractionEngineInfo : {}", oCRExtractionEngineInfo);
        if (oCRExtractionEngineInfo.getId() != null) {
            throw new BadRequestAlertException("A new oCRExtractionEngineInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OCRExtractionEngineInfo result = oCRExtractionEngineInfoRepository.save(oCRExtractionEngineInfo);
        return ResponseEntity.created(new URI("/api/ocr-extraction-engine-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ocr-extraction-engine-infos} : Updates an existing oCRExtractionEngineInfo.
     *
     * @param oCRExtractionEngineInfo the oCRExtractionEngineInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oCRExtractionEngineInfo,
     * or with status {@code 400 (Bad Request)} if the oCRExtractionEngineInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the oCRExtractionEngineInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ocr-extraction-engine-infos")
    public ResponseEntity<OCRExtractionEngineInfo> updateOCRExtractionEngineInfo(@RequestBody OCRExtractionEngineInfo oCRExtractionEngineInfo) throws URISyntaxException {
        log.debug("REST request to update OCRExtractionEngineInfo : {}", oCRExtractionEngineInfo);
        if (oCRExtractionEngineInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OCRExtractionEngineInfo result = oCRExtractionEngineInfoRepository.save(oCRExtractionEngineInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, oCRExtractionEngineInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ocr-extraction-engine-infos} : get all the oCRExtractionEngineInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oCRExtractionEngineInfos in body.
     */
    @GetMapping("/ocr-extraction-engine-infos")
    public List<OCRExtractionEngineInfo> getAllOCRExtractionEngineInfos() {
        log.debug("REST request to get all OCRExtractionEngineInfos");
        return oCRExtractionEngineInfoRepository.findAll();
    }

    /**
     * {@code GET  /ocr-extraction-engine-infos/:id} : get the "id" oCRExtractionEngineInfo.
     *
     * @param id the id of the oCRExtractionEngineInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the oCRExtractionEngineInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ocr-extraction-engine-infos/{id}")
    public ResponseEntity<OCRExtractionEngineInfo> getOCRExtractionEngineInfo(@PathVariable Long id) {
        log.debug("REST request to get OCRExtractionEngineInfo : {}", id);
        Optional<OCRExtractionEngineInfo> oCRExtractionEngineInfo = oCRExtractionEngineInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(oCRExtractionEngineInfo);
    }

    /**
     * {@code DELETE  /ocr-extraction-engine-infos/:id} : delete the "id" oCRExtractionEngineInfo.
     *
     * @param id the id of the oCRExtractionEngineInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ocr-extraction-engine-infos/{id}")
    public ResponseEntity<Void> deleteOCRExtractionEngineInfo(@PathVariable Long id) {
        log.debug("REST request to delete OCRExtractionEngineInfo : {}", id);

        oCRExtractionEngineInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
