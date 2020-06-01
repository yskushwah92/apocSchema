package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.ShippingLocation;
import com.mycompany.myapp.repository.ShippingLocationRepository;

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
 * Integration tests for the {@link ShippingLocationResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ShippingLocationResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private ShippingLocationRepository shippingLocationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShippingLocationMockMvc;

    private ShippingLocation shippingLocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShippingLocation createEntity(EntityManager em) {
        ShippingLocation shippingLocation = new ShippingLocation()
            .name(DEFAULT_NAME)
            .locationCode(DEFAULT_LOCATION_CODE)
            .description(DEFAULT_DESCRIPTION)
            .address(DEFAULT_ADDRESS)
            .country(DEFAULT_COUNTRY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return shippingLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShippingLocation createUpdatedEntity(EntityManager em) {
        ShippingLocation shippingLocation = new ShippingLocation()
            .name(UPDATED_NAME)
            .locationCode(UPDATED_LOCATION_CODE)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .postalCode(UPDATED_POSTAL_CODE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return shippingLocation;
    }

    @BeforeEach
    public void initTest() {
        shippingLocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createShippingLocation() throws Exception {
        int databaseSizeBeforeCreate = shippingLocationRepository.findAll().size();
        // Create the ShippingLocation
        restShippingLocationMockMvc.perform(post("/api/shipping-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingLocation)))
            .andExpect(status().isCreated());

        // Validate the ShippingLocation in the database
        List<ShippingLocation> shippingLocationList = shippingLocationRepository.findAll();
        assertThat(shippingLocationList).hasSize(databaseSizeBeforeCreate + 1);
        ShippingLocation testShippingLocation = shippingLocationList.get(shippingLocationList.size() - 1);
        assertThat(testShippingLocation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testShippingLocation.getLocationCode()).isEqualTo(DEFAULT_LOCATION_CODE);
        assertThat(testShippingLocation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testShippingLocation.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testShippingLocation.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testShippingLocation.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testShippingLocation.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testShippingLocation.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createShippingLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shippingLocationRepository.findAll().size();

        // Create the ShippingLocation with an existing ID
        shippingLocation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShippingLocationMockMvc.perform(post("/api/shipping-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingLocation)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingLocation in the database
        List<ShippingLocation> shippingLocationList = shippingLocationRepository.findAll();
        assertThat(shippingLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllShippingLocations() throws Exception {
        // Initialize the database
        shippingLocationRepository.saveAndFlush(shippingLocation);

        // Get all the shippingLocationList
        restShippingLocationMockMvc.perform(get("/api/shipping-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shippingLocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].locationCode").value(hasItem(DEFAULT_LOCATION_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getShippingLocation() throws Exception {
        // Initialize the database
        shippingLocationRepository.saveAndFlush(shippingLocation);

        // Get the shippingLocation
        restShippingLocationMockMvc.perform(get("/api/shipping-locations/{id}", shippingLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(shippingLocation.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.locationCode").value(DEFAULT_LOCATION_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingShippingLocation() throws Exception {
        // Get the shippingLocation
        restShippingLocationMockMvc.perform(get("/api/shipping-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShippingLocation() throws Exception {
        // Initialize the database
        shippingLocationRepository.saveAndFlush(shippingLocation);

        int databaseSizeBeforeUpdate = shippingLocationRepository.findAll().size();

        // Update the shippingLocation
        ShippingLocation updatedShippingLocation = shippingLocationRepository.findById(shippingLocation.getId()).get();
        // Disconnect from session so that the updates on updatedShippingLocation are not directly saved in db
        em.detach(updatedShippingLocation);
        updatedShippingLocation
            .name(UPDATED_NAME)
            .locationCode(UPDATED_LOCATION_CODE)
            .description(UPDATED_DESCRIPTION)
            .address(UPDATED_ADDRESS)
            .country(UPDATED_COUNTRY)
            .postalCode(UPDATED_POSTAL_CODE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restShippingLocationMockMvc.perform(put("/api/shipping-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedShippingLocation)))
            .andExpect(status().isOk());

        // Validate the ShippingLocation in the database
        List<ShippingLocation> shippingLocationList = shippingLocationRepository.findAll();
        assertThat(shippingLocationList).hasSize(databaseSizeBeforeUpdate);
        ShippingLocation testShippingLocation = shippingLocationList.get(shippingLocationList.size() - 1);
        assertThat(testShippingLocation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testShippingLocation.getLocationCode()).isEqualTo(UPDATED_LOCATION_CODE);
        assertThat(testShippingLocation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testShippingLocation.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testShippingLocation.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testShippingLocation.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testShippingLocation.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testShippingLocation.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingShippingLocation() throws Exception {
        int databaseSizeBeforeUpdate = shippingLocationRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShippingLocationMockMvc.perform(put("/api/shipping-locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(shippingLocation)))
            .andExpect(status().isBadRequest());

        // Validate the ShippingLocation in the database
        List<ShippingLocation> shippingLocationList = shippingLocationRepository.findAll();
        assertThat(shippingLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteShippingLocation() throws Exception {
        // Initialize the database
        shippingLocationRepository.saveAndFlush(shippingLocation);

        int databaseSizeBeforeDelete = shippingLocationRepository.findAll().size();

        // Delete the shippingLocation
        restShippingLocationMockMvc.perform(delete("/api/shipping-locations/{id}", shippingLocation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShippingLocation> shippingLocationList = shippingLocationRepository.findAll();
        assertThat(shippingLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
