package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.InvoiceLineItem;
import com.mycompany.myapp.repository.InvoiceLineItemRepository;

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
 * Integration tests for the {@link InvoiceLineItemResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvoiceLineItemResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXPENSE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EXPENSE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRIBUTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DISTRIBUTION_ID = "BBBBBBBBBB";

    private static final Double DEFAULT_LINE_NUMBER = 1D;
    private static final Double UPDATED_LINE_NUMBER = 2D;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_QUANTITY = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITY = "BBBBBBBBBB";

    private static final Double DEFAULT_UNIT_PRICE = 1D;
    private static final Double UPDATED_UNIT_PRICE = 2D;

    private static final Double DEFAULT_TAX_RATE = 1D;
    private static final Double UPDATED_TAX_RATE = 2D;

    private static final String DEFAULT_TAX_DESC = "AAAAAAAAAA";
    private static final String UPDATED_TAX_DESC = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX_AMOUNT = 1D;
    private static final Double UPDATED_TAX_AMOUNT = 2D;

    private static final Double DEFAULT_LINE_AMOUNT = 1D;
    private static final Double UPDATED_LINE_AMOUNT = 2D;

    private static final Double DEFAULT_GROSS_LINE_AMOUNT = 1D;
    private static final Double UPDATED_GROSS_LINE_AMOUNT = 2D;

    private static final String DEFAULT_CONFIRMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PO_REQUEST_BY = "AAAAAAAAAA";
    private static final String UPDATED_PO_REQUEST_BY = "BBBBBBBBBB";

    private static final String DEFAULT_UOM = "AAAAAAAAAA";
    private static final String UPDATED_UOM = "BBBBBBBBBB";

    private static final String DEFAULT_DEBIT_CREDIT_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_DEBIT_CREDIT_INDICATOR = "BBBBBBBBBB";

    private static final Double DEFAULT_AVAILABLE_QTY = 1D;
    private static final Double UPDATED_AVAILABLE_QTY = 2D;

    private static final Double DEFAULT_ADDITIONAL_COST_PER_UNIT = 1D;
    private static final Double UPDATED_ADDITIONAL_COST_PER_UNIT = 2D;

    private static final Double DEFAULT_TAX_BASE_AMOUNT = 1D;
    private static final Double UPDATED_TAX_BASE_AMOUNT = 2D;

    private static final Double DEFAULT_INVOICE_QTY = 1D;
    private static final Double UPDATED_INVOICE_QTY = 2D;

    private static final Double DEFAULT_INVOICE_UNIT_PRICE = 1D;
    private static final Double UPDATED_INVOICE_UNIT_PRICE = 2D;

    private static final Double DEFAULT_VAT_TAX_RATE = 1D;
    private static final Double UPDATED_VAT_TAX_RATE = 2D;

    private static final String DEFAULT_CONFIRMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRMER_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_AVAILABLE_EXPECTEDVALUE = 1D;
    private static final Double UPDATED_AVAILABLE_EXPECTEDVALUE = 2D;

    private static final String DEFAULT_AVAILABLE_LIMIT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_AVAILABLE_LIMIT_VALUE = "BBBBBBBBBB";

    private static final Double DEFAULT_EXPECTED_VALUE = 1D;
    private static final Double UPDATED_EXPECTED_VALUE = 2D;

    private static final Double DEFAULT_ACTUAL_LIMIT = 1D;
    private static final Double UPDATED_ACTUAL_LIMIT = 2D;

    private static final Double DEFAULT_OVERALL_LIMIT = 1D;
    private static final Double UPDATED_OVERALL_LIMIT = 2D;

    private static final ZonedDateTime DEFAULT_LIMIT_ORDER_EXPIRY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LIMIT_ORDER_EXPIRY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_LIMIT_ORDER_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LIMIT_ORDER_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_VAT_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VAT_TAX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_ORDER_STATISTICAL = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_ORDER_STATISTICAL = "BBBBBBBBBB";

    private static final Double DEFAULT_DEBIT_CREDIT_VALUE = 1D;
    private static final Double UPDATED_DEBIT_CREDIT_VALUE = 2D;

    private static final String DEFAULT_DELIVERY_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_NOTE = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private InvoiceLineItemRepository invoiceLineItemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceLineItemMockMvc;

    private InvoiceLineItem invoiceLineItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceLineItem createEntity(EntityManager em) {
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem()
            .name(DEFAULT_NAME)
            .expenseType(DEFAULT_EXPENSE_TYPE)
            .distributionId(DEFAULT_DISTRIBUTION_ID)
            .lineNumber(DEFAULT_LINE_NUMBER)
            .description(DEFAULT_DESCRIPTION)
            .quantity(DEFAULT_QUANTITY)
            .unitPrice(DEFAULT_UNIT_PRICE)
            .taxRate(DEFAULT_TAX_RATE)
            .taxDesc(DEFAULT_TAX_DESC)
            .taxAmount(DEFAULT_TAX_AMOUNT)
            .lineAmount(DEFAULT_LINE_AMOUNT)
            .grossLineAmount(DEFAULT_GROSS_LINE_AMOUNT)
            .confirmerId(DEFAULT_CONFIRMER_ID)
            .poRequestBy(DEFAULT_PO_REQUEST_BY)
            .uom(DEFAULT_UOM)
            .debitCreditIndicator(DEFAULT_DEBIT_CREDIT_INDICATOR)
            .availableQty(DEFAULT_AVAILABLE_QTY)
            .additionalCostPerUnit(DEFAULT_ADDITIONAL_COST_PER_UNIT)
            .taxBaseAmount(DEFAULT_TAX_BASE_AMOUNT)
            .invoiceQty(DEFAULT_INVOICE_QTY)
            .invoiceUnitPrice(DEFAULT_INVOICE_UNIT_PRICE)
            .vatTaxRate(DEFAULT_VAT_TAX_RATE)
            .confirmerName(DEFAULT_CONFIRMER_NAME)
            .availableExpectedvalue(DEFAULT_AVAILABLE_EXPECTEDVALUE)
            .availableLimitValue(DEFAULT_AVAILABLE_LIMIT_VALUE)
            .expectedValue(DEFAULT_EXPECTED_VALUE)
            .actualLimit(DEFAULT_ACTUAL_LIMIT)
            .overallLimit(DEFAULT_OVERALL_LIMIT)
            .limitOrderExpiryDate(DEFAULT_LIMIT_ORDER_EXPIRY_DATE)
            .limitOrderStartDate(DEFAULT_LIMIT_ORDER_START_DATE)
            .vatTaxCode(DEFAULT_VAT_TAX_CODE)
            .internalOrderStatistical(DEFAULT_INTERNAL_ORDER_STATISTICAL)
            .debitCreditValue(DEFAULT_DEBIT_CREDIT_VALUE)
            .deliveryNote(DEFAULT_DELIVERY_NOTE)
            .reason(DEFAULT_REASON)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return invoiceLineItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceLineItem createUpdatedEntity(EntityManager em) {
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem()
            .name(UPDATED_NAME)
            .expenseType(UPDATED_EXPENSE_TYPE)
            .distributionId(UPDATED_DISTRIBUTION_ID)
            .lineNumber(UPDATED_LINE_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .quantity(UPDATED_QUANTITY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .taxRate(UPDATED_TAX_RATE)
            .taxDesc(UPDATED_TAX_DESC)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .lineAmount(UPDATED_LINE_AMOUNT)
            .grossLineAmount(UPDATED_GROSS_LINE_AMOUNT)
            .confirmerId(UPDATED_CONFIRMER_ID)
            .poRequestBy(UPDATED_PO_REQUEST_BY)
            .uom(UPDATED_UOM)
            .debitCreditIndicator(UPDATED_DEBIT_CREDIT_INDICATOR)
            .availableQty(UPDATED_AVAILABLE_QTY)
            .additionalCostPerUnit(UPDATED_ADDITIONAL_COST_PER_UNIT)
            .taxBaseAmount(UPDATED_TAX_BASE_AMOUNT)
            .invoiceQty(UPDATED_INVOICE_QTY)
            .invoiceUnitPrice(UPDATED_INVOICE_UNIT_PRICE)
            .vatTaxRate(UPDATED_VAT_TAX_RATE)
            .confirmerName(UPDATED_CONFIRMER_NAME)
            .availableExpectedvalue(UPDATED_AVAILABLE_EXPECTEDVALUE)
            .availableLimitValue(UPDATED_AVAILABLE_LIMIT_VALUE)
            .expectedValue(UPDATED_EXPECTED_VALUE)
            .actualLimit(UPDATED_ACTUAL_LIMIT)
            .overallLimit(UPDATED_OVERALL_LIMIT)
            .limitOrderExpiryDate(UPDATED_LIMIT_ORDER_EXPIRY_DATE)
            .limitOrderStartDate(UPDATED_LIMIT_ORDER_START_DATE)
            .vatTaxCode(UPDATED_VAT_TAX_CODE)
            .internalOrderStatistical(UPDATED_INTERNAL_ORDER_STATISTICAL)
            .debitCreditValue(UPDATED_DEBIT_CREDIT_VALUE)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .reason(UPDATED_REASON)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return invoiceLineItem;
    }

    @BeforeEach
    public void initTest() {
        invoiceLineItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceLineItem() throws Exception {
        int databaseSizeBeforeCreate = invoiceLineItemRepository.findAll().size();
        // Create the InvoiceLineItem
        restInvoiceLineItemMockMvc.perform(post("/api/invoice-line-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineItem)))
            .andExpect(status().isCreated());

        // Validate the InvoiceLineItem in the database
        List<InvoiceLineItem> invoiceLineItemList = invoiceLineItemRepository.findAll();
        assertThat(invoiceLineItemList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceLineItem testInvoiceLineItem = invoiceLineItemList.get(invoiceLineItemList.size() - 1);
        assertThat(testInvoiceLineItem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testInvoiceLineItem.getExpenseType()).isEqualTo(DEFAULT_EXPENSE_TYPE);
        assertThat(testInvoiceLineItem.getDistributionId()).isEqualTo(DEFAULT_DISTRIBUTION_ID);
        assertThat(testInvoiceLineItem.getLineNumber()).isEqualTo(DEFAULT_LINE_NUMBER);
        assertThat(testInvoiceLineItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInvoiceLineItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testInvoiceLineItem.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
        assertThat(testInvoiceLineItem.getTaxRate()).isEqualTo(DEFAULT_TAX_RATE);
        assertThat(testInvoiceLineItem.getTaxDesc()).isEqualTo(DEFAULT_TAX_DESC);
        assertThat(testInvoiceLineItem.getTaxAmount()).isEqualTo(DEFAULT_TAX_AMOUNT);
        assertThat(testInvoiceLineItem.getLineAmount()).isEqualTo(DEFAULT_LINE_AMOUNT);
        assertThat(testInvoiceLineItem.getGrossLineAmount()).isEqualTo(DEFAULT_GROSS_LINE_AMOUNT);
        assertThat(testInvoiceLineItem.getConfirmerId()).isEqualTo(DEFAULT_CONFIRMER_ID);
        assertThat(testInvoiceLineItem.getPoRequestBy()).isEqualTo(DEFAULT_PO_REQUEST_BY);
        assertThat(testInvoiceLineItem.getUom()).isEqualTo(DEFAULT_UOM);
        assertThat(testInvoiceLineItem.getDebitCreditIndicator()).isEqualTo(DEFAULT_DEBIT_CREDIT_INDICATOR);
        assertThat(testInvoiceLineItem.getAvailableQty()).isEqualTo(DEFAULT_AVAILABLE_QTY);
        assertThat(testInvoiceLineItem.getAdditionalCostPerUnit()).isEqualTo(DEFAULT_ADDITIONAL_COST_PER_UNIT);
        assertThat(testInvoiceLineItem.getTaxBaseAmount()).isEqualTo(DEFAULT_TAX_BASE_AMOUNT);
        assertThat(testInvoiceLineItem.getInvoiceQty()).isEqualTo(DEFAULT_INVOICE_QTY);
        assertThat(testInvoiceLineItem.getInvoiceUnitPrice()).isEqualTo(DEFAULT_INVOICE_UNIT_PRICE);
        assertThat(testInvoiceLineItem.getVatTaxRate()).isEqualTo(DEFAULT_VAT_TAX_RATE);
        assertThat(testInvoiceLineItem.getConfirmerName()).isEqualTo(DEFAULT_CONFIRMER_NAME);
        assertThat(testInvoiceLineItem.getAvailableExpectedvalue()).isEqualTo(DEFAULT_AVAILABLE_EXPECTEDVALUE);
        assertThat(testInvoiceLineItem.getAvailableLimitValue()).isEqualTo(DEFAULT_AVAILABLE_LIMIT_VALUE);
        assertThat(testInvoiceLineItem.getExpectedValue()).isEqualTo(DEFAULT_EXPECTED_VALUE);
        assertThat(testInvoiceLineItem.getActualLimit()).isEqualTo(DEFAULT_ACTUAL_LIMIT);
        assertThat(testInvoiceLineItem.getOverallLimit()).isEqualTo(DEFAULT_OVERALL_LIMIT);
        assertThat(testInvoiceLineItem.getLimitOrderExpiryDate()).isEqualTo(DEFAULT_LIMIT_ORDER_EXPIRY_DATE);
        assertThat(testInvoiceLineItem.getLimitOrderStartDate()).isEqualTo(DEFAULT_LIMIT_ORDER_START_DATE);
        assertThat(testInvoiceLineItem.getVatTaxCode()).isEqualTo(DEFAULT_VAT_TAX_CODE);
        assertThat(testInvoiceLineItem.getInternalOrderStatistical()).isEqualTo(DEFAULT_INTERNAL_ORDER_STATISTICAL);
        assertThat(testInvoiceLineItem.getDebitCreditValue()).isEqualTo(DEFAULT_DEBIT_CREDIT_VALUE);
        assertThat(testInvoiceLineItem.getDeliveryNote()).isEqualTo(DEFAULT_DELIVERY_NOTE);
        assertThat(testInvoiceLineItem.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testInvoiceLineItem.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testInvoiceLineItem.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createInvoiceLineItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceLineItemRepository.findAll().size();

        // Create the InvoiceLineItem with an existing ID
        invoiceLineItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceLineItemMockMvc.perform(post("/api/invoice-line-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineItem)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLineItem in the database
        List<InvoiceLineItem> invoiceLineItemList = invoiceLineItemRepository.findAll();
        assertThat(invoiceLineItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvoiceLineItems() throws Exception {
        // Initialize the database
        invoiceLineItemRepository.saveAndFlush(invoiceLineItem);

        // Get all the invoiceLineItemList
        restInvoiceLineItemMockMvc.perform(get("/api/invoice-line-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceLineItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].expenseType").value(hasItem(DEFAULT_EXPENSE_TYPE)))
            .andExpect(jsonPath("$.[*].distributionId").value(hasItem(DEFAULT_DISTRIBUTION_ID)))
            .andExpect(jsonPath("$.[*].lineNumber").value(hasItem(DEFAULT_LINE_NUMBER.doubleValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].taxRate").value(hasItem(DEFAULT_TAX_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].taxDesc").value(hasItem(DEFAULT_TAX_DESC)))
            .andExpect(jsonPath("$.[*].taxAmount").value(hasItem(DEFAULT_TAX_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].lineAmount").value(hasItem(DEFAULT_LINE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].grossLineAmount").value(hasItem(DEFAULT_GROSS_LINE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].confirmerId").value(hasItem(DEFAULT_CONFIRMER_ID)))
            .andExpect(jsonPath("$.[*].poRequestBy").value(hasItem(DEFAULT_PO_REQUEST_BY)))
            .andExpect(jsonPath("$.[*].uom").value(hasItem(DEFAULT_UOM)))
            .andExpect(jsonPath("$.[*].debitCreditIndicator").value(hasItem(DEFAULT_DEBIT_CREDIT_INDICATOR)))
            .andExpect(jsonPath("$.[*].availableQty").value(hasItem(DEFAULT_AVAILABLE_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].additionalCostPerUnit").value(hasItem(DEFAULT_ADDITIONAL_COST_PER_UNIT.doubleValue())))
            .andExpect(jsonPath("$.[*].taxBaseAmount").value(hasItem(DEFAULT_TAX_BASE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].invoiceQty").value(hasItem(DEFAULT_INVOICE_QTY.doubleValue())))
            .andExpect(jsonPath("$.[*].invoiceUnitPrice").value(hasItem(DEFAULT_INVOICE_UNIT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].vatTaxRate").value(hasItem(DEFAULT_VAT_TAX_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].confirmerName").value(hasItem(DEFAULT_CONFIRMER_NAME)))
            .andExpect(jsonPath("$.[*].availableExpectedvalue").value(hasItem(DEFAULT_AVAILABLE_EXPECTEDVALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].availableLimitValue").value(hasItem(DEFAULT_AVAILABLE_LIMIT_VALUE)))
            .andExpect(jsonPath("$.[*].expectedValue").value(hasItem(DEFAULT_EXPECTED_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].actualLimit").value(hasItem(DEFAULT_ACTUAL_LIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].overallLimit").value(hasItem(DEFAULT_OVERALL_LIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].limitOrderExpiryDate").value(hasItem(sameInstant(DEFAULT_LIMIT_ORDER_EXPIRY_DATE))))
            .andExpect(jsonPath("$.[*].limitOrderStartDate").value(hasItem(sameInstant(DEFAULT_LIMIT_ORDER_START_DATE))))
            .andExpect(jsonPath("$.[*].vatTaxCode").value(hasItem(DEFAULT_VAT_TAX_CODE)))
            .andExpect(jsonPath("$.[*].internalOrderStatistical").value(hasItem(DEFAULT_INTERNAL_ORDER_STATISTICAL)))
            .andExpect(jsonPath("$.[*].debitCreditValue").value(hasItem(DEFAULT_DEBIT_CREDIT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].deliveryNote").value(hasItem(DEFAULT_DELIVERY_NOTE)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getInvoiceLineItem() throws Exception {
        // Initialize the database
        invoiceLineItemRepository.saveAndFlush(invoiceLineItem);

        // Get the invoiceLineItem
        restInvoiceLineItemMockMvc.perform(get("/api/invoice-line-items/{id}", invoiceLineItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceLineItem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.expenseType").value(DEFAULT_EXPENSE_TYPE))
            .andExpect(jsonPath("$.distributionId").value(DEFAULT_DISTRIBUTION_ID))
            .andExpect(jsonPath("$.lineNumber").value(DEFAULT_LINE_NUMBER.doubleValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.taxRate").value(DEFAULT_TAX_RATE.doubleValue()))
            .andExpect(jsonPath("$.taxDesc").value(DEFAULT_TAX_DESC))
            .andExpect(jsonPath("$.taxAmount").value(DEFAULT_TAX_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.lineAmount").value(DEFAULT_LINE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.grossLineAmount").value(DEFAULT_GROSS_LINE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.confirmerId").value(DEFAULT_CONFIRMER_ID))
            .andExpect(jsonPath("$.poRequestBy").value(DEFAULT_PO_REQUEST_BY))
            .andExpect(jsonPath("$.uom").value(DEFAULT_UOM))
            .andExpect(jsonPath("$.debitCreditIndicator").value(DEFAULT_DEBIT_CREDIT_INDICATOR))
            .andExpect(jsonPath("$.availableQty").value(DEFAULT_AVAILABLE_QTY.doubleValue()))
            .andExpect(jsonPath("$.additionalCostPerUnit").value(DEFAULT_ADDITIONAL_COST_PER_UNIT.doubleValue()))
            .andExpect(jsonPath("$.taxBaseAmount").value(DEFAULT_TAX_BASE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.invoiceQty").value(DEFAULT_INVOICE_QTY.doubleValue()))
            .andExpect(jsonPath("$.invoiceUnitPrice").value(DEFAULT_INVOICE_UNIT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.vatTaxRate").value(DEFAULT_VAT_TAX_RATE.doubleValue()))
            .andExpect(jsonPath("$.confirmerName").value(DEFAULT_CONFIRMER_NAME))
            .andExpect(jsonPath("$.availableExpectedvalue").value(DEFAULT_AVAILABLE_EXPECTEDVALUE.doubleValue()))
            .andExpect(jsonPath("$.availableLimitValue").value(DEFAULT_AVAILABLE_LIMIT_VALUE))
            .andExpect(jsonPath("$.expectedValue").value(DEFAULT_EXPECTED_VALUE.doubleValue()))
            .andExpect(jsonPath("$.actualLimit").value(DEFAULT_ACTUAL_LIMIT.doubleValue()))
            .andExpect(jsonPath("$.overallLimit").value(DEFAULT_OVERALL_LIMIT.doubleValue()))
            .andExpect(jsonPath("$.limitOrderExpiryDate").value(sameInstant(DEFAULT_LIMIT_ORDER_EXPIRY_DATE)))
            .andExpect(jsonPath("$.limitOrderStartDate").value(sameInstant(DEFAULT_LIMIT_ORDER_START_DATE)))
            .andExpect(jsonPath("$.vatTaxCode").value(DEFAULT_VAT_TAX_CODE))
            .andExpect(jsonPath("$.internalOrderStatistical").value(DEFAULT_INTERNAL_ORDER_STATISTICAL))
            .andExpect(jsonPath("$.debitCreditValue").value(DEFAULT_DEBIT_CREDIT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.deliveryNote").value(DEFAULT_DELIVERY_NOTE))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingInvoiceLineItem() throws Exception {
        // Get the invoiceLineItem
        restInvoiceLineItemMockMvc.perform(get("/api/invoice-line-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceLineItem() throws Exception {
        // Initialize the database
        invoiceLineItemRepository.saveAndFlush(invoiceLineItem);

        int databaseSizeBeforeUpdate = invoiceLineItemRepository.findAll().size();

        // Update the invoiceLineItem
        InvoiceLineItem updatedInvoiceLineItem = invoiceLineItemRepository.findById(invoiceLineItem.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceLineItem are not directly saved in db
        em.detach(updatedInvoiceLineItem);
        updatedInvoiceLineItem
            .name(UPDATED_NAME)
            .expenseType(UPDATED_EXPENSE_TYPE)
            .distributionId(UPDATED_DISTRIBUTION_ID)
            .lineNumber(UPDATED_LINE_NUMBER)
            .description(UPDATED_DESCRIPTION)
            .quantity(UPDATED_QUANTITY)
            .unitPrice(UPDATED_UNIT_PRICE)
            .taxRate(UPDATED_TAX_RATE)
            .taxDesc(UPDATED_TAX_DESC)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .lineAmount(UPDATED_LINE_AMOUNT)
            .grossLineAmount(UPDATED_GROSS_LINE_AMOUNT)
            .confirmerId(UPDATED_CONFIRMER_ID)
            .poRequestBy(UPDATED_PO_REQUEST_BY)
            .uom(UPDATED_UOM)
            .debitCreditIndicator(UPDATED_DEBIT_CREDIT_INDICATOR)
            .availableQty(UPDATED_AVAILABLE_QTY)
            .additionalCostPerUnit(UPDATED_ADDITIONAL_COST_PER_UNIT)
            .taxBaseAmount(UPDATED_TAX_BASE_AMOUNT)
            .invoiceQty(UPDATED_INVOICE_QTY)
            .invoiceUnitPrice(UPDATED_INVOICE_UNIT_PRICE)
            .vatTaxRate(UPDATED_VAT_TAX_RATE)
            .confirmerName(UPDATED_CONFIRMER_NAME)
            .availableExpectedvalue(UPDATED_AVAILABLE_EXPECTEDVALUE)
            .availableLimitValue(UPDATED_AVAILABLE_LIMIT_VALUE)
            .expectedValue(UPDATED_EXPECTED_VALUE)
            .actualLimit(UPDATED_ACTUAL_LIMIT)
            .overallLimit(UPDATED_OVERALL_LIMIT)
            .limitOrderExpiryDate(UPDATED_LIMIT_ORDER_EXPIRY_DATE)
            .limitOrderStartDate(UPDATED_LIMIT_ORDER_START_DATE)
            .vatTaxCode(UPDATED_VAT_TAX_CODE)
            .internalOrderStatistical(UPDATED_INTERNAL_ORDER_STATISTICAL)
            .debitCreditValue(UPDATED_DEBIT_CREDIT_VALUE)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .reason(UPDATED_REASON)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restInvoiceLineItemMockMvc.perform(put("/api/invoice-line-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceLineItem)))
            .andExpect(status().isOk());

        // Validate the InvoiceLineItem in the database
        List<InvoiceLineItem> invoiceLineItemList = invoiceLineItemRepository.findAll();
        assertThat(invoiceLineItemList).hasSize(databaseSizeBeforeUpdate);
        InvoiceLineItem testInvoiceLineItem = invoiceLineItemList.get(invoiceLineItemList.size() - 1);
        assertThat(testInvoiceLineItem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testInvoiceLineItem.getExpenseType()).isEqualTo(UPDATED_EXPENSE_TYPE);
        assertThat(testInvoiceLineItem.getDistributionId()).isEqualTo(UPDATED_DISTRIBUTION_ID);
        assertThat(testInvoiceLineItem.getLineNumber()).isEqualTo(UPDATED_LINE_NUMBER);
        assertThat(testInvoiceLineItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInvoiceLineItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testInvoiceLineItem.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
        assertThat(testInvoiceLineItem.getTaxRate()).isEqualTo(UPDATED_TAX_RATE);
        assertThat(testInvoiceLineItem.getTaxDesc()).isEqualTo(UPDATED_TAX_DESC);
        assertThat(testInvoiceLineItem.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
        assertThat(testInvoiceLineItem.getLineAmount()).isEqualTo(UPDATED_LINE_AMOUNT);
        assertThat(testInvoiceLineItem.getGrossLineAmount()).isEqualTo(UPDATED_GROSS_LINE_AMOUNT);
        assertThat(testInvoiceLineItem.getConfirmerId()).isEqualTo(UPDATED_CONFIRMER_ID);
        assertThat(testInvoiceLineItem.getPoRequestBy()).isEqualTo(UPDATED_PO_REQUEST_BY);
        assertThat(testInvoiceLineItem.getUom()).isEqualTo(UPDATED_UOM);
        assertThat(testInvoiceLineItem.getDebitCreditIndicator()).isEqualTo(UPDATED_DEBIT_CREDIT_INDICATOR);
        assertThat(testInvoiceLineItem.getAvailableQty()).isEqualTo(UPDATED_AVAILABLE_QTY);
        assertThat(testInvoiceLineItem.getAdditionalCostPerUnit()).isEqualTo(UPDATED_ADDITIONAL_COST_PER_UNIT);
        assertThat(testInvoiceLineItem.getTaxBaseAmount()).isEqualTo(UPDATED_TAX_BASE_AMOUNT);
        assertThat(testInvoiceLineItem.getInvoiceQty()).isEqualTo(UPDATED_INVOICE_QTY);
        assertThat(testInvoiceLineItem.getInvoiceUnitPrice()).isEqualTo(UPDATED_INVOICE_UNIT_PRICE);
        assertThat(testInvoiceLineItem.getVatTaxRate()).isEqualTo(UPDATED_VAT_TAX_RATE);
        assertThat(testInvoiceLineItem.getConfirmerName()).isEqualTo(UPDATED_CONFIRMER_NAME);
        assertThat(testInvoiceLineItem.getAvailableExpectedvalue()).isEqualTo(UPDATED_AVAILABLE_EXPECTEDVALUE);
        assertThat(testInvoiceLineItem.getAvailableLimitValue()).isEqualTo(UPDATED_AVAILABLE_LIMIT_VALUE);
        assertThat(testInvoiceLineItem.getExpectedValue()).isEqualTo(UPDATED_EXPECTED_VALUE);
        assertThat(testInvoiceLineItem.getActualLimit()).isEqualTo(UPDATED_ACTUAL_LIMIT);
        assertThat(testInvoiceLineItem.getOverallLimit()).isEqualTo(UPDATED_OVERALL_LIMIT);
        assertThat(testInvoiceLineItem.getLimitOrderExpiryDate()).isEqualTo(UPDATED_LIMIT_ORDER_EXPIRY_DATE);
        assertThat(testInvoiceLineItem.getLimitOrderStartDate()).isEqualTo(UPDATED_LIMIT_ORDER_START_DATE);
        assertThat(testInvoiceLineItem.getVatTaxCode()).isEqualTo(UPDATED_VAT_TAX_CODE);
        assertThat(testInvoiceLineItem.getInternalOrderStatistical()).isEqualTo(UPDATED_INTERNAL_ORDER_STATISTICAL);
        assertThat(testInvoiceLineItem.getDebitCreditValue()).isEqualTo(UPDATED_DEBIT_CREDIT_VALUE);
        assertThat(testInvoiceLineItem.getDeliveryNote()).isEqualTo(UPDATED_DELIVERY_NOTE);
        assertThat(testInvoiceLineItem.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testInvoiceLineItem.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testInvoiceLineItem.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceLineItem() throws Exception {
        int databaseSizeBeforeUpdate = invoiceLineItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceLineItemMockMvc.perform(put("/api/invoice-line-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceLineItem)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceLineItem in the database
        List<InvoiceLineItem> invoiceLineItemList = invoiceLineItemRepository.findAll();
        assertThat(invoiceLineItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoiceLineItem() throws Exception {
        // Initialize the database
        invoiceLineItemRepository.saveAndFlush(invoiceLineItem);

        int databaseSizeBeforeDelete = invoiceLineItemRepository.findAll().size();

        // Delete the invoiceLineItem
        restInvoiceLineItemMockMvc.perform(delete("/api/invoice-line-items/{id}", invoiceLineItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceLineItem> invoiceLineItemList = invoiceLineItemRepository.findAll();
        assertThat(invoiceLineItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
