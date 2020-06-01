package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CurrencyExchange;
import com.mycompany.myapp.repository.CurrencyExchangeRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CurrencyExchange}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CurrencyExchangeResource {

    private final Logger log = LoggerFactory.getLogger(CurrencyExchangeResource.class);

    private static final String ENTITY_NAME = "currencyExchange";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeResource(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    /**
     * {@code POST  /currency-exchanges} : Create a new currencyExchange.
     *
     * @param currencyExchange the currencyExchange to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new currencyExchange, or with status {@code 400 (Bad Request)} if the currencyExchange has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/currency-exchanges")
    public ResponseEntity<CurrencyExchange> createCurrencyExchange(@RequestBody CurrencyExchange currencyExchange) throws URISyntaxException {
        log.debug("REST request to save CurrencyExchange : {}", currencyExchange);
        if (currencyExchange.getId() != null) {
            throw new BadRequestAlertException("A new currencyExchange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CurrencyExchange result = currencyExchangeRepository.save(currencyExchange);
        return ResponseEntity.created(new URI("/api/currency-exchanges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /currency-exchanges} : Updates an existing currencyExchange.
     *
     * @param currencyExchange the currencyExchange to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated currencyExchange,
     * or with status {@code 400 (Bad Request)} if the currencyExchange is not valid,
     * or with status {@code 500 (Internal Server Error)} if the currencyExchange couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/currency-exchanges")
    public ResponseEntity<CurrencyExchange> updateCurrencyExchange(@RequestBody CurrencyExchange currencyExchange) throws URISyntaxException {
        log.debug("REST request to update CurrencyExchange : {}", currencyExchange);
        if (currencyExchange.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CurrencyExchange result = currencyExchangeRepository.save(currencyExchange);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, currencyExchange.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /currency-exchanges} : get all the currencyExchanges.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of currencyExchanges in body.
     */
    @GetMapping("/currency-exchanges")
    public List<CurrencyExchange> getAllCurrencyExchanges() {
        log.debug("REST request to get all CurrencyExchanges");
        return currencyExchangeRepository.findAll();
    }

    /**
     * {@code GET  /currency-exchanges/:id} : get the "id" currencyExchange.
     *
     * @param id the id of the currencyExchange to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the currencyExchange, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/currency-exchanges/{id}")
    public ResponseEntity<CurrencyExchange> getCurrencyExchange(@PathVariable Long id) {
        log.debug("REST request to get CurrencyExchange : {}", id);
        Optional<CurrencyExchange> currencyExchange = currencyExchangeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(currencyExchange);
    }

    /**
     * {@code DELETE  /currency-exchanges/:id} : delete the "id" currencyExchange.
     *
     * @param id the id of the currencyExchange to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/currency-exchanges/{id}")
    public ResponseEntity<Void> deleteCurrencyExchange(@PathVariable Long id) {
        log.debug("REST request to delete CurrencyExchange : {}", id);

        currencyExchangeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
