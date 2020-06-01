package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.FileSourceDetails;
import com.mycompany.myapp.repository.FileSourceDetailsRepository;

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
 * Integration tests for the {@link FileSourceDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FileSourceDetailsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_API_KEY = "AAAAAAAAAA";
    private static final String UPDATED_API_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_PROTOCOL = "AAAAAAAAAA";
    private static final String UPDATED_PROTOCOL = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private FileSourceDetailsRepository fileSourceDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFileSourceDetailsMockMvc;

    private FileSourceDetails fileSourceDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileSourceDetails createEntity(EntityManager em) {
        FileSourceDetails fileSourceDetails = new FileSourceDetails()
            .name(DEFAULT_NAME)
            .apiKey(DEFAULT_API_KEY)
            .userName(DEFAULT_USER_NAME)
            .password(DEFAULT_PASSWORD)
            .protocol(DEFAULT_PROTOCOL)
            .url(DEFAULT_URL)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return fileSourceDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileSourceDetails createUpdatedEntity(EntityManager em) {
        FileSourceDetails fileSourceDetails = new FileSourceDetails()
            .name(UPDATED_NAME)
            .apiKey(UPDATED_API_KEY)
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .protocol(UPDATED_PROTOCOL)
            .url(UPDATED_URL)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return fileSourceDetails;
    }

    @BeforeEach
    public void initTest() {
        fileSourceDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileSourceDetails() throws Exception {
        int databaseSizeBeforeCreate = fileSourceDetailsRepository.findAll().size();
        // Create the FileSourceDetails
        restFileSourceDetailsMockMvc.perform(post("/api/file-source-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileSourceDetails)))
            .andExpect(status().isCreated());

        // Validate the FileSourceDetails in the database
        List<FileSourceDetails> fileSourceDetailsList = fileSourceDetailsRepository.findAll();
        assertThat(fileSourceDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        FileSourceDetails testFileSourceDetails = fileSourceDetailsList.get(fileSourceDetailsList.size() - 1);
        assertThat(testFileSourceDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFileSourceDetails.getApiKey()).isEqualTo(DEFAULT_API_KEY);
        assertThat(testFileSourceDetails.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testFileSourceDetails.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testFileSourceDetails.getProtocol()).isEqualTo(DEFAULT_PROTOCOL);
        assertThat(testFileSourceDetails.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testFileSourceDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testFileSourceDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createFileSourceDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileSourceDetailsRepository.findAll().size();

        // Create the FileSourceDetails with an existing ID
        fileSourceDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileSourceDetailsMockMvc.perform(post("/api/file-source-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileSourceDetails)))
            .andExpect(status().isBadRequest());

        // Validate the FileSourceDetails in the database
        List<FileSourceDetails> fileSourceDetailsList = fileSourceDetailsRepository.findAll();
        assertThat(fileSourceDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFileSourceDetails() throws Exception {
        // Initialize the database
        fileSourceDetailsRepository.saveAndFlush(fileSourceDetails);

        // Get all the fileSourceDetailsList
        restFileSourceDetailsMockMvc.perform(get("/api/file-source-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileSourceDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].apiKey").value(hasItem(DEFAULT_API_KEY)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].protocol").value(hasItem(DEFAULT_PROTOCOL)))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getFileSourceDetails() throws Exception {
        // Initialize the database
        fileSourceDetailsRepository.saveAndFlush(fileSourceDetails);

        // Get the fileSourceDetails
        restFileSourceDetailsMockMvc.perform(get("/api/file-source-details/{id}", fileSourceDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fileSourceDetails.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.apiKey").value(DEFAULT_API_KEY))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.protocol").value(DEFAULT_PROTOCOL))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingFileSourceDetails() throws Exception {
        // Get the fileSourceDetails
        restFileSourceDetailsMockMvc.perform(get("/api/file-source-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileSourceDetails() throws Exception {
        // Initialize the database
        fileSourceDetailsRepository.saveAndFlush(fileSourceDetails);

        int databaseSizeBeforeUpdate = fileSourceDetailsRepository.findAll().size();

        // Update the fileSourceDetails
        FileSourceDetails updatedFileSourceDetails = fileSourceDetailsRepository.findById(fileSourceDetails.getId()).get();
        // Disconnect from session so that the updates on updatedFileSourceDetails are not directly saved in db
        em.detach(updatedFileSourceDetails);
        updatedFileSourceDetails
            .name(UPDATED_NAME)
            .apiKey(UPDATED_API_KEY)
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .protocol(UPDATED_PROTOCOL)
            .url(UPDATED_URL)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restFileSourceDetailsMockMvc.perform(put("/api/file-source-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFileSourceDetails)))
            .andExpect(status().isOk());

        // Validate the FileSourceDetails in the database
        List<FileSourceDetails> fileSourceDetailsList = fileSourceDetailsRepository.findAll();
        assertThat(fileSourceDetailsList).hasSize(databaseSizeBeforeUpdate);
        FileSourceDetails testFileSourceDetails = fileSourceDetailsList.get(fileSourceDetailsList.size() - 1);
        assertThat(testFileSourceDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFileSourceDetails.getApiKey()).isEqualTo(UPDATED_API_KEY);
        assertThat(testFileSourceDetails.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testFileSourceDetails.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testFileSourceDetails.getProtocol()).isEqualTo(UPDATED_PROTOCOL);
        assertThat(testFileSourceDetails.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testFileSourceDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testFileSourceDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingFileSourceDetails() throws Exception {
        int databaseSizeBeforeUpdate = fileSourceDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileSourceDetailsMockMvc.perform(put("/api/file-source-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileSourceDetails)))
            .andExpect(status().isBadRequest());

        // Validate the FileSourceDetails in the database
        List<FileSourceDetails> fileSourceDetailsList = fileSourceDetailsRepository.findAll();
        assertThat(fileSourceDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFileSourceDetails() throws Exception {
        // Initialize the database
        fileSourceDetailsRepository.saveAndFlush(fileSourceDetails);

        int databaseSizeBeforeDelete = fileSourceDetailsRepository.findAll().size();

        // Delete the fileSourceDetails
        restFileSourceDetailsMockMvc.perform(delete("/api/file-source-details/{id}", fileSourceDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FileSourceDetails> fileSourceDetailsList = fileSourceDetailsRepository.findAll();
        assertThat(fileSourceDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
