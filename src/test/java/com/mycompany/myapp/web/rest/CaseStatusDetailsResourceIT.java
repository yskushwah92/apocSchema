package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.CaseStatusDetails;
import com.mycompany.myapp.repository.CaseStatusDetailsRepository;

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
 * Integration tests for the {@link CaseStatusDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CaseStatusDetailsResourceIT {

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNEE = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNEE = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_TIME_ELAPASED_IN_CURRENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TIME_ELAPASED_IN_CURRENT_STATUS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private CaseStatusDetailsRepository caseStatusDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCaseStatusDetailsMockMvc;

    private CaseStatusDetails caseStatusDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseStatusDetails createEntity(EntityManager em) {
        CaseStatusDetails caseStatusDetails = new CaseStatusDetails()
            .status(DEFAULT_STATUS)
            .assignee(DEFAULT_ASSIGNEE)
            .comments(DEFAULT_COMMENTS)
            .timeElapasedInCurrentStatus(DEFAULT_TIME_ELAPASED_IN_CURRENT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return caseStatusDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaseStatusDetails createUpdatedEntity(EntityManager em) {
        CaseStatusDetails caseStatusDetails = new CaseStatusDetails()
            .status(UPDATED_STATUS)
            .assignee(UPDATED_ASSIGNEE)
            .comments(UPDATED_COMMENTS)
            .timeElapasedInCurrentStatus(UPDATED_TIME_ELAPASED_IN_CURRENT_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return caseStatusDetails;
    }

    @BeforeEach
    public void initTest() {
        caseStatusDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaseStatusDetails() throws Exception {
        int databaseSizeBeforeCreate = caseStatusDetailsRepository.findAll().size();
        // Create the CaseStatusDetails
        restCaseStatusDetailsMockMvc.perform(post("/api/case-status-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caseStatusDetails)))
            .andExpect(status().isCreated());

        // Validate the CaseStatusDetails in the database
        List<CaseStatusDetails> caseStatusDetailsList = caseStatusDetailsRepository.findAll();
        assertThat(caseStatusDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        CaseStatusDetails testCaseStatusDetails = caseStatusDetailsList.get(caseStatusDetailsList.size() - 1);
        assertThat(testCaseStatusDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCaseStatusDetails.getAssignee()).isEqualTo(DEFAULT_ASSIGNEE);
        assertThat(testCaseStatusDetails.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testCaseStatusDetails.getTimeElapasedInCurrentStatus()).isEqualTo(DEFAULT_TIME_ELAPASED_IN_CURRENT_STATUS);
        assertThat(testCaseStatusDetails.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testCaseStatusDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createCaseStatusDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caseStatusDetailsRepository.findAll().size();

        // Create the CaseStatusDetails with an existing ID
        caseStatusDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaseStatusDetailsMockMvc.perform(post("/api/case-status-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caseStatusDetails)))
            .andExpect(status().isBadRequest());

        // Validate the CaseStatusDetails in the database
        List<CaseStatusDetails> caseStatusDetailsList = caseStatusDetailsRepository.findAll();
        assertThat(caseStatusDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaseStatusDetails() throws Exception {
        // Initialize the database
        caseStatusDetailsRepository.saveAndFlush(caseStatusDetails);

        // Get all the caseStatusDetailsList
        restCaseStatusDetailsMockMvc.perform(get("/api/case-status-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caseStatusDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].assignee").value(hasItem(DEFAULT_ASSIGNEE)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].timeElapasedInCurrentStatus").value(hasItem(DEFAULT_TIME_ELAPASED_IN_CURRENT_STATUS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getCaseStatusDetails() throws Exception {
        // Initialize the database
        caseStatusDetailsRepository.saveAndFlush(caseStatusDetails);

        // Get the caseStatusDetails
        restCaseStatusDetailsMockMvc.perform(get("/api/case-status-details/{id}", caseStatusDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caseStatusDetails.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.assignee").value(DEFAULT_ASSIGNEE))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.timeElapasedInCurrentStatus").value(DEFAULT_TIME_ELAPASED_IN_CURRENT_STATUS))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingCaseStatusDetails() throws Exception {
        // Get the caseStatusDetails
        restCaseStatusDetailsMockMvc.perform(get("/api/case-status-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaseStatusDetails() throws Exception {
        // Initialize the database
        caseStatusDetailsRepository.saveAndFlush(caseStatusDetails);

        int databaseSizeBeforeUpdate = caseStatusDetailsRepository.findAll().size();

        // Update the caseStatusDetails
        CaseStatusDetails updatedCaseStatusDetails = caseStatusDetailsRepository.findById(caseStatusDetails.getId()).get();
        // Disconnect from session so that the updates on updatedCaseStatusDetails are not directly saved in db
        em.detach(updatedCaseStatusDetails);
        updatedCaseStatusDetails
            .status(UPDATED_STATUS)
            .assignee(UPDATED_ASSIGNEE)
            .comments(UPDATED_COMMENTS)
            .timeElapasedInCurrentStatus(UPDATED_TIME_ELAPASED_IN_CURRENT_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restCaseStatusDetailsMockMvc.perform(put("/api/case-status-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCaseStatusDetails)))
            .andExpect(status().isOk());

        // Validate the CaseStatusDetails in the database
        List<CaseStatusDetails> caseStatusDetailsList = caseStatusDetailsRepository.findAll();
        assertThat(caseStatusDetailsList).hasSize(databaseSizeBeforeUpdate);
        CaseStatusDetails testCaseStatusDetails = caseStatusDetailsList.get(caseStatusDetailsList.size() - 1);
        assertThat(testCaseStatusDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCaseStatusDetails.getAssignee()).isEqualTo(UPDATED_ASSIGNEE);
        assertThat(testCaseStatusDetails.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testCaseStatusDetails.getTimeElapasedInCurrentStatus()).isEqualTo(UPDATED_TIME_ELAPASED_IN_CURRENT_STATUS);
        assertThat(testCaseStatusDetails.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testCaseStatusDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingCaseStatusDetails() throws Exception {
        int databaseSizeBeforeUpdate = caseStatusDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaseStatusDetailsMockMvc.perform(put("/api/case-status-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caseStatusDetails)))
            .andExpect(status().isBadRequest());

        // Validate the CaseStatusDetails in the database
        List<CaseStatusDetails> caseStatusDetailsList = caseStatusDetailsRepository.findAll();
        assertThat(caseStatusDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaseStatusDetails() throws Exception {
        // Initialize the database
        caseStatusDetailsRepository.saveAndFlush(caseStatusDetails);

        int databaseSizeBeforeDelete = caseStatusDetailsRepository.findAll().size();

        // Delete the caseStatusDetails
        restCaseStatusDetailsMockMvc.perform(delete("/api/case-status-details/{id}", caseStatusDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CaseStatusDetails> caseStatusDetailsList = caseStatusDetailsRepository.findAll();
        assertThat(caseStatusDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
