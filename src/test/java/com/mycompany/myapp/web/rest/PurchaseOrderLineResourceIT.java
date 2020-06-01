package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.PurchaseOrderLine;
import com.mycompany.myapp.repository.PurchaseOrderLineRepository;

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
/**
 * Integration tests for the {@link PurchaseOrderLineResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PurchaseOrderLineResourceIT {

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_ARTICLE_NO = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_QTY = 1D;
    private static final Double UPDATED_QTY = 2D;

    private static final Double DEFAULT_RECEIVED_QTY = 1D;
    private static final Double UPDATED_RECEIVED_QTY = 2D;

    private static final Double DEFAULT_BILLED_QTY = 1D;
    private static final Double UPDATED_BILLED_QTY = 2D;

    private static final String DEFAULT_UNIT_OF_MEASUREMENT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_OF_MEASUREMENT = "BBBBBBBBBB";

    private static final Currency DEFAULT_CURRENCY = Currency.INDIAN_RUPEE;
    private static final Currency UPDATED_CURRENCY = Currency.US_DOLLAR;

    private static final Double DEFAULT_LINE_AMOUNT = 1D;
    private static final Double UPDATED_LINE_AMOUNT = 2D;

    private static final Double DEFAULT_LINE_AMOUNT_EXCL_TAX = 1D;
    private static final Double UPDATED_LINE_AMOUNT_EXCL_TAX = 2D;

    private static final Double DEFAULT_UNIT_PRICE = 1D;
    private static final Double UPDATED_UNIT_PRICE = 2D;

    private static final String DEFAULT_COST_CENTRE = "AAAAAAAAAA";
    private static final String UPDATED_COST_CENTRE = "BBBBBBBBBB";

    private static final String DEFAULT_COST_CENTREID = "AAAAAAAAAA";
    private static final String UPDATED_COST_CENTREID = "BBBBBBBBBB";

    private static final String DEFAULT_ARTICLE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_RECEIPT_NO = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_RECEIPT_NO = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DELIVERY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DELIVERY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_HSN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HSN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TOLERANCE = "AAAAAAAAAA";
    private static final String UPDATED_TOLERANCE = "BBBBBBBBBB";

    private static final Double DEFAULT_TOLERANCE_PRICE = 1D;
    private static final Double UPDATED_TOLERANCE_PRICE = 2D;

    private static final Boolean DEFAULT_RECEIPT_REQD = false;
    private static final Boolean UPDATED_RECEIPT_REQD = true;

    private static final Double DEFAULT_ACCEPTED_QTY = 1D;
    private static final Double UPDATED_ACCEPTED_QTY = 2D;

    private static final Double DEFAULT_AMOUNT_RECEIVED = 1D;
    private static final Double UPDATED_AMOUNT_RECEIVED = 2D;

    private static final Double DEFAULT_AVAILABLE_QTY = 1D;
    private static final Double UPDATED_AVAILABLE_QTY = 2D;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private PurchaseOrderLineRepository purchaseOrderLineRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseOrderLineMockMvc;

    private PurchaseOrderLine purchaseOrderLine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderLine createEntity(EntityManager em) {
        PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine()
            .country(DEFAULT_COUNTRY)
            .articleNo(DEFAULT_ARTICLE_NO)
            .qty(DEFAULT_QTY)
            .receivedQty(DEFAULT_RECEIVED_QTY)
            .billedQty(DEFAULT_BILLED_QTY)
            .unitOfMeasurement(DEFAULT_UNIT_OF_MEASUREMENT)
            .currency(DEFAULT_CURRENCY)
            .lineAmount(DEFAULT_LINE_AMOUNT)
            .lineAmountExclTax(DEFAULT_LINE_AMOUNT_EXCL_TAX)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .costCentre(DEFAULT_COST_CENTRE)
            .costCentreid(DEFAULT_COST_CENTREID)
            .articleDescription(DEFAULT_ARTICLE_DESCRIPTION)
            .deliveryReceiptNo(DEFAULT_DELIVERY_RECEIPT_NO)
            .deliveryDate(DEFAULT_DELIVERY_DATE)
            .hsnCode(DEFAULT_HSN_CODE)
            .tolerance(DEFAULT_TOLERANCE)
            .tolerancePrice(DEFAULT_TOLERANCE_PRICE)
            .receiptReqd(DEFAULT_RECEIPT_REQD)
            .acceptedQty(DEFAULT_ACCEPTED_QTY)
            .amountReceived(DEFAULT_AMOUNT_RECEIVED)
            .availableQty(DEFAULT_AVAILABLE_QTY)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return purchaseOrderLine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PurchaseOrderLine createUpdatedEntity(EntityManager em) {
        PurchaseOrderLine purchaseOrderLine = new PurchaseOrderLine()
            .country(UPDATED_COUNTRY)
            .articleNo(UPDATED_ARTICLE_NO)
            .qty(UPDATED_QTY)
            .receivedQty(UPDATED_RECEIVED_QTY)
            .billedQty(UPDATED_BILLED_QTY)
            .unitOfMeasurement(UPDATED_UNIT_OF_MEASUREMENT)
            .currency(UPDATED_CURRENCY)
            .lineAmount(UPDATED_LINE_AMOUNT)
            .lineAmountExclTax(UPDATED_LINE_AMOUNT_EXCL_TAX)
            .unitPrice(UPDATED_UNIT_PRICE)
            .costCentre(UPDATED_COST_CENTRE)
            .costCentreid(UPDATED_COST_CENTREID)
            .articleDescription(UPDATED_ARTICLE_DESCRIPTION)
            .deliveryReceiptNo(UPDATED_DELIVERY_RECEIPT_NO)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .hsnCode(UPDATED_HSN_CODE)
            .tolerance(UPDATED_TOLERANCE)
            .tolerancePrice(UPDATED_TOLERANCE_PRICE)
            .receiptReqd(UPDATED_RECEIPT_REQD)
            .acceptedQty(UPDATED_ACCEPTED_QTY)
            .amountReceived(UPDATED_AMOUNT_RECEIVED)
            .availableQty(UPDATED_AVAILABLE_QTY)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return purchaseOrderLine;
    }

    @BeforeEach
    public void initTest() {
        purchaseOrderLine = createEntity(em);
    }

    @Test
    @Transactional
    public void createPurchaseOrderLine() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderLineRepository.findAll().size();
        // Create the PurchaseOrderLine
        restPurchaseOrderLineMockMvc.perform(post("/api/purchase-order-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderLine)))
            .andExpect(status().isCreated());

        // Validate the PurchaseOrderLine in the database
        List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findAll();
        assertThat(purchaseOrderLineList).hasSize(databaseSizeBeforeCreate + 1);
        PurchaseOrderLine testPurchaseOrderLine = purchaseOrderLineList.get(purchaseOrderLineList.size() - 1);
        assertThat(testPurchaseOrderLine.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPurchaseOrderLine.getArticleNo()).isEqualTo(DEFAULT_ARTICLE_NO);
        assertThat(testPurchaseOrderLine.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testPurchaseOrderLine.getReceivedQty()).isEqualTo(DEFAULT_RECEIVED_QTY);
        assertThat(testPurchaseOrderLine.getBilledQty()).isEqualTo(DEFAULT_BILLED_QTY);
        assertThat(testPurchaseOrderLine.getUnitOfMeasurement()).isEqualTo(DEFAULT_UNIT_OF_MEASUREMENT);
        assertThat(testPurchaseOrderLine.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testPurchaseOrderLine.getLineAmount()).isEqualTo(DEFAULT_LINE_AMOUNT);
        assertThat(testPurchaseOrderLine.getLineAmountExclTax()).isEqualTo(DEFAULT_LINE_AMOUNT_EXCL_TAX);
        assertThat(testPurchaseOrderLine.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testPurchaseOrderLine.getCostCentre()).isEqualTo(DEFAULT_COST_CENTRE);
        assertThat(testPurchaseOrderLine.getCostCentreid()).isEqualTo(DEFAULT_COST_CENTREID);
        assertThat(testPurchaseOrderLine.getArticleDescription()).isEqualTo(DEFAULT_ARTICLE_DESCRIPTION);
        assertThat(testPurchaseOrderLine.getDeliveryReceiptNo()).isEqualTo(DEFAULT_DELIVERY_RECEIPT_NO);
        assertThat(testPurchaseOrderLine.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testPurchaseOrderLine.getHsnCode()).isEqualTo(DEFAULT_HSN_CODE);
        assertThat(testPurchaseOrderLine.getTolerance()).isEqualTo(DEFAULT_TOLERANCE);
        assertThat(testPurchaseOrderLine.getTolerancePrice()).isEqualTo(DEFAULT_TOLERANCE_PRICE);
        assertThat(testPurchaseOrderLine.isReceiptReqd()).isEqualTo(DEFAULT_RECEIPT_REQD);
        assertThat(testPurchaseOrderLine.getAcceptedQty()).isEqualTo(DEFAULT_ACCEPTED_QTY);
        assertThat(testPurchaseOrderLine.getAmountReceived()).isEqualTo(DEFAULT_AMOUNT_RECEIVED);
        assertThat(testPurchaseOrderLine.getAvailableQty()).isEqualTo(DEFAULT_AVAILABLE_QTY);
        assertThat(testPurchaseOrderLine.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPurchaseOrderLine.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createPurchaseOrderLineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = purchaseOrderLineRepository.findAll().size();

        // Create the PurchaseOrderLine with an existing ID
        purchaseOrderLine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPurchaseOrderLineMockMvc.perform(post("/api/purchase-order-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderLine)))
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderLine in the database
        List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findAll();
        assertThat(purchaseOrderLineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPurchaseOrderLines() throws Exception {
        // Initialize the database
        purchaseOrderLineRepository.saveAndFlush(purchaseOrderLine);

        // Get all the purchaseOrderLineList
        restPurchaseOrderLineMockMvc.perform(get("/api/purchase-order-lines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseOrderLine.getId().intValue())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].articleNo").value(hasItem(DEFAULT_ARTICLE_NO)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].receivedQty").value(hasItem(DEFAULT_RECEIVED_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].billedQty").value(hasItem(DEFAULT_BILLED_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].unitOfMeasurement").value(hasItem(DEFAULT_UNIT_OF_MEASUREMENT)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].lineAmount").value(hasItem(DEFAULT_LINE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].lineAmountExclTax").value(hasItem(DEFAULT_LINE_AMOUNT_EXCL_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].costCentre").value(hasItem(DEFAULT_COST_CENTRE)))
            .andExpect(jsonPath("$.[*].costCentreid").value(hasItem(DEFAULT_COST_CENTREID)))
            .andExpect(jsonPath("$.[*].articleDescription").value(hasItem(DEFAULT_ARTICLE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].deliveryReceiptNo").value(hasItem(DEFAULT_DELIVERY_RECEIPT_NO)))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(sameInstant(DEFAULT_DELIVERY_DATE))))
            .andExpect(jsonPath("$.[*].hsnCode").value(hasItem(DEFAULT_HSN_CODE)))
            .andExpect(jsonPath("$.[*].tolerance").value(hasItem(DEFAULT_TOLERANCE)))
            .andExpect(jsonPath("$.[*].tolerancePrice").value(hasItem(DEFAULT_TOLERANCE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].receiptReqd").value(hasItem(DEFAULT_RECEIPT_REQD.booleanValue())))
            .andExpect(jsonPath("$.[*].acceptedQty").value(hasItem(DEFAULT_ACCEPTED_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].amountReceived").value(hasItem(DEFAULT_AMOUNT_RECEIVED.doubleValue())))
            .andExpect(jsonPath("$.[*].availableQty").value(hasItem(DEFAULT_AVAILABLE_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getPurchaseOrderLine() throws Exception {
        // Initialize the database
        purchaseOrderLineRepository.saveAndFlush(purchaseOrderLine);

        // Get the purchaseOrderLine
        restPurchaseOrderLineMockMvc.perform(get("/api/purchase-order-lines/{id}", purchaseOrderLine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(purchaseOrderLine.getId().intValue()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.articleNo").value(DEFAULT_ARTICLE_NO))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY.doubleValue()))
            .andExpect(jsonPath("$.receivedQty").value(DEFAULT_RECEIVED_QTY.doubleValue()))
            .andExpect(jsonPath("$.billedQty").value(DEFAULT_BILLED_QTY.doubleValue()))
            .andExpect(jsonPath("$.unitOfMeasurement").value(DEFAULT_UNIT_OF_MEASUREMENT))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.lineAmount").value(DEFAULT_LINE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.lineAmountExclTax").value(DEFAULT_LINE_AMOUNT_EXCL_TAX.doubleValue()))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.costCentre").value(DEFAULT_COST_CENTRE))
            .andExpect(jsonPath("$.costCentreid").value(DEFAULT_COST_CENTREID))
            .andExpect(jsonPath("$.articleDescription").value(DEFAULT_ARTICLE_DESCRIPTION))
            .andExpect(jsonPath("$.deliveryReceiptNo").value(DEFAULT_DELIVERY_RECEIPT_NO))
            .andExpect(jsonPath("$.deliveryDate").value(sameInstant(DEFAULT_DELIVERY_DATE)))
            .andExpect(jsonPath("$.hsnCode").value(DEFAULT_HSN_CODE))
            .andExpect(jsonPath("$.tolerance").value(DEFAULT_TOLERANCE))
            .andExpect(jsonPath("$.tolerancePrice").value(DEFAULT_TOLERANCE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.receiptReqd").value(DEFAULT_RECEIPT_REQD.booleanValue()))
            .andExpect(jsonPath("$.acceptedQty").value(DEFAULT_ACCEPTED_QTY.doubleValue()))
            .andExpect(jsonPath("$.amountReceived").value(DEFAULT_AMOUNT_RECEIVED.doubleValue()))
            .andExpect(jsonPath("$.availableQty").value(DEFAULT_AVAILABLE_QTY.doubleValue()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingPurchaseOrderLine() throws Exception {
        // Get the purchaseOrderLine
        restPurchaseOrderLineMockMvc.perform(get("/api/purchase-order-lines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePurchaseOrderLine() throws Exception {
        // Initialize the database
        purchaseOrderLineRepository.saveAndFlush(purchaseOrderLine);

        int databaseSizeBeforeUpdate = purchaseOrderLineRepository.findAll().size();

        // Update the purchaseOrderLine
        PurchaseOrderLine updatedPurchaseOrderLine = purchaseOrderLineRepository.findById(purchaseOrderLine.getId()).get();
        // Disconnect from session so that the updates on updatedPurchaseOrderLine are not directly saved in db
        em.detach(updatedPurchaseOrderLine);
        updatedPurchaseOrderLine
            .country(UPDATED_COUNTRY)
            .articleNo(UPDATED_ARTICLE_NO)
            .qty(UPDATED_QTY)
            .receivedQty(UPDATED_RECEIVED_QTY)
            .billedQty(UPDATED_BILLED_QTY)
            .unitOfMeasurement(UPDATED_UNIT_OF_MEASUREMENT)
            .currency(UPDATED_CURRENCY)
            .lineAmount(UPDATED_LINE_AMOUNT)
            .lineAmountExclTax(UPDATED_LINE_AMOUNT_EXCL_TAX)
            .unitPrice(UPDATED_UNIT_PRICE)
            .costCentre(UPDATED_COST_CENTRE)
            .costCentreid(UPDATED_COST_CENTREID)
            .articleDescription(UPDATED_ARTICLE_DESCRIPTION)
            .deliveryReceiptNo(UPDATED_DELIVERY_RECEIPT_NO)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .hsnCode(UPDATED_HSN_CODE)
            .tolerance(UPDATED_TOLERANCE)
            .tolerancePrice(UPDATED_TOLERANCE_PRICE)
            .receiptReqd(UPDATED_RECEIPT_REQD)
            .acceptedQty(UPDATED_ACCEPTED_QTY)
            .amountReceived(UPDATED_AMOUNT_RECEIVED)
            .availableQty(UPDATED_AVAILABLE_QTY)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restPurchaseOrderLineMockMvc.perform(put("/api/purchase-order-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPurchaseOrderLine)))
            .andExpect(status().isOk());

        // Validate the PurchaseOrderLine in the database
        List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findAll();
        assertThat(purchaseOrderLineList).hasSize(databaseSizeBeforeUpdate);
        PurchaseOrderLine testPurchaseOrderLine = purchaseOrderLineList.get(purchaseOrderLineList.size() - 1);
        assertThat(testPurchaseOrderLine.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPurchaseOrderLine.getArticleNo()).isEqualTo(UPDATED_ARTICLE_NO);
        assertThat(testPurchaseOrderLine.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testPurchaseOrderLine.getReceivedQty()).isEqualTo(UPDATED_RECEIVED_QTY);
        assertThat(testPurchaseOrderLine.getBilledQty()).isEqualTo(UPDATED_BILLED_QTY);
        assertThat(testPurchaseOrderLine.getUnitOfMeasurement()).isEqualTo(UPDATED_UNIT_OF_MEASUREMENT);
        assertThat(testPurchaseOrderLine.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testPurchaseOrderLine.getLineAmount()).isEqualTo(UPDATED_LINE_AMOUNT);
        assertThat(testPurchaseOrderLine.getLineAmountExclTax()).isEqualTo(UPDATED_LINE_AMOUNT_EXCL_TAX);
        assertThat(testPurchaseOrderLine.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testPurchaseOrderLine.getCostCentre()).isEqualTo(UPDATED_COST_CENTRE);
        assertThat(testPurchaseOrderLine.getCostCentreid()).isEqualTo(UPDATED_COST_CENTREID);
        assertThat(testPurchaseOrderLine.getArticleDescription()).isEqualTo(UPDATED_ARTICLE_DESCRIPTION);
        assertThat(testPurchaseOrderLine.getDeliveryReceiptNo()).isEqualTo(UPDATED_DELIVERY_RECEIPT_NO);
        assertThat(testPurchaseOrderLine.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testPurchaseOrderLine.getHsnCode()).isEqualTo(UPDATED_HSN_CODE);
        assertThat(testPurchaseOrderLine.getTolerance()).isEqualTo(UPDATED_TOLERANCE);
        assertThat(testPurchaseOrderLine.getTolerancePrice()).isEqualTo(UPDATED_TOLERANCE_PRICE);
        assertThat(testPurchaseOrderLine.isReceiptReqd()).isEqualTo(UPDATED_RECEIPT_REQD);
        assertThat(testPurchaseOrderLine.getAcceptedQty()).isEqualTo(UPDATED_ACCEPTED_QTY);
        assertThat(testPurchaseOrderLine.getAmountReceived()).isEqualTo(UPDATED_AMOUNT_RECEIVED);
        assertThat(testPurchaseOrderLine.getAvailableQty()).isEqualTo(UPDATED_AVAILABLE_QTY);
        assertThat(testPurchaseOrderLine.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPurchaseOrderLine.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingPurchaseOrderLine() throws Exception {
        int databaseSizeBeforeUpdate = purchaseOrderLineRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPurchaseOrderLineMockMvc.perform(put("/api/purchase-order-lines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderLine)))
            .andExpect(status().isBadRequest());

        // Validate the PurchaseOrderLine in the database
        List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findAll();
        assertThat(purchaseOrderLineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePurchaseOrderLine() throws Exception {
        // Initialize the database
        purchaseOrderLineRepository.saveAndFlush(purchaseOrderLine);

        int databaseSizeBeforeDelete = purchaseOrderLineRepository.findAll().size();

        // Delete the purchaseOrderLine
        restPurchaseOrderLineMockMvc.perform(delete("/api/purchase-order-lines/{id}", purchaseOrderLine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findAll();
        assertThat(purchaseOrderLineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
