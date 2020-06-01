package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.PaymentInfo;
import com.mycompany.myapp.repository.PaymentInfoRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.PaymentInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PaymentInfoResource {

    private final Logger log = LoggerFactory.getLogger(PaymentInfoResource.class);

    private static final String ENTITY_NAME = "paymentInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentInfoRepository paymentInfoRepository;

    public PaymentInfoResource(PaymentInfoRepository paymentInfoRepository) {
        this.paymentInfoRepository = paymentInfoRepository;
    }

    /**
     * {@code POST  /payment-infos} : Create a new paymentInfo.
     *
     * @param paymentInfo the paymentInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentInfo, or with status {@code 400 (Bad Request)} if the paymentInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-infos")
    public ResponseEntity<PaymentInfo> createPaymentInfo(@RequestBody PaymentInfo paymentInfo) throws URISyntaxException {
        log.debug("REST request to save PaymentInfo : {}", paymentInfo);
        if (paymentInfo.getId() != null) {
            throw new BadRequestAlertException("A new paymentInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentInfo result = paymentInfoRepository.save(paymentInfo);
        return ResponseEntity.created(new URI("/api/payment-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payment-infos} : Updates an existing paymentInfo.
     *
     * @param paymentInfo the paymentInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentInfo,
     * or with status {@code 400 (Bad Request)} if the paymentInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-infos")
    public ResponseEntity<PaymentInfo> updatePaymentInfo(@RequestBody PaymentInfo paymentInfo) throws URISyntaxException {
        log.debug("REST request to update PaymentInfo : {}", paymentInfo);
        if (paymentInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentInfo result = paymentInfoRepository.save(paymentInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payment-infos} : get all the paymentInfos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentInfos in body.
     */
    @GetMapping("/payment-infos")
    public List<PaymentInfo> getAllPaymentInfos(@RequestParam(required = false) String filter) {
        if ("invoice-is-null".equals(filter)) {
            log.debug("REST request to get all PaymentInfos where invoice is null");
            return StreamSupport
                .stream(paymentInfoRepository.findAll().spliterator(), false)
                .filter(paymentInfo -> paymentInfo.getInvoice() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all PaymentInfos");
        return paymentInfoRepository.findAll();
    }

    /**
     * {@code GET  /payment-infos/:id} : get the "id" paymentInfo.
     *
     * @param id the id of the paymentInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-infos/{id}")
    public ResponseEntity<PaymentInfo> getPaymentInfo(@PathVariable Long id) {
        log.debug("REST request to get PaymentInfo : {}", id);
        Optional<PaymentInfo> paymentInfo = paymentInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paymentInfo);
    }

    /**
     * {@code DELETE  /payment-infos/:id} : delete the "id" paymentInfo.
     *
     * @param id the id of the paymentInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-infos/{id}")
    public ResponseEntity<Void> deletePaymentInfo(@PathVariable Long id) {
        log.debug("REST request to delete PaymentInfo : {}", id);

        paymentInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
