package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.VendorPaymentAccountDetails;
import com.mycompany.myapp.repository.VendorPaymentAccountDetailsRepository;

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

import com.mycompany.myapp.domain.enumeration.PaymentAccountType;
import com.mycompany.myapp.domain.enumeration.PaymentMethod;
/**
 * Integration tests for the {@link VendorPaymentAccountDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VendorPaymentAccountDetailsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final PaymentAccountType DEFAULT_TYPE = PaymentAccountType.BYBANKTRANSFERINADVANCE;
    private static final PaymentAccountType UPDATED_TYPE = PaymentAccountType.BYINVOICE;

    private static final PaymentMethod DEFAULT_METHOD = PaymentMethod.BANK;
    private static final PaymentMethod UPDATED_METHOD = PaymentMethod.CASH;

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_IBAN = "AAAAAAAAAA";
    private static final String UPDATED_IBAN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private VendorPaymentAccountDetailsRepository vendorPaymentAccountDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVendorPaymentAccountDetailsMockMvc;

    private VendorPaymentAccountDetails vendorPaymentAccountDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VendorPaymentAccountDetails createEntity(EntityManager em) {
        VendorPaymentAccountDetails vendorPaymentAccountDetails = new VendorPaymentAccountDetails()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .method(DEFAULT_METHOD)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .accountCode(DEFAULT_ACCOUNT_CODE)
            .accountKey(DEFAULT_ACCOUNT_KEY)
            .bankAccountType(DEFAULT_BANK_ACCOUNT_TYPE)
            .iban(DEFAULT_IBAN)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return vendorPaymentAccountDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VendorPaymentAccountDetails createUpdatedEntity(EntityManager em) {
        VendorPaymentAccountDetails vendorPaymentAccountDetails = new VendorPaymentAccountDetails()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .method(UPDATED_METHOD)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountCode(UPDATED_ACCOUNT_CODE)
            .accountKey(UPDATED_ACCOUNT_KEY)
            .bankAccountType(UPDATED_BANK_ACCOUNT_TYPE)
            .iban(UPDATED_IBAN)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return vendorPaymentAccountDetails;
    }

    @BeforeEach
    public void initTest() {
        vendorPaymentAccountDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createVendorPaymentAccountDetails() throws Exception {
        int databaseSizeBeforeCreate = vendorPaymentAccountDetailsRepository.findAll().size();
        // Create the VendorPaymentAccountDetails
        restVendorPaymentAccountDetailsMockMvc.perform(post("/api/vendor-payment-account-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendorPaymentAccountDetails)))
            .andExpect(status().isCreated());

        // Validate the VendorPaymentAccountDetails in the database
        List<VendorPaymentAccountDetails> vendorPaymentAccountDetailsList = vendorPaymentAccountDetailsRepository.findAll();
        assertThat(vendorPaymentAccountDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        VendorPaymentAccountDetails testVendorPaymentAccountDetails = vendorPaymentAccountDetailsList.get(vendorPaymentAccountDetailsList.size() - 1);
        assertThat(testVendorPaymentAccountDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVendorPaymentAccountDetails.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVendorPaymentAccountDetails.getMethod()).isEqualTo(DEFAULT_METHOD);
        assertThat(testVendorPaymentAccountDetails.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testVendorPaymentAccountDetails.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testVendorPaymentAccountDetails.getAccountCode()).isEqualTo(DEFAULT_ACCOUNT_CODE);
        assertThat(testVendorPaymentAccountDetails.getAccountKey()).isEqualTo(DEFAULT_ACCOUNT_KEY);
        assertThat(testVendorPaymentAccountDetails.getBankAccountType()).isEqualTo(DEFAULT_BANK_ACCOUNT_TYPE);
        assertThat(testVendorPaymentAccountDetails.getIban()).isEqualTo(DEFAULT_IBAN);
        assertThat(testVendorPaymentAccountDetails.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testVendorPaymentAccountDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testVendorPaymentAccountDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createVendorPaymentAccountDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vendorPaymentAccountDetailsRepository.findAll().size();

        // Create the VendorPaymentAccountDetails with an existing ID
        vendorPaymentAccountDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVendorPaymentAccountDetailsMockMvc.perform(post("/api/vendor-payment-account-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendorPaymentAccountDetails)))
            .andExpect(status().isBadRequest());

        // Validate the VendorPaymentAccountDetails in the database
        List<VendorPaymentAccountDetails> vendorPaymentAccountDetailsList = vendorPaymentAccountDetailsRepository.findAll();
        assertThat(vendorPaymentAccountDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVendorPaymentAccountDetails() throws Exception {
        // Initialize the database
        vendorPaymentAccountDetailsRepository.saveAndFlush(vendorPaymentAccountDetails);

        // Get all the vendorPaymentAccountDetailsList
        restVendorPaymentAccountDetailsMockMvc.perform(get("/api/vendor-payment-account-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vendorPaymentAccountDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].method").value(hasItem(DEFAULT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].accountCode").value(hasItem(DEFAULT_ACCOUNT_CODE)))
            .andExpect(jsonPath("$.[*].accountKey").value(hasItem(DEFAULT_ACCOUNT_KEY)))
            .andExpect(jsonPath("$.[*].bankAccountType").value(hasItem(DEFAULT_BANK_ACCOUNT_TYPE)))
            .andExpect(jsonPath("$.[*].iban").value(hasItem(DEFAULT_IBAN)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getVendorPaymentAccountDetails() throws Exception {
        // Initialize the database
        vendorPaymentAccountDetailsRepository.saveAndFlush(vendorPaymentAccountDetails);

        // Get the vendorPaymentAccountDetails
        restVendorPaymentAccountDetailsMockMvc.perform(get("/api/vendor-payment-account-details/{id}", vendorPaymentAccountDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vendorPaymentAccountDetails.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.method").value(DEFAULT_METHOD.toString()))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.accountCode").value(DEFAULT_ACCOUNT_CODE))
            .andExpect(jsonPath("$.accountKey").value(DEFAULT_ACCOUNT_KEY))
            .andExpect(jsonPath("$.bankAccountType").value(DEFAULT_BANK_ACCOUNT_TYPE))
            .andExpect(jsonPath("$.iban").value(DEFAULT_IBAN))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingVendorPaymentAccountDetails() throws Exception {
        // Get the vendorPaymentAccountDetails
        restVendorPaymentAccountDetailsMockMvc.perform(get("/api/vendor-payment-account-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVendorPaymentAccountDetails() throws Exception {
        // Initialize the database
        vendorPaymentAccountDetailsRepository.saveAndFlush(vendorPaymentAccountDetails);

        int databaseSizeBeforeUpdate = vendorPaymentAccountDetailsRepository.findAll().size();

        // Update the vendorPaymentAccountDetails
        VendorPaymentAccountDetails updatedVendorPaymentAccountDetails = vendorPaymentAccountDetailsRepository.findById(vendorPaymentAccountDetails.getId()).get();
        // Disconnect from session so that the updates on updatedVendorPaymentAccountDetails are not directly saved in db
        em.detach(updatedVendorPaymentAccountDetails);
        updatedVendorPaymentAccountDetails
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .method(UPDATED_METHOD)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountCode(UPDATED_ACCOUNT_CODE)
            .accountKey(UPDATED_ACCOUNT_KEY)
            .bankAccountType(UPDATED_BANK_ACCOUNT_TYPE)
            .iban(UPDATED_IBAN)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restVendorPaymentAccountDetailsMockMvc.perform(put("/api/vendor-payment-account-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVendorPaymentAccountDetails)))
            .andExpect(status().isOk());

        // Validate the VendorPaymentAccountDetails in the database
        List<VendorPaymentAccountDetails> vendorPaymentAccountDetailsList = vendorPaymentAccountDetailsRepository.findAll();
        assertThat(vendorPaymentAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
        VendorPaymentAccountDetails testVendorPaymentAccountDetails = vendorPaymentAccountDetailsList.get(vendorPaymentAccountDetailsList.size() - 1);
        assertThat(testVendorPaymentAccountDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVendorPaymentAccountDetails.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVendorPaymentAccountDetails.getMethod()).isEqualTo(UPDATED_METHOD);
        assertThat(testVendorPaymentAccountDetails.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testVendorPaymentAccountDetails.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testVendorPaymentAccountDetails.getAccountCode()).isEqualTo(UPDATED_ACCOUNT_CODE);
        assertThat(testVendorPaymentAccountDetails.getAccountKey()).isEqualTo(UPDATED_ACCOUNT_KEY);
        assertThat(testVendorPaymentAccountDetails.getBankAccountType()).isEqualTo(UPDATED_BANK_ACCOUNT_TYPE);
        assertThat(testVendorPaymentAccountDetails.getIban()).isEqualTo(UPDATED_IBAN);
        assertThat(testVendorPaymentAccountDetails.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testVendorPaymentAccountDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVendorPaymentAccountDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingVendorPaymentAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = vendorPaymentAccountDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVendorPaymentAccountDetailsMockMvc.perform(put("/api/vendor-payment-account-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendorPaymentAccountDetails)))
            .andExpect(status().isBadRequest());

        // Validate the VendorPaymentAccountDetails in the database
        List<VendorPaymentAccountDetails> vendorPaymentAccountDetailsList = vendorPaymentAccountDetailsRepository.findAll();
        assertThat(vendorPaymentAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVendorPaymentAccountDetails() throws Exception {
        // Initialize the database
        vendorPaymentAccountDetailsRepository.saveAndFlush(vendorPaymentAccountDetails);

        int databaseSizeBeforeDelete = vendorPaymentAccountDetailsRepository.findAll().size();

        // Delete the vendorPaymentAccountDetails
        restVendorPaymentAccountDetailsMockMvc.perform(delete("/api/vendor-payment-account-details/{id}", vendorPaymentAccountDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VendorPaymentAccountDetails> vendorPaymentAccountDetailsList = vendorPaymentAccountDetailsRepository.findAll();
        assertThat(vendorPaymentAccountDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
