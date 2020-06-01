package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.AuditLogDetails;
import com.mycompany.myapp.repository.AuditLogDetailsRepository;

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
 * Integration tests for the {@link AuditLogDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AuditLogDetailsResourceIT {

    private static final String DEFAULT_ASSIGNED_BY = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_ACTOR = "AAAAAAAAAA";
    private static final String UPDATED_ACTOR = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_REASON_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REASON_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_ON = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_ON = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_DELEGATE = "AAAAAAAAAA";
    private static final String UPDATED_DELEGATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELEGATED_FLAG = false;
    private static final Boolean UPDATED_DELEGATED_FLAG = true;

    private static final String DEFAULT_MET_SLA = "AAAAAAAAAA";
    private static final String UPDATED_MET_SLA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private AuditLogDetailsRepository auditLogDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuditLogDetailsMockMvc;

    private AuditLogDetails auditLogDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditLogDetails createEntity(EntityManager em) {
        AuditLogDetails auditLogDetails = new AuditLogDetails()
            .assignedBy(DEFAULT_ASSIGNED_BY)
            .actor(DEFAULT_ACTOR)
            .status(DEFAULT_STATUS)
            .statusCode(DEFAULT_STATUS_CODE)
            .reason(DEFAULT_REASON)
            .reasonCode(DEFAULT_REASON_CODE)
            .createdOn(DEFAULT_CREATED_ON)
            .comments(DEFAULT_COMMENTS)
            .delegate(DEFAULT_DELEGATE)
            .delegatedFlag(DEFAULT_DELEGATED_FLAG)
            .metSLA(DEFAULT_MET_SLA)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return auditLogDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditLogDetails createUpdatedEntity(EntityManager em) {
        AuditLogDetails auditLogDetails = new AuditLogDetails()
            .assignedBy(UPDATED_ASSIGNED_BY)
            .actor(UPDATED_ACTOR)
            .status(UPDATED_STATUS)
            .statusCode(UPDATED_STATUS_CODE)
            .reason(UPDATED_REASON)
            .reasonCode(UPDATED_REASON_CODE)
            .createdOn(UPDATED_CREATED_ON)
            .comments(UPDATED_COMMENTS)
            .delegate(UPDATED_DELEGATE)
            .delegatedFlag(UPDATED_DELEGATED_FLAG)
            .metSLA(UPDATED_MET_SLA)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return auditLogDetails;
    }

    @BeforeEach
    public void initTest() {
        auditLogDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuditLogDetails() throws Exception {
        int databaseSizeBeforeCreate = auditLogDetailsRepository.findAll().size();
        // Create the AuditLogDetails
        restAuditLogDetailsMockMvc.perform(post("/api/audit-log-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auditLogDetails)))
            .andExpect(status().isCreated());

        // Validate the AuditLogDetails in the database
        List<AuditLogDetails> auditLogDetailsList = auditLogDetailsRepository.findAll();
        assertThat(auditLogDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        AuditLogDetails testAuditLogDetails = auditLogDetailsList.get(auditLogDetailsList.size() - 1);
        assertThat(testAuditLogDetails.getAssignedBy()).isEqualTo(DEFAULT_ASSIGNED_BY);
        assertThat(testAuditLogDetails.getActor()).isEqualTo(DEFAULT_ACTOR);
        assertThat(testAuditLogDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAuditLogDetails.getStatusCode()).isEqualTo(DEFAULT_STATUS_CODE);
        assertThat(testAuditLogDetails.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testAuditLogDetails.getReasonCode()).isEqualTo(DEFAULT_REASON_CODE);
        assertThat(testAuditLogDetails.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testAuditLogDetails.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testAuditLogDetails.getDelegate()).isEqualTo(DEFAULT_DELEGATE);
        assertThat(testAuditLogDetails.isDelegatedFlag()).isEqualTo(DEFAULT_DELEGATED_FLAG);
        assertThat(testAuditLogDetails.getMetSLA()).isEqualTo(DEFAULT_MET_SLA);
        assertThat(testAuditLogDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAuditLogDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createAuditLogDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = auditLogDetailsRepository.findAll().size();

        // Create the AuditLogDetails with an existing ID
        auditLogDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuditLogDetailsMockMvc.perform(post("/api/audit-log-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auditLogDetails)))
            .andExpect(status().isBadRequest());

        // Validate the AuditLogDetails in the database
        List<AuditLogDetails> auditLogDetailsList = auditLogDetailsRepository.findAll();
        assertThat(auditLogDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAuditLogDetails() throws Exception {
        // Initialize the database
        auditLogDetailsRepository.saveAndFlush(auditLogDetails);

        // Get all the auditLogDetailsList
        restAuditLogDetailsMockMvc.perform(get("/api/audit-log-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auditLogDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].assignedBy").value(hasItem(DEFAULT_ASSIGNED_BY)))
            .andExpect(jsonPath("$.[*].actor").value(hasItem(DEFAULT_ACTOR)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusCode").value(hasItem(DEFAULT_STATUS_CODE)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].reasonCode").value(hasItem(DEFAULT_REASON_CODE)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(sameInstant(DEFAULT_CREATED_ON))))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].delegate").value(hasItem(DEFAULT_DELEGATE)))
            .andExpect(jsonPath("$.[*].delegatedFlag").value(hasItem(DEFAULT_DELEGATED_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].metSLA").value(hasItem(DEFAULT_MET_SLA)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getAuditLogDetails() throws Exception {
        // Initialize the database
        auditLogDetailsRepository.saveAndFlush(auditLogDetails);

        // Get the auditLogDetails
        restAuditLogDetailsMockMvc.perform(get("/api/audit-log-details/{id}", auditLogDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(auditLogDetails.getId().intValue()))
            .andExpect(jsonPath("$.assignedBy").value(DEFAULT_ASSIGNED_BY))
            .andExpect(jsonPath("$.actor").value(DEFAULT_ACTOR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.statusCode").value(DEFAULT_STATUS_CODE))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.reasonCode").value(DEFAULT_REASON_CODE))
            .andExpect(jsonPath("$.createdOn").value(sameInstant(DEFAULT_CREATED_ON)))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.delegate").value(DEFAULT_DELEGATE))
            .andExpect(jsonPath("$.delegatedFlag").value(DEFAULT_DELEGATED_FLAG.booleanValue()))
            .andExpect(jsonPath("$.metSLA").value(DEFAULT_MET_SLA))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingAuditLogDetails() throws Exception {
        // Get the auditLogDetails
        restAuditLogDetailsMockMvc.perform(get("/api/audit-log-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuditLogDetails() throws Exception {
        // Initialize the database
        auditLogDetailsRepository.saveAndFlush(auditLogDetails);

        int databaseSizeBeforeUpdate = auditLogDetailsRepository.findAll().size();

        // Update the auditLogDetails
        AuditLogDetails updatedAuditLogDetails = auditLogDetailsRepository.findById(auditLogDetails.getId()).get();
        // Disconnect from session so that the updates on updatedAuditLogDetails are not directly saved in db
        em.detach(updatedAuditLogDetails);
        updatedAuditLogDetails
            .assignedBy(UPDATED_ASSIGNED_BY)
            .actor(UPDATED_ACTOR)
            .status(UPDATED_STATUS)
            .statusCode(UPDATED_STATUS_CODE)
            .reason(UPDATED_REASON)
            .reasonCode(UPDATED_REASON_CODE)
            .createdOn(UPDATED_CREATED_ON)
            .comments(UPDATED_COMMENTS)
            .delegate(UPDATED_DELEGATE)
            .delegatedFlag(UPDATED_DELEGATED_FLAG)
            .metSLA(UPDATED_MET_SLA)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restAuditLogDetailsMockMvc.perform(put("/api/audit-log-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAuditLogDetails)))
            .andExpect(status().isOk());

        // Validate the AuditLogDetails in the database
        List<AuditLogDetails> auditLogDetailsList = auditLogDetailsRepository.findAll();
        assertThat(auditLogDetailsList).hasSize(databaseSizeBeforeUpdate);
        AuditLogDetails testAuditLogDetails = auditLogDetailsList.get(auditLogDetailsList.size() - 1);
        assertThat(testAuditLogDetails.getAssignedBy()).isEqualTo(UPDATED_ASSIGNED_BY);
        assertThat(testAuditLogDetails.getActor()).isEqualTo(UPDATED_ACTOR);
        assertThat(testAuditLogDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAuditLogDetails.getStatusCode()).isEqualTo(UPDATED_STATUS_CODE);
        assertThat(testAuditLogDetails.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testAuditLogDetails.getReasonCode()).isEqualTo(UPDATED_REASON_CODE);
        assertThat(testAuditLogDetails.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testAuditLogDetails.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testAuditLogDetails.getDelegate()).isEqualTo(UPDATED_DELEGATE);
        assertThat(testAuditLogDetails.isDelegatedFlag()).isEqualTo(UPDATED_DELEGATED_FLAG);
        assertThat(testAuditLogDetails.getMetSLA()).isEqualTo(UPDATED_MET_SLA);
        assertThat(testAuditLogDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAuditLogDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingAuditLogDetails() throws Exception {
        int databaseSizeBeforeUpdate = auditLogDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditLogDetailsMockMvc.perform(put("/api/audit-log-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auditLogDetails)))
            .andExpect(status().isBadRequest());

        // Validate the AuditLogDetails in the database
        List<AuditLogDetails> auditLogDetailsList = auditLogDetailsRepository.findAll();
        assertThat(auditLogDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAuditLogDetails() throws Exception {
        // Initialize the database
        auditLogDetailsRepository.saveAndFlush(auditLogDetails);

        int databaseSizeBeforeDelete = auditLogDetailsRepository.findAll().size();

        // Delete the auditLogDetails
        restAuditLogDetailsMockMvc.perform(delete("/api/audit-log-details/{id}", auditLogDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AuditLogDetails> auditLogDetailsList = auditLogDetailsRepository.findAll();
        assertThat(auditLogDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
