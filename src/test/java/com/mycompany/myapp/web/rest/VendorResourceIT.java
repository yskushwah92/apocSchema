package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.Vendor;
import com.mycompany.myapp.repository.VendorRepository;

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
 * Integration tests for the {@link VendorResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VendorResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENDOR_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VENDOR_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_TERMS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_TERMS = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ILN = "AAAAAAAAAA";
    private static final String UPDATED_ILN = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_TAX_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_GST_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GST_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_GST_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_GST_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_VAT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_VAT_ID_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CLERK_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLERK_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_VAT_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VAT_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION = "AAAAAAAAAA";
    private static final String UPDATED_PREFFERRED_MODE_OF_COMMUNICATION = "BBBBBBBBBB";

    private static final String DEFAULT_POINT_OF_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_POINT_OF_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_PREFERRED_MODE_OF_PAYMENT = "AAAAAAAAAA";
    private static final String UPDATED_PREFERRED_MODE_OF_PAYMENT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVendorMockMvc;

    private Vendor vendor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vendor createEntity(EntityManager em) {
        Vendor vendor = new Vendor()
            .name(DEFAULT_NAME)
            .displayName(DEFAULT_DISPLAY_NAME)
            .vendorNumber(DEFAULT_VENDOR_NUMBER)
            .paymentTerms(DEFAULT_PAYMENT_TERMS)
            .type(DEFAULT_TYPE)
            .iln(DEFAULT_ILN)
            .taxIdNO(DEFAULT_TAX_ID_NO)
            .gstRegistrationNumber(DEFAULT_GST_REGISTRATION_NUMBER)
            .gstStatus(DEFAULT_GST_STATUS)
            .vatIdNo(DEFAULT_VAT_ID_NO)
            .clerkId(DEFAULT_CLERK_ID)
            .status(DEFAULT_STATUS)
            .vatRegistrationNumber(DEFAULT_VAT_REGISTRATION_NUMBER)
            .prefferredModeOfCommunication(DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION)
            .pointOfContact(DEFAULT_POINT_OF_CONTACT)
            .preferredModeOfPayment(DEFAULT_PREFERRED_MODE_OF_PAYMENT)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return vendor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vendor createUpdatedEntity(EntityManager em) {
        Vendor vendor = new Vendor()
            .name(UPDATED_NAME)
            .displayName(UPDATED_DISPLAY_NAME)
            .vendorNumber(UPDATED_VENDOR_NUMBER)
            .paymentTerms(UPDATED_PAYMENT_TERMS)
            .type(UPDATED_TYPE)
            .iln(UPDATED_ILN)
            .taxIdNO(UPDATED_TAX_ID_NO)
            .gstRegistrationNumber(UPDATED_GST_REGISTRATION_NUMBER)
            .gstStatus(UPDATED_GST_STATUS)
            .vatIdNo(UPDATED_VAT_ID_NO)
            .clerkId(UPDATED_CLERK_ID)
            .status(UPDATED_STATUS)
            .vatRegistrationNumber(UPDATED_VAT_REGISTRATION_NUMBER)
            .prefferredModeOfCommunication(UPDATED_PREFFERRED_MODE_OF_COMMUNICATION)
            .pointOfContact(UPDATED_POINT_OF_CONTACT)
            .preferredModeOfPayment(UPDATED_PREFERRED_MODE_OF_PAYMENT)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return vendor;
    }

    @BeforeEach
    public void initTest() {
        vendor = createEntity(em);
    }

    @Test
    @Transactional
    public void createVendor() throws Exception {
        int databaseSizeBeforeCreate = vendorRepository.findAll().size();
        // Create the Vendor
        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendor)))
            .andExpect(status().isCreated());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeCreate + 1);
        Vendor testVendor = vendorList.get(vendorList.size() - 1);
        assertThat(testVendor.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVendor.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testVendor.getVendorNumber()).isEqualTo(DEFAULT_VENDOR_NUMBER);
        assertThat(testVendor.getPaymentTerms()).isEqualTo(DEFAULT_PAYMENT_TERMS);
        assertThat(testVendor.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVendor.getIln()).isEqualTo(DEFAULT_ILN);
        assertThat(testVendor.getTaxIdNO()).isEqualTo(DEFAULT_TAX_ID_NO);
        assertThat(testVendor.getGstRegistrationNumber()).isEqualTo(DEFAULT_GST_REGISTRATION_NUMBER);
        assertThat(testVendor.getGstStatus()).isEqualTo(DEFAULT_GST_STATUS);
        assertThat(testVendor.getVatIdNo()).isEqualTo(DEFAULT_VAT_ID_NO);
        assertThat(testVendor.getClerkId()).isEqualTo(DEFAULT_CLERK_ID);
        assertThat(testVendor.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testVendor.getVatRegistrationNumber()).isEqualTo(DEFAULT_VAT_REGISTRATION_NUMBER);
        assertThat(testVendor.getPrefferredModeOfCommunication()).isEqualTo(DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION);
        assertThat(testVendor.getPointOfContact()).isEqualTo(DEFAULT_POINT_OF_CONTACT);
        assertThat(testVendor.getPreferredModeOfPayment()).isEqualTo(DEFAULT_PREFERRED_MODE_OF_PAYMENT);
        assertThat(testVendor.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testVendor.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createVendorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vendorRepository.findAll().size();

        // Create the Vendor with an existing ID
        vendor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVendorMockMvc.perform(post("/api/vendors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendor)))
            .andExpect(status().isBadRequest());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVendors() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get all the vendorList
        restVendorMockMvc.perform(get("/api/vendors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vendor.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME)))
            .andExpect(jsonPath("$.[*].vendorNumber").value(hasItem(DEFAULT_VENDOR_NUMBER)))
            .andExpect(jsonPath("$.[*].paymentTerms").value(hasItem(DEFAULT_PAYMENT_TERMS)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].iln").value(hasItem(DEFAULT_ILN)))
            .andExpect(jsonPath("$.[*].taxIdNO").value(hasItem(DEFAULT_TAX_ID_NO)))
            .andExpect(jsonPath("$.[*].gstRegistrationNumber").value(hasItem(DEFAULT_GST_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].gstStatus").value(hasItem(DEFAULT_GST_STATUS)))
            .andExpect(jsonPath("$.[*].vatIdNo").value(hasItem(DEFAULT_VAT_ID_NO)))
            .andExpect(jsonPath("$.[*].clerkId").value(hasItem(DEFAULT_CLERK_ID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].vatRegistrationNumber").value(hasItem(DEFAULT_VAT_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].prefferredModeOfCommunication").value(hasItem(DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION)))
            .andExpect(jsonPath("$.[*].pointOfContact").value(hasItem(DEFAULT_POINT_OF_CONTACT)))
            .andExpect(jsonPath("$.[*].preferredModeOfPayment").value(hasItem(DEFAULT_PREFERRED_MODE_OF_PAYMENT)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get the vendor
        restVendorMockMvc.perform(get("/api/vendors/{id}", vendor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vendor.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME))
            .andExpect(jsonPath("$.vendorNumber").value(DEFAULT_VENDOR_NUMBER))
            .andExpect(jsonPath("$.paymentTerms").value(DEFAULT_PAYMENT_TERMS))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.iln").value(DEFAULT_ILN))
            .andExpect(jsonPath("$.taxIdNO").value(DEFAULT_TAX_ID_NO))
            .andExpect(jsonPath("$.gstRegistrationNumber").value(DEFAULT_GST_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.gstStatus").value(DEFAULT_GST_STATUS))
            .andExpect(jsonPath("$.vatIdNo").value(DEFAULT_VAT_ID_NO))
            .andExpect(jsonPath("$.clerkId").value(DEFAULT_CLERK_ID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.vatRegistrationNumber").value(DEFAULT_VAT_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.prefferredModeOfCommunication").value(DEFAULT_PREFFERRED_MODE_OF_COMMUNICATION))
            .andExpect(jsonPath("$.pointOfContact").value(DEFAULT_POINT_OF_CONTACT))
            .andExpect(jsonPath("$.preferredModeOfPayment").value(DEFAULT_PREFERRED_MODE_OF_PAYMENT))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingVendor() throws Exception {
        // Get the vendor
        restVendorMockMvc.perform(get("/api/vendors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        int databaseSizeBeforeUpdate = vendorRepository.findAll().size();

        // Update the vendor
        Vendor updatedVendor = vendorRepository.findById(vendor.getId()).get();
        // Disconnect from session so that the updates on updatedVendor are not directly saved in db
        em.detach(updatedVendor);
        updatedVendor
            .name(UPDATED_NAME)
            .displayName(UPDATED_DISPLAY_NAME)
            .vendorNumber(UPDATED_VENDOR_NUMBER)
            .paymentTerms(UPDATED_PAYMENT_TERMS)
            .type(UPDATED_TYPE)
            .iln(UPDATED_ILN)
            .taxIdNO(UPDATED_TAX_ID_NO)
            .gstRegistrationNumber(UPDATED_GST_REGISTRATION_NUMBER)
            .gstStatus(UPDATED_GST_STATUS)
            .vatIdNo(UPDATED_VAT_ID_NO)
            .clerkId(UPDATED_CLERK_ID)
            .status(UPDATED_STATUS)
            .vatRegistrationNumber(UPDATED_VAT_REGISTRATION_NUMBER)
            .prefferredModeOfCommunication(UPDATED_PREFFERRED_MODE_OF_COMMUNICATION)
            .pointOfContact(UPDATED_POINT_OF_CONTACT)
            .preferredModeOfPayment(UPDATED_PREFERRED_MODE_OF_PAYMENT)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restVendorMockMvc.perform(put("/api/vendors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVendor)))
            .andExpect(status().isOk());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeUpdate);
        Vendor testVendor = vendorList.get(vendorList.size() - 1);
        assertThat(testVendor.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVendor.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testVendor.getVendorNumber()).isEqualTo(UPDATED_VENDOR_NUMBER);
        assertThat(testVendor.getPaymentTerms()).isEqualTo(UPDATED_PAYMENT_TERMS);
        assertThat(testVendor.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVendor.getIln()).isEqualTo(UPDATED_ILN);
        assertThat(testVendor.getTaxIdNO()).isEqualTo(UPDATED_TAX_ID_NO);
        assertThat(testVendor.getGstRegistrationNumber()).isEqualTo(UPDATED_GST_REGISTRATION_NUMBER);
        assertThat(testVendor.getGstStatus()).isEqualTo(UPDATED_GST_STATUS);
        assertThat(testVendor.getVatIdNo()).isEqualTo(UPDATED_VAT_ID_NO);
        assertThat(testVendor.getClerkId()).isEqualTo(UPDATED_CLERK_ID);
        assertThat(testVendor.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testVendor.getVatRegistrationNumber()).isEqualTo(UPDATED_VAT_REGISTRATION_NUMBER);
        assertThat(testVendor.getPrefferredModeOfCommunication()).isEqualTo(UPDATED_PREFFERRED_MODE_OF_COMMUNICATION);
        assertThat(testVendor.getPointOfContact()).isEqualTo(UPDATED_POINT_OF_CONTACT);
        assertThat(testVendor.getPreferredModeOfPayment()).isEqualTo(UPDATED_PREFERRED_MODE_OF_PAYMENT);
        assertThat(testVendor.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testVendor.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingVendor() throws Exception {
        int databaseSizeBeforeUpdate = vendorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVendorMockMvc.perform(put("/api/vendors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vendor)))
            .andExpect(status().isBadRequest());

        // Validate the Vendor in the database
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        int databaseSizeBeforeDelete = vendorRepository.findAll().size();

        // Delete the vendor
        restVendorMockMvc.perform(delete("/api/vendors/{id}", vendor.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vendor> vendorList = vendorRepository.findAll();
        assertThat(vendorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
