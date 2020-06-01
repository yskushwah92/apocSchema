package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.OCRExtractionEngineInfo;
import com.mycompany.myapp.repository.OCRExtractionEngineInfoRepository;

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
 * Integration tests for the {@link OCRExtractionEngineInfoResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OCRExtractionEngineInfoResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_API_KEY = "AAAAAAAAAA";
    private static final String UPDATED_API_KEY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private OCRExtractionEngineInfoRepository oCRExtractionEngineInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOCRExtractionEngineInfoMockMvc;

    private OCRExtractionEngineInfo oCRExtractionEngineInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OCRExtractionEngineInfo createEntity(EntityManager em) {
        OCRExtractionEngineInfo oCRExtractionEngineInfo = new OCRExtractionEngineInfo()
            .name(DEFAULT_NAME)
            .userName(DEFAULT_USER_NAME)
            .password(DEFAULT_PASSWORD)
            .apiKey(DEFAULT_API_KEY)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return oCRExtractionEngineInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OCRExtractionEngineInfo createUpdatedEntity(EntityManager em) {
        OCRExtractionEngineInfo oCRExtractionEngineInfo = new OCRExtractionEngineInfo()
            .name(UPDATED_NAME)
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .apiKey(UPDATED_API_KEY)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return oCRExtractionEngineInfo;
    }

    @BeforeEach
    public void initTest() {
        oCRExtractionEngineInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createOCRExtractionEngineInfo() throws Exception {
        int databaseSizeBeforeCreate = oCRExtractionEngineInfoRepository.findAll().size();
        // Create the OCRExtractionEngineInfo
        restOCRExtractionEngineInfoMockMvc.perform(post("/api/ocr-extraction-engine-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oCRExtractionEngineInfo)))
            .andExpect(status().isCreated());

        // Validate the OCRExtractionEngineInfo in the database
        List<OCRExtractionEngineInfo> oCRExtractionEngineInfoList = oCRExtractionEngineInfoRepository.findAll();
        assertThat(oCRExtractionEngineInfoList).hasSize(databaseSizeBeforeCreate + 1);
        OCRExtractionEngineInfo testOCRExtractionEngineInfo = oCRExtractionEngineInfoList.get(oCRExtractionEngineInfoList.size() - 1);
        assertThat(testOCRExtractionEngineInfo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOCRExtractionEngineInfo.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testOCRExtractionEngineInfo.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testOCRExtractionEngineInfo.getApiKey()).isEqualTo(DEFAULT_API_KEY);
        assertThat(testOCRExtractionEngineInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testOCRExtractionEngineInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createOCRExtractionEngineInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oCRExtractionEngineInfoRepository.findAll().size();

        // Create the OCRExtractionEngineInfo with an existing ID
        oCRExtractionEngineInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOCRExtractionEngineInfoMockMvc.perform(post("/api/ocr-extraction-engine-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oCRExtractionEngineInfo)))
            .andExpect(status().isBadRequest());

        // Validate the OCRExtractionEngineInfo in the database
        List<OCRExtractionEngineInfo> oCRExtractionEngineInfoList = oCRExtractionEngineInfoRepository.findAll();
        assertThat(oCRExtractionEngineInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOCRExtractionEngineInfos() throws Exception {
        // Initialize the database
        oCRExtractionEngineInfoRepository.saveAndFlush(oCRExtractionEngineInfo);

        // Get all the oCRExtractionEngineInfoList
        restOCRExtractionEngineInfoMockMvc.perform(get("/api/ocr-extraction-engine-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oCRExtractionEngineInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].apiKey").value(hasItem(DEFAULT_API_KEY)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getOCRExtractionEngineInfo() throws Exception {
        // Initialize the database
        oCRExtractionEngineInfoRepository.saveAndFlush(oCRExtractionEngineInfo);

        // Get the oCRExtractionEngineInfo
        restOCRExtractionEngineInfoMockMvc.perform(get("/api/ocr-extraction-engine-infos/{id}", oCRExtractionEngineInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(oCRExtractionEngineInfo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.apiKey").value(DEFAULT_API_KEY))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingOCRExtractionEngineInfo() throws Exception {
        // Get the oCRExtractionEngineInfo
        restOCRExtractionEngineInfoMockMvc.perform(get("/api/ocr-extraction-engine-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOCRExtractionEngineInfo() throws Exception {
        // Initialize the database
        oCRExtractionEngineInfoRepository.saveAndFlush(oCRExtractionEngineInfo);

        int databaseSizeBeforeUpdate = oCRExtractionEngineInfoRepository.findAll().size();

        // Update the oCRExtractionEngineInfo
        OCRExtractionEngineInfo updatedOCRExtractionEngineInfo = oCRExtractionEngineInfoRepository.findById(oCRExtractionEngineInfo.getId()).get();
        // Disconnect from session so that the updates on updatedOCRExtractionEngineInfo are not directly saved in db
        em.detach(updatedOCRExtractionEngineInfo);
        updatedOCRExtractionEngineInfo
            .name(UPDATED_NAME)
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .apiKey(UPDATED_API_KEY)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restOCRExtractionEngineInfoMockMvc.perform(put("/api/ocr-extraction-engine-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOCRExtractionEngineInfo)))
            .andExpect(status().isOk());

        // Validate the OCRExtractionEngineInfo in the database
        List<OCRExtractionEngineInfo> oCRExtractionEngineInfoList = oCRExtractionEngineInfoRepository.findAll();
        assertThat(oCRExtractionEngineInfoList).hasSize(databaseSizeBeforeUpdate);
        OCRExtractionEngineInfo testOCRExtractionEngineInfo = oCRExtractionEngineInfoList.get(oCRExtractionEngineInfoList.size() - 1);
        assertThat(testOCRExtractionEngineInfo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOCRExtractionEngineInfo.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testOCRExtractionEngineInfo.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testOCRExtractionEngineInfo.getApiKey()).isEqualTo(UPDATED_API_KEY);
        assertThat(testOCRExtractionEngineInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testOCRExtractionEngineInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingOCRExtractionEngineInfo() throws Exception {
        int databaseSizeBeforeUpdate = oCRExtractionEngineInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOCRExtractionEngineInfoMockMvc.perform(put("/api/ocr-extraction-engine-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oCRExtractionEngineInfo)))
            .andExpect(status().isBadRequest());

        // Validate the OCRExtractionEngineInfo in the database
        List<OCRExtractionEngineInfo> oCRExtractionEngineInfoList = oCRExtractionEngineInfoRepository.findAll();
        assertThat(oCRExtractionEngineInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOCRExtractionEngineInfo() throws Exception {
        // Initialize the database
        oCRExtractionEngineInfoRepository.saveAndFlush(oCRExtractionEngineInfo);

        int databaseSizeBeforeDelete = oCRExtractionEngineInfoRepository.findAll().size();

        // Delete the oCRExtractionEngineInfo
        restOCRExtractionEngineInfoMockMvc.perform(delete("/api/ocr-extraction-engine-infos/{id}", oCRExtractionEngineInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OCRExtractionEngineInfo> oCRExtractionEngineInfoList = oCRExtractionEngineInfoRepository.findAll();
        assertThat(oCRExtractionEngineInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
