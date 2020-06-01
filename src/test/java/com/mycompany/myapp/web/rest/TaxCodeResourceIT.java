package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.TaxCode;
import com.mycompany.myapp.repository.TaxCodeRepository;

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

/**
 * Integration tests for the {@link TaxCodeResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TaxCodeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_KEY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_CODE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TAX_CODE_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private TaxCodeRepository taxCodeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaxCodeMockMvc;

    private TaxCode taxCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxCode createEntity(EntityManager em) {
        TaxCode taxCode = new TaxCode()
            .name(DEFAULT_NAME)
            .country(DEFAULT_COUNTRY)
            .countryKey(DEFAULT_COUNTRY_KEY)
            .taxCode(DEFAULT_TAX_CODE)
            .description(DEFAULT_DESCRIPTION)
            .taxCodeDescription(DEFAULT_TAX_CODE_DESCRIPTION)
            .taxRate(DEFAULT_TAX_RATE)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return taxCode;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaxCode createUpdatedEntity(EntityManager em) {
        TaxCode taxCode = new TaxCode()
            .name(UPDATED_NAME)
            .country(UPDATED_COUNTRY)
            .countryKey(UPDATED_COUNTRY_KEY)
            .taxCode(UPDATED_TAX_CODE)
            .description(UPDATED_DESCRIPTION)
            .taxCodeDescription(UPDATED_TAX_CODE_DESCRIPTION)
            .taxRate(UPDATED_TAX_RATE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return taxCode;
    }

    @BeforeEach
    public void initTest() {
        taxCode = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaxCode() throws Exception {
        int databaseSizeBeforeCreate = taxCodeRepository.findAll().size();
        // Create the TaxCode
        restTaxCodeMockMvc.perform(post("/api/tax-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxCode)))
            .andExpect(status().isCreated());

        // Validate the TaxCode in the database
        List<TaxCode> taxCodeList = taxCodeRepository.findAll();
        assertThat(taxCodeList).hasSize(databaseSizeBeforeCreate + 1);
        TaxCode testTaxCode = taxCodeList.get(taxCodeList.size() - 1);
        assertThat(testTaxCode.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTaxCode.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testTaxCode.getCountryKey()).isEqualTo(DEFAULT_COUNTRY_KEY);
        assertThat(testTaxCode.getTaxCode()).isEqualTo(DEFAULT_TAX_CODE);
        assertThat(testTaxCode.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTaxCode.getTaxCodeDescription()).isEqualTo(DEFAULT_TAX_CODE_DESCRIPTION);
        assertThat(testTaxCode.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testTaxCode.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testTaxCode.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createTaxCodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taxCodeRepository.findAll().size();

        // Create the TaxCode with an existing ID
        taxCode.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxCodeMockMvc.perform(post("/api/tax-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxCode)))
            .andExpect(status().isBadRequest());

        // Validate the TaxCode in the database
        List<TaxCode> taxCodeList = taxCodeRepository.findAll();
        assertThat(taxCodeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTaxCodes() throws Exception {
        // Initialize the database
        taxCodeRepository.saveAndFlush(taxCode);

        // Get all the taxCodeList
        restTaxCodeMockMvc.perform(get("/api/tax-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taxCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].countryKey").value(hasItem(DEFAULT_COUNTRY_KEY)))
            .andExpect(jsonPath("$.[*].taxCode").value(hasItem(DEFAULT_TAX_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].taxCodeDescription").value(hasItem(DEFAULT_TAX_CODE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].taxRate").value(hasItem(DEFAULT_TAX_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getTaxCode() throws Exception {
        // Initialize the database
        taxCodeRepository.saveAndFlush(taxCode);

        // Get the taxCode
        restTaxCodeMockMvc.perform(get("/api/tax-codes/{id}", taxCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taxCode.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.countryKey").value(DEFAULT_COUNTRY_KEY))
            .andExpect(jsonPath("$.taxCode").value(DEFAULT_TAX_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.taxCodeDescription").value(DEFAULT_TAX_CODE_DESCRIPTION))
            .andExpect(jsonPath("$.taxRate").value(DEFAULT_TAX_RATE.doubleValue()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingTaxCode() throws Exception {
        // Get the taxCode
        restTaxCodeMockMvc.perform(get("/api/tax-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaxCode() throws Exception {
        // Initialize the database
        taxCodeRepository.saveAndFlush(taxCode);

        int databaseSizeBeforeUpdate = taxCodeRepository.findAll().size();

        // Update the taxCode
        TaxCode updatedTaxCode = taxCodeRepository.findById(taxCode.getId()).get();
        // Disconnect from session so that the updates on updatedTaxCode are not directly saved in db
        em.detach(updatedTaxCode);
        updatedTaxCode
            .name(UPDATED_NAME)
            .country(UPDATED_COUNTRY)
            .countryKey(UPDATED_COUNTRY_KEY)
            .taxCode(UPDATED_TAX_CODE)
            .description(UPDATED_DESCRIPTION)
            .taxCodeDescription(UPDATED_TAX_CODE_DESCRIPTION)
            .taxRate(UPDATED_TAX_RATE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restTaxCodeMockMvc.perform(put("/api/tax-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTaxCode)))
            .andExpect(status().isOk());

        // Validate the TaxCode in the database
        List<TaxCode> taxCodeList = taxCodeRepository.findAll();
        assertThat(taxCodeList).hasSize(databaseSizeBeforeUpdate);
        TaxCode testTaxCode = taxCodeList.get(taxCodeList.size() - 1);
        assertThat(testTaxCode.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTaxCode.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testTaxCode.getCountryKey()).isEqualTo(UPDATED_COUNTRY_KEY);
        assertThat(testTaxCode.getTaxCode()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testTaxCode.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTaxCode.getTaxCodeDescription()).isEqualTo(UPDATED_TAX_CODE_DESCRIPTION);
        assertThat(testTaxCode.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testTaxCode.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testTaxCode.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingTaxCode() throws Exception {
        int databaseSizeBeforeUpdate = taxCodeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxCodeMockMvc.perform(put("/api/tax-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxCode)))
            .andExpect(status().isBadRequest());

        // Validate the TaxCode in the database
        List<TaxCode> taxCodeList = taxCodeRepository.findAll();
        assertThat(taxCodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaxCode() throws Exception {
        // Initialize the database
        taxCodeRepository.saveAndFlush(taxCode);

        int databaseSizeBeforeDelete = taxCodeRepository.findAll().size();

        // Delete the taxCode
        restTaxCodeMockMvc.perform(delete("/api/tax-codes/{id}", taxCode.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TaxCode> taxCodeList = taxCodeRepository.findAll();
        assertThat(taxCodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
