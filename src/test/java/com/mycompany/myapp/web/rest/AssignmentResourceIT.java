package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.Assignment;
import com.mycompany.myapp.repository.AssignmentRepository;

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

import com.mycompany.myapp.domain.enumeration.AssignmentMode;
/**
 * Integration tests for the {@link AssignmentResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AssignmentResourceIT {

    private static final ZonedDateTime DEFAULT_ASSIGNMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ASSIGNMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_ASSIGNED_TO = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_TO = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNED_BY = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_SET_SLA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SET_SLA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_ASSIGNMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNMENT_STATUS = "BBBBBBBBBB";

    private static final Integer DEFAULT_PERCENTAGE_COMPLETED = 1;
    private static final Integer UPDATED_PERCENTAGE_COMPLETED = 2;

    private static final AssignmentMode DEFAULT_ASSIGNMENT_MODE = AssignmentMode.MANUAL;
    private static final AssignmentMode UPDATED_ASSIGNMENT_MODE = AssignmentMode.AUTOMATIC;

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAssignmentMockMvc;

    private Assignment assignment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Assignment createEntity(EntityManager em) {
        Assignment assignment = new Assignment()
            .assignmentDate(DEFAULT_ASSIGNMENT_DATE)
            .assignedTo(DEFAULT_ASSIGNED_TO)
            .assignedBy(DEFAULT_ASSIGNED_BY)
            .setSLA(DEFAULT_SET_SLA)
            .assignmentStatus(DEFAULT_ASSIGNMENT_STATUS)
            .percentageCompleted(DEFAULT_PERCENTAGE_COMPLETED)
            .assignmentMode(DEFAULT_ASSIGNMENT_MODE)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return assignment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Assignment createUpdatedEntity(EntityManager em) {
        Assignment assignment = new Assignment()
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .assignedTo(UPDATED_ASSIGNED_TO)
            .assignedBy(UPDATED_ASSIGNED_BY)
            .setSLA(UPDATED_SET_SLA)
            .assignmentStatus(UPDATED_ASSIGNMENT_STATUS)
            .percentageCompleted(UPDATED_PERCENTAGE_COMPLETED)
            .assignmentMode(UPDATED_ASSIGNMENT_MODE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return assignment;
    }

    @BeforeEach
    public void initTest() {
        assignment = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssignment() throws Exception {
        int databaseSizeBeforeCreate = assignmentRepository.findAll().size();
        // Create the Assignment
        restAssignmentMockMvc.perform(post("/api/assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assignment)))
            .andExpect(status().isCreated());

        // Validate the Assignment in the database
        List<Assignment> assignmentList = assignmentRepository.findAll();
        assertThat(assignmentList).hasSize(databaseSizeBeforeCreate + 1);
        Assignment testAssignment = assignmentList.get(assignmentList.size() - 1);
        assertThat(testAssignment.getAssignmentDate()).isEqualTo(DEFAULT_ASSIGNMENT_DATE);
        assertThat(testAssignment.getAssignedTo()).isEqualTo(DEFAULT_ASSIGNED_TO);
        assertThat(testAssignment.getAssignedBy()).isEqualTo(DEFAULT_ASSIGNED_BY);
        assertThat(testAssignment.getSetSLA()).isEqualTo(DEFAULT_SET_SLA);
        assertThat(testAssignment.getAssignmentStatus()).isEqualTo(DEFAULT_ASSIGNMENT_STATUS);
        assertThat(testAssignment.getPercentageCompleted()).isEqualTo(DEFAULT_PERCENTAGE_COMPLETED);
        assertThat(testAssignment.getAssignmentMode()).isEqualTo(DEFAULT_ASSIGNMENT_MODE);
        assertThat(testAssignment.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAssignment.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createAssignmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assignmentRepository.findAll().size();

        // Create the Assignment with an existing ID
        assignment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssignmentMockMvc.perform(post("/api/assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assignment)))
            .andExpect(status().isBadRequest());

        // Validate the Assignment in the database
        List<Assignment> assignmentList = assignmentRepository.findAll();
        assertThat(assignmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAssignments() throws Exception {
        // Initialize the database
        assignmentRepository.saveAndFlush(assignment);

        // Get all the assignmentList
        restAssignmentMockMvc.perform(get("/api/assignments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assignment.getId().intValue())))
            .andExpect(jsonPath("$.[*].assignmentDate").value(hasItem(sameInstant(DEFAULT_ASSIGNMENT_DATE))))
            .andExpect(jsonPath("$.[*].assignedTo").value(hasItem(DEFAULT_ASSIGNED_TO)))
            .andExpect(jsonPath("$.[*].assignedBy").value(hasItem(DEFAULT_ASSIGNED_BY)))
            .andExpect(jsonPath("$.[*].setSLA").value(hasItem(sameInstant(DEFAULT_SET_SLA))))
            .andExpect(jsonPath("$.[*].assignmentStatus").value(hasItem(DEFAULT_ASSIGNMENT_STATUS)))
            .andExpect(jsonPath("$.[*].percentageCompleted").value(hasItem(DEFAULT_PERCENTAGE_COMPLETED)))
            .andExpect(jsonPath("$.[*].assignmentMode").value(hasItem(DEFAULT_ASSIGNMENT_MODE.toString())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getAssignment() throws Exception {
        // Initialize the database
        assignmentRepository.saveAndFlush(assignment);

        // Get the assignment
        restAssignmentMockMvc.perform(get("/api/assignments/{id}", assignment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(assignment.getId().intValue()))
            .andExpect(jsonPath("$.assignmentDate").value(sameInstant(DEFAULT_ASSIGNMENT_DATE)))
            .andExpect(jsonPath("$.assignedTo").value(DEFAULT_ASSIGNED_TO))
            .andExpect(jsonPath("$.assignedBy").value(DEFAULT_ASSIGNED_BY))
            .andExpect(jsonPath("$.setSLA").value(sameInstant(DEFAULT_SET_SLA)))
            .andExpect(jsonPath("$.assignmentStatus").value(DEFAULT_ASSIGNMENT_STATUS))
            .andExpect(jsonPath("$.percentageCompleted").value(DEFAULT_PERCENTAGE_COMPLETED))
            .andExpect(jsonPath("$.assignmentMode").value(DEFAULT_ASSIGNMENT_MODE.toString()))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingAssignment() throws Exception {
        // Get the assignment
        restAssignmentMockMvc.perform(get("/api/assignments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssignment() throws Exception {
        // Initialize the database
        assignmentRepository.saveAndFlush(assignment);

        int databaseSizeBeforeUpdate = assignmentRepository.findAll().size();

        // Update the assignment
        Assignment updatedAssignment = assignmentRepository.findById(assignment.getId()).get();
        // Disconnect from session so that the updates on updatedAssignment are not directly saved in db
        em.detach(updatedAssignment);
        updatedAssignment
            .assignmentDate(UPDATED_ASSIGNMENT_DATE)
            .assignedTo(UPDATED_ASSIGNED_TO)
            .assignedBy(UPDATED_ASSIGNED_BY)
            .setSLA(UPDATED_SET_SLA)
            .assignmentStatus(UPDATED_ASSIGNMENT_STATUS)
            .percentageCompleted(UPDATED_PERCENTAGE_COMPLETED)
            .assignmentMode(UPDATED_ASSIGNMENT_MODE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restAssignmentMockMvc.perform(put("/api/assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssignment)))
            .andExpect(status().isOk());

        // Validate the Assignment in the database
        List<Assignment> assignmentList = assignmentRepository.findAll();
        assertThat(assignmentList).hasSize(databaseSizeBeforeUpdate);
        Assignment testAssignment = assignmentList.get(assignmentList.size() - 1);
        assertThat(testAssignment.getAssignmentDate()).isEqualTo(UPDATED_ASSIGNMENT_DATE);
        assertThat(testAssignment.getAssignedTo()).isEqualTo(UPDATED_ASSIGNED_TO);
        assertThat(testAssignment.getAssignedBy()).isEqualTo(UPDATED_ASSIGNED_BY);
        assertThat(testAssignment.getSetSLA()).isEqualTo(UPDATED_SET_SLA);
        assertThat(testAssignment.getAssignmentStatus()).isEqualTo(UPDATED_ASSIGNMENT_STATUS);
        assertThat(testAssignment.getPercentageCompleted()).isEqualTo(UPDATED_PERCENTAGE_COMPLETED);
        assertThat(testAssignment.getAssignmentMode()).isEqualTo(UPDATED_ASSIGNMENT_MODE);
        assertThat(testAssignment.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAssignment.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingAssignment() throws Exception {
        int databaseSizeBeforeUpdate = assignmentRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssignmentMockMvc.perform(put("/api/assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assignment)))
            .andExpect(status().isBadRequest());

        // Validate the Assignment in the database
        List<Assignment> assignmentList = assignmentRepository.findAll();
        assertThat(assignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssignment() throws Exception {
        // Initialize the database
        assignmentRepository.saveAndFlush(assignment);

        int databaseSizeBeforeDelete = assignmentRepository.findAll().size();

        // Delete the assignment
        restAssignmentMockMvc.perform(delete("/api/assignments/{id}", assignment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Assignment> assignmentList = assignmentRepository.findAll();
        assertThat(assignmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
