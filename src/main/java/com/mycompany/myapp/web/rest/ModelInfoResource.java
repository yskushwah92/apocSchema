package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ModelInfo;
import com.mycompany.myapp.repository.ModelInfoRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ModelInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ModelInfoResource {

    private final Logger log = LoggerFactory.getLogger(ModelInfoResource.class);

    private static final String ENTITY_NAME = "modelInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModelInfoRepository modelInfoRepository;

    public ModelInfoResource(ModelInfoRepository modelInfoRepository) {
        this.modelInfoRepository = modelInfoRepository;
    }

    /**
     * {@code POST  /model-infos} : Create a new modelInfo.
     *
     * @param modelInfo the modelInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modelInfo, or with status {@code 400 (Bad Request)} if the modelInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/model-infos")
    public ResponseEntity<ModelInfo> createModelInfo(@RequestBody ModelInfo modelInfo) throws URISyntaxException {
        log.debug("REST request to save ModelInfo : {}", modelInfo);
        if (modelInfo.getId() != null) {
            throw new BadRequestAlertException("A new modelInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModelInfo result = modelInfoRepository.save(modelInfo);
        return ResponseEntity.created(new URI("/api/model-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /model-infos} : Updates an existing modelInfo.
     *
     * @param modelInfo the modelInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modelInfo,
     * or with status {@code 400 (Bad Request)} if the modelInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modelInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/model-infos")
    public ResponseEntity<ModelInfo> updateModelInfo(@RequestBody ModelInfo modelInfo) throws URISyntaxException {
        log.debug("REST request to update ModelInfo : {}", modelInfo);
        if (modelInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModelInfo result = modelInfoRepository.save(modelInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modelInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /model-infos} : get all the modelInfos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modelInfos in body.
     */
    @GetMapping("/model-infos")
    public List<ModelInfo> getAllModelInfos(@RequestParam(required = false) String filter) {
        if ("invoice-is-null".equals(filter)) {
            log.debug("REST request to get all ModelInfos where invoice is null");
            return StreamSupport
                .stream(modelInfoRepository.findAll().spliterator(), false)
                .filter(modelInfo -> modelInfo.getInvoice() == null)
                .collect(Collectors.toList());
        }
        if ("ocrextractionengineinfo-is-null".equals(filter)) {
            log.debug("REST request to get all ModelInfos where oCRExtractionEngineInfo is null");
            return StreamSupport
                .stream(modelInfoRepository.findAll().spliterator(), false)
                .filter(modelInfo -> modelInfo.getOCRExtractionEngineInfo() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all ModelInfos");
        return modelInfoRepository.findAll();
    }

    /**
     * {@code GET  /model-infos/:id} : get the "id" modelInfo.
     *
     * @param id the id of the modelInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modelInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/model-infos/{id}")
    public ResponseEntity<ModelInfo> getModelInfo(@PathVariable Long id) {
        log.debug("REST request to get ModelInfo : {}", id);
        Optional<ModelInfo> modelInfo = modelInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(modelInfo);
    }

    /**
     * {@code DELETE  /model-infos/:id} : delete the "id" modelInfo.
     *
     * @param id the id of the modelInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/model-infos/{id}")
    public ResponseEntity<Void> deleteModelInfo(@PathVariable Long id) {
        log.debug("REST request to delete ModelInfo : {}", id);

        modelInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
