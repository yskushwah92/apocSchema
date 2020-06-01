package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.PaymentInfo;
import com.mycompany.myapp.repository.PaymentInfoRepository;

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

import com.mycompany.myapp.domain.enumeration.PaymentMode;
import com.mycompany.myapp.domain.enumeration.PaymentStatus;
import com.mycompany.myapp.domain.enumeration.PaymentType;
import com.mycompany.myapp.domain.enumeration.ApprovalStatus;
import com.mycompany.myapp.domain.enumeration.Currency;
/**
 * Integration tests for the {@link PaymentInfoResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PaymentInfoResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TERMS = "AAAAAAAAAA";
    private static final String UPDATED_TERMS = "BBBBBBBBBB";

    private static final PaymentMode DEFAULT_MODE = PaymentMode.CASH;
    private static final PaymentMode UPDATED_MODE = PaymentMode.UPI;

    private static final ZonedDateTime DEFAULT_DUE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DUE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final PaymentStatus DEFAULT_STATUS = PaymentStatus.SUCCESS;
    private static final PaymentStatus UPDATED_STATUS = PaymentStatus.FAILURE;

    private static final String DEFAULT_PAYMENT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_CHANNEL = "BBBBBBBBBB";

    private static final PaymentType DEFAULT_TYPE = PaymentType.FORWARD;
    private static final PaymentType UPDATED_TYPE = PaymentType.REFUND;

    private static final ApprovalStatus DEFAULT_APPROVAL_STATUS = ApprovalStatus.APPROVED;
    private static final ApprovalStatus UPDATED_APPROVAL_STATUS = ApprovalStatus.DISAPPROVED;

    private static final ZonedDateTime DEFAULT_DATE_OF_APPROVAL = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OF_APPROVAL = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_OF_PAYMENT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OF_PAYMENT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PAYMENT_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CHECK_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CHECK_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CHECK_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_NUMBER = "BBBBBBBBBB";

    private static final Double DEFAULT_CHECK_AMOUNT = 1D;
    private static final Double UPDATED_CHECK_AMOUNT = 2D;

    private static final ZonedDateTime DEFAULT_EARLY_PAYMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_EARLY_PAYMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_EARLY_PAYMENT_DISCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_EARLY_PAYMENT_DISCOUNT = "BBBBBBBBBB";

    private static final Double DEFAULT_EARLY_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_EARLY_PAYMENT_AMOUNT = 2D;

    private static final String DEFAULT_EARLY_PAYMENT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_EARLY_PAYMENT_REMARKS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_PAYMENT_DOC_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_PAYMENT_DOC_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PAYMENT_DOC_NO = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_DOC_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_PAYMENT_AMOUNT = 1D;
    private static final Double UPDATED_PAYMENT_AMOUNT = 2D;

    private static final Double DEFAULT_DISCOUNT_TAKEN = 1D;
    private static final Double UPDATED_DISCOUNT_TAKEN = 2D;

    private static final Double DEFAULT_DISCOUNT_LOST = 1D;
    private static final Double UPDATED_DISCOUNT_LOST = 2D;

    private static final Currency DEFAULT_PAYMENT_CURRENCY = Currency.INDIAN_RUPEE;
    private static final Currency UPDATED_PAYMENT_CURRENCY = Currency.US_DOLLAR;

    private static final Double DEFAULT_INVOICE_BASE_AMOUNT = 1D;
    private static final Double UPDATED_INVOICE_BASE_AMOUNT = 2D;

    private static final ZonedDateTime DEFAULT_CLEARED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CLEARED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_CLEARED_AMOUNT = 1D;
    private static final Double UPDATED_CLEARED_AMOUNT = 2D;

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymentInfoMockMvc;

    private PaymentInfo paymentInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentInfo createEntity(EntityManager em) {
        PaymentInfo paymentInfo = new PaymentInfo()
            .name(DEFAULT_NAME)
            .terms(DEFAULT_TERMS)
            .mode(DEFAULT_MODE)
            .dueDate(DEFAULT_DUE_DATE)
            .status(DEFAULT_STATUS)
            .paymentChannel(DEFAULT_PAYMENT_CHANNEL)
            .type(DEFAULT_TYPE)
            .approvalStatus(DEFAULT_APPROVAL_STATUS)
            .dateOfApproval(DEFAULT_DATE_OF_APPROVAL)
            .dateOfPayment(DEFAULT_DATE_OF_PAYMENT)
            .paymentReferenceNumber(DEFAULT_PAYMENT_REFERENCE_NUMBER)
            .checkDate(DEFAULT_CHECK_DATE)
            .checkNumber(DEFAULT_CHECK_NUMBER)
            .checkAmount(DEFAULT_CHECK_AMOUNT)
            .earlyPaymentDate(DEFAULT_EARLY_PAYMENT_DATE)
            .earlyPaymentDiscount(DEFAULT_EARLY_PAYMENT_DISCOUNT)
            .earlyPaymentAmount(DEFAULT_EARLY_PAYMENT_AMOUNT)
            .earlyPaymentRemarks(DEFAULT_EARLY_PAYMENT_REMARKS)
            .paymentDocDate(DEFAULT_PAYMENT_DOC_DATE)
            .paymentDocNo(DEFAULT_PAYMENT_DOC_NO)
            .paymentAmount(DEFAULT_PAYMENT_AMOUNT)
            .discountTaken(DEFAULT_DISCOUNT_TAKEN)
            .discountLost(DEFAULT_DISCOUNT_LOST)
            .paymentCurrency(DEFAULT_PAYMENT_CURRENCY)
            .invoiceBaseAmount(DEFAULT_INVOICE_BASE_AMOUNT)
            .clearedDate(DEFAULT_CLEARED_DATE)
            .clearedAmount(DEFAULT_CLEARED_AMOUNT)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return paymentInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaymentInfo createUpdatedEntity(EntityManager em) {
        PaymentInfo paymentInfo = new PaymentInfo()
            .name(UPDATED_NAME)
            .terms(UPDATED_TERMS)
            .mode(UPDATED_MODE)
            .dueDate(UPDATED_DUE_DATE)
            .status(UPDATED_STATUS)
            .paymentChannel(UPDATED_PAYMENT_CHANNEL)
            .type(UPDATED_TYPE)
            .approvalStatus(UPDATED_APPROVAL_STATUS)
            .dateOfApproval(UPDATED_DATE_OF_APPROVAL)
            .dateOfPayment(UPDATED_DATE_OF_PAYMENT)
            .paymentReferenceNumber(UPDATED_PAYMENT_REFERENCE_NUMBER)
            .checkDate(UPDATED_CHECK_DATE)
            .checkNumber(UPDATED_CHECK_NUMBER)
            .checkAmount(UPDATED_CHECK_AMOUNT)
            .earlyPaymentDate(UPDATED_EARLY_PAYMENT_DATE)
            .earlyPaymentDiscount(UPDATED_EARLY_PAYMENT_DISCOUNT)
            .earlyPaymentAmount(UPDATED_EARLY_PAYMENT_AMOUNT)
            .earlyPaymentRemarks(UPDATED_EARLY_PAYMENT_REMARKS)
            .paymentDocDate(UPDATED_PAYMENT_DOC_DATE)
            .paymentDocNo(UPDATED_PAYMENT_DOC_NO)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .discountTaken(UPDATED_DISCOUNT_TAKEN)
            .discountLost(UPDATED_DISCOUNT_LOST)
            .paymentCurrency(UPDATED_PAYMENT_CURRENCY)
            .invoiceBaseAmount(UPDATED_INVOICE_BASE_AMOUNT)
            .clearedDate(UPDATED_CLEARED_DATE)
            .clearedAmount(UPDATED_CLEARED_AMOUNT)
            .transactionId(UPDATED_TRANSACTION_ID)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return paymentInfo;
    }

    @BeforeEach
    public void initTest() {
        paymentInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaymentInfo() throws Exception {
        int databaseSizeBeforeCreate = paymentInfoRepository.findAll().size();
        // Create the PaymentInfo
        restPaymentInfoMockMvc.perform(post("/api/payment-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentInfo)))
            .andExpect(status().isCreated());

        // Validate the PaymentInfo in the database
        List<PaymentInfo> paymentInfoList = paymentInfoRepository.findAll();
        assertThat(paymentInfoList).hasSize(databaseSizeBeforeCreate + 1);
        PaymentInfo testPaymentInfo = paymentInfoList.get(paymentInfoList.size() - 1);
        assertThat(testPaymentInfo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPaymentInfo.getTerms()).isEqualTo(DEFAULT_TERMS);
        assertThat(testPaymentInfo.getMode()).isEqualTo(DEFAULT_MODE);
        assertThat(testPaymentInfo.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testPaymentInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPaymentInfo.getPaymentChannel()).isEqualTo(DEFAULT_PAYMENT_CHANNEL);
        assertThat(testPaymentInfo.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testPaymentInfo.getApprovalStatus()).isEqualTo(DEFAULT_APPROVAL_STATUS);
        assertThat(testPaymentInfo.getDateOfApproval()).isEqualTo(DEFAULT_DATE_OF_APPROVAL);
        assertThat(testPaymentInfo.getDateOfPayment()).isEqualTo(DEFAULT_DATE_OF_PAYMENT);
        assertThat(testPaymentInfo.getPaymentReferenceNumber()).isEqualTo(DEFAULT_PAYMENT_REFERENCE_NUMBER);
        assertThat(testPaymentInfo.getCheckDate()).isEqualTo(DEFAULT_CHECK_DATE);
        assertThat(testPaymentInfo.getCheckNumber()).isEqualTo(DEFAULT_CHECK_NUMBER);
        assertThat(testPaymentInfo.getCheckAmount()).isEqualTo(DEFAULT_CHECK_AMOUNT);
        assertThat(testPaymentInfo.getEarlyPaymentDate()).isEqualTo(DEFAULT_EARLY_PAYMENT_DATE);
        assertThat(testPaymentInfo.getEarlyPaymentDiscount()).isEqualTo(DEFAULT_EARLY_PAYMENT_DISCOUNT);
        assertThat(testPaymentInfo.getEarlyPaymentAmount()).isEqualTo(DEFAULT_EARLY_PAYMENT_AMOUNT);
        assertThat(testPaymentInfo.getEarlyPaymentRemarks()).isEqualTo(DEFAULT_EARLY_PAYMENT_REMARKS);
        assertThat(testPaymentInfo.getPaymentDocDate()).isEqualTo(DEFAULT_PAYMENT_DOC_DATE);
        assertThat(testPaymentInfo.getPaymentDocNo()).isEqualTo(DEFAULT_PAYMENT_DOC_NO);
        assertThat(testPaymentInfo.getPaymentAmount()).isEqualTo(DEFAULT_PAYMENT_AMOUNT);
        assertThat(testPaymentInfo.getDiscountTaken()).isEqualTo(DEFAULT_DISCOUNT_TAKEN);
        assertThat(testPaymentInfo.getDiscountLost()).isEqualTo(DEFAULT_DISCOUNT_LOST);
        assertThat(testPaymentInfo.getPaymentCurrency()).isEqualTo(DEFAULT_PAYMENT_CURRENCY);
        assertThat(testPaymentInfo.getInvoiceBaseAmount()).isEqualTo(DEFAULT_INVOICE_BASE_AMOUNT);
        assertThat(testPaymentInfo.getClearedDate()).isEqualTo(DEFAULT_CLEARED_DATE);
        assertThat(testPaymentInfo.getClearedAmount()).isEqualTo(DEFAULT_CLEARED_AMOUNT);
        assertThat(testPaymentInfo.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testPaymentInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testPaymentInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createPaymentInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paymentInfoRepository.findAll().size();

        // Create the PaymentInfo with an existing ID
        paymentInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymentInfoMockMvc.perform(post("/api/payment-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentInfo)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentInfo in the database
        List<PaymentInfo> paymentInfoList = paymentInfoRepository.findAll();
        assertThat(paymentInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPaymentInfos() throws Exception {
        // Initialize the database
        paymentInfoRepository.saveAndFlush(paymentInfo);

        // Get all the paymentInfoList
        restPaymentInfoMockMvc.perform(get("/api/payment-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].terms").value(hasItem(DEFAULT_TERMS)))
            .andExpect(jsonPath("$.[*].mode").value(hasItem(DEFAULT_MODE.toString())))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(sameInstant(DEFAULT_DUE_DATE))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].paymentChannel").value(hasItem(DEFAULT_PAYMENT_CHANNEL)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].approvalStatus").value(hasItem(DEFAULT_APPROVAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].dateOfApproval").value(hasItem(sameInstant(DEFAULT_DATE_OF_APPROVAL))))
            .andExpect(jsonPath("$.[*].dateOfPayment").value(hasItem(sameInstant(DEFAULT_DATE_OF_PAYMENT))))
            .andExpect(jsonPath("$.[*].paymentReferenceNumber").value(hasItem(DEFAULT_PAYMENT_REFERENCE_NUMBER)))
            .andExpect(jsonPath("$.[*].checkDate").value(hasItem(sameInstant(DEFAULT_CHECK_DATE))))
            .andExpect(jsonPath("$.[*].checkNumber").value(hasItem(DEFAULT_CHECK_NUMBER)))
            .andExpect(jsonPath("$.[*].checkAmount").value(hasItem(DEFAULT_CHECK_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].earlyPaymentDate").value(hasItem(sameInstant(DEFAULT_EARLY_PAYMENT_DATE))))
            .andExpect(jsonPath("$.[*].earlyPaymentDiscount").value(hasItem(DEFAULT_EARLY_PAYMENT_DISCOUNT)))
            .andExpect(jsonPath("$.[*].earlyPaymentAmount").value(hasItem(DEFAULT_EARLY_PAYMENT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].earlyPaymentRemarks").value(hasItem(DEFAULT_EARLY_PAYMENT_REMARKS)))
            .andExpect(jsonPath("$.[*].paymentDocDate").value(hasItem(sameInstant(DEFAULT_PAYMENT_DOC_DATE))))
            .andExpect(jsonPath("$.[*].paymentDocNo").value(hasItem(DEFAULT_PAYMENT_DOC_NO)))
            .andExpect(jsonPath("$.[*].paymentAmount").value(hasItem(DEFAULT_PAYMENT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].discountTaken").value(hasItem(DEFAULT_DISCOUNT_TAKEN.doubleValue())))
            .andExpect(jsonPath("$.[*].discountLost").value(hasItem(DEFAULT_DISCOUNT_LOST.doubleValue())))
            .andExpect(jsonPath("$.[*].paymentCurrency").value(hasItem(DEFAULT_PAYMENT_CURRENCY.toString())))
            .andExpect(jsonPath("$.[*].invoiceBaseAmount").value(hasItem(DEFAULT_INVOICE_BASE_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].clearedDate").value(hasItem(sameInstant(DEFAULT_CLEARED_DATE))))
            .andExpect(jsonPath("$.[*].clearedAmount").value(hasItem(DEFAULT_CLEARED_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].transactionId").value(hasItem(DEFAULT_TRANSACTION_ID)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getPaymentInfo() throws Exception {
        // Initialize the database
        paymentInfoRepository.saveAndFlush(paymentInfo);

        // Get the paymentInfo
        restPaymentInfoMockMvc.perform(get("/api/payment-infos/{id}", paymentInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentInfo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.terms").value(DEFAULT_TERMS))
            .andExpect(jsonPath("$.mode").value(DEFAULT_MODE.toString()))
            .andExpect(jsonPath("$.dueDate").value(sameInstant(DEFAULT_DUE_DATE)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.paymentChannel").value(DEFAULT_PAYMENT_CHANNEL))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.approvalStatus").value(DEFAULT_APPROVAL_STATUS.toString()))
            .andExpect(jsonPath("$.dateOfApproval").value(sameInstant(DEFAULT_DATE_OF_APPROVAL)))
            .andExpect(jsonPath("$.dateOfPayment").value(sameInstant(DEFAULT_DATE_OF_PAYMENT)))
            .andExpect(jsonPath("$.paymentReferenceNumber").value(DEFAULT_PAYMENT_REFERENCE_NUMBER))
            .andExpect(jsonPath("$.checkDate").value(sameInstant(DEFAULT_CHECK_DATE)))
            .andExpect(jsonPath("$.checkNumber").value(DEFAULT_CHECK_NUMBER))
            .andExpect(jsonPath("$.checkAmount").value(DEFAULT_CHECK_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.earlyPaymentDate").value(sameInstant(DEFAULT_EARLY_PAYMENT_DATE)))
            .andExpect(jsonPath("$.earlyPaymentDiscount").value(DEFAULT_EARLY_PAYMENT_DISCOUNT))
            .andExpect(jsonPath("$.earlyPaymentAmount").value(DEFAULT_EARLY_PAYMENT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.earlyPaymentRemarks").value(DEFAULT_EARLY_PAYMENT_REMARKS))
            .andExpect(jsonPath("$.paymentDocDate").value(sameInstant(DEFAULT_PAYMENT_DOC_DATE)))
            .andExpect(jsonPath("$.paymentDocNo").value(DEFAULT_PAYMENT_DOC_NO))
            .andExpect(jsonPath("$.paymentAmount").value(DEFAULT_PAYMENT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.discountTaken").value(DEFAULT_DISCOUNT_TAKEN.doubleValue()))
            .andExpect(jsonPath("$.discountLost").value(DEFAULT_DISCOUNT_LOST.doubleValue()))
            .andExpect(jsonPath("$.paymentCurrency").value(DEFAULT_PAYMENT_CURRENCY.toString()))
            .andExpect(jsonPath("$.invoiceBaseAmount").value(DEFAULT_INVOICE_BASE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.clearedDate").value(sameInstant(DEFAULT_CLEARED_DATE)))
            .andExpect(jsonPath("$.clearedAmount").value(DEFAULT_CLEARED_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.transactionId").value(DEFAULT_TRANSACTION_ID))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingPaymentInfo() throws Exception {
        // Get the paymentInfo
        restPaymentInfoMockMvc.perform(get("/api/payment-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentInfo() throws Exception {
        // Initialize the database
        paymentInfoRepository.saveAndFlush(paymentInfo);

        int databaseSizeBeforeUpdate = paymentInfoRepository.findAll().size();

        // Update the paymentInfo
        PaymentInfo updatedPaymentInfo = paymentInfoRepository.findById(paymentInfo.getId()).get();
        // Disconnect from session so that the updates on updatedPaymentInfo are not directly saved in db
        em.detach(updatedPaymentInfo);
        updatedPaymentInfo
            .name(UPDATED_NAME)
            .terms(UPDATED_TERMS)
            .mode(UPDATED_MODE)
            .dueDate(UPDATED_DUE_DATE)
            .status(UPDATED_STATUS)
            .paymentChannel(UPDATED_PAYMENT_CHANNEL)
            .type(UPDATED_TYPE)
            .approvalStatus(UPDATED_APPROVAL_STATUS)
            .dateOfApproval(UPDATED_DATE_OF_APPROVAL)
            .dateOfPayment(UPDATED_DATE_OF_PAYMENT)
            .paymentReferenceNumber(UPDATED_PAYMENT_REFERENCE_NUMBER)
            .checkDate(UPDATED_CHECK_DATE)
            .checkNumber(UPDATED_CHECK_NUMBER)
            .checkAmount(UPDATED_CHECK_AMOUNT)
            .earlyPaymentDate(UPDATED_EARLY_PAYMENT_DATE)
            .earlyPaymentDiscount(UPDATED_EARLY_PAYMENT_DISCOUNT)
            .earlyPaymentAmount(UPDATED_EARLY_PAYMENT_AMOUNT)
            .earlyPaymentRemarks(UPDATED_EARLY_PAYMENT_REMARKS)
            .paymentDocDate(UPDATED_PAYMENT_DOC_DATE)
            .paymentDocNo(UPDATED_PAYMENT_DOC_NO)
            .paymentAmount(UPDATED_PAYMENT_AMOUNT)
            .discountTaken(UPDATED_DISCOUNT_TAKEN)
            .discountLost(UPDATED_DISCOUNT_LOST)
            .paymentCurrency(UPDATED_PAYMENT_CURRENCY)
            .invoiceBaseAmount(UPDATED_INVOICE_BASE_AMOUNT)
            .clearedDate(UPDATED_CLEARED_DATE)
            .clearedAmount(UPDATED_CLEARED_AMOUNT)
            .transactionId(UPDATED_TRANSACTION_ID)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restPaymentInfoMockMvc.perform(put("/api/payment-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPaymentInfo)))
            .andExpect(status().isOk());

        // Validate the PaymentInfo in the database
        List<PaymentInfo> paymentInfoList = paymentInfoRepository.findAll();
        assertThat(paymentInfoList).hasSize(databaseSizeBeforeUpdate);
        PaymentInfo testPaymentInfo = paymentInfoList.get(paymentInfoList.size() - 1);
        assertThat(testPaymentInfo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPaymentInfo.getTerms()).isEqualTo(UPDATED_TERMS);
        assertThat(testPaymentInfo.getMode()).isEqualTo(UPDATED_MODE);
        assertThat(testPaymentInfo.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testPaymentInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPaymentInfo.getPaymentChannel()).isEqualTo(UPDATED_PAYMENT_CHANNEL);
        assertThat(testPaymentInfo.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testPaymentInfo.getApprovalStatus()).isEqualTo(UPDATED_APPROVAL_STATUS);
        assertThat(testPaymentInfo.getDateOfApproval()).isEqualTo(UPDATED_DATE_OF_APPROVAL);
        assertThat(testPaymentInfo.getDateOfPayment()).isEqualTo(UPDATED_DATE_OF_PAYMENT);
        assertThat(testPaymentInfo.getPaymentReferenceNumber()).isEqualTo(UPDATED_PAYMENT_REFERENCE_NUMBER);
        assertThat(testPaymentInfo.getCheckDate()).isEqualTo(UPDATED_CHECK_DATE);
        assertThat(testPaymentInfo.getCheckNumber()).isEqualTo(UPDATED_CHECK_NUMBER);
        assertThat(testPaymentInfo.getCheckAmount()).isEqualTo(UPDATED_CHECK_AMOUNT);
        assertThat(testPaymentInfo.getEarlyPaymentDate()).isEqualTo(UPDATED_EARLY_PAYMENT_DATE);
        assertThat(testPaymentInfo.getEarlyPaymentDiscount()).isEqualTo(UPDATED_EARLY_PAYMENT_DISCOUNT);
        assertThat(testPaymentInfo.getEarlyPaymentAmount()).isEqualTo(UPDATED_EARLY_PAYMENT_AMOUNT);
        assertThat(testPaymentInfo.getEarlyPaymentRemarks()).isEqualTo(UPDATED_EARLY_PAYMENT_REMARKS);
        assertThat(testPaymentInfo.getPaymentDocDate()).isEqualTo(UPDATED_PAYMENT_DOC_DATE);
        assertThat(testPaymentInfo.getPaymentDocNo()).isEqualTo(UPDATED_PAYMENT_DOC_NO);
        assertThat(testPaymentInfo.getPaymentAmount()).isEqualTo(UPDATED_PAYMENT_AMOUNT);
        assertThat(testPaymentInfo.getDiscountTaken()).isEqualTo(UPDATED_DISCOUNT_TAKEN);
        assertThat(testPaymentInfo.getDiscountLost()).isEqualTo(UPDATED_DISCOUNT_LOST);
        assertThat(testPaymentInfo.getPaymentCurrency()).isEqualTo(UPDATED_PAYMENT_CURRENCY);
        assertThat(testPaymentInfo.getInvoiceBaseAmount()).isEqualTo(UPDATED_INVOICE_BASE_AMOUNT);
        assertThat(testPaymentInfo.getClearedDate()).isEqualTo(UPDATED_CLEARED_DATE);
        assertThat(testPaymentInfo.getClearedAmount()).isEqualTo(UPDATED_CLEARED_AMOUNT);
        assertThat(testPaymentInfo.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testPaymentInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testPaymentInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingPaymentInfo() throws Exception {
        int databaseSizeBeforeUpdate = paymentInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymentInfoMockMvc.perform(put("/api/payment-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(paymentInfo)))
            .andExpect(status().isBadRequest());

        // Validate the PaymentInfo in the database
        List<PaymentInfo> paymentInfoList = paymentInfoRepository.findAll();
        assertThat(paymentInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePaymentInfo() throws Exception {
        // Initialize the database
        paymentInfoRepository.saveAndFlush(paymentInfo);

        int databaseSizeBeforeDelete = paymentInfoRepository.findAll().size();

        // Delete the paymentInfo
        restPaymentInfoMockMvc.perform(delete("/api/payment-infos/{id}", paymentInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PaymentInfo> paymentInfoList = paymentInfoRepository.findAll();
        assertThat(paymentInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
