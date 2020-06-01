package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.InvoiceHeaderTaxDetails;
import com.mycompany.myapp.repository.InvoiceHeaderTaxDetailsRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InvoiceHeaderTaxDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvoiceHeaderTaxDetailsResourceIT {

    private static final String DEFAULT_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

    private static final Double DEFAULT_TAX_AMOUNT = 1D;
    private static final Double UPDATED_TAX_AMOUNT = 2D;

    private static final String DEFAULT_TAX_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_TAX_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_TAX_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_WHT_APPLICABLE = "AAAAAAAAAA";
    private static final String UPDATED_WHT_APPLICABLE = "BBBBBBBBBB";

    private static final String DEFAULT_WHT_GRP_ID = "AAAAAAAAAA";
    private static final String UPDATED_WHT_GRP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_WHT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_WHT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_WHT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WHT_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_WHT_AMOUNT = 1D;
    private static final Double UPDATED_WHT_AMOUNT = 2D;

    private static final Double DEFAULT_UNPLANNED_DELIVERY_TAX_RATE = 1D;
    private static final Double UPDATED_UNPLANNED_DELIVERY_TAX_RATE = 2D;

    private static final Double DEFAULT_UNPLANNED_DELIVERY_TAX_AMOUNT = 1D;
    private static final Double UPDATED_UNPLANNED_DELIVERY_TAX_AMOUNT = 2D;

    @Autowired
    private InvoiceHeaderTaxDetailsRepository invoiceHeaderTaxDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceHeaderTaxDetailsMockMvc;

    private InvoiceHeaderTaxDetails invoiceHeaderTaxDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceHeaderTaxDetails createEntity(EntityManager em) {
        InvoiceHeaderTaxDetails invoiceHeaderTaxDetails = new InvoiceHeaderTaxDetails()
            .taxCode(DEFAULT_TAX_CODE)
            .taxRate(DEFAULT_TAX_RATE)
            .taxAmount(DEFAULT_TAX_AMOUNT)
            .taxDescription(DEFAULT_TAX_DESCRIPTION)
            .taxDisplay(DEFAULT_TAX_DISPLAY)
            .whtApplicable(DEFAULT_WHT_APPLICABLE)
            .whtGrpId(DEFAULT_WHT_GRP_ID)
            .whtType(DEFAULT_WHT_TYPE)
            .whtCode(DEFAULT_WHT_CODE)
            .whtAmount(DEFAULT_WHT_AMOUNT)
            .unplannedDeliveryTaxRate(DEFAULT_UNPLANNED_DELIVERY_TAX_RATE)
            .unplannedDeliveryTaxAmount(DEFAULT_UNPLANNED_DELIVERY_TAX_AMOUNT);
        return invoiceHeaderTaxDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceHeaderTaxDetails createUpdatedEntity(EntityManager em) {
        InvoiceHeaderTaxDetails invoiceHeaderTaxDetails = new InvoiceHeaderTaxDetails()
            .taxCode(UPDATED_TAX_CODE)
            .taxRate(UPDATED_TAX_RATE)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .taxDescription(UPDATED_TAX_DESCRIPTION)
            .taxDisplay(UPDATED_TAX_DISPLAY)
            .whtApplicable(UPDATED_WHT_APPLICABLE)
            .whtGrpId(UPDATED_WHT_GRP_ID)
            .whtType(UPDATED_WHT_TYPE)
            .whtCode(UPDATED_WHT_CODE)
            .whtAmount(UPDATED_WHT_AMOUNT)
            .unplannedDeliveryTaxRate(UPDATED_UNPLANNED_DELIVERY_TAX_RATE)
            .unplannedDeliveryTaxAmount(UPDATED_UNPLANNED_DELIVERY_TAX_AMOUNT);
        return invoiceHeaderTaxDetails;
    }

    @BeforeEach
    public void initTest() {
        invoiceHeaderTaxDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceHeaderTaxDetails() throws Exception {
        int databaseSizeBeforeCreate = invoiceHeaderTaxDetailsRepository.findAll().size();
        // Create the InvoiceHeaderTaxDetails
        restInvoiceHeaderTaxDetailsMockMvc.perform(post("/api/invoice-header-tax-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceHeaderTaxDetails)))
            .andExpect(status().isCreated());

        // Validate the InvoiceHeaderTaxDetails in the database
        List<InvoiceHeaderTaxDetails> invoiceHeaderTaxDetailsList = invoiceHeaderTaxDetailsRepository.findAll();
        assertThat(invoiceHeaderTaxDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceHeaderTaxDetails testInvoiceHeaderTaxDetails = invoiceHeaderTaxDetailsList.get(invoiceHeaderTaxDetailsList.size() - 1);
        assertThat(testInvoiceHeaderTaxDetails.getTaxCode()).isEqualTo(DEFAULT_TAX_CODE);
        assertThat(testInvoiceHeaderTaxDetails.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testInvoiceHeaderTaxDetails.getTaxAmount()).isEqualTo(DEFAULT_TAX_AMOUNT);
        assertThat(testInvoiceHeaderTaxDetails.getTaxDescription()).isEqualTo(DEFAULT_TAX_DESCRIPTION);
        assertThat(testInvoiceHeaderTaxDetails.getTaxDisplay()).isEqualTo(DEFAULT_TAX_DISPLAY);
        assertThat(testInvoiceHeaderTaxDetails.getWhtApplicable()).isEqualTo(DEFAULT_WHT_APPLICABLE);
        assertThat(testInvoiceHeaderTaxDetails.getWhtGrpId()).isEqualTo(DEFAULT_WHT_GRP_ID);
        assertThat(testInvoiceHeaderTaxDetails.getWhtType()).isEqualTo(DEFAULT_WHT_TYPE);
        assertThat(testInvoiceHeaderTaxDetails.getWhtCode()).isEqualTo(DEFAULT_WHT_CODE);
        assertThat(testInvoiceHeaderTaxDetails.getWhtAmount()).isEqualTo(DEFAULT_WHT_AMOUNT);
        assertThat(testInvoiceHeaderTaxDetails.getUnplannedDeliveryTaxRate()).isEqualTo(DEFAULT_UNPLANNED_DELIVERY_TAX_RATE);
        assertThat(testInvoiceHeaderTaxDetails.getUnplannedDeliveryTaxAmount()).isEqualTo(DEFAULT_UNPLANNED_DELIVERY_TAX_AMOUNT);
    }

    @Test
    @Transactional
    public void createInvoiceHeaderTaxDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceHeaderTaxDetailsRepository.findAll().size();

        // Create the InvoiceHeaderTaxDetails with an existing ID
        invoiceHeaderTaxDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceHeaderTaxDetailsMockMvc.perform(post("/api/invoice-header-tax-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceHeaderTaxDetails)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceHeaderTaxDetails in the database
        List<InvoiceHeaderTaxDetails> invoiceHeaderTaxDetailsList = invoiceHeaderTaxDetailsRepository.findAll();
        assertThat(invoiceHeaderTaxDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvoiceHeaderTaxDetails() throws Exception {
        // Initialize the database
        invoiceHeaderTaxDetailsRepository.saveAndFlush(invoiceHeaderTaxDetails);

        // Get all the invoiceHeaderTaxDetailsList
        restInvoiceHeaderTaxDetailsMockMvc.perform(get("/api/invoice-header-tax-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceHeaderTaxDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].taxCode").value(hasItem(DEFAULT_TAX_CODE)))
            .andExpect(jsonPath("$.[*].taxRate").value(hasItem(DEFAULT_TAX_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].taxAmount").value(hasItem(DEFAULT_TAX_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].taxDescription").value(hasItem(DEFAULT_TAX_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].taxDisplay").value(hasItem(DEFAULT_TAX_DISPLAY)))
            .andExpect(jsonPath("$.[*].whtApplicable").value(hasItem(DEFAULT_WHT_APPLICABLE)))
            .andExpect(jsonPath("$.[*].whtGrpId").value(hasItem(DEFAULT_WHT_GRP_ID)))
            .andExpect(jsonPath("$.[*].whtType").value(hasItem(DEFAULT_WHT_TYPE)))
            .andExpect(jsonPath("$.[*].whtCode").value(hasItem(DEFAULT_WHT_CODE)))
            .andExpect(jsonPath("$.[*].whtAmount").value(hasItem(DEFAULT_WHT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].unplannedDeliveryTaxRate").value(hasItem(DEFAULT_UNPLANNED_DELIVERY_TAX_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].unplannedDeliveryTaxAmount").value(hasItem(DEFAULT_UNPLANNED_DELIVERY_TAX_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getInvoiceHeaderTaxDetails() throws Exception {
        // Initialize the database
        invoiceHeaderTaxDetailsRepository.saveAndFlush(invoiceHeaderTaxDetails);

        // Get the invoiceHeaderTaxDetails
        restInvoiceHeaderTaxDetailsMockMvc.perform(get("/api/invoice-header-tax-details/{id}", invoiceHeaderTaxDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceHeaderTaxDetails.getId().intValue()))
            .andExpect(jsonPath("$.taxCode").value(DEFAULT_TAX_CODE))
            .andExpect(jsonPath("$.taxRate").value(DEFAULT_TAX_RATE.doubleValue()))
            .andExpect(jsonPath("$.taxAmount").value(DEFAULT_TAX_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.taxDescription").value(DEFAULT_TAX_DESCRIPTION))
            .andExpect(jsonPath("$.taxDisplay").value(DEFAULT_TAX_DISPLAY))
            .andExpect(jsonPath("$.whtApplicable").value(DEFAULT_WHT_APPLICABLE))
            .andExpect(jsonPath("$.whtGrpId").value(DEFAULT_WHT_GRP_ID))
            .andExpect(jsonPath("$.whtType").value(DEFAULT_WHT_TYPE))
            .andExpect(jsonPath("$.whtCode").value(DEFAULT_WHT_CODE))
            .andExpect(jsonPath("$.whtAmount").value(DEFAULT_WHT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.unplannedDeliveryTaxRate").value(DEFAULT_UNPLANNED_DELIVERY_TAX_RATE.doubleValue()))
            .andExpect(jsonPath("$.unplannedDeliveryTaxAmount").value(DEFAULT_UNPLANNED_DELIVERY_TAX_AMOUNT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingInvoiceHeaderTaxDetails() throws Exception {
        // Get the invoiceHeaderTaxDetails
        restInvoiceHeaderTaxDetailsMockMvc.perform(get("/api/invoice-header-tax-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceHeaderTaxDetails() throws Exception {
        // Initialize the database
        invoiceHeaderTaxDetailsRepository.saveAndFlush(invoiceHeaderTaxDetails);

        int databaseSizeBeforeUpdate = invoiceHeaderTaxDetailsRepository.findAll().size();

        // Update the invoiceHeaderTaxDetails
        InvoiceHeaderTaxDetails updatedInvoiceHeaderTaxDetails = invoiceHeaderTaxDetailsRepository.findById(invoiceHeaderTaxDetails.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceHeaderTaxDetails are not directly saved in db
        em.detach(updatedInvoiceHeaderTaxDetails);
        updatedInvoiceHeaderTaxDetails
            .taxCode(UPDATED_TAX_CODE)
            .taxRate(UPDATED_TAX_RATE)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .taxDescription(UPDATED_TAX_DESCRIPTION)
            .taxDisplay(UPDATED_TAX_DISPLAY)
            .whtApplicable(UPDATED_WHT_APPLICABLE)
            .whtGrpId(UPDATED_WHT_GRP_ID)
            .whtType(UPDATED_WHT_TYPE)
            .whtCode(UPDATED_WHT_CODE)
            .whtAmount(UPDATED_WHT_AMOUNT)
            .unplannedDeliveryTaxRate(UPDATED_UNPLANNED_DELIVERY_TAX_RATE)
            .unplannedDeliveryTaxAmount(UPDATED_UNPLANNED_DELIVERY_TAX_AMOUNT);

        restInvoiceHeaderTaxDetailsMockMvc.perform(put("/api/invoice-header-tax-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceHeaderTaxDetails)))
            .andExpect(status().isOk());

        // Validate the InvoiceHeaderTaxDetails in the database
        List<InvoiceHeaderTaxDetails> invoiceHeaderTaxDetailsList = invoiceHeaderTaxDetailsRepository.findAll();
        assertThat(invoiceHeaderTaxDetailsList).hasSize(databaseSizeBeforeUpdate);
        InvoiceHeaderTaxDetails testInvoiceHeaderTaxDetails = invoiceHeaderTaxDetailsList.get(invoiceHeaderTaxDetailsList.size() - 1);
        assertThat(testInvoiceHeaderTaxDetails.getTaxCode()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testInvoiceHeaderTaxDetails.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testInvoiceHeaderTaxDetails.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
        assertThat(testInvoiceHeaderTaxDetails.getTaxDescription()).isEqualTo(UPDATED_TAX_DESCRIPTION);
        assertThat(testInvoiceHeaderTaxDetails.getTaxDisplay()).isEqualTo(UPDATED_TAX_DISPLAY);
        assertThat(testInvoiceHeaderTaxDetails.getWhtApplicable()).isEqualTo(UPDATED_WHT_APPLICABLE);
        assertThat(testInvoiceHeaderTaxDetails.getWhtGrpId()).isEqualTo(UPDATED_WHT_GRP_ID);
        assertThat(testInvoiceHeaderTaxDetails.getWhtType()).isEqualTo(UPDATED_WHT_TYPE);
        assertThat(testInvoiceHeaderTaxDetails.getWhtCode()).isEqualTo(UPDATED_WHT_CODE);
        assertThat(testInvoiceHeaderTaxDetails.getWhtAmount()).isEqualTo(UPDATED_WHT_AMOUNT);
        assertThat(testInvoiceHeaderTaxDetails.getUnplannedDeliveryTaxRate()).isEqualTo(UPDATED_UNPLANNED_DELIVERY_TAX_RATE);
        assertThat(testInvoiceHeaderTaxDetails.getUnplannedDeliveryTaxAmount()).isEqualTo(UPDATED_UNPLANNED_DELIVERY_TAX_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceHeaderTaxDetails() throws Exception {
        int databaseSizeBeforeUpdate = invoiceHeaderTaxDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceHeaderTaxDetailsMockMvc.perform(put("/api/invoice-header-tax-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceHeaderTaxDetails)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceHeaderTaxDetails in the database
        List<InvoiceHeaderTaxDetails> invoiceHeaderTaxDetailsList = invoiceHeaderTaxDetailsRepository.findAll();
        assertThat(invoiceHeaderTaxDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoiceHeaderTaxDetails() throws Exception {
        // Initialize the database
        invoiceHeaderTaxDetailsRepository.saveAndFlush(invoiceHeaderTaxDetails);

        int databaseSizeBeforeDelete = invoiceHeaderTaxDetailsRepository.findAll().size();

        // Delete the invoiceHeaderTaxDetails
        restInvoiceHeaderTaxDetailsMockMvc.perform(delete("/api/invoice-header-tax-details/{id}", invoiceHeaderTaxDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceHeaderTaxDetails> invoiceHeaderTaxDetailsList = invoiceHeaderTaxDetailsRepository.findAll();
        assertThat(invoiceHeaderTaxDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
