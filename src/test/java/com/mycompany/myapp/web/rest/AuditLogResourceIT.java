package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.AuditLog;
import com.mycompany.myapp.repository.AuditLogRepository;

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
import java.time.Duration;
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
 * Integration tests for the {@link AuditLogResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AuditLogResourceIT {

    private static final String DEFAULT_ACTIVITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVITY_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_ACTIVITY_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ACTIVITY_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ACTIVITY_START_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ACTIVITY_START_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ACTIVITY_END_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ACTIVITY_END_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_ASSIGNED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ASSIGNED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_COMPLETE_ON = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_COMPLETE_ON = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Duration DEFAULT_DURATION = Duration.ofHours(6);
    private static final Duration UPDATED_DURATION = Duration.ofHours(12);

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuditLogMockMvc;

    private AuditLog auditLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditLog createEntity(EntityManager em) {
        AuditLog auditLog = new AuditLog()
            .activityName(DEFAULT_ACTIVITY_NAME)
            .activityCreationDate(DEFAULT_ACTIVITY_CREATION_DATE)
            .activityStartTime(DEFAULT_ACTIVITY_START_TIME)
            .activityEndTime(DEFAULT_ACTIVITY_END_TIME)
            .assignedDate(DEFAULT_ASSIGNED_DATE)
            .user(DEFAULT_USER)
            .status(DEFAULT_STATUS)
            .reason(DEFAULT_REASON)
            .comments(DEFAULT_COMMENTS)
            .completeOn(DEFAULT_COMPLETE_ON)
            .duration(DEFAULT_DURATION)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return auditLog;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditLog createUpdatedEntity(EntityManager em) {
        AuditLog auditLog = new AuditLog()
            .activityName(UPDATED_ACTIVITY_NAME)
            .activityCreationDate(UPDATED_ACTIVITY_CREATION_DATE)
            .activityStartTime(UPDATED_ACTIVITY_START_TIME)
            .activityEndTime(UPDATED_ACTIVITY_END_TIME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .user(UPDATED_USER)
            .status(UPDATED_STATUS)
            .reason(UPDATED_REASON)
            .comments(UPDATED_COMMENTS)
            .completeOn(UPDATED_COMPLETE_ON)
            .duration(UPDATED_DURATION)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return auditLog;
    }

    @BeforeEach
    public void initTest() {
        auditLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuditLog() throws Exception {
        int databaseSizeBeforeCreate = auditLogRepository.findAll().size();
        // Create the AuditLog
        restAuditLogMockMvc.perform(post("/api/audit-logs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auditLog)))
            .andExpect(status().isCreated());

        // Validate the AuditLog in the database
        List<AuditLog> auditLogList = auditLogRepository.findAll();
        assertThat(auditLogList).hasSize(databaseSizeBeforeCreate + 1);
        AuditLog testAuditLog = auditLogList.get(auditLogList.size() - 1);
        assertThat(testAuditLog.getActivityName()).isEqualTo(DEFAULT_ACTIVITY_NAME);
        assertThat(testAuditLog.getActivityCreationDate()).isEqualTo(DEFAULT_ACTIVITY_CREATION_DATE);
        assertThat(testAuditLog.getActivityStartTime()).isEqualTo(DEFAULT_ACTIVITY_START_TIME);
        assertThat(testAuditLog.getActivityEndTime()).isEqualTo(DEFAULT_ACTIVITY_END_TIME);
        assertThat(testAuditLog.getAssignedDate()).isEqualTo(DEFAULT_ASSIGNED_DATE);
        assertThat(testAuditLog.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testAuditLog.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAuditLog.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testAuditLog.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testAuditLog.getCompleteOn()).isEqualTo(DEFAULT_COMPLETE_ON);
        assertThat(testAuditLog.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testAuditLog.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAuditLog.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createAuditLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = auditLogRepository.findAll().size();

        // Create the AuditLog with an existing ID
        auditLog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuditLogMockMvc.perform(post("/api/audit-logs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auditLog)))
            .andExpect(status().isBadRequest());

        // Validate the AuditLog in the database
        List<AuditLog> auditLogList = auditLogRepository.findAll();
        assertThat(auditLogList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAuditLogs() throws Exception {
        // Initialize the database
        auditLogRepository.saveAndFlush(auditLog);

        // Get all the auditLogList
        restAuditLogMockMvc.perform(get("/api/audit-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auditLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].activityName").value(hasItem(DEFAULT_ACTIVITY_NAME)))
            .andExpect(jsonPath("$.[*].activityCreationDate").value(hasItem(sameInstant(DEFAULT_ACTIVITY_CREATION_DATE))))
            .andExpect(jsonPath("$.[*].activityStartTime").value(hasItem(sameInstant(DEFAULT_ACTIVITY_START_TIME))))
            .andExpect(jsonPath("$.[*].activityEndTime").value(hasItem(sameInstant(DEFAULT_ACTIVITY_END_TIME))))
            .andExpect(jsonPath("$.[*].assignedDate").value(hasItem(sameInstant(DEFAULT_ASSIGNED_DATE))))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].completeOn").value(hasItem(sameInstant(DEFAULT_COMPLETE_ON))))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getAuditLog() throws Exception {
        // Initialize the database
        auditLogRepository.saveAndFlush(auditLog);

        // Get the auditLog
        restAuditLogMockMvc.perform(get("/api/audit-logs/{id}", auditLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(auditLog.getId().intValue()))
            .andExpect(jsonPath("$.activityName").value(DEFAULT_ACTIVITY_NAME))
            .andExpect(jsonPath("$.activityCreationDate").value(sameInstant(DEFAULT_ACTIVITY_CREATION_DATE)))
            .andExpect(jsonPath("$.activityStartTime").value(sameInstant(DEFAULT_ACTIVITY_START_TIME)))
            .andExpect(jsonPath("$.activityEndTime").value(sameInstant(DEFAULT_ACTIVITY_END_TIME)))
            .andExpect(jsonPath("$.assignedDate").value(sameInstant(DEFAULT_ASSIGNED_DATE)))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.completeOn").value(sameInstant(DEFAULT_COMPLETE_ON)))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION.toString()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingAuditLog() throws Exception {
        // Get the auditLog
        restAuditLogMockMvc.perform(get("/api/audit-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuditLog() throws Exception {
        // Initialize the database
        auditLogRepository.saveAndFlush(auditLog);

        int databaseSizeBeforeUpdate = auditLogRepository.findAll().size();

        // Update the auditLog
        AuditLog updatedAuditLog = auditLogRepository.findById(auditLog.getId()).get();
        // Disconnect from session so that the updates on updatedAuditLog are not directly saved in db
        em.detach(updatedAuditLog);
        updatedAuditLog
            .activityName(UPDATED_ACTIVITY_NAME)
            .activityCreationDate(UPDATED_ACTIVITY_CREATION_DATE)
            .activityStartTime(UPDATED_ACTIVITY_START_TIME)
            .activityEndTime(UPDATED_ACTIVITY_END_TIME)
            .assignedDate(UPDATED_ASSIGNED_DATE)
            .user(UPDATED_USER)
            .status(UPDATED_STATUS)
            .reason(UPDATED_REASON)
            .comments(UPDATED_COMMENTS)
            .completeOn(UPDATED_COMPLETE_ON)
            .duration(UPDATED_DURATION)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restAuditLogMockMvc.perform(put("/api/audit-logs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAuditLog)))
            .andExpect(status().isOk());

        // Validate the AuditLog in the database
        List<AuditLog> auditLogList = auditLogRepository.findAll();
        assertThat(auditLogList).hasSize(databaseSizeBeforeUpdate);
        AuditLog testAuditLog = auditLogList.get(auditLogList.size() - 1);
        assertThat(testAuditLog.getActivityName()).isEqualTo(UPDATED_ACTIVITY_NAME);
        assertThat(testAuditLog.getActivityCreationDate()).isEqualTo(UPDATED_ACTIVITY_CREATION_DATE);
        assertThat(testAuditLog.getActivityStartTime()).isEqualTo(UPDATED_ACTIVITY_START_TIME);
        assertThat(testAuditLog.getActivityEndTime()).isEqualTo(UPDATED_ACTIVITY_END_TIME);
        assertThat(testAuditLog.getAssignedDate()).isEqualTo(UPDATED_ASSIGNED_DATE);
        assertThat(testAuditLog.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testAuditLog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAuditLog.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testAuditLog.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testAuditLog.getCompleteOn()).isEqualTo(UPDATED_COMPLETE_ON);
        assertThat(testAuditLog.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testAuditLog.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAuditLog.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingAuditLog() throws Exception {
        int databaseSizeBeforeUpdate = auditLogRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditLogMockMvc.perform(put("/api/audit-logs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auditLog)))
            .andExpect(status().isBadRequest());

        // Validate the AuditLog in the database
        List<AuditLog> auditLogList = auditLogRepository.findAll();
        assertThat(auditLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAuditLog() throws Exception {
        // Initialize the database
        auditLogRepository.saveAndFlush(auditLog);

        int databaseSizeBeforeDelete = auditLogRepository.findAll().size();

        // Delete the auditLog
        restAuditLogMockMvc.perform(delete("/api/audit-logs/{id}", auditLog.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AuditLog> auditLogList = auditLogRepository.findAll();
        assertThat(auditLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
