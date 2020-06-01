package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.GRNDetails;
import com.mycompany.myapp.repository.GRNDetailsRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.domain.enumeration.GRNType;
/**
 * Integration tests for the {@link GRNDetailsResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GRNDetailsResourceIT {

    private static final String DEFAULT_GRN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GRN_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_GRN_LINE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_GRN_LINE_NUMBER = "BBBBBBBBBB";

    private static final Double DEFAULT_GRN_QUANTITY = 1D;
    private static final Double UPDATED_GRN_QUANTITY = 2D;

    private static final GRNType DEFAULT_GRN_TYPE = GRNType.INVOICE;
    private static final GRNType UPDATED_GRN_TYPE = GRNType.PO;

    private static final String DEFAULT_DELIVERY_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_NOTE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_OPEN = false;
    private static final Boolean UPDATED_IS_OPEN = true;

    @Autowired
    private GRNDetailsRepository gRNDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGRNDetailsMockMvc;

    private GRNDetails gRNDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GRNDetails createEntity(EntityManager em) {
        GRNDetails gRNDetails = new GRNDetails()
            .grnNumber(DEFAULT_GRN_NUMBER)
            .grnLineNumber(DEFAULT_GRN_LINE_NUMBER)
            .grnQuantity(DEFAULT_GRN_QUANTITY)
            .grnType(DEFAULT_GRN_TYPE)
            .deliveryNote(DEFAULT_DELIVERY_NOTE)
            .isOpen(DEFAULT_IS_OPEN);
        return gRNDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GRNDetails createUpdatedEntity(EntityManager em) {
        GRNDetails gRNDetails = new GRNDetails()
            .grnNumber(UPDATED_GRN_NUMBER)
            .grnLineNumber(UPDATED_GRN_LINE_NUMBER)
            .grnQuantity(UPDATED_GRN_QUANTITY)
            .grnType(UPDATED_GRN_TYPE)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .isOpen(UPDATED_IS_OPEN);
        return gRNDetails;
    }

    @BeforeEach
    public void initTest() {
        gRNDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createGRNDetails() throws Exception {
        int databaseSizeBeforeCreate = gRNDetailsRepository.findAll().size();
        // Create the GRNDetails
        restGRNDetailsMockMvc.perform(post("/api/grn-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gRNDetails)))
            .andExpect(status().isCreated());

        // Validate the GRNDetails in the database
        List<GRNDetails> gRNDetailsList = gRNDetailsRepository.findAll();
        assertThat(gRNDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        GRNDetails testGRNDetails = gRNDetailsList.get(gRNDetailsList.size() - 1);
        assertThat(testGRNDetails.getGrnNumber()).isEqualTo(DEFAULT_GRN_NUMBER);
        assertThat(testGRNDetails.getGrnLineNumber()).isEqualTo(DEFAULT_GRN_LINE_NUMBER);
        assertThat(testGRNDetails.getGrnQuantity()).isEqualTo(DEFAULT_GRN_QUANTITY);
        assertThat(testGRNDetails.getGrnType()).isEqualTo(DEFAULT_GRN_TYPE);
        assertThat(testGRNDetails.getDeliveryNote()).isEqualTo(DEFAULT_DELIVERY_NOTE);
        assertThat(testGRNDetails.isIsOpen()).isEqualTo(DEFAULT_IS_OPEN);
    }

    @Test
    @Transactional
    public void createGRNDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gRNDetailsRepository.findAll().size();

        // Create the GRNDetails with an existing ID
        gRNDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGRNDetailsMockMvc.perform(post("/api/grn-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gRNDetails)))
            .andExpect(status().isBadRequest());

        // Validate the GRNDetails in the database
        List<GRNDetails> gRNDetailsList = gRNDetailsRepository.findAll();
        assertThat(gRNDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGRNDetails() throws Exception {
        // Initialize the database
        gRNDetailsRepository.saveAndFlush(gRNDetails);

        // Get all the gRNDetailsList
        restGRNDetailsMockMvc.perform(get("/api/grn-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gRNDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].grnNumber").value(hasItem(DEFAULT_GRN_NUMBER)))
            .andExpect(jsonPath("$.[*].grnLineNumber").value(hasItem(DEFAULT_GRN_LINE_NUMBER)))
            .andExpect(jsonPath("$.[*].grnQuantity").value(hasItem(DEFAULT_GRN_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].grnType").value(hasItem(DEFAULT_GRN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].deliveryNote").value(hasItem(DEFAULT_DELIVERY_NOTE)))
            .andExpect(jsonPath("$.[*].isOpen").value(hasItem(DEFAULT_IS_OPEN.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getGRNDetails() throws Exception {
        // Initialize the database
        gRNDetailsRepository.saveAndFlush(gRNDetails);

        // Get the gRNDetails
        restGRNDetailsMockMvc.perform(get("/api/grn-details/{id}", gRNDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gRNDetails.getId().intValue()))
            .andExpect(jsonPath("$.grnNumber").value(DEFAULT_GRN_NUMBER))
            .andExpect(jsonPath("$.grnLineNumber").value(DEFAULT_GRN_LINE_NUMBER))
            .andExpect(jsonPath("$.grnQuantity").value(DEFAULT_GRN_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.grnType").value(DEFAULT_GRN_TYPE.toString()))
            .andExpect(jsonPath("$.deliveryNote").value(DEFAULT_DELIVERY_NOTE))
            .andExpect(jsonPath("$.isOpen").value(DEFAULT_IS_OPEN.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingGRNDetails() throws Exception {
        // Get the gRNDetails
        restGRNDetailsMockMvc.perform(get("/api/grn-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGRNDetails() throws Exception {
        // Initialize the database
        gRNDetailsRepository.saveAndFlush(gRNDetails);

        int databaseSizeBeforeUpdate = gRNDetailsRepository.findAll().size();

        // Update the gRNDetails
        GRNDetails updatedGRNDetails = gRNDetailsRepository.findById(gRNDetails.getId()).get();
        // Disconnect from session so that the updates on updatedGRNDetails are not directly saved in db
        em.detach(updatedGRNDetails);
        updatedGRNDetails
            .grnNumber(UPDATED_GRN_NUMBER)
            .grnLineNumber(UPDATED_GRN_LINE_NUMBER)
            .grnQuantity(UPDATED_GRN_QUANTITY)
            .grnType(UPDATED_GRN_TYPE)
            .deliveryNote(UPDATED_DELIVERY_NOTE)
            .isOpen(UPDATED_IS_OPEN);

        restGRNDetailsMockMvc.perform(put("/api/grn-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGRNDetails)))
            .andExpect(status().isOk());

        // Validate the GRNDetails in the database
        List<GRNDetails> gRNDetailsList = gRNDetailsRepository.findAll();
        assertThat(gRNDetailsList).hasSize(databaseSizeBeforeUpdate);
        GRNDetails testGRNDetails = gRNDetailsList.get(gRNDetailsList.size() - 1);
        assertThat(testGRNDetails.getGrnNumber()).isEqualTo(UPDATED_GRN_NUMBER);
        assertThat(testGRNDetails.getGrnLineNumber()).isEqualTo(UPDATED_GRN_LINE_NUMBER);
        assertThat(testGRNDetails.getGrnQuantity()).isEqualTo(UPDATED_GRN_QUANTITY);
        assertThat(testGRNDetails.getGrnType()).isEqualTo(UPDATED_GRN_TYPE);
        assertThat(testGRNDetails.getDeliveryNote()).isEqualTo(UPDATED_DELIVERY_NOTE);
        assertThat(testGRNDetails.isIsOpen()).isEqualTo(UPDATED_IS_OPEN);
    }

    @Test
    @Transactional
    public void updateNonExistingGRNDetails() throws Exception {
        int databaseSizeBeforeUpdate = gRNDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGRNDetailsMockMvc.perform(put("/api/grn-details")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gRNDetails)))
            .andExpect(status().isBadRequest());

        // Validate the GRNDetails in the database
        List<GRNDetails> gRNDetailsList = gRNDetailsRepository.findAll();
        assertThat(gRNDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGRNDetails() throws Exception {
        // Initialize the database
        gRNDetailsRepository.saveAndFlush(gRNDetails);

        int databaseSizeBeforeDelete = gRNDetailsRepository.findAll().size();

        // Delete the gRNDetails
        restGRNDetailsMockMvc.perform(delete("/api/grn-details/{id}", gRNDetails.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GRNDetails> gRNDetailsList = gRNDetailsRepository.findAll();
        assertThat(gRNDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
