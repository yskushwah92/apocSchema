package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.CommunicationConfiguration;
import com.mycompany.myapp.repository.CommunicationConfigurationRepository;

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

import com.mycompany.myapp.domain.enumeration.CommunicationMode;
/**
 * Integration tests for the {@link CommunicationConfigurationResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CommunicationConfigurationResourceIT {

    private static final String DEFAULT_PRIORITY = "AAAAAAAAAA";
    private static final String UPDATED_PRIORITY = "BBBBBBBBBB";

    private static final String DEFAULT_RETRIES = "AAAAAAAAAA";
    private static final String UPDATED_RETRIES = "BBBBBBBBBB";

    private static final CommunicationMode DEFAULT_MODE = CommunicationMode.MAIL;
    private static final CommunicationMode UPDATED_MODE = CommunicationMode.SMS;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private CommunicationConfigurationRepository communicationConfigurationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommunicationConfigurationMockMvc;

    private CommunicationConfiguration communicationConfiguration;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationConfiguration createEntity(EntityManager em) {
        CommunicationConfiguration communicationConfiguration = new CommunicationConfiguration()
            .priority(DEFAULT_PRIORITY)
            .retries(DEFAULT_RETRIES)
            .mode(DEFAULT_MODE)
            .name(DEFAULT_NAME)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return communicationConfiguration;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationConfiguration createUpdatedEntity(EntityManager em) {
        CommunicationConfiguration communicationConfiguration = new CommunicationConfiguration()
            .priority(UPDATED_PRIORITY)
            .retries(UPDATED_RETRIES)
            .mode(UPDATED_MODE)
            .name(UPDATED_NAME)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return communicationConfiguration;
    }

    @BeforeEach
    public void initTest() {
        communicationConfiguration = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommunicationConfiguration() throws Exception {
        int databaseSizeBeforeCreate = communicationConfigurationRepository.findAll().size();
        // Create the CommunicationConfiguration
        restCommunicationConfigurationMockMvc.perform(post("/api/communication-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communicationConfiguration)))
            .andExpect(status().isCreated());

        // Validate the CommunicationConfiguration in the database
        List<CommunicationConfiguration> communicationConfigurationList = communicationConfigurationRepository.findAll();
        assertThat(communicationConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        CommunicationConfiguration testCommunicationConfiguration = communicationConfigurationList.get(communicationConfigurationList.size() - 1);
        assertThat(testCommunicationConfiguration.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testCommunicationConfiguration.getRetries()).isEqualTo(DEFAULT_RETRIES);
        assertThat(testCommunicationConfiguration.getMode()).isEqualTo(DEFAULT_MODE);
        assertThat(testCommunicationConfiguration.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCommunicationConfiguration.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCommunicationConfiguration.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createCommunicationConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = communicationConfigurationRepository.findAll().size();

        // Create the CommunicationConfiguration with an existing ID
        communicationConfiguration.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommunicationConfigurationMockMvc.perform(post("/api/communication-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communicationConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the CommunicationConfiguration in the database
        List<CommunicationConfiguration> communicationConfigurationList = communicationConfigurationRepository.findAll();
        assertThat(communicationConfigurationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommunicationConfigurations() throws Exception {
        // Initialize the database
        communicationConfigurationRepository.saveAndFlush(communicationConfiguration);

        // Get all the communicationConfigurationList
        restCommunicationConfigurationMockMvc.perform(get("/api/communication-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(communicationConfiguration.getId().intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].retries").value(hasItem(DEFAULT_RETRIES)))
            .andExpect(jsonPath("$.[*].mode").value(hasItem(DEFAULT_MODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getCommunicationConfiguration() throws Exception {
        // Initialize the database
        communicationConfigurationRepository.saveAndFlush(communicationConfiguration);

        // Get the communicationConfiguration
        restCommunicationConfigurationMockMvc.perform(get("/api/communication-configurations/{id}", communicationConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(communicationConfiguration.getId().intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.retries").value(DEFAULT_RETRIES))
            .andExpect(jsonPath("$.mode").value(DEFAULT_MODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingCommunicationConfiguration() throws Exception {
        // Get the communicationConfiguration
        restCommunicationConfigurationMockMvc.perform(get("/api/communication-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommunicationConfiguration() throws Exception {
        // Initialize the database
        communicationConfigurationRepository.saveAndFlush(communicationConfiguration);

        int databaseSizeBeforeUpdate = communicationConfigurationRepository.findAll().size();

        // Update the communicationConfiguration
        CommunicationConfiguration updatedCommunicationConfiguration = communicationConfigurationRepository.findById(communicationConfiguration.getId()).get();
        // Disconnect from session so that the updates on updatedCommunicationConfiguration are not directly saved in db
        em.detach(updatedCommunicationConfiguration);
        updatedCommunicationConfiguration
            .priority(UPDATED_PRIORITY)
            .retries(UPDATED_RETRIES)
            .mode(UPDATED_MODE)
            .name(UPDATED_NAME)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restCommunicationConfigurationMockMvc.perform(put("/api/communication-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCommunicationConfiguration)))
            .andExpect(status().isOk());

        // Validate the CommunicationConfiguration in the database
        List<CommunicationConfiguration> communicationConfigurationList = communicationConfigurationRepository.findAll();
        assertThat(communicationConfigurationList).hasSize(databaseSizeBeforeUpdate);
        CommunicationConfiguration testCommunicationConfiguration = communicationConfigurationList.get(communicationConfigurationList.size() - 1);
        assertThat(testCommunicationConfiguration.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testCommunicationConfiguration.getRetries()).isEqualTo(UPDATED_RETRIES);
        assertThat(testCommunicationConfiguration.getMode()).isEqualTo(UPDATED_MODE);
        assertThat(testCommunicationConfiguration.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCommunicationConfiguration.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCommunicationConfiguration.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingCommunicationConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = communicationConfigurationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationConfigurationMockMvc.perform(put("/api/communication-configurations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communicationConfiguration)))
            .andExpect(status().isBadRequest());

        // Validate the CommunicationConfiguration in the database
        List<CommunicationConfiguration> communicationConfigurationList = communicationConfigurationRepository.findAll();
        assertThat(communicationConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommunicationConfiguration() throws Exception {
        // Initialize the database
        communicationConfigurationRepository.saveAndFlush(communicationConfiguration);

        int databaseSizeBeforeDelete = communicationConfigurationRepository.findAll().size();

        // Delete the communicationConfiguration
        restCommunicationConfigurationMockMvc.perform(delete("/api/communication-configurations/{id}", communicationConfiguration.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CommunicationConfiguration> communicationConfigurationList = communicationConfigurationRepository.findAll();
        assertThat(communicationConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
