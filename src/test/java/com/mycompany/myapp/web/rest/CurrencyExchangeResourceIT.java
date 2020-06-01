package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.CurrencyExchange;
import com.mycompany.myapp.repository.CurrencyExchangeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.Currency;
import com.mycompany.myapp.domain.enumeration.Currency;
/**
 * Integration tests for the {@link CurrencyExchangeResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CurrencyExchangeResourceIT {

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final Currency DEFAULT_FROM_CURRENCY = Currency.INDIAN_RUPEE;
    private static final Currency UPDATED_FROM_CURRENCY = Currency.US_DOLLAR;

    private static final Currency DEFAULT_TO_CURRENCY = Currency.INDIAN_RUPEE;
    private static final Currency UPDATED_TO_CURRENCY = Currency.US_DOLLAR;

    private static final ZonedDateTime DEFAULT_VALIDFROM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_VALIDFROM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_EXCHANGE_RATE = 1D;
    private static final Double UPDATED_EXCHANGE_RATE = 2D;

    private static final String DEFAULT_EXCHANGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EXCHANGE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RATIO_FROM = "AAAAAAAAAA";
    private static final String UPDATED_RATIO_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_RATIO_TO = "AAAAAAAAAA";
    private static final String UPDATED_RATIO_TO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCurrencyExchangeMockMvc;

    private CurrencyExchange currencyExchange;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CurrencyExchange createEntity(EntityManager em) {
        CurrencyExchange currencyExchange = new CurrencyExchange()
            .country(DEFAULT_COUNTRY)
            .fromCurrency(DEFAULT_FROM_CURRENCY)
            .toCurrency(DEFAULT_TO_CURRENCY)
            .validfrom(DEFAULT_VALIDFROM)
            .exchangeRate(DEFAULT_EXCHANGE_RATE)
            .exchangeType(DEFAULT_EXCHANGE_TYPE)
            .ratioFrom(DEFAULT_RATIO_FROM)
            .ratioTo(DEFAULT_RATIO_TO)
            .isActive(DEFAULT_IS_ACTIVE)
            .isDeleted(DEFAULT_IS_DELETED)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return currencyExchange;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CurrencyExchange createUpdatedEntity(EntityManager em) {
        CurrencyExchange currencyExchange = new CurrencyExchange()
            .country(UPDATED_COUNTRY)
            .fromCurrency(UPDATED_FROM_CURRENCY)
            .toCurrency(UPDATED_TO_CURRENCY)
            .validfrom(UPDATED_VALIDFROM)
            .exchangeRate(UPDATED_EXCHANGE_RATE)
            .exchangeType(UPDATED_EXCHANGE_TYPE)
            .ratioFrom(UPDATED_RATIO_FROM)
            .ratioTo(UPDATED_RATIO_TO)
            .isActive(UPDATED_IS_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return currencyExchange;
    }

    @BeforeEach
    public void initTest() {
        currencyExchange = createEntity(em);
    }

    @Test
    @Transactional
    public void createCurrencyExchange() throws Exception {
        int databaseSizeBeforeCreate = currencyExchangeRepository.findAll().size();
        // Create the CurrencyExchange
        restCurrencyExchangeMockMvc.perform(post("/api/currency-exchanges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(currencyExchange)))
            .andExpect(status().isCreated());

        // Validate the CurrencyExchange in the database
        List<CurrencyExchange> currencyExchangeList = currencyExchangeRepository.findAll();
        assertThat(currencyExchangeList).hasSize(databaseSizeBeforeCreate + 1);
        CurrencyExchange testCurrencyExchange = currencyExchangeList.get(currencyExchangeList.size() - 1);
        assertThat(testCurrencyExchange.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testCurrencyExchange.getFromCurrency()).isEqualTo(DEFAULT_FROM_CURRENCY);
        assertThat(testCurrencyExchange.getToCurrency()).isEqualTo(DEFAULT_TO_CURRENCY);
        assertThat(testCurrencyExchange.getValidfrom()).isEqualTo(DEFAULT_VALIDFROM);
        assertThat(testCurrencyExchange.getExchangeRate()).isEqualTo(DEFAULT_EXCHANGE_RATE);
        assertThat(testCurrencyExchange.getExchangeType()).isEqualTo(DEFAULT_EXCHANGE_TYPE);
        assertThat(testCurrencyExchange.getRatioFrom()).isEqualTo(DEFAULT_RATIO_FROM);
        assertThat(testCurrencyExchange.getRatioTo()).isEqualTo(DEFAULT_RATIO_TO);
        assertThat(testCurrencyExchange.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testCurrencyExchange.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testCurrencyExchange.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCurrencyExchange.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createCurrencyExchangeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = currencyExchangeRepository.findAll().size();

        // Create the CurrencyExchange with an existing ID
        currencyExchange.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCurrencyExchangeMockMvc.perform(post("/api/currency-exchanges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(currencyExchange)))
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchange in the database
        List<CurrencyExchange> currencyExchangeList = currencyExchangeRepository.findAll();
        assertThat(currencyExchangeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCurrencyExchanges() throws Exception {
        // Initialize the database
        currencyExchangeRepository.saveAndFlush(currencyExchange);

        // Get all the currencyExchangeList
        restCurrencyExchangeMockMvc.perform(get("/api/currency-exchanges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(currencyExchange.getId().intValue())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].fromCurrency").value(hasItem(DEFAULT_FROM_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].toCurrency").value(hasItem(DEFAULT_TO_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].validfrom").value(hasItem(sameInstant(DEFAULT_VALIDFROM))))
            .andExpect(jsonPath("$.[*].exchangeRate").value(hasItem(DEFAULT_EXCHANGE_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].exchangeType").value(hasItem(DEFAULT_EXCHANGE_TYPE)))
            .andExpect(jsonPath("$.[*].ratioFrom").value(hasItem(DEFAULT_RATIO_FROM)))
            .andExpect(jsonPath("$.[*].ratioTo").value(hasItem(DEFAULT_RATIO_TO)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getCurrencyExchange() throws Exception {
        // Initialize the database
        currencyExchangeRepository.saveAndFlush(currencyExchange);

        // Get the currencyExchange
        restCurrencyExchangeMockMvc.perform(get("/api/currency-exchanges/{id}", currencyExchange.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(currencyExchange.getId().intValue()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.fromCurrency").value(DEFAULT_FROM_CURRENCY.toString()))
            .andExpect(jsonPath("$.toCurrency").value(DEFAULT_TO_CURRENCY.toString()))
            .andExpect(jsonPath("$.validfrom").value(sameInstant(DEFAULT_VALIDFROM)))
            .andExpect(jsonPath("$.exchangeRate").value(DEFAULT_EXCHANGE_RATE.doubleValue()))
            .andExpect(jsonPath("$.exchangeType").value(DEFAULT_EXCHANGE_TYPE))
            .andExpect(jsonPath("$.ratioFrom").value(DEFAULT_RATIO_FROM))
            .andExpect(jsonPath("$.ratioTo").value(DEFAULT_RATIO_TO))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingCurrencyExchange() throws Exception {
        // Get the currencyExchange
        restCurrencyExchangeMockMvc.perform(get("/api/currency-exchanges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurrencyExchange() throws Exception {
        // Initialize the database
        currencyExchangeRepository.saveAndFlush(currencyExchange);

        int databaseSizeBeforeUpdate = currencyExchangeRepository.findAll().size();

        // Update the currencyExchange
        CurrencyExchange updatedCurrencyExchange = currencyExchangeRepository.findById(currencyExchange.getId()).get();
        // Disconnect from session so that the updates on updatedCurrencyExchange are not directly saved in db
        em.detach(updatedCurrencyExchange);
        updatedCurrencyExchange
            .country(UPDATED_COUNTRY)
            .fromCurrency(UPDATED_FROM_CURRENCY)
            .toCurrency(UPDATED_TO_CURRENCY)
            .validfrom(UPDATED_VALIDFROM)
            .exchangeRate(UPDATED_EXCHANGE_RATE)
            .exchangeType(UPDATED_EXCHANGE_TYPE)
            .ratioFrom(UPDATED_RATIO_FROM)
            .ratioTo(UPDATED_RATIO_TO)
            .isActive(UPDATED_IS_ACTIVE)
            .isDeleted(UPDATED_IS_DELETED)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restCurrencyExchangeMockMvc.perform(put("/api/currency-exchanges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCurrencyExchange)))
            .andExpect(status().isOk());

        // Validate the CurrencyExchange in the database
        List<CurrencyExchange> currencyExchangeList = currencyExchangeRepository.findAll();
        assertThat(currencyExchangeList).hasSize(databaseSizeBeforeUpdate);
        CurrencyExchange testCurrencyExchange = currencyExchangeList.get(currencyExchangeList.size() - 1);
        assertThat(testCurrencyExchange.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testCurrencyExchange.getFromCurrency()).isEqualTo(UPDATED_FROM_CURRENCY);
        assertThat(testCurrencyExchange.getToCurrency()).isEqualTo(UPDATED_TO_CURRENCY);
        assertThat(testCurrencyExchange.getValidfrom()).isEqualTo(UPDATED_VALIDFROM);
        assertThat(testCurrencyExchange.getExchangeRate()).isEqualTo(UPDATED_EXCHANGE_RATE);
        assertThat(testCurrencyExchange.getExchangeType()).isEqualTo(UPDATED_EXCHANGE_TYPE);
        assertThat(testCurrencyExchange.getRatioFrom()).isEqualTo(UPDATED_RATIO_FROM);
        assertThat(testCurrencyExchange.getRatioTo()).isEqualTo(UPDATED_RATIO_TO);
        assertThat(testCurrencyExchange.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testCurrencyExchange.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testCurrencyExchange.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCurrencyExchange.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingCurrencyExchange() throws Exception {
        int databaseSizeBeforeUpdate = currencyExchangeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCurrencyExchangeMockMvc.perform(put("/api/currency-exchanges")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(currencyExchange)))
            .andExpect(status().isBadRequest());

        // Validate the CurrencyExchange in the database
        List<CurrencyExchange> currencyExchangeList = currencyExchangeRepository.findAll();
        assertThat(currencyExchangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCurrencyExchange() throws Exception {
        // Initialize the database
        currencyExchangeRepository.saveAndFlush(currencyExchange);

        int databaseSizeBeforeDelete = currencyExchangeRepository.findAll().size();

        // Delete the currencyExchange
        restCurrencyExchangeMockMvc.perform(delete("/api/currency-exchanges/{id}", currencyExchange.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CurrencyExchange> currencyExchangeList = currencyExchangeRepository.findAll();
        assertThat(currencyExchangeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
