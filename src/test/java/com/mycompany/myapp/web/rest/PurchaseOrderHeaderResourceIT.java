package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.PurchaseOrderHeader;
import com.mycompany.myapp.repository.PurchaseOrderHeaderRepository;

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

import com.mycompany.myapp.domain.enumeration.POStatus;
/**
 * Integration tests for the {@link PurchaseOrderHeaderResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PurchaseOrderHeaderResourceIT {

    private static final String DEFAULT_SERIAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUESTED_BY = "AAAAAAAAAA";
    private static final String UPDATED_REQUESTED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_RECEIPT_REQUIRED = false;
    private static final Boolean UPDATED_RECEIPT_REQUIRED = true;

    private static final POStatus DEFAULT_STATUS = POStatus.CLOSED;
    private static final POStatus UPDATED_STATUS = POStatus.CANCELLED;

    private static final String DEFAULT_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private PurchaseOrderHeaderRepository purchaseOrderHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseOrderHeaderMockMvc;

    private PurchaseOrderHeader purchaseOrderHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderHeader createEntity(EntityManager em) {
        PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader()
            .serialNo(DEFAULT_SERIAL_NO)
            .amount(DEFAULT_AMOUNT)
            .type(DEFAULT_TYPE)
            .requestedBy(DEFAULT_REQUESTED_BY)
            .creationDate(DEFAULT_CREATION_DATE)
            .receiptRequired(DEFAULT_RECEIPT_REQUIRED)
            .status(DEFAULT_STATUS)
            .companyCode(DEFAULT_COMPANY_CODE)
            .currencyCode(DEFAULT_CURRENCY_CODE)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return purchaseOrderHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderHeader createUpdatedEntity(EntityManager em) {
        PurchaseOrderHeader purchaseOrderHeader = new PurchaseOrderHeader()
            .serialNo(UPDATED_SERIAL_NO)
            .amount(UPDATED_AMOUNT)
            .type(UPDATED_TYPE)
            .requestedBy(UPDATED_REQUESTED_BY)
            .creationDate(UPDATED_CREATION_DATE)
            .receiptRequired(UPDATED_RECEIPT_REQUIRED)
            .status(UPDATED_STATUS)
            .companyCode(UPDATED_COMPANY_CODE)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return purchaseOrderHeader;
    }

    @BeforeEach
    public void initTest() {
        purchaseOrderHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createPurchaseOrderHeader() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderHeaderRepository.findAll().size();
        // Create the PurchaseOrderHeader
        restPurchaseOrderHeaderMockMvc.perform(post("/api/purchase-order-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderHeader)))
            .andExpect(status().isCreated());

        // Validate the PurchaseOrderHeader in the database
        List<PurchaseOrderHeader> purchaseOrderHeaderList = purchaseOrderHeaderRepository.findAll();
        assertThat(purchaseOrderHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrderHeader testPurchaseOrderHeader = purchaseOrderHeaderList.get(purchaseOrderHeaderList.size() - 1);
        assertThat(testPurchaseOrderHeader.getSerialNo()).isEqualTo(DEFAULT_SERIAL_NO);
        assertThat(testPurchaseOrderHeader.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testPurchaseOrderHeader.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPurchaseOrderHeader.getRequestedBy()).isEqualTo(DEFAULT_REQUESTED_BY);
        assertThat(testPurchaseOrderHeader.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testPurchaseOrderHeader.isReceiptRequired()).isEqualTo(DEFAULT_RECEIPT_REQUIRED);
        assertThat(testPurchaseOrderHeader.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPurchaseOrderHeader.getCompanyCode()).isEqualTo(DEFAULT_COMPANY_CODE);
        assertThat(testPurchaseOrderHeader.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
        assertThat(testPurchaseOrderHeader.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPurchaseOrderHeader.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createPurchaseOrderHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderHeaderRepository.findAll().size();

        // Create the PurchaseOrderHeader with an existing ID
        purchaseOrderHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseOrderHeaderMockMvc.perform(post("/api/purchase-order-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderHeader)))
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderHeader in the database
        List<PurchaseOrderHeader> purchaseOrderHeaderList = purchaseOrderHeaderRepository.findAll();
        assertThat(purchaseOrderHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPurchaseOrderHeaders() throws Exception {
        // Initialize the database
        purchaseOrderHeaderRepository.saveAndFlush(purchaseOrderHeader);

        // Get all the purchaseOrderHeaderList
        restPurchaseOrderHeaderMockMvc.perform(get("/api/purchase-order-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseOrderHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].serialNo").value(hasItem(DEFAULT_SERIAL_NO)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].requestedBy").value(hasItem(DEFAULT_REQUESTED_BY)))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(sameInstant(DEFAULT_CREATION_DATE))))
            .andExpect(jsonPath("$.[*].receiptRequired").value(hasItem(DEFAULT_RECEIPT_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].companyCode").value(hasItem(DEFAULT_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getPurchaseOrderHeader() throws Exception {
        // Initialize the database
        purchaseOrderHeaderRepository.saveAndFlush(purchaseOrderHeader);

        // Get the purchaseOrderHeader
        restPurchaseOrderHeaderMockMvc.perform(get("/api/purchase-order-headers/{id}", purchaseOrderHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseOrderHeader.getId().intValue()))
            .andExpect(jsonPath("$.serialNo").value(DEFAULT_SERIAL_NO))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.requestedBy").value(DEFAULT_REQUESTED_BY))
            .andExpect(jsonPath("$.creationDate").value(sameInstant(DEFAULT_CREATION_DATE)))
            .andExpect(jsonPath("$.receiptRequired").value(DEFAULT_RECEIPT_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.companyCode").value(DEFAULT_COMPANY_CODE))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingPurchaseOrderHeader() throws Exception {
        // Get the purchaseOrderHeader
        restPurchaseOrderHeaderMockMvc.perform(get("/api/purchase-order-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePurchaseOrderHeader() throws Exception {
        // Initialize the database
        purchaseOrderHeaderRepository.saveAndFlush(purchaseOrderHeader);

        int databaseSizeBeforeUpdate = purchaseOrderHeaderRepository.findAll().size();

        // Update the purchaseOrderHeader
        PurchaseOrderHeader updatedPurchaseOrderHeader = purchaseOrderHeaderRepository.findById(purchaseOrderHeader.getId()).get();
        // Disconnect from session so that the updates on updatedPurchaseOrderHeader are not directly saved in db
        em.detach(updatedPurchaseOrderHeader);
        updatedPurchaseOrderHeader
            .serialNo(UPDATED_SERIAL_NO)
            .amount(UPDATED_AMOUNT)
            .type(UPDATED_TYPE)
            .requestedBy(UPDATED_REQUESTED_BY)
            .creationDate(UPDATED_CREATION_DATE)
            .receiptRequired(UPDATED_RECEIPT_REQUIRED)
            .status(UPDATED_STATUS)
            .companyCode(UPDATED_COMPANY_CODE)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restPurchaseOrderHeaderMockMvc.perform(put("/api/purchase-order-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPurchaseOrderHeader)))
            .andExpect(status().isOk());

        // Validate the PurchaseOrderHeader in the database
        List<PurchaseOrderHeader> purchaseOrderHeaderList = purchaseOrderHeaderRepository.findAll();
        assertThat(purchaseOrderHeaderList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderHeader testPurchaseOrderHeader = purchaseOrderHeaderList.get(purchaseOrderHeaderList.size() - 1);
        assertThat(testPurchaseOrderHeader.getSerialNo()).isEqualTo(UPDATED_SERIAL_NO);
        assertThat(testPurchaseOrderHeader.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testPurchaseOrderHeader.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPurchaseOrderHeader.getRequestedBy()).isEqualTo(UPDATED_REQUESTED_BY);
        assertThat(testPurchaseOrderHeader.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testPurchaseOrderHeader.isReceiptRequired()).isEqualTo(UPDATED_RECEIPT_REQUIRED);
        assertThat(testPurchaseOrderHeader.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPurchaseOrderHeader.getCompanyCode()).isEqualTo(UPDATED_COMPANY_CODE);
        assertThat(testPurchaseOrderHeader.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
        assertThat(testPurchaseOrderHeader.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPurchaseOrderHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingPurchaseOrderHeader() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderHeaderRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderHeaderMockMvc.perform(put("/api/purchase-order-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderHeader)))
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderHeader in the database
        List<PurchaseOrderHeader> purchaseOrderHeaderList = purchaseOrderHeaderRepository.findAll();
        assertThat(purchaseOrderHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePurchaseOrderHeader() throws Exception {
        // Initialize the database
        purchaseOrderHeaderRepository.saveAndFlush(purchaseOrderHeader);

        int databaseSizeBeforeDelete = purchaseOrderHeaderRepository.findAll().size();

        // Delete the purchaseOrderHeader
        restPurchaseOrderHeaderMockMvc.perform(delete("/api/purchase-order-headers/{id}", purchaseOrderHeader.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseOrderHeader> purchaseOrderHeaderList = purchaseOrderHeaderRepository.findAll();
        assertThat(purchaseOrderHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
