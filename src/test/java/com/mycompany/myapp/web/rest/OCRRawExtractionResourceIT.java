package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.OCRRawExtraction;
import com.mycompany.myapp.repository.OCRRawExtractionRepository;

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

import com.mycompany.myapp.domain.enumeration.Language;
import com.mycompany.myapp.domain.enumeration.DocumentType;
/**
 * Integration tests for the {@link OCRRawExtractionResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OCRRawExtractionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIELD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIELD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAPTUREDFIELD_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_CAPTUREDFIELD_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTUAL_FIELD_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_ACTUAL_FIELD_VALUE = "BBBBBBBBBB";

    private static final Language DEFAULT_LANGUAGE = Language.ENGLISH;
    private static final Language UPDATED_LANGUAGE = Language.ENGLISH;

    private static final DocumentType DEFAULT_DOCUMENT_TYPE = DocumentType.INVOICE;
    private static final DocumentType UPDATED_DOCUMENT_TYPE = DocumentType.PURCHASE_ORDER;

    private static final String DEFAULT_ACCURACY = "AAAAAAAAAA";
    private static final String UPDATED_ACCURACY = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRACTIONS = "AAAAAAAAAA";
    private static final String UPDATED_EXTRACTIONS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private OCRRawExtractionRepository oCRRawExtractionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOCRRawExtractionMockMvc;

    private OCRRawExtraction oCRRawExtraction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OCRRawExtraction createEntity(EntityManager em) {
        OCRRawExtraction oCRRawExtraction = new OCRRawExtraction()
            .name(DEFAULT_NAME)
            .fieldName(DEFAULT_FIELD_NAME)
            .capturedfieldValue(DEFAULT_CAPTUREDFIELD_VALUE)
            .actualFieldValue(DEFAULT_ACTUAL_FIELD_VALUE)
            .language(DEFAULT_LANGUAGE)
            .documentType(DEFAULT_DOCUMENT_TYPE)
            .accuracy(DEFAULT_ACCURACY)
            .extractions(DEFAULT_EXTRACTIONS)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return oCRRawExtraction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OCRRawExtraction createUpdatedEntity(EntityManager em) {
        OCRRawExtraction oCRRawExtraction = new OCRRawExtraction()
            .name(UPDATED_NAME)
            .fieldName(UPDATED_FIELD_NAME)
            .capturedfieldValue(UPDATED_CAPTUREDFIELD_VALUE)
            .actualFieldValue(UPDATED_ACTUAL_FIELD_VALUE)
            .language(UPDATED_LANGUAGE)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .accuracy(UPDATED_ACCURACY)
            .extractions(UPDATED_EXTRACTIONS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return oCRRawExtraction;
    }

    @BeforeEach
    public void initTest() {
        oCRRawExtraction = createEntity(em);
    }

    @Test
    @Transactional
    public void createOCRRawExtraction() throws Exception {
        int databaseSizeBeforeCreate = oCRRawExtractionRepository.findAll().size();
        // Create the OCRRawExtraction
        restOCRRawExtractionMockMvc.perform(post("/api/ocr-raw-extractions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oCRRawExtraction)))
            .andExpect(status().isCreated());

        // Validate the OCRRawExtraction in the database
        List<OCRRawExtraction> oCRRawExtractionList = oCRRawExtractionRepository.findAll();
        assertThat(oCRRawExtractionList).hasSize(databaseSizeBeforeCreate + 1);
        OCRRawExtraction testOCRRawExtraction = oCRRawExtractionList.get(oCRRawExtractionList.size() - 1);
        assertThat(testOCRRawExtraction.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOCRRawExtraction.getFieldName()).isEqualTo(DEFAULT_FIELD_NAME);
        assertThat(testOCRRawExtraction.getCapturedfieldValue()).isEqualTo(DEFAULT_CAPTUREDFIELD_VALUE);
        assertThat(testOCRRawExtraction.getActualFieldValue()).isEqualTo(DEFAULT_ACTUAL_FIELD_VALUE);
        assertThat(testOCRRawExtraction.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testOCRRawExtraction.getDocumentType()).isEqualTo(DEFAULT_DOCUMENT_TYPE);
        assertThat(testOCRRawExtraction.getAccuracy()).isEqualTo(DEFAULT_ACCURACY);
        assertThat(testOCRRawExtraction.getExtractions()).isEqualTo(DEFAULT_EXTRACTIONS);
        assertThat(testOCRRawExtraction.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testOCRRawExtraction.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createOCRRawExtractionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oCRRawExtractionRepository.findAll().size();

        // Create the OCRRawExtraction with an existing ID
        oCRRawExtraction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOCRRawExtractionMockMvc.perform(post("/api/ocr-raw-extractions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oCRRawExtraction)))
            .andExpect(status().isBadRequest());

        // Validate the OCRRawExtraction in the database
        List<OCRRawExtraction> oCRRawExtractionList = oCRRawExtractionRepository.findAll();
        assertThat(oCRRawExtractionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOCRRawExtractions() throws Exception {
        // Initialize the database
        oCRRawExtractionRepository.saveAndFlush(oCRRawExtraction);

        // Get all the oCRRawExtractionList
        restOCRRawExtractionMockMvc.perform(get("/api/ocr-raw-extractions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oCRRawExtraction.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fieldName").value(hasItem(DEFAULT_FIELD_NAME)))
            .andExpect(jsonPath("$.[*].capturedfieldValue").value(hasItem(DEFAULT_CAPTUREDFIELD_VALUE)))
            .andExpect(jsonPath("$.[*].actualFieldValue").value(hasItem(DEFAULT_ACTUAL_FIELD_VALUE)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].documentType").value(hasItem(DEFAULT_DOCUMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].accuracy").value(hasItem(DEFAULT_ACCURACY)))
            .andExpect(jsonPath("$.[*].extractions").value(hasItem(DEFAULT_EXTRACTIONS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getOCRRawExtraction() throws Exception {
        // Initialize the database
        oCRRawExtractionRepository.saveAndFlush(oCRRawExtraction);

        // Get the oCRRawExtraction
        restOCRRawExtractionMockMvc.perform(get("/api/ocr-raw-extractions/{id}", oCRRawExtraction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(oCRRawExtraction.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.fieldName").value(DEFAULT_FIELD_NAME))
            .andExpect(jsonPath("$.capturedfieldValue").value(DEFAULT_CAPTUREDFIELD_VALUE))
            .andExpect(jsonPath("$.actualFieldValue").value(DEFAULT_ACTUAL_FIELD_VALUE))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.documentType").value(DEFAULT_DOCUMENT_TYPE.toString()))
            .andExpect(jsonPath("$.accuracy").value(DEFAULT_ACCURACY))
            .andExpect(jsonPath("$.extractions").value(DEFAULT_EXTRACTIONS))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingOCRRawExtraction() throws Exception {
        // Get the oCRRawExtraction
        restOCRRawExtractionMockMvc.perform(get("/api/ocr-raw-extractions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOCRRawExtraction() throws Exception {
        // Initialize the database
        oCRRawExtractionRepository.saveAndFlush(oCRRawExtraction);

        int databaseSizeBeforeUpdate = oCRRawExtractionRepository.findAll().size();

        // Update the oCRRawExtraction
        OCRRawExtraction updatedOCRRawExtraction = oCRRawExtractionRepository.findById(oCRRawExtraction.getId()).get();
        // Disconnect from session so that the updates on updatedOCRRawExtraction are not directly saved in db
        em.detach(updatedOCRRawExtraction);
        updatedOCRRawExtraction
            .name(UPDATED_NAME)
            .fieldName(UPDATED_FIELD_NAME)
            .capturedfieldValue(UPDATED_CAPTUREDFIELD_VALUE)
            .actualFieldValue(UPDATED_ACTUAL_FIELD_VALUE)
            .language(UPDATED_LANGUAGE)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .accuracy(UPDATED_ACCURACY)
            .extractions(UPDATED_EXTRACTIONS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restOCRRawExtractionMockMvc.perform(put("/api/ocr-raw-extractions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOCRRawExtraction)))
            .andExpect(status().isOk());

        // Validate the OCRRawExtraction in the database
        List<OCRRawExtraction> oCRRawExtractionList = oCRRawExtractionRepository.findAll();
        assertThat(oCRRawExtractionList).hasSize(databaseSizeBeforeUpdate);
        OCRRawExtraction testOCRRawExtraction = oCRRawExtractionList.get(oCRRawExtractionList.size() - 1);
        assertThat(testOCRRawExtraction.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOCRRawExtraction.getFieldName()).isEqualTo(UPDATED_FIELD_NAME);
        assertThat(testOCRRawExtraction.getCapturedfieldValue()).isEqualTo(UPDATED_CAPTUREDFIELD_VALUE);
        assertThat(testOCRRawExtraction.getActualFieldValue()).isEqualTo(UPDATED_ACTUAL_FIELD_VALUE);
        assertThat(testOCRRawExtraction.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testOCRRawExtraction.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testOCRRawExtraction.getAccuracy()).isEqualTo(UPDATED_ACCURACY);
        assertThat(testOCRRawExtraction.getExtractions()).isEqualTo(UPDATED_EXTRACTIONS);
        assertThat(testOCRRawExtraction.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testOCRRawExtraction.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingOCRRawExtraction() throws Exception {
        int databaseSizeBeforeUpdate = oCRRawExtractionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOCRRawExtractionMockMvc.perform(put("/api/ocr-raw-extractions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oCRRawExtraction)))
            .andExpect(status().isBadRequest());

        // Validate the OCRRawExtraction in the database
        List<OCRRawExtraction> oCRRawExtractionList = oCRRawExtractionRepository.findAll();
        assertThat(oCRRawExtractionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOCRRawExtraction() throws Exception {
        // Initialize the database
        oCRRawExtractionRepository.saveAndFlush(oCRRawExtraction);

        int databaseSizeBeforeDelete = oCRRawExtractionRepository.findAll().size();

        // Delete the oCRRawExtraction
        restOCRRawExtractionMockMvc.perform(delete("/api/ocr-raw-extractions/{id}", oCRRawExtraction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OCRRawExtraction> oCRRawExtractionList = oCRRawExtractionRepository.findAll();
        assertThat(oCRRawExtractionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
