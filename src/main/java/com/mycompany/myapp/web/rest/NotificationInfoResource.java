package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.NotificationInfo;
import com.mycompany.myapp.repository.NotificationInfoRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.NotificationInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class NotificationInfoResource {

    private final Logger log = LoggerFactory.getLogger(NotificationInfoResource.class);

    private static final String ENTITY_NAME = "notificationInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificationInfoRepository notificationInfoRepository;

    public NotificationInfoResource(NotificationInfoRepository notificationInfoRepository) {
        this.notificationInfoRepository = notificationInfoRepository;
    }

    /**
     * {@code POST  /notification-infos} : Create a new notificationInfo.
     *
     * @param notificationInfo the notificationInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationInfo, or with status {@code 400 (Bad Request)} if the notificationInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notification-infos")
    public ResponseEntity<NotificationInfo> createNotificationInfo(@RequestBody NotificationInfo notificationInfo) throws URISyntaxException {
        log.debug("REST request to save NotificationInfo : {}", notificationInfo);
        if (notificationInfo.getId() != null) {
            throw new BadRequestAlertException("A new notificationInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationInfo result = notificationInfoRepository.save(notificationInfo);
        return ResponseEntity.created(new URI("/api/notification-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notification-infos} : Updates an existing notificationInfo.
     *
     * @param notificationInfo the notificationInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationInfo,
     * or with status {@code 400 (Bad Request)} if the notificationInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notification-infos")
    public ResponseEntity<NotificationInfo> updateNotificationInfo(@RequestBody NotificationInfo notificationInfo) throws URISyntaxException {
        log.debug("REST request to update NotificationInfo : {}", notificationInfo);
        if (notificationInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationInfo result = notificationInfoRepository.save(notificationInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notificationInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notification-infos} : get all the notificationInfos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificationInfos in body.
     */
    @GetMapping("/notification-infos")
    public List<NotificationInfo> getAllNotificationInfos(@RequestParam(required = false) String filter) {
        if ("invoice-is-null".equals(filter)) {
            log.debug("REST request to get all NotificationInfos where invoice is null");
            return StreamSupport
                .stream(notificationInfoRepository.findAll().spliterator(), false)
                .filter(notificationInfo -> notificationInfo.getInvoice() == null)
                .collect(Collectors.toList());
        }
        if ("notificationinfo-is-null".equals(filter)) {
            log.debug("REST request to get all NotificationInfos where notificationInfo is null");
            return StreamSupport
                .stream(notificationInfoRepository.findAll().spliterator(), false)
                .filter(notificationInfo -> notificationInfo.getNotificationInfo() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all NotificationInfos");
        return notificationInfoRepository.findAll();
    }

    /**
     * {@code GET  /notification-infos/:id} : get the "id" notificationInfo.
     *
     * @param id the id of the notificationInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notification-infos/{id}")
    public ResponseEntity<NotificationInfo> getNotificationInfo(@PathVariable Long id) {
        log.debug("REST request to get NotificationInfo : {}", id);
        Optional<NotificationInfo> notificationInfo = notificationInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(notificationInfo);
    }

    /**
     * {@code DELETE  /notification-infos/:id} : delete the "id" notificationInfo.
     *
     * @param id the id of the notificationInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notification-infos/{id}")
    public ResponseEntity<Void> deleteNotificationInfo(@PathVariable Long id) {
        log.debug("REST request to delete NotificationInfo : {}", id);

        notificationInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
