package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.Invoice;
import com.mycompany.myapp.repository.InvoiceRepository;

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

import com.mycompany.myapp.domain.enumeration.Priority;
import com.mycompany.myapp.domain.enumeration.InvoiceType;
import com.mycompany.myapp.domain.enumeration.InvoiceStatus;
/**
 * Integration tests for the {@link InvoiceResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvoiceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_INVOICE_NO = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CASE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Priority DEFAULT_PRIORITY = Priority.HIGH;
    private static final Priority UPDATED_PRIORITY = Priority.MEDIUM;

    private static final InvoiceType DEFAULT_TYPE = InvoiceType.PURCHASE_ORDER;
    private static final InvoiceType UPDATED_TYPE = InvoiceType.NON_PURCHASE_ORDER;

    private static final ZonedDateTime DEFAULT_DATE_OF_CREATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OF_CREATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_SELLER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SELLER_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT_FUNCTIONAL = 1D;
    private static final Double UPDATED_AMOUNT_FUNCTIONAL = 2D;

    private static final Double DEFAULT_AMOUNT_REPORTING = 1D;
    private static final Double UPDATED_AMOUNT_REPORTING = 2D;

    private static final String DEFAULT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_ISSUER = "AAAAAAAAAA";
    private static final String UPDATED_ISSUER = "BBBBBBBBBB";

    private static final String DEFAULT_TAX = "AAAAAAAAAA";
    private static final String UPDATED_TAX = "BBBBBBBBBB";

    private static final Boolean DEFAULT_INCLUSIVE_OF_TAX = false;
    private static final Boolean UPDATED_INCLUSIVE_OF_TAX = true;

    private static final InvoiceStatus DEFAULT_STATUS = InvoiceStatus.ACCEPTED;
    private static final InvoiceStatus UPDATED_STATUS = InvoiceStatus.REJECTED;

    private static final String DEFAULT_GSTN = "AAAAAAAAAA";
    private static final String UPDATED_GSTN = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION = "AAAAAAAAAA";
    private static final String UPDATED_PREFFERRED_MODE_OF_COMMUNICATION = "BBBBBBBBBB";

    private static final String DEFAULT_POINT_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_POINT_OF_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACC_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACC_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_UPI_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_UPI_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_PREFERRED_MODE_OF_PAYMENT = "AAAAAAAAAA";
    private static final String UPDATED_PREFERRED_MODE_OF_PAYMENT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceMockMvc;

    private Invoice invoice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invoice createEntity(EntityManager em) {
        Invoice invoice = new Invoice()
            .name(DEFAULT_NAME)
            .serialNo(DEFAULT_SERIAL_NO)
            .invoiceNo(DEFAULT_INVOICE_NO)
            .referenceNo(DEFAULT_REFERENCE_NO)
            .caseNo(DEFAULT_CASE_NO)
            .description(DEFAULT_DESCRIPTION)
            .priority(DEFAULT_PRIORITY)
            .type(DEFAULT_TYPE)
            .dateOfCreation(DEFAULT_DATE_OF_CREATION)
            .sellerName(DEFAULT_SELLER_NAME)
            .amountFunctional(DEFAULT_AMOUNT_FUNCTIONAL)
            .amountReporting(DEFAULT_AMOUNT_REPORTING)
            .price(DEFAULT_PRICE)
            .issuer(DEFAULT_ISSUER)
            .tax(DEFAULT_TAX)
            .inclusiveOfTax(DEFAULT_INCLUSIVE_OF_TAX)
            .status(DEFAULT_STATUS)
            .gstn(DEFAULT_GSTN)
            .address(DEFAULT_ADDRESS)
            .category(DEFAULT_CATEGORY)
            .contactNo(DEFAULT_CONTACT_NO)
            .email(DEFAULT_EMAIL)
            .prefferredModeOfCommunication(DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION)
            .pointOfContact(DEFAULT_POINT_OF_CONTACT)
            .bankAccDetails(DEFAULT_BANK_ACC_DETAILS)
            .upiDetails(DEFAULT_UPI_DETAILS)
            .preferredModeOfPayment(DEFAULT_PREFERRED_MODE_OF_PAYMENT)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return invoice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invoice createUpdatedEntity(EntityManager em) {
        Invoice invoice = new Invoice()
            .name(UPDATED_NAME)
            .serialNo(UPDATED_SERIAL_NO)
            .invoiceNo(UPDATED_INVOICE_NO)
            .referenceNo(UPDATED_REFERENCE_NO)
            .caseNo(UPDATED_CASE_NO)
            .description(UPDATED_DESCRIPTION)
            .priority(UPDATED_PRIORITY)
            .type(UPDATED_TYPE)
            .dateOfCreation(UPDATED_DATE_OF_CREATION)
            .sellerName(UPDATED_SELLER_NAME)
            .amountFunctional(UPDATED_AMOUNT_FUNCTIONAL)
            .amountReporting(UPDATED_AMOUNT_REPORTING)
            .price(UPDATED_PRICE)
            .issuer(UPDATED_ISSUER)
            .tax(UPDATED_TAX)
            .inclusiveOfTax(UPDATED_INCLUSIVE_OF_TAX)
            .status(UPDATED_STATUS)
            .gstn(UPDATED_GSTN)
            .address(UPDATED_ADDRESS)
            .category(UPDATED_CATEGORY)
            .contactNo(UPDATED_CONTACT_NO)
            .email(UPDATED_EMAIL)
            .prefferredModeOfCommunication(UPDATED_PREFFERRED_MODE_OF_COMMUNICATION)
            .pointOfContact(UPDATED_POINT_OF_CONTACT)
            .bankAccDetails(UPDATED_BANK_ACC_DETAILS)
            .upiDetails(UPDATED_UPI_DETAILS)
            .preferredModeOfPayment(UPDATED_PREFERRED_MODE_OF_PAYMENT)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return invoice;
    }

    @BeforeEach
    public void initTest() {
        invoice = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvoice() throws Exception {
        int databaseSizeBeforeCreate = invoiceRepository.findAll().size();
        // Create the Invoice
        restInvoiceMockMvc.perform(post("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoice)))
            .andExpect(status().isCreated());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeCreate + 1);
        Invoice testInvoice = invoiceList.get(invoiceList.size() - 1);
        assertThat(testInvoice.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testInvoice.getSerialNo()).isEqualTo(DEFAULT_SERIAL_NO);
        assertThat(testInvoice.getInvoiceNo()).isEqualTo(DEFAULT_INVOICE_NO);
        assertThat(testInvoice.getReferenceNo()).isEqualTo(DEFAULT_REFERENCE_NO);
        assertThat(testInvoice.getCaseNo()).isEqualTo(DEFAULT_CASE_NO);
        assertThat(testInvoice.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInvoice.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testInvoice.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testInvoice.getDateOfCreation()).isEqualTo(DEFAULT_DATE_OF_CREATION);
        assertThat(testInvoice.getSellerName()).isEqualTo(DEFAULT_SELLER_NAME);
        assertThat(testInvoice.getAmountFunctional()).isEqualTo(DEFAULT_AMOUNT_FUNCTIONAL);
        assertThat(testInvoice.getAmountReporting()).isEqualTo(DEFAULT_AMOUNT_REPORTING);
        assertThat(testInvoice.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testInvoice.getIssuer()).isEqualTo(DEFAULT_ISSUER);
        assertThat(testInvoice.getTax()).isEqualTo(DEFAULT_TAX);
        assertThat(testInvoice.isInclusiveOfTax()).isEqualTo(DEFAULT_INCLUSIVE_OF_TAX);
        assertThat(testInvoice.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testInvoice.getGstn()).isEqualTo(DEFAULT_GSTN);
        assertThat(testInvoice.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testInvoice.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testInvoice.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testInvoice.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testInvoice.getPrefferredModeOfCommunication()).isEqualTo(DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION);
        assertThat(testInvoice.getPointOfContact()).isEqualTo(DEFAULT_POINT_OF_CONTACT);
        assertThat(testInvoice.getBankAccDetails()).isEqualTo(DEFAULT_BANK_ACC_DETAILS);
        assertThat(testInvoice.getUpiDetails()).isEqualTo(DEFAULT_UPI_DETAILS);
        assertThat(testInvoice.getPreferredModeOfPayment()).isEqualTo(DEFAULT_PREFERRED_MODE_OF_PAYMENT);
        assertThat(testInvoice.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testInvoice.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createInvoiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invoiceRepository.findAll().size();

        // Create the Invoice with an existing ID
        invoice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceMockMvc.perform(post("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoice)))
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvoices() throws Exception {
        // Initialize the database
        invoiceRepository.saveAndFlush(invoice);

        // Get all the invoiceList
        restInvoiceMockMvc.perform(get("/api/invoices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].serialNo").value(hasItem(DEFAULT_SERIAL_NO)))
            .andExpect(jsonPath("$.[*].invoiceNo").value(hasItem(DEFAULT_INVOICE_NO)))
            .andExpect(jsonPath("$.[*].referenceNo").value(hasItem(DEFAULT_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].caseNo").value(hasItem(DEFAULT_CASE_NO)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].dateOfCreation").value(hasItem(sameInstant(DEFAULT_DATE_OF_CREATION))))
            .andExpect(jsonPath("$.[*].sellerName").value(hasItem(DEFAULT_SELLER_NAME)))
            .andExpect(jsonPath("$.[*].amountFunctional").value(hasItem(DEFAULT_AMOUNT_FUNCTIONAL.doubleValue())))
            .andExpect(jsonPath("$.[*].amountReporting").value(hasItem(DEFAULT_AMOUNT_REPORTING.doubleValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].issuer").value(hasItem(DEFAULT_ISSUER)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX)))
            .andExpect(jsonPath("$.[*].inclusiveOfTax").value(hasItem(DEFAULT_INCLUSIVE_OF_TAX.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].gstn").value(hasItem(DEFAULT_GSTN)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].prefferredModeOfCommunication").value(hasItem(DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION)))
            .andExpect(jsonPath("$.[*].pointOfContact").value(hasItem(DEFAULT_POINT_OF_CONTACT)))
            .andExpect(jsonPath("$.[*].bankAccDetails").value(hasItem(DEFAULT_BANK_ACC_DETAILS)))
            .andExpect(jsonPath("$.[*].upiDetails").value(hasItem(DEFAULT_UPI_DETAILS)))
            .andExpect(jsonPath("$.[*].preferredModeOfPayment").value(hasItem(DEFAULT_PREFERRED_MODE_OF_PAYMENT)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getInvoice() throws Exception {
        // Initialize the database
        invoiceRepository.saveAndFlush(invoice);

        // Get the invoice
        restInvoiceMockMvc.perform(get("/api/invoices/{id}", invoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoice.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.serialNo").value(DEFAULT_SERIAL_NO))
            .andExpect(jsonPath("$.invoiceNo").value(DEFAULT_INVOICE_NO))
            .andExpect(jsonPath("$.referenceNo").value(DEFAULT_REFERENCE_NO))
            .andExpect(jsonPath("$.caseNo").value(DEFAULT_CASE_NO))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.dateOfCreation").value(sameInstant(DEFAULT_DATE_OF_CREATION)))
            .andExpect(jsonPath("$.sellerName").value(DEFAULT_SELLER_NAME))
            .andExpect(jsonPath("$.amountFunctional").value(DEFAULT_AMOUNT_FUNCTIONAL.doubleValue()))
            .andExpect(jsonPath("$.amountReporting").value(DEFAULT_AMOUNT_REPORTING.doubleValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.issuer").value(DEFAULT_ISSUER))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX))
            .andExpect(jsonPath("$.inclusiveOfTax").value(DEFAULT_INCLUSIVE_OF_TAX.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.gstn").value(DEFAULT_GSTN))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.prefferredModeOfCommunication").value(DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION))
            .andExpect(jsonPath("$.pointOfContact").value(DEFAULT_POINT_OF_CONTACT))
            .andExpect(jsonPath("$.bankAccDetails").value(DEFAULT_BANK_ACC_DETAILS))
            .andExpect(jsonPath("$.upiDetails").value(DEFAULT_UPI_DETAILS))
            .andExpect(jsonPath("$.preferredModeOfPayment").value(DEFAULT_PREFERRED_MODE_OF_PAYMENT))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingInvoice() throws Exception {
        // Get the invoice
        restInvoiceMockMvc.perform(get("/api/invoices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvoice() throws Exception {
        // Initialize the database
        invoiceRepository.saveAndFlush(invoice);

        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();

        // Update the invoice
        Invoice updatedInvoice = invoiceRepository.findById(invoice.getId()).get();
        // Disconnect from session so that the updates on updatedInvoice are not directly saved in db
        em.detach(updatedInvoice);
        updatedInvoice
            .name(UPDATED_NAME)
            .serialNo(UPDATED_SERIAL_NO)
            .invoiceNo(UPDATED_INVOICE_NO)
            .referenceNo(UPDATED_REFERENCE_NO)
            .caseNo(UPDATED_CASE_NO)
            .description(UPDATED_DESCRIPTION)
            .priority(UPDATED_PRIORITY)
            .type(UPDATED_TYPE)
            .dateOfCreation(UPDATED_DATE_OF_CREATION)
            .sellerName(UPDATED_SELLER_NAME)
            .amountFunctional(UPDATED_AMOUNT_FUNCTIONAL)
            .amountReporting(UPDATED_AMOUNT_REPORTING)
            .price(UPDATED_PRICE)
            .issuer(UPDATED_ISSUER)
            .tax(UPDATED_TAX)
            .inclusiveOfTax(UPDATED_INCLUSIVE_OF_TAX)
            .status(UPDATED_STATUS)
            .gstn(UPDATED_GSTN)
            .address(UPDATED_ADDRESS)
            .category(UPDATED_CATEGORY)
            .contactNo(UPDATED_CONTACT_NO)
            .email(UPDATED_EMAIL)
            .prefferredModeOfCommunication(UPDATED_PREFFERRED_MODE_OF_COMMUNICATION)
            .pointOfContact(UPDATED_POINT_OF_CONTACT)
            .bankAccDetails(UPDATED_BANK_ACC_DETAILS)
            .upiDetails(UPDATED_UPI_DETAILS)
            .preferredModeOfPayment(UPDATED_PREFERRED_MODE_OF_PAYMENT)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restInvoiceMockMvc.perform(put("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInvoice)))
            .andExpect(status().isOk());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
        Invoice testInvoice = invoiceList.get(invoiceList.size() - 1);
        assertThat(testInvoice.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testInvoice.getSerialNo()).isEqualTo(UPDATED_SERIAL_NO);
        assertThat(testInvoice.getInvoiceNo()).isEqualTo(UPDATED_INVOICE_NO);
        assertThat(testInvoice.getReferenceNo()).isEqualTo(UPDATED_REFERENCE_NO);
        assertThat(testInvoice.getCaseNo()).isEqualTo(UPDATED_CASE_NO);
        assertThat(testInvoice.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInvoice.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testInvoice.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testInvoice.getDateOfCreation()).isEqualTo(UPDATED_DATE_OF_CREATION);
        assertThat(testInvoice.getSellerName()).isEqualTo(UPDATED_SELLER_NAME);
        assertThat(testInvoice.getAmountFunctional()).isEqualTo(UPDATED_AMOUNT_FUNCTIONAL);
        assertThat(testInvoice.getAmountReporting()).isEqualTo(UPDATED_AMOUNT_REPORTING);
        assertThat(testInvoice.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testInvoice.getIssuer()).isEqualTo(UPDATED_ISSUER);
        assertThat(testInvoice.getTax()).isEqualTo(UPDATED_TAX);
        assertThat(testInvoice.isInclusiveOfTax()).isEqualTo(UPDATED_INCLUSIVE_OF_TAX);
        assertThat(testInvoice.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testInvoice.getGstn()).isEqualTo(UPDATED_GSTN);
        assertThat(testInvoice.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testInvoice.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testInvoice.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testInvoice.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testInvoice.getPrefferredModeOfCommunication()).isEqualTo(UPDATED_PREFFERRED_MODE_OF_COMMUNICATION);
        assertThat(testInvoice.getPointOfContact()).isEqualTo(UPDATED_POINT_OF_CONTACT);
        assertThat(testInvoice.getBankAccDetails()).isEqualTo(UPDATED_BANK_ACC_DETAILS);
        assertThat(testInvoice.getUpiDetails()).isEqualTo(UPDATED_UPI_DETAILS);
        assertThat(testInvoice.getPreferredModeOfPayment()).isEqualTo(UPDATED_PREFERRED_MODE_OF_PAYMENT);
        assertThat(testInvoice.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testInvoice.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingInvoice() throws Exception {
        int databaseSizeBeforeUpdate = invoiceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceMockMvc.perform(put("/api/invoices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invoice)))
            .andExpect(status().isBadRequest());

        // Validate the Invoice in the database
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvoice() throws Exception {
        // Initialize the database
        invoiceRepository.saveAndFlush(invoice);

        int databaseSizeBeforeDelete = invoiceRepository.findAll().size();

        // Delete the invoice
        restInvoiceMockMvc.perform(delete("/api/invoices/{id}", invoice.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Invoice> invoiceList = invoiceRepository.findAll();
        assertThat(invoiceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
