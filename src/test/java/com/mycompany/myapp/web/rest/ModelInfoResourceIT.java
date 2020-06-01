package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.ModelInfo;
import com.mycompany.myapp.repository.ModelInfoRepository;

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
 * Integration tests for the {@link ModelInfoResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ModelInfoResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_EXECUTION_SCRIPT = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTION_SCRIPT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private ModelInfoRepository modelInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModelInfoMockMvc;

    private ModelInfo modelInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModelInfo createEntity(EntityManager em) {
        ModelInfo modelInfo = new ModelInfo()
            .name(DEFAULT_NAME)
            .version(DEFAULT_VERSION)
            .location(DEFAULT_LOCATION)
            .executionScript(DEFAULT_EXECUTION_SCRIPT)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return modelInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ModelInfo createUpdatedEntity(EntityManager em) {
        ModelInfo modelInfo = new ModelInfo()
            .name(UPDATED_NAME)
            .version(UPDATED_VERSION)
            .location(UPDATED_LOCATION)
            .executionScript(UPDATED_EXECUTION_SCRIPT)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return modelInfo;
    }

    @BeforeEach
    public void initTest() {
        modelInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createModelInfo() throws Exception {
        int databaseSizeBeforeCreate = modelInfoRepository.findAll().size();
        // Create the ModelInfo
        restModelInfoMockMvc.perform(post("/api/model-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelInfo)))
            .andExpect(status().isCreated());

        // Validate the ModelInfo in the database
        List<ModelInfo> modelInfoList = modelInfoRepository.findAll();
        assertThat(modelInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ModelInfo testModelInfo = modelInfoList.get(modelInfoList.size() - 1);
        assertThat(testModelInfo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testModelInfo.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testModelInfo.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testModelInfo.getExecutionScript()).isEqualTo(DEFAULT_EXECUTION_SCRIPT);
        assertThat(testModelInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testModelInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createModelInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modelInfoRepository.findAll().size();

        // Create the ModelInfo with an existing ID
        modelInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModelInfoMockMvc.perform(post("/api/model-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelInfo)))
            .andExpect(status().isBadRequest());

        // Validate the ModelInfo in the database
        List<ModelInfo> modelInfoList = modelInfoRepository.findAll();
        assertThat(modelInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllModelInfos() throws Exception {
        // Initialize the database
        modelInfoRepository.saveAndFlush(modelInfo);

        // Get all the modelInfoList
        restModelInfoMockMvc.perform(get("/api/model-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modelInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].executionScript").value(hasItem(DEFAULT_EXECUTION_SCRIPT)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getModelInfo() throws Exception {
        // Initialize the database
        modelInfoRepository.saveAndFlush(modelInfo);

        // Get the modelInfo
        restModelInfoMockMvc.perform(get("/api/model-infos/{id}", modelInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modelInfo.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.executionScript").value(DEFAULT_EXECUTION_SCRIPT))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingModelInfo() throws Exception {
        // Get the modelInfo
        restModelInfoMockMvc.perform(get("/api/model-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModelInfo() throws Exception {
        // Initialize the database
        modelInfoRepository.saveAndFlush(modelInfo);

        int databaseSizeBeforeUpdate = modelInfoRepository.findAll().size();

        // Update the modelInfo
        ModelInfo updatedModelInfo = modelInfoRepository.findById(modelInfo.getId()).get();
        // Disconnect from session so that the updates on updatedModelInfo are not directly saved in db
        em.detach(updatedModelInfo);
        updatedModelInfo
            .name(UPDATED_NAME)
            .version(UPDATED_VERSION)
            .location(UPDATED_LOCATION)
            .executionScript(UPDATED_EXECUTION_SCRIPT)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restModelInfoMockMvc.perform(put("/api/model-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedModelInfo)))
            .andExpect(status().isOk());

        // Validate the ModelInfo in the database
        List<ModelInfo> modelInfoList = modelInfoRepository.findAll();
        assertThat(modelInfoList).hasSize(databaseSizeBeforeUpdate);
        ModelInfo testModelInfo = modelInfoList.get(modelInfoList.size() - 1);
        assertThat(testModelInfo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testModelInfo.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testModelInfo.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testModelInfo.getExecutionScript()).isEqualTo(UPDATED_EXECUTION_SCRIPT);
        assertThat(testModelInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testModelInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingModelInfo() throws Exception {
        int databaseSizeBeforeUpdate = modelInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModelInfoMockMvc.perform(put("/api/model-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modelInfo)))
            .andExpect(status().isBadRequest());

        // Validate the ModelInfo in the database
        List<ModelInfo> modelInfoList = modelInfoRepository.findAll();
        assertThat(modelInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModelInfo() throws Exception {
        // Initialize the database
        modelInfoRepository.saveAndFlush(modelInfo);

        int databaseSizeBeforeDelete = modelInfoRepository.findAll().size();

        // Delete the modelInfo
        restModelInfoMockMvc.perform(delete("/api/model-infos/{id}", modelInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ModelInfo> modelInfoList = modelInfoRepository.findAll();
        assertThat(modelInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
