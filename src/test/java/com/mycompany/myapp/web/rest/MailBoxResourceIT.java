package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.MailBox;
import com.mycompany.myapp.repository.MailBoxRepository;

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
 * Integration tests for the {@link MailBoxResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MailBoxResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVER = "AAAAAAAAAA";
    private static final String UPDATED_SERVER = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DISPLAY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private MailBoxRepository mailBoxRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMailBoxMockMvc;

    private MailBox mailBox;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MailBox createEntity(EntityManager em) {
        MailBox mailBox = new MailBox()
            .name(DEFAULT_NAME)
            .server(DEFAULT_SERVER)
            .address(DEFAULT_ADDRESS)
            .displayName(DEFAULT_DISPLAY_NAME)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return mailBox;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MailBox createUpdatedEntity(EntityManager em) {
        MailBox mailBox = new MailBox()
            .name(UPDATED_NAME)
            .server(UPDATED_SERVER)
            .address(UPDATED_ADDRESS)
            .displayName(UPDATED_DISPLAY_NAME)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return mailBox;
    }

    @BeforeEach
    public void initTest() {
        mailBox = createEntity(em);
    }

    @Test
    @Transactional
    public void createMailBox() throws Exception {
        int databaseSizeBeforeCreate = mailBoxRepository.findAll().size();
        // Create the MailBox
        restMailBoxMockMvc.perform(post("/api/mail-boxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mailBox)))
            .andExpect(status().isCreated());

        // Validate the MailBox in the database
        List<MailBox> mailBoxList = mailBoxRepository.findAll();
        assertThat(mailBoxList).hasSize(databaseSizeBeforeCreate + 1);
        MailBox testMailBox = mailBoxList.get(mailBoxList.size() - 1);
        assertThat(testMailBox.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMailBox.getServer()).isEqualTo(DEFAULT_SERVER);
        assertThat(testMailBox.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testMailBox.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
        assertThat(testMailBox.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testMailBox.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createMailBoxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mailBoxRepository.findAll().size();

        // Create the MailBox with an existing ID
        mailBox.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMailBoxMockMvc.perform(post("/api/mail-boxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mailBox)))
            .andExpect(status().isBadRequest());

        // Validate the MailBox in the database
        List<MailBox> mailBoxList = mailBoxRepository.findAll();
        assertThat(mailBoxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMailBoxes() throws Exception {
        // Initialize the database
        mailBoxRepository.saveAndFlush(mailBox);

        // Get all the mailBoxList
        restMailBoxMockMvc.perform(get("/api/mail-boxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mailBox.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].server").value(hasItem(DEFAULT_SERVER)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getMailBox() throws Exception {
        // Initialize the database
        mailBoxRepository.saveAndFlush(mailBox);

        // Get the mailBox
        restMailBoxMockMvc.perform(get("/api/mail-boxes/{id}", mailBox.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mailBox.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.server").value(DEFAULT_SERVER))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingMailBox() throws Exception {
        // Get the mailBox
        restMailBoxMockMvc.perform(get("/api/mail-boxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMailBox() throws Exception {
        // Initialize the database
        mailBoxRepository.saveAndFlush(mailBox);

        int databaseSizeBeforeUpdate = mailBoxRepository.findAll().size();

        // Update the mailBox
        MailBox updatedMailBox = mailBoxRepository.findById(mailBox.getId()).get();
        // Disconnect from session so that the updates on updatedMailBox are not directly saved in db
        em.detach(updatedMailBox);
        updatedMailBox
            .name(UPDATED_NAME)
            .server(UPDATED_SERVER)
            .address(UPDATED_ADDRESS)
            .displayName(UPDATED_DISPLAY_NAME)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restMailBoxMockMvc.perform(put("/api/mail-boxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMailBox)))
            .andExpect(status().isOk());

        // Validate the MailBox in the database
        List<MailBox> mailBoxList = mailBoxRepository.findAll();
        assertThat(mailBoxList).hasSize(databaseSizeBeforeUpdate);
        MailBox testMailBox = mailBoxList.get(mailBoxList.size() - 1);
        assertThat(testMailBox.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMailBox.getServer()).isEqualTo(UPDATED_SERVER);
        assertThat(testMailBox.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testMailBox.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
        assertThat(testMailBox.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testMailBox.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingMailBox() throws Exception {
        int databaseSizeBeforeUpdate = mailBoxRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMailBoxMockMvc.perform(put("/api/mail-boxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mailBox)))
            .andExpect(status().isBadRequest());

        // Validate the MailBox in the database
        List<MailBox> mailBoxList = mailBoxRepository.findAll();
        assertThat(mailBoxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMailBox() throws Exception {
        // Initialize the database
        mailBoxRepository.saveAndFlush(mailBox);

        int databaseSizeBeforeDelete = mailBoxRepository.findAll().size();

        // Delete the mailBox
        restMailBoxMockMvc.perform(delete("/api/mail-boxes/{id}", mailBox.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MailBox> mailBoxList = mailBoxRepository.findAll();
        assertThat(mailBoxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
