package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.InvoiceStatusDetails;
import com.mycompany.myapp.repository.InvoiceStatusDetailsRepository;

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
 * Integration tests for the {@link InvoiceStatusDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvoiceStatusDetailsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_REASON = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_REASON = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private InvoiceStatusDetailsRepository invoiceStatusDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceStatusDetailsMockMvc;

    private InvoiceStatusDetails invoiceStatusDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceStatusDetails createEntity(EntityManager em) {
        InvoiceStatusDetails invoiceStatusDetails = new InvoiceStatusDetails()
            .name(DEFAULT_NAME)
            .currentStatus(DEFAULT_CURRENT_STATUS)
            .statusReason(DEFAULT_STATUS_REASON)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return invoiceStatusDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceStatusDetails createUpdatedEntity(EntityManager em) {
        InvoiceStatusDetails invoiceStatusDetails = new InvoiceStatusDetails()
            .name(UPDATED_NAME)
            .currentStatus(UPDATED_CURRENT_STATUS)
            .statusReason(UPDATED_STATUS_REASON)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return invoiceStatusDetails;
    }

    @BeforeEach
    public void initTest() {
        invoiceStatusDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceStatusDetails() throws Exception {
        int databaseSizeBeforeCreate = invoiceStatusDetailsRepository.findAll().size();
        // Create the InvoiceStatusDetails
        restInvoiceStatusDetailsMockMvc.perform(post("/api/invoice-status-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceStatusDetails)))
            .andExpect(status().isCreated());

        // Validate the InvoiceStatusDetails in the database
        List<InvoiceStatusDetails> invoiceStatusDetailsList = invoiceStatusDetailsRepository.findAll();
        assertThat(invoiceStatusDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceStatusDetails testInvoiceStatusDetails = invoiceStatusDetailsList.get(invoiceStatusDetailsList.size() - 1);
        assertThat(testInvoiceStatusDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testInvoiceStatusDetails.getCurrentStatus()).isEqualTo(DEFAULT_CURRENT_STATUS);
        assertThat(testInvoiceStatusDetails.getStatusReason()).isEqualTo(DEFAULT_STATUS_REASON);
        assertThat(testInvoiceStatusDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testInvoiceStatusDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createInvoiceStatusDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceStatusDetailsRepository.findAll().size();

        // Create the InvoiceStatusDetails with an existing ID
        invoiceStatusDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceStatusDetailsMockMvc.perform(post("/api/invoice-status-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceStatusDetails)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceStatusDetails in the database
        List<InvoiceStatusDetails> invoiceStatusDetailsList = invoiceStatusDetailsRepository.findAll();
        assertThat(invoiceStatusDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvoiceStatusDetails() throws Exception {
        // Initialize the database
        invoiceStatusDetailsRepository.saveAndFlush(invoiceStatusDetails);

        // Get all the invoiceStatusDetailsList
        restInvoiceStatusDetailsMockMvc.perform(get("/api/invoice-status-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceStatusDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].currentStatus").value(hasItem(DEFAULT_CURRENT_STATUS)))
            .andExpect(jsonPath("$.[*].statusReason").value(hasItem(DEFAULT_STATUS_REASON)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getInvoiceStatusDetails() throws Exception {
        // Initialize the database
        invoiceStatusDetailsRepository.saveAndFlush(invoiceStatusDetails);

        // Get the invoiceStatusDetails
        restInvoiceStatusDetailsMockMvc.perform(get("/api/invoice-status-details/{id}", invoiceStatusDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceStatusDetails.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.currentStatus").value(DEFAULT_CURRENT_STATUS))
            .andExpect(jsonPath("$.statusReason").value(DEFAULT_STATUS_REASON))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingInvoiceStatusDetails() throws Exception {
        // Get the invoiceStatusDetails
        restInvoiceStatusDetailsMockMvc.perform(get("/api/invoice-status-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceStatusDetails() throws Exception {
        // Initialize the database
        invoiceStatusDetailsRepository.saveAndFlush(invoiceStatusDetails);

        int databaseSizeBeforeUpdate = invoiceStatusDetailsRepository.findAll().size();

        // Update the invoiceStatusDetails
        InvoiceStatusDetails updatedInvoiceStatusDetails = invoiceStatusDetailsRepository.findById(invoiceStatusDetails.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceStatusDetails are not directly saved in db
        em.detach(updatedInvoiceStatusDetails);
        updatedInvoiceStatusDetails
            .name(UPDATED_NAME)
            .currentStatus(UPDATED_CURRENT_STATUS)
            .statusReason(UPDATED_STATUS_REASON)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restInvoiceStatusDetailsMockMvc.perform(put("/api/invoice-status-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceStatusDetails)))
            .andExpect(status().isOk());

        // Validate the InvoiceStatusDetails in the database
        List<InvoiceStatusDetails> invoiceStatusDetailsList = invoiceStatusDetailsRepository.findAll();
        assertThat(invoiceStatusDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceStatusDetails testInvoiceStatusDetails = invoiceStatusDetailsList.get(invoiceStatusDetailsList.size() - 1);
        assertThat(testInvoiceStatusDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testInvoiceStatusDetails.getCurrentStatus()).isEqualTo(UPDATED_CURRENT_STATUS);
        assertThat(testInvoiceStatusDetails.getStatusReason()).isEqualTo(UPDATED_STATUS_REASON);
        assertThat(testInvoiceStatusDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testInvoiceStatusDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceStatusDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceStatusDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceStatusDetailsMockMvc.perform(put("/api/invoice-status-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceStatusDetails)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceStatusDetails in the database
        List<InvoiceStatusDetails> invoiceStatusDetailsList = invoiceStatusDetailsRepository.findAll();
        assertThat(invoiceStatusDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoiceStatusDetails() throws Exception {
        // Initialize the database
        invoiceStatusDetailsRepository.saveAndFlush(invoiceStatusDetails);

        int databaseSizeBeforeDelete = invoiceStatusDetailsRepository.findAll().size();

        // Delete the invoiceStatusDetails
        restInvoiceStatusDetailsMockMvc.perform(delete("/api/invoice-status-details/{id}", invoiceStatusDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceStatusDetails> invoiceStatusDetailsList = invoiceStatusDetailsRepository.findAll();
        assertThat(invoiceStatusDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
