package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.NotificationInfo;
import com.mycompany.myapp.repository.NotificationInfoRepository;

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
 * Integration tests for the {@link NotificationInfoResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NotificationInfoResourceIT {

    private static final String DEFAULT_SENDER = "AAAAAAAAAA";
    private static final String UPDATED_SENDER = "BBBBBBBBBB";

    private static final String DEFAULT_RECEIVER = "AAAAAAAAAA";
    private static final String UPDATED_RECEIVER = "BBBBBBBBBB";

    private static final String DEFAULT_MODE = "AAAAAAAAAA";
    private static final String UPDATED_MODE = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private NotificationInfoRepository notificationInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotificationInfoMockMvc;

    private NotificationInfo notificationInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationInfo createEntity(EntityManager em) {
        NotificationInfo notificationInfo = new NotificationInfo()
            .sender(DEFAULT_SENDER)
            .receiver(DEFAULT_RECEIVER)
            .mode(DEFAULT_MODE)
            .message(DEFAULT_MESSAGE)
            .status(DEFAULT_STATUS)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return notificationInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotificationInfo createUpdatedEntity(EntityManager em) {
        NotificationInfo notificationInfo = new NotificationInfo()
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .mode(UPDATED_MODE)
            .message(UPDATED_MESSAGE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return notificationInfo;
    }

    @BeforeEach
    public void initTest() {
        notificationInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificationInfo() throws Exception {
        int databaseSizeBeforeCreate = notificationInfoRepository.findAll().size();
        // Create the NotificationInfo
        restNotificationInfoMockMvc.perform(post("/api/notification-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationInfo)))
            .andExpect(status().isCreated());

        // Validate the NotificationInfo in the database
        List<NotificationInfo> notificationInfoList = notificationInfoRepository.findAll();
        assertThat(notificationInfoList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationInfo testNotificationInfo = notificationInfoList.get(notificationInfoList.size() - 1);
        assertThat(testNotificationInfo.getSender()).isEqualTo(DEFAULT_SENDER);
        assertThat(testNotificationInfo.getReceiver()).isEqualTo(DEFAULT_RECEIVER);
        assertThat(testNotificationInfo.getMode()).isEqualTo(DEFAULT_MODE);
        assertThat(testNotificationInfo.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testNotificationInfo.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNotificationInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testNotificationInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createNotificationInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationInfoRepository.findAll().size();

        // Create the NotificationInfo with an existing ID
        notificationInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationInfoMockMvc.perform(post("/api/notification-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationInfo)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationInfo in the database
        List<NotificationInfo> notificationInfoList = notificationInfoRepository.findAll();
        assertThat(notificationInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNotificationInfos() throws Exception {
        // Initialize the database
        notificationInfoRepository.saveAndFlush(notificationInfo);

        // Get all the notificationInfoList
        restNotificationInfoMockMvc.perform(get("/api/notification-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].sender").value(hasItem(DEFAULT_SENDER)))
            .andExpect(jsonPath("$.[*].receiver").value(hasItem(DEFAULT_RECEIVER)))
            .andExpect(jsonPath("$.[*].mode").value(hasItem(DEFAULT_MODE)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getNotificationInfo() throws Exception {
        // Initialize the database
        notificationInfoRepository.saveAndFlush(notificationInfo);

        // Get the notificationInfo
        restNotificationInfoMockMvc.perform(get("/api/notification-infos/{id}", notificationInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notificationInfo.getId().intValue()))
            .andExpect(jsonPath("$.sender").value(DEFAULT_SENDER))
            .andExpect(jsonPath("$.receiver").value(DEFAULT_RECEIVER))
            .andExpect(jsonPath("$.mode").value(DEFAULT_MODE))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingNotificationInfo() throws Exception {
        // Get the notificationInfo
        restNotificationInfoMockMvc.perform(get("/api/notification-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificationInfo() throws Exception {
        // Initialize the database
        notificationInfoRepository.saveAndFlush(notificationInfo);

        int databaseSizeBeforeUpdate = notificationInfoRepository.findAll().size();

        // Update the notificationInfo
        NotificationInfo updatedNotificationInfo = notificationInfoRepository.findById(notificationInfo.getId()).get();
        // Disconnect from session so that the updates on updatedNotificationInfo are not directly saved in db
        em.detach(updatedNotificationInfo);
        updatedNotificationInfo
            .sender(UPDATED_SENDER)
            .receiver(UPDATED_RECEIVER)
            .mode(UPDATED_MODE)
            .message(UPDATED_MESSAGE)
            .status(UPDATED_STATUS)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restNotificationInfoMockMvc.perform(put("/api/notification-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNotificationInfo)))
            .andExpect(status().isOk());

        // Validate the NotificationInfo in the database
        List<NotificationInfo> notificationInfoList = notificationInfoRepository.findAll();
        assertThat(notificationInfoList).hasSize(databaseSizeBeforeUpdate);
        NotificationInfo testNotificationInfo = notificationInfoList.get(notificationInfoList.size() - 1);
        assertThat(testNotificationInfo.getSender()).isEqualTo(UPDATED_SENDER);
        assertThat(testNotificationInfo.getReceiver()).isEqualTo(UPDATED_RECEIVER);
        assertThat(testNotificationInfo.getMode()).isEqualTo(UPDATED_MODE);
        assertThat(testNotificationInfo.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testNotificationInfo.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNotificationInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testNotificationInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificationInfo() throws Exception {
        int databaseSizeBeforeUpdate = notificationInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationInfoMockMvc.perform(put("/api/notification-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificationInfo)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationInfo in the database
        List<NotificationInfo> notificationInfoList = notificationInfoRepository.findAll();
        assertThat(notificationInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotificationInfo() throws Exception {
        // Initialize the database
        notificationInfoRepository.saveAndFlush(notificationInfo);

        int databaseSizeBeforeDelete = notificationInfoRepository.findAll().size();

        // Delete the notificationInfo
        restNotificationInfoMockMvc.perform(delete("/api/notification-infos/{id}", notificationInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotificationInfo> notificationInfoList = notificationInfoRepository.findAll();
        assertThat(notificationInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
