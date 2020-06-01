package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.InvoiceHeader;
import com.mycompany.myapp.repository.InvoiceHeaderRepository;

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
import com.mycompany.myapp.domain.enumeration.Currency;
import com.mycompany.myapp.domain.enumeration.Currency;
/**
 * Integration tests for the {@link InvoiceHeaderResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvoiceHeaderResourceIT {

    private static final String DEFAULT_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ENTITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ENTITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_GROSS_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_GROSS_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_NET_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_NET_AMOUNT = "BBBBBBBBBB";

    private static final Currency DEFAULT_CURRENCY = Currency.INDIAN_RUPEE;
    private static final Currency UPDATED_CURRENCY = Currency.US_DOLLAR;

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_KEY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_KEY = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_ERP_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ERP_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PO_BOX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PO_BOX_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_SCAN_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SCAN_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_RECEIVED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_RECEIVED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CASE_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CASE_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NUMBER_OF_SCANNED_PAGES = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_OF_SCANNED_PAGES = "BBBBBBBBBB";

    private static final String DEFAULT_SLA = "AAAAAAAAAA";
    private static final String UPDATED_SLA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_SLA_EXPIRATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SLA_EXPIRATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TRADING_PARTNER = "AAAAAAAAAA";
    private static final String UPDATED_TRADING_PARTNER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DELIVERY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DELIVERY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DELIVERY_NOTE_NUMBER = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DELIVERY_NOTE_NUMBER = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_RECEPIENT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_RECEPIENT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_IS_CASE_CLOSE = "AAAAAAAAAA";
    private static final String UPDATED_IS_CASE_CLOSE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OCR_REQUIRED = false;
    private static final Boolean UPDATED_OCR_REQUIRED = true;

    private static final String DEFAULT_BARCODE = "AAAAAAAAAA";
    private static final String UPDATED_BARCODE = "BBBBBBBBBB";

    private static final Currency DEFAULT_FUNCTIONAL_CURRENCY = Currency.INDIAN_RUPEE;
    private static final Currency UPDATED_FUNCTIONAL_CURRENCY = Currency.US_DOLLAR;

    private static final Currency DEFAULT_REPORTING_CURRENCY = Currency.INDIAN_RUPEE;
    private static final Currency UPDATED_REPORTING_CURRENCY = Currency.US_DOLLAR;

    private static final String DEFAULT_APPROVER_REQUIRED = "AAAAAAAAAA";
    private static final String UPDATED_APPROVER_REQUIRED = "BBBBBBBBBB";

    private static final String DEFAULT_SITE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SITE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SORT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SORT_CODE = "BBBBBBBBBB";

    private static final Double DEFAULT_DISCOUNT = 1D;
    private static final Double UPDATED_DISCOUNT = 2D;

    private static final Double DEFAULT_ADDITIONAL_CHARGES = 1D;
    private static final Double UPDATED_ADDITIONAL_CHARGES = 2D;

    private static final Double DEFAULT_SUM_LINE_AMOUNT = 1D;
    private static final Double UPDATED_SUM_LINE_AMOUNT = 2D;

    private static final Double DEFAULT_CONVERSION_RATE = 1D;
    private static final Double UPDATED_CONVERSION_RATE = 2D;

    private static final Currency DEFAULT_PAYMENT_CURRENCY = Currency.INDIAN_RUPEE;
    private static final Currency UPDATED_PAYMENT_CURRENCY = Currency.US_DOLLAR;

    private static final String DEFAULT_LIABILITY_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_LIABILITY_ACCOUNT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_POSTING_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_POSTING_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TERM_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TERM_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_SHIPPING_AMOUNT = 1D;
    private static final Double UPDATED_SHIPPING_AMOUNT = 2D;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private InvoiceHeaderRepository invoiceHeaderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceHeaderMockMvc;

    private InvoiceHeader invoiceHeader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceHeader createEntity(EntityManager em) {
        InvoiceHeader invoiceHeader = new InvoiceHeader()
            .companyCode(DEFAULT_COMPANY_CODE)
            .legalEntityName(DEFAULT_LEGAL_ENTITY_NAME)
            .comments(DEFAULT_COMMENTS)
            .grossAmount(DEFAULT_GROSS_AMOUNT)
            .netAmount(DEFAULT_NET_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .country(DEFAULT_COUNTRY)
            .countryKey(DEFAULT_COUNTRY_KEY)
            .languageKey(DEFAULT_LANGUAGE_KEY)
            .erpReferenceNumber(DEFAULT_ERP_REFERENCE_NUMBER)
            .poBoxCode(DEFAULT_PO_BOX_CODE)
            .scanDate(DEFAULT_SCAN_DATE)
            .receivedDate(DEFAULT_RECEIVED_DATE)
            .caseCreationDate(DEFAULT_CASE_CREATION_DATE)
            .numberOfScannedPages(DEFAULT_NUMBER_OF_SCANNED_PAGES)
            .sla(DEFAULT_SLA)
            .slaExpirationDate(DEFAULT_SLA_EXPIRATION_DATE)
            .tradingPartner(DEFAULT_TRADING_PARTNER)
            .deliveryDate(DEFAULT_DELIVERY_DATE)
            .deliveryNoteNumber(DEFAULT_DELIVERY_NOTE_NUMBER)
            .recepientEmail(DEFAULT_RECEPIENT_EMAIL)
            .isCaseClose(DEFAULT_IS_CASE_CLOSE)
            .ocrRequired(DEFAULT_OCR_REQUIRED)
            .barcode(DEFAULT_BARCODE)
            .functionalCurrency(DEFAULT_FUNCTIONAL_CURRENCY)
            .reportingCurrency(DEFAULT_REPORTING_CURRENCY)
            .approverRequired(DEFAULT_APPROVER_REQUIRED)
            .siteCode(DEFAULT_SITE_CODE)
            .sortCode(DEFAULT_SORT_CODE)
            .discount(DEFAULT_DISCOUNT)
            .additionalCharges(DEFAULT_ADDITIONAL_CHARGES)
            .sumLineAmount(DEFAULT_SUM_LINE_AMOUNT)
            .conversionRate(DEFAULT_CONVERSION_RATE)
            .paymentCurrency(DEFAULT_PAYMENT_CURRENCY)
            .liabilityAccount(DEFAULT_LIABILITY_ACCOUNT)
            .postingDate(DEFAULT_POSTING_DATE)
            .termDate(DEFAULT_TERM_DATE)
            .shippingAmount(DEFAULT_SHIPPING_AMOUNT)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return invoiceHeader;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceHeader createUpdatedEntity(EntityManager em) {
        InvoiceHeader invoiceHeader = new InvoiceHeader()
            .companyCode(UPDATED_COMPANY_CODE)
            .legalEntityName(UPDATED_LEGAL_ENTITY_NAME)
            .comments(UPDATED_COMMENTS)
            .grossAmount(UPDATED_GROSS_AMOUNT)
            .netAmount(UPDATED_NET_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .country(UPDATED_COUNTRY)
            .countryKey(UPDATED_COUNTRY_KEY)
            .languageKey(UPDATED_LANGUAGE_KEY)
            .erpReferenceNumber(UPDATED_ERP_REFERENCE_NUMBER)
            .poBoxCode(UPDATED_PO_BOX_CODE)
            .scanDate(UPDATED_SCAN_DATE)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .caseCreationDate(UPDATED_CASE_CREATION_DATE)
            .numberOfScannedPages(UPDATED_NUMBER_OF_SCANNED_PAGES)
            .sla(UPDATED_SLA)
            .slaExpirationDate(UPDATED_SLA_EXPIRATION_DATE)
            .tradingPartner(UPDATED_TRADING_PARTNER)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .deliveryNoteNumber(UPDATED_DELIVERY_NOTE_NUMBER)
            .recepientEmail(UPDATED_RECEPIENT_EMAIL)
            .isCaseClose(UPDATED_IS_CASE_CLOSE)
            .ocrRequired(UPDATED_OCR_REQUIRED)
            .barcode(UPDATED_BARCODE)
            .functionalCurrency(UPDATED_FUNCTIONAL_CURRENCY)
            .reportingCurrency(UPDATED_REPORTING_CURRENCY)
            .approverRequired(UPDATED_APPROVER_REQUIRED)
            .siteCode(UPDATED_SITE_CODE)
            .sortCode(UPDATED_SORT_CODE)
            .discount(UPDATED_DISCOUNT)
            .additionalCharges(UPDATED_ADDITIONAL_CHARGES)
            .sumLineAmount(UPDATED_SUM_LINE_AMOUNT)
            .conversionRate(UPDATED_CONVERSION_RATE)
            .paymentCurrency(UPDATED_PAYMENT_CURRENCY)
            .liabilityAccount(UPDATED_LIABILITY_ACCOUNT)
            .postingDate(UPDATED_POSTING_DATE)
            .termDate(UPDATED_TERM_DATE)
            .shippingAmount(UPDATED_SHIPPING_AMOUNT)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return invoiceHeader;
    }

    @BeforeEach
    public void initTest() {
        invoiceHeader = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoiceHeader() throws Exception {
        int databaseSizeBeforeCreate = invoiceHeaderRepository.findAll().size();
        // Create the InvoiceHeader
        restInvoiceHeaderMockMvc.perform(post("/api/invoice-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceHeader)))
            .andExpect(status().isCreated());

        // Validate the InvoiceHeader in the database
        List<InvoiceHeader> invoiceHeaderList = invoiceHeaderRepository.findAll();
        assertThat(invoiceHeaderList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceHeader testInvoiceHeader = invoiceHeaderList.get(invoiceHeaderList.size() - 1);
        assertThat(testInvoiceHeader.getCompanyCode()).isEqualTo(DEFAULT_COMPANY_CODE);
        assertThat(testInvoiceHeader.getLegalEntityName()).isEqualTo(DEFAULT_LEGAL_ENTITY_NAME);
        assertThat(testInvoiceHeader.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testInvoiceHeader.getGrossAmount()).isEqualTo(DEFAULT_GROSS_AMOUNT);
        assertThat(testInvoiceHeader.getNetAmount()).isEqualTo(DEFAULT_NET_AMOUNT);
        assertThat(testInvoiceHeader.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testInvoiceHeader.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testInvoiceHeader.getCountryKey()).isEqualTo(DEFAULT_COUNTRY_KEY);
        assertThat(testInvoiceHeader.getLanguageKey()).isEqualTo(DEFAULT_LANGUAGE_KEY);
        assertThat(testInvoiceHeader.getErpReferenceNumber()).isEqualTo(DEFAULT_ERP_REFERENCE_NUMBER);
        assertThat(testInvoiceHeader.getPoBoxCode()).isEqualTo(DEFAULT_PO_BOX_CODE);
        assertThat(testInvoiceHeader.getScanDate()).isEqualTo(DEFAULT_SCAN_DATE);
        assertThat(testInvoiceHeader.getReceivedDate()).isEqualTo(DEFAULT_RECEIVED_DATE);
        assertThat(testInvoiceHeader.getCaseCreationDate()).isEqualTo(DEFAULT_CASE_CREATION_DATE);
        assertThat(testInvoiceHeader.getNumberOfScannedPages()).isEqualTo(DEFAULT_NUMBER_OF_SCANNED_PAGES);
        assertThat(testInvoiceHeader.getSla()).isEqualTo(DEFAULT_SLA);
        assertThat(testInvoiceHeader.getSlaExpirationDate()).isEqualTo(DEFAULT_SLA_EXPIRATION_DATE);
        assertThat(testInvoiceHeader.getTradingPartner()).isEqualTo(DEFAULT_TRADING_PARTNER);
        assertThat(testInvoiceHeader.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testInvoiceHeader.getDeliveryNoteNumber()).isEqualTo(DEFAULT_DELIVERY_NOTE_NUMBER);
        assertThat(testInvoiceHeader.getRecepientEmail()).isEqualTo(DEFAULT_RECEPIENT_EMAIL);
        assertThat(testInvoiceHeader.getIsCaseClose()).isEqualTo(DEFAULT_IS_CASE_CLOSE);
        assertThat(testInvoiceHeader.isOcrRequired()).isEqualTo(DEFAULT_OCR_REQUIRED);
        assertThat(testInvoiceHeader.getBarcode()).isEqualTo(DEFAULT_BARCODE);
        assertThat(testInvoiceHeader.getFunctionalCurrency()).isEqualTo(DEFAULT_FUNCTIONAL_CURRENCY);
        assertThat(testInvoiceHeader.getReportingCurrency()).isEqualTo(DEFAULT_REPORTING_CURRENCY);
        assertThat(testInvoiceHeader.getApproverRequired()).isEqualTo(DEFAULT_APPROVER_REQUIRED);
        assertThat(testInvoiceHeader.getSiteCode()).isEqualTo(DEFAULT_SITE_CODE);
        assertThat(testInvoiceHeader.getSortCode()).isEqualTo(DEFAULT_SORT_CODE);
        assertThat(testInvoiceHeader.getDiscount()).isEqualTo(DEFAULT_DISCOUNT);
        assertThat(testInvoiceHeader.getAdditionalCharges()).isEqualTo(DEFAULT_ADDITIONAL_CHARGES);
        assertThat(testInvoiceHeader.getSumLineAmount()).isEqualTo(DEFAULT_SUM_LINE_AMOUNT);
        assertThat(testInvoiceHeader.getConversionRate()).isEqualTo(DEFAULT_CONVERSION_RATE);
        assertThat(testInvoiceHeader.getPaymentCurrency()).isEqualTo(DEFAULT_PAYMENT_CURRENCY);
        assertThat(testInvoiceHeader.getLiabilityAccount()).isEqualTo(DEFAULT_LIABILITY_ACCOUNT);
        assertThat(testInvoiceHeader.getPostingDate()).isEqualTo(DEFAULT_POSTING_DATE);
        assertThat(testInvoiceHeader.getTermDate()).isEqualTo(DEFAULT_TERM_DATE);
        assertThat(testInvoiceHeader.getShippingAmount()).isEqualTo(DEFAULT_SHIPPING_AMOUNT);
        assertThat(testInvoiceHeader.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testInvoiceHeader.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createInvoiceHeaderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceHeaderRepository.findAll().size();

        // Create the InvoiceHeader with an existing ID
        invoiceHeader.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceHeaderMockMvc.perform(post("/api/invoice-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceHeader)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceHeader in the database
        List<InvoiceHeader> invoiceHeaderList = invoiceHeaderRepository.findAll();
        assertThat(invoiceHeaderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvoiceHeaders() throws Exception {
        // Initialize the database
        invoiceHeaderRepository.saveAndFlush(invoiceHeader);

        // Get all the invoiceHeaderList
        restInvoiceHeaderMockMvc.perform(get("/api/invoice-headers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceHeader.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyCode").value(hasItem(DEFAULT_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].legalEntityName").value(hasItem(DEFAULT_LEGAL_ENTITY_NAME)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].grossAmount").value(hasItem(DEFAULT_GROSS_AMOUNT)))
            .andExpect(jsonPath("$.[*].netAmount").value(hasItem(DEFAULT_NET_AMOUNT)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].countryKey").value(hasItem(DEFAULT_COUNTRY_KEY)))
            .andExpect(jsonPath("$.[*].languageKey").value(hasItem(DEFAULT_LANGUAGE_KEY)))
            .andExpect(jsonPath("$.[*].erpReferenceNumber").value(hasItem(DEFAULT_ERP_REFERENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].poBoxCode").value(hasItem(DEFAULT_PO_BOX_CODE)))
            .andExpect(jsonPath("$.[*].scanDate").value(hasItem(sameInstant(DEFAULT_SCAN_DATE))))
            .andExpect(jsonPath("$.[*].receivedDate").value(hasItem(sameInstant(DEFAULT_RECEIVED_DATE))))
            .andExpect(jsonPath("$.[*].caseCreationDate").value(hasItem(sameInstant(DEFAULT_CASE_CREATION_DATE))))
            .andExpect(jsonPath("$.[*].numberOfScannedPages").value(hasItem(DEFAULT_NUMBER_OF_SCANNED_PAGES)))
            .andExpect(jsonPath("$.[*].sla").value(hasItem(DEFAULT_SLA)))
            .andExpect(jsonPath("$.[*].slaExpirationDate").value(hasItem(sameInstant(DEFAULT_SLA_EXPIRATION_DATE))))
            .andExpect(jsonPath("$.[*].tradingPartner").value(hasItem(DEFAULT_TRADING_PARTNER)))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(sameInstant(DEFAULT_DELIVERY_DATE))))
            .andExpect(jsonPath("$.[*].deliveryNoteNumber").value(hasItem(sameInstant(DEFAULT_DELIVERY_NOTE_NUMBER))))
            .andExpect(jsonPath("$.[*].recepientEmail").value(hasItem(DEFAULT_RECEPIENT_EMAIL)))
            .andExpect(jsonPath("$.[*].isCaseClose").value(hasItem(DEFAULT_IS_CASE_CLOSE)))
            .andExpect(jsonPath("$.[*].ocrRequired").value(hasItem(DEFAULT_OCR_REQUIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].barcode").value(hasItem(DEFAULT_BARCODE)))
            .andExpect(jsonPath("$.[*].functionalCurrency").value(hasItem(DEFAULT_FUNCTIONAL_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].reportingCurrency").value(hasItem(DEFAULT_REPORTING_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].approverRequired").value(hasItem(DEFAULT_APPROVER_REQUIRED)))
            .andExpect(jsonPath("$.[*].siteCode").value(hasItem(DEFAULT_SITE_CODE)))
            .andExpect(jsonPath("$.[*].sortCode").value(hasItem(DEFAULT_SORT_CODE)))
            .andExpect(jsonPath("$.[*].discount").value(hasItem(DEFAULT_DISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].additionalCharges").value(hasItem(DEFAULT_ADDITIONAL_CHARGES.doubleValue())))
            .andExpect(jsonPath("$.[*].sumLineAmount").value(hasItem(DEFAULT_SUM_LINE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].conversionRate").value(hasItem(DEFAULT_CONVERSION_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentCurrency").value(hasItem(DEFAULT_PAYMENT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].liabilityAccount").value(hasItem(DEFAULT_LIABILITY_ACCOUNT)))
            .andExpect(jsonPath("$.[*].postingDate").value(hasItem(sameInstant(DEFAULT_POSTING_DATE))))
            .andExpect(jsonPath("$.[*].termDate").value(hasItem(sameInstant(DEFAULT_TERM_DATE))))
            .andExpect(jsonPath("$.[*].shippingAmount").value(hasItem(DEFAULT_SHIPPING_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getInvoiceHeader() throws Exception {
        // Initialize the database
        invoiceHeaderRepository.saveAndFlush(invoiceHeader);

        // Get the invoiceHeader
        restInvoiceHeaderMockMvc.perform(get("/api/invoice-headers/{id}", invoiceHeader.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceHeader.getId().intValue()))
            .andExpect(jsonPath("$.companyCode").value(DEFAULT_COMPANY_CODE))
            .andExpect(jsonPath("$.legalEntityName").value(DEFAULT_LEGAL_ENTITY_NAME))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.grossAmount").value(DEFAULT_GROSS_AMOUNT))
            .andExpect(jsonPath("$.netAmount").value(DEFAULT_NET_AMOUNT))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.countryKey").value(DEFAULT_COUNTRY_KEY))
            .andExpect(jsonPath("$.languageKey").value(DEFAULT_LANGUAGE_KEY))
            .andExpect(jsonPath("$.erpReferenceNumber").value(DEFAULT_ERP_REFERENCE_NUMBER))
            .andExpect(jsonPath("$.poBoxCode").value(DEFAULT_PO_BOX_CODE))
            .andExpect(jsonPath("$.scanDate").value(sameInstant(DEFAULT_SCAN_DATE)))
            .andExpect(jsonPath("$.receivedDate").value(sameInstant(DEFAULT_RECEIVED_DATE)))
            .andExpect(jsonPath("$.caseCreationDate").value(sameInstant(DEFAULT_CASE_CREATION_DATE)))
            .andExpect(jsonPath("$.numberOfScannedPages").value(DEFAULT_NUMBER_OF_SCANNED_PAGES))
            .andExpect(jsonPath("$.sla").value(DEFAULT_SLA))
            .andExpect(jsonPath("$.slaExpirationDate").value(sameInstant(DEFAULT_SLA_EXPIRATION_DATE)))
            .andExpect(jsonPath("$.tradingPartner").value(DEFAULT_TRADING_PARTNER))
            .andExpect(jsonPath("$.deliveryDate").value(sameInstant(DEFAULT_DELIVERY_DATE)))
            .andExpect(jsonPath("$.deliveryNoteNumber").value(sameInstant(DEFAULT_DELIVERY_NOTE_NUMBER)))
            .andExpect(jsonPath("$.recepientEmail").value(DEFAULT_RECEPIENT_EMAIL))
            .andExpect(jsonPath("$.isCaseClose").value(DEFAULT_IS_CASE_CLOSE))
            .andExpect(jsonPath("$.ocrRequired").value(DEFAULT_OCR_REQUIRED.booleanValue()))
            .andExpect(jsonPath("$.barcode").value(DEFAULT_BARCODE))
            .andExpect(jsonPath("$.functionalCurrency").value(DEFAULT_FUNCTIONAL_CURRENCY.toString()))
            .andExpect(jsonPath("$.reportingCurrency").value(DEFAULT_REPORTING_CURRENCY.toString()))
            .andExpect(jsonPath("$.approverRequired").value(DEFAULT_APPROVER_REQUIRED))
            .andExpect(jsonPath("$.siteCode").value(DEFAULT_SITE_CODE))
            .andExpect(jsonPath("$.sortCode").value(DEFAULT_SORT_CODE))
            .andExpect(jsonPath("$.discount").value(DEFAULT_DISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.additionalCharges").value(DEFAULT_ADDITIONAL_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.sumLineAmount").value(DEFAULT_SUM_LINE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.conversionRate").value(DEFAULT_CONVERSION_RATE.doubleValue()))
            .andExpect(jsonPath("$.paymentCurrency").value(DEFAULT_PAYMENT_CURRENCY.toString()))
            .andExpect(jsonPath("$.liabilityAccount").value(DEFAULT_LIABILITY_ACCOUNT))
            .andExpect(jsonPath("$.postingDate").value(sameInstant(DEFAULT_POSTING_DATE)))
            .andExpect(jsonPath("$.termDate").value(sameInstant(DEFAULT_TERM_DATE)))
            .andExpect(jsonPath("$.shippingAmount").value(DEFAULT_SHIPPING_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingInvoiceHeader() throws Exception {
        // Get the invoiceHeader
        restInvoiceHeaderMockMvc.perform(get("/api/invoice-headers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoiceHeader() throws Exception {
        // Initialize the database
        invoiceHeaderRepository.saveAndFlush(invoiceHeader);

        int databaseSizeBeforeUpdate = invoiceHeaderRepository.findAll().size();

        // Update the invoiceHeader
        InvoiceHeader updatedInvoiceHeader = invoiceHeaderRepository.findById(invoiceHeader.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceHeader are not directly saved in db
        em.detach(updatedInvoiceHeader);
        updatedInvoiceHeader
            .companyCode(UPDATED_COMPANY_CODE)
            .legalEntityName(UPDATED_LEGAL_ENTITY_NAME)
            .comments(UPDATED_COMMENTS)
            .grossAmount(UPDATED_GROSS_AMOUNT)
            .netAmount(UPDATED_NET_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .country(UPDATED_COUNTRY)
            .countryKey(UPDATED_COUNTRY_KEY)
            .languageKey(UPDATED_LANGUAGE_KEY)
            .erpReferenceNumber(UPDATED_ERP_REFERENCE_NUMBER)
            .poBoxCode(UPDATED_PO_BOX_CODE)
            .scanDate(UPDATED_SCAN_DATE)
            .receivedDate(UPDATED_RECEIVED_DATE)
            .caseCreationDate(UPDATED_CASE_CREATION_DATE)
            .numberOfScannedPages(UPDATED_NUMBER_OF_SCANNED_PAGES)
            .sla(UPDATED_SLA)
            .slaExpirationDate(UPDATED_SLA_EXPIRATION_DATE)
            .tradingPartner(UPDATED_TRADING_PARTNER)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .deliveryNoteNumber(UPDATED_DELIVERY_NOTE_NUMBER)
            .recepientEmail(UPDATED_RECEPIENT_EMAIL)
            .isCaseClose(UPDATED_IS_CASE_CLOSE)
            .ocrRequired(UPDATED_OCR_REQUIRED)
            .barcode(UPDATED_BARCODE)
            .functionalCurrency(UPDATED_FUNCTIONAL_CURRENCY)
            .reportingCurrency(UPDATED_REPORTING_CURRENCY)
            .approverRequired(UPDATED_APPROVER_REQUIRED)
            .siteCode(UPDATED_SITE_CODE)
            .sortCode(UPDATED_SORT_CODE)
            .discount(UPDATED_DISCOUNT)
            .additionalCharges(UPDATED_ADDITIONAL_CHARGES)
            .sumLineAmount(UPDATED_SUM_LINE_AMOUNT)
            .conversionRate(UPDATED_CONVERSION_RATE)
            .paymentCurrency(UPDATED_PAYMENT_CURRENCY)
            .liabilityAccount(UPDATED_LIABILITY_ACCOUNT)
            .postingDate(UPDATED_POSTING_DATE)
            .termDate(UPDATED_TERM_DATE)
            .shippingAmount(UPDATED_SHIPPING_AMOUNT)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restInvoiceHeaderMockMvc.perform(put("/api/invoice-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceHeader)))
            .andExpect(status().isOk());

        // Validate the InvoiceHeader in the database
        List<InvoiceHeader> invoiceHeaderList = invoiceHeaderRepository.findAll();
        assertThat(invoiceHeaderList).hasSize(databaseSizeBeforeUpdate);
        InvoiceHeader testInvoiceHeader = invoiceHeaderList.get(invoiceHeaderList.size() - 1);
        assertThat(testInvoiceHeader.getCompanyCode()).isEqualTo(UPDATED_COMPANY_CODE);
        assertThat(testInvoiceHeader.getLegalEntityName()).isEqualTo(UPDATED_LEGAL_ENTITY_NAME);
        assertThat(testInvoiceHeader.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testInvoiceHeader.getGrossAmount()).isEqualTo(UPDATED_GROSS_AMOUNT);
        assertThat(testInvoiceHeader.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testInvoiceHeader.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testInvoiceHeader.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testInvoiceHeader.getCountryKey()).isEqualTo(UPDATED_COUNTRY_KEY);
        assertThat(testInvoiceHeader.getLanguageKey()).isEqualTo(UPDATED_LANGUAGE_KEY);
        assertThat(testInvoiceHeader.getErpReferenceNumber()).isEqualTo(UPDATED_ERP_REFERENCE_NUMBER);
        assertThat(testInvoiceHeader.getPoBoxCode()).isEqualTo(UPDATED_PO_BOX_CODE);
        assertThat(testInvoiceHeader.getScanDate()).isEqualTo(UPDATED_SCAN_DATE);
        assertThat(testInvoiceHeader.getReceivedDate()).isEqualTo(UPDATED_RECEIVED_DATE);
        assertThat(testInvoiceHeader.getCaseCreationDate()).isEqualTo(UPDATED_CASE_CREATION_DATE);
        assertThat(testInvoiceHeader.getNumberOfScannedPages()).isEqualTo(UPDATED_NUMBER_OF_SCANNED_PAGES);
        assertThat(testInvoiceHeader.getSla()).isEqualTo(UPDATED_SLA);
        assertThat(testInvoiceHeader.getSlaExpirationDate()).isEqualTo(UPDATED_SLA_EXPIRATION_DATE);
        assertThat(testInvoiceHeader.getTradingPartner()).isEqualTo(UPDATED_TRADING_PARTNER);
        assertThat(testInvoiceHeader.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testInvoiceHeader.getDeliveryNoteNumber()).isEqualTo(UPDATED_DELIVERY_NOTE_NUMBER);
        assertThat(testInvoiceHeader.getRecepientEmail()).isEqualTo(UPDATED_RECEPIENT_EMAIL);
        assertThat(testInvoiceHeader.getIsCaseClose()).isEqualTo(UPDATED_IS_CASE_CLOSE);
        assertThat(testInvoiceHeader.isOcrRequired()).isEqualTo(UPDATED_OCR_REQUIRED);
        assertThat(testInvoiceHeader.getBarcode()).isEqualTo(UPDATED_BARCODE);
        assertThat(testInvoiceHeader.getFunctionalCurrency()).isEqualTo(UPDATED_FUNCTIONAL_CURRENCY);
        assertThat(testInvoiceHeader.getReportingCurrency()).isEqualTo(UPDATED_REPORTING_CURRENCY);
        assertThat(testInvoiceHeader.getApproverRequired()).isEqualTo(UPDATED_APPROVER_REQUIRED);
        assertThat(testInvoiceHeader.getSiteCode()).isEqualTo(UPDATED_SITE_CODE);
        assertThat(testInvoiceHeader.getSortCode()).isEqualTo(UPDATED_SORT_CODE);
        assertThat(testInvoiceHeader.getDiscount()).isEqualTo(UPDATED_DISCOUNT);
        assertThat(testInvoiceHeader.getAdditionalCharges()).isEqualTo(UPDATED_ADDITIONAL_CHARGES);
        assertThat(testInvoiceHeader.getSumLineAmount()).isEqualTo(UPDATED_SUM_LINE_AMOUNT);
        assertThat(testInvoiceHeader.getConversionRate()).isEqualTo(UPDATED_CONVERSION_RATE);
        assertThat(testInvoiceHeader.getPaymentCurrency()).isEqualTo(UPDATED_PAYMENT_CURRENCY);
        assertThat(testInvoiceHeader.getLiabilityAccount()).isEqualTo(UPDATED_LIABILITY_ACCOUNT);
        assertThat(testInvoiceHeader.getPostingDate()).isEqualTo(UPDATED_POSTING_DATE);
        assertThat(testInvoiceHeader.getTermDate()).isEqualTo(UPDATED_TERM_DATE);
        assertThat(testInvoiceHeader.getShippingAmount()).isEqualTo(UPDATED_SHIPPING_AMOUNT);
        assertThat(testInvoiceHeader.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testInvoiceHeader.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoiceHeader() throws Exception {
        int databaseSizeBeforeUpdate = invoiceHeaderRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceHeaderMockMvc.perform(put("/api/invoice-headers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoiceHeader)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceHeader in the database
        List<InvoiceHeader> invoiceHeaderList = invoiceHeaderRepository.findAll();
        assertThat(invoiceHeaderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoiceHeader() throws Exception {
        // Initialize the database
        invoiceHeaderRepository.saveAndFlush(invoiceHeader);

        int databaseSizeBeforeDelete = invoiceHeaderRepository.findAll().size();

        // Delete the invoiceHeader
        restInvoiceHeaderMockMvc.perform(delete("/api/invoice-headers/{id}", invoiceHeader.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceHeader> invoiceHeaderList = invoiceHeaderRepository.findAll();
        assertThat(invoiceHeaderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
