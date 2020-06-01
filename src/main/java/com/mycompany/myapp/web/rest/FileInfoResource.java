package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.FileInfo;
import com.mycompany.myapp.repository.FileInfoRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.FileInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FileInfoResource {

    private final Logger log = LoggerFactory.getLogger(FileInfoResource.class);

    private static final String ENTITY_NAME = "fileInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileInfoRepository fileInfoRepository;

    public FileInfoResource(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    /**
     * {@code POST  /file-infos} : Create a new fileInfo.
     *
     * @param fileInfo the fileInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileInfo, or with status {@code 400 (Bad Request)} if the fileInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-infos")
    public ResponseEntity<FileInfo> createFileInfo(@RequestBody FileInfo fileInfo) throws URISyntaxException {
        log.debug("REST request to save FileInfo : {}", fileInfo);
        if (fileInfo.getId() != null) {
            throw new BadRequestAlertException("A new fileInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileInfo result = fileInfoRepository.save(fileInfo);
        return ResponseEntity.created(new URI("/api/file-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-infos} : Updates an existing fileInfo.
     *
     * @param fileInfo the fileInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileInfo,
     * or with status {@code 400 (Bad Request)} if the fileInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-infos")
    public ResponseEntity<FileInfo> updateFileInfo(@RequestBody FileInfo fileInfo) throws URISyntaxException {
        log.debug("REST request to update FileInfo : {}", fileInfo);
        if (fileInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileInfo result = fileInfoRepository.save(fileInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-infos} : get all the fileInfos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileInfos in body.
     */
    @GetMapping("/file-infos")
    public List<FileInfo> getAllFileInfos(@RequestParam(required = false) String filter) {
        if ("invoice-is-null".equals(filter)) {
            log.debug("REST request to get all FileInfos where invoice is null");
            return StreamSupport
                .stream(fileInfoRepository.findAll().spliterator(), false)
                .filter(fileInfo -> fileInfo.getInvoice() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all FileInfos");
        return fileInfoRepository.findAll();
    }

    /**
     * {@code GET  /file-infos/:id} : get the "id" fileInfo.
     *
     * @param id the id of the fileInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-infos/{id}")
    public ResponseEntity<FileInfo> getFileInfo(@PathVariable Long id) {
        log.debug("REST request to get FileInfo : {}", id);
        Optional<FileInfo> fileInfo = fileInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fileInfo);
    }

    /**
     * {@code DELETE  /file-infos/:id} : delete the "id" fileInfo.
     *
     * @param id the id of the fileInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-infos/{id}")
    public ResponseEntity<Void> deleteFileInfo(@PathVariable Long id) {
        log.debug("REST request to delete FileInfo : {}", id);

        fileInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
