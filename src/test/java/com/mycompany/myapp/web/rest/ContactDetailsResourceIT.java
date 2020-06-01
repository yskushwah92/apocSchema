package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.ContactDetails;
import com.mycompany.myapp.repository.ContactDetailsRepository;

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
 * Integration tests for the {@link ContactDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ContactDetailsResourceIT {

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_PO_BOX = "AAAAAAAAAA";
    private static final String UPDATED_PO_BOX = "BBBBBBBBBB";

    private static final String DEFAULT_PO_BOX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PO_BOX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_NO = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FAX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FAX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContactDetailsMockMvc;

    private ContactDetails contactDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactDetails createEntity(EntityManager em) {
        ContactDetails contactDetails = new ContactDetails()
            .street(DEFAULT_STREET)
            .zipCode(DEFAULT_ZIP_CODE)
            .city(DEFAULT_CITY)
            .district(DEFAULT_DISTRICT)
            .poBox(DEFAULT_PO_BOX)
            .poBoxCode(DEFAULT_PO_BOX_CODE)
            .country(DEFAULT_COUNTRY)
            .telephoneNo(DEFAULT_TELEPHONE_NO)
            .faxNumber(DEFAULT_FAX_NUMBER)
            .email(DEFAULT_EMAIL)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return contactDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContactDetails createUpdatedEntity(EntityManager em) {
        ContactDetails contactDetails = new ContactDetails()
            .street(UPDATED_STREET)
            .zipCode(UPDATED_ZIP_CODE)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .poBox(UPDATED_PO_BOX)
            .poBoxCode(UPDATED_PO_BOX_CODE)
            .country(UPDATED_COUNTRY)
            .telephoneNo(UPDATED_TELEPHONE_NO)
            .faxNumber(UPDATED_FAX_NUMBER)
            .email(UPDATED_EMAIL)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return contactDetails;
    }

    @BeforeEach
    public void initTest() {
        contactDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createContactDetails() throws Exception {
        int databaseSizeBeforeCreate = contactDetailsRepository.findAll().size();
        // Create the ContactDetails
        restContactDetailsMockMvc.perform(post("/api/contact-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactDetails)))
            .andExpect(status().isCreated());

        // Validate the ContactDetails in the database
        List<ContactDetails> contactDetailsList = contactDetailsRepository.findAll();
        assertThat(contactDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        ContactDetails testContactDetails = contactDetailsList.get(contactDetailsList.size() - 1);
        assertThat(testContactDetails.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testContactDetails.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testContactDetails.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testContactDetails.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testContactDetails.getPoBox()).isEqualTo(DEFAULT_PO_BOX);
        assertThat(testContactDetails.getPoBoxCode()).isEqualTo(DEFAULT_PO_BOX_CODE);
        assertThat(testContactDetails.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testContactDetails.getTelephoneNo()).isEqualTo(DEFAULT_TELEPHONE_NO);
        assertThat(testContactDetails.getFaxNumber()).isEqualTo(DEFAULT_FAX_NUMBER);
        assertThat(testContactDetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testContactDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testContactDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createContactDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contactDetailsRepository.findAll().size();

        // Create the ContactDetails with an existing ID
        contactDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContactDetailsMockMvc.perform(post("/api/contact-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactDetails)))
            .andExpect(status().isBadRequest());

        // Validate the ContactDetails in the database
        List<ContactDetails> contactDetailsList = contactDetailsRepository.findAll();
        assertThat(contactDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllContactDetails() throws Exception {
        // Initialize the database
        contactDetailsRepository.saveAndFlush(contactDetails);

        // Get all the contactDetailsList
        restContactDetailsMockMvc.perform(get("/api/contact-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contactDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET)))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].poBox").value(hasItem(DEFAULT_PO_BOX)))
            .andExpect(jsonPath("$.[*].poBoxCode").value(hasItem(DEFAULT_PO_BOX_CODE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].telephoneNo").value(hasItem(DEFAULT_TELEPHONE_NO)))
            .andExpect(jsonPath("$.[*].faxNumber").value(hasItem(DEFAULT_FAX_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getContactDetails() throws Exception {
        // Initialize the database
        contactDetailsRepository.saveAndFlush(contactDetails);

        // Get the contactDetails
        restContactDetailsMockMvc.perform(get("/api/contact-details/{id}", contactDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contactDetails.getId().intValue()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.poBox").value(DEFAULT_PO_BOX))
            .andExpect(jsonPath("$.poBoxCode").value(DEFAULT_PO_BOX_CODE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.telephoneNo").value(DEFAULT_TELEPHONE_NO))
            .andExpect(jsonPath("$.faxNumber").value(DEFAULT_FAX_NUMBER))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingContactDetails() throws Exception {
        // Get the contactDetails
        restContactDetailsMockMvc.perform(get("/api/contact-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContactDetails() throws Exception {
        // Initialize the database
        contactDetailsRepository.saveAndFlush(contactDetails);

        int databaseSizeBeforeUpdate = contactDetailsRepository.findAll().size();

        // Update the contactDetails
        ContactDetails updatedContactDetails = contactDetailsRepository.findById(contactDetails.getId()).get();
        // Disconnect from session so that the updates on updatedContactDetails are not directly saved in db
        em.detach(updatedContactDetails);
        updatedContactDetails
            .street(UPDATED_STREET)
            .zipCode(UPDATED_ZIP_CODE)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .poBox(UPDATED_PO_BOX)
            .poBoxCode(UPDATED_PO_BOX_CODE)
            .country(UPDATED_COUNTRY)
            .telephoneNo(UPDATED_TELEPHONE_NO)
            .faxNumber(UPDATED_FAX_NUMBER)
            .email(UPDATED_EMAIL)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restContactDetailsMockMvc.perform(put("/api/contact-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedContactDetails)))
            .andExpect(status().isOk());

        // Validate the ContactDetails in the database
        List<ContactDetails> contactDetailsList = contactDetailsRepository.findAll();
        assertThat(contactDetailsList).hasSize(databaseSizeBeforeUpdate);
        ContactDetails testContactDetails = contactDetailsList.get(contactDetailsList.size() - 1);
        assertThat(testContactDetails.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testContactDetails.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testContactDetails.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testContactDetails.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testContactDetails.getPoBox()).isEqualTo(UPDATED_PO_BOX);
        assertThat(testContactDetails.getPoBoxCode()).isEqualTo(UPDATED_PO_BOX_CODE);
        assertThat(testContactDetails.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testContactDetails.getTelephoneNo()).isEqualTo(UPDATED_TELEPHONE_NO);
        assertThat(testContactDetails.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
        assertThat(testContactDetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testContactDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testContactDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingContactDetails() throws Exception {
        int databaseSizeBeforeUpdate = contactDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContactDetailsMockMvc.perform(put("/api/contact-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(contactDetails)))
            .andExpect(status().isBadRequest());

        // Validate the ContactDetails in the database
        List<ContactDetails> contactDetailsList = contactDetailsRepository.findAll();
        assertThat(contactDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContactDetails() throws Exception {
        // Initialize the database
        contactDetailsRepository.saveAndFlush(contactDetails);

        int databaseSizeBeforeDelete = contactDetailsRepository.findAll().size();

        // Delete the contactDetails
        restContactDetailsMockMvc.perform(delete("/api/contact-details/{id}", contactDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContactDetails> contactDetailsList = contactDetailsRepository.findAll();
        assertThat(contactDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
