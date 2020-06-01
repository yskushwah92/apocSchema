package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppApp;
import com.mycompany.myapp.domain.FileInfo;
import com.mycompany.myapp.repository.FileInfoRepository;

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

import com.mycompany.myapp.domain.enumeration.FileSource;
import com.mycompany.myapp.domain.enumeration.FileType;
import com.mycompany.myapp.domain.enumeration.DocumentType;
import com.mycompany.myapp.domain.enumeration.DocumentCategory;
/**
 * Integration tests for the {@link FileInfoResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FileInfoResourceIT {

    private static final FileSource DEFAULT_SOURCE = FileSource.MAIL;
    private static final FileSource UPDATED_SOURCE = FileSource.FAX;

    private static final String DEFAULT_SOURCE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER_MAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_MAIL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FILE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_EXTENSION = "AAAAAAAAAA";
    private static final String UPDATED_FILE_EXTENSION = "BBBBBBBBBB";

    private static final FileType DEFAULT_TYPE = FileType.PDF;
    private static final FileType UPDATED_TYPE = FileType.DOC;

    private static final DocumentType DEFAULT_DOCUMENT_TYPE = DocumentType.INVOICE;
    private static final DocumentType UPDATED_DOCUMENT_TYPE = DocumentType.PURCHASE_ORDER;

    private static final DocumentCategory DEFAULT_DOCUMENT_CATEGORY = DocumentCategory.INVOICE;
    private static final DocumentCategory UPDATED_DOCUMENT_CATEGORY = DocumentCategory.PO;

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_PROTOCOL = "AAAAAAAAAA";
    private static final String UPDATED_PROTOCOL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFileInfoMockMvc;

    private FileInfo fileInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileInfo createEntity(EntityManager em) {
        FileInfo fileInfo = new FileInfo()
            .source(DEFAULT_SOURCE)
            .sourceDetails(DEFAULT_SOURCE_DETAILS)
            .senderMailId(DEFAULT_SENDER_MAIL_ID)
            .filePath(DEFAULT_FILE_PATH)
            .fileName(DEFAULT_FILE_NAME)
            .reason(DEFAULT_REASON)
            .fileExtension(DEFAULT_FILE_EXTENSION)
            .type(DEFAULT_TYPE)
            .documentType(DEFAULT_DOCUMENT_TYPE)
            .documentCategory(DEFAULT_DOCUMENT_CATEGORY)
            .clientId(DEFAULT_CLIENT_ID)
            .clientDetails(DEFAULT_CLIENT_DETAILS)
            .protocol(DEFAULT_PROTOCOL)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY);
        return fileInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileInfo createUpdatedEntity(EntityManager em) {
        FileInfo fileInfo = new FileInfo()
            .source(UPDATED_SOURCE)
            .sourceDetails(UPDATED_SOURCE_DETAILS)
            .senderMailId(UPDATED_SENDER_MAIL_ID)
            .filePath(UPDATED_FILE_PATH)
            .fileName(UPDATED_FILE_NAME)
            .reason(UPDATED_REASON)
            .fileExtension(UPDATED_FILE_EXTENSION)
            .type(UPDATED_TYPE)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentCategory(UPDATED_DOCUMENT_CATEGORY)
            .clientId(UPDATED_CLIENT_ID)
            .clientDetails(UPDATED_CLIENT_DETAILS)
            .protocol(UPDATED_PROTOCOL)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);
        return fileInfo;
    }

    @BeforeEach
    public void initTest() {
        fileInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileInfo() throws Exception {
        int databaseSizeBeforeCreate = fileInfoRepository.findAll().size();
        // Create the FileInfo
        restFileInfoMockMvc.perform(post("/api/file-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileInfo)))
            .andExpect(status().isCreated());

        // Validate the FileInfo in the database
        List<FileInfo> fileInfoList = fileInfoRepository.findAll();
        assertThat(fileInfoList).hasSize(databaseSizeBeforeCreate + 1);
        FileInfo testFileInfo = fileInfoList.get(fileInfoList.size() - 1);
        assertThat(testFileInfo.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testFileInfo.getSourceDetails()).isEqualTo(DEFAULT_SOURCE_DETAILS);
        assertThat(testFileInfo.getSenderMailId()).isEqualTo(DEFAULT_SENDER_MAIL_ID);
        assertThat(testFileInfo.getFilePath()).isEqualTo(DEFAULT_FILE_PATH);
        assertThat(testFileInfo.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testFileInfo.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testFileInfo.getFileExtension()).isEqualTo(DEFAULT_FILE_EXTENSION);
        assertThat(testFileInfo.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFileInfo.getDocumentType()).isEqualTo(DEFAULT_DOCUMENT_TYPE);
        assertThat(testFileInfo.getDocumentCategory()).isEqualTo(DEFAULT_DOCUMENT_CATEGORY);
        assertThat(testFileInfo.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testFileInfo.getClientDetails()).isEqualTo(DEFAULT_CLIENT_DETAILS);
        assertThat(testFileInfo.getProtocol()).isEqualTo(DEFAULT_PROTOCOL);
        assertThat(testFileInfo.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testFileInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    public void createFileInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileInfoRepository.findAll().size();

        // Create the FileInfo with an existing ID
        fileInfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileInfoMockMvc.perform(post("/api/file-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileInfo)))
            .andExpect(status().isBadRequest());

        // Validate the FileInfo in the database
        List<FileInfo> fileInfoList = fileInfoRepository.findAll();
        assertThat(fileInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFileInfos() throws Exception {
        // Initialize the database
        fileInfoRepository.saveAndFlush(fileInfo);

        // Get all the fileInfoList
        restFileInfoMockMvc.perform(get("/api/file-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].sourceDetails").value(hasItem(DEFAULT_SOURCE_DETAILS)))
            .andExpect(jsonPath("$.[*].senderMailId").value(hasItem(DEFAULT_SENDER_MAIL_ID)))
            .andExpect(jsonPath("$.[*].filePath").value(hasItem(DEFAULT_FILE_PATH)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)))
            .andExpect(jsonPath("$.[*].fileExtension").value(hasItem(DEFAULT_FILE_EXTENSION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].documentType").value(hasItem(DEFAULT_DOCUMENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].documentCategory").value(hasItem(DEFAULT_DOCUMENT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].clientDetails").value(hasItem(DEFAULT_CLIENT_DETAILS)))
            .andExpect(jsonPath("$.[*].protocol").value(hasItem(DEFAULT_PROTOCOL)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)));
    }
    
    @Test
    @Transactional
    public void getFileInfo() throws Exception {
        // Initialize the database
        fileInfoRepository.saveAndFlush(fileInfo);

        // Get the fileInfo
        restFileInfoMockMvc.perform(get("/api/file-infos/{id}", fileInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fileInfo.getId().intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.sourceDetails").value(DEFAULT_SOURCE_DETAILS))
            .andExpect(jsonPath("$.senderMailId").value(DEFAULT_SENDER_MAIL_ID))
            .andExpect(jsonPath("$.filePath").value(DEFAULT_FILE_PATH))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON))
            .andExpect(jsonPath("$.fileExtension").value(DEFAULT_FILE_EXTENSION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.documentType").value(DEFAULT_DOCUMENT_TYPE.toString()))
            .andExpect(jsonPath("$.documentCategory").value(DEFAULT_DOCUMENT_CATEGORY.toString()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.clientDetails").value(DEFAULT_CLIENT_DETAILS))
            .andExpect(jsonPath("$.protocol").value(DEFAULT_PROTOCOL))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY));
    }
    @Test
    @Transactional
    public void getNonExistingFileInfo() throws Exception {
        // Get the fileInfo
        restFileInfoMockMvc.perform(get("/api/file-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileInfo() throws Exception {
        // Initialize the database
        fileInfoRepository.saveAndFlush(fileInfo);

        int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();

        // Update the fileInfo
        FileInfo updatedFileInfo = fileInfoRepository.findById(fileInfo.getId()).get();
        // Disconnect from session so that the updates on updatedFileInfo are not directly saved in db
        em.detach(updatedFileInfo);
        updatedFileInfo
            .source(UPDATED_SOURCE)
            .sourceDetails(UPDATED_SOURCE_DETAILS)
            .senderMailId(UPDATED_SENDER_MAIL_ID)
            .filePath(UPDATED_FILE_PATH)
            .fileName(UPDATED_FILE_NAME)
            .reason(UPDATED_REASON)
            .fileExtension(UPDATED_FILE_EXTENSION)
            .type(UPDATED_TYPE)
            .documentType(UPDATED_DOCUMENT_TYPE)
            .documentCategory(UPDATED_DOCUMENT_CATEGORY)
            .clientId(UPDATED_CLIENT_ID)
            .clientDetails(UPDATED_CLIENT_DETAILS)
            .protocol(UPDATED_PROTOCOL)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restFileInfoMockMvc.perform(put("/api/file-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFileInfo)))
            .andExpect(status().isOk());

        // Validate the FileInfo in the database
        List<FileInfo> fileInfoList = fileInfoRepository.findAll();
        assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);
        FileInfo testFileInfo = fileInfoList.get(fileInfoList.size() - 1);
        assertThat(testFileInfo.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testFileInfo.getSourceDetails()).isEqualTo(UPDATED_SOURCE_DETAILS);
        assertThat(testFileInfo.getSenderMailId()).isEqualTo(UPDATED_SENDER_MAIL_ID);
        assertThat(testFileInfo.getFilePath()).isEqualTo(UPDATED_FILE_PATH);
        assertThat(testFileInfo.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testFileInfo.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testFileInfo.getFileExtension()).isEqualTo(UPDATED_FILE_EXTENSION);
        assertThat(testFileInfo.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFileInfo.getDocumentType()).isEqualTo(UPDATED_DOCUMENT_TYPE);
        assertThat(testFileInfo.getDocumentCategory()).isEqualTo(UPDATED_DOCUMENT_CATEGORY);
        assertThat(testFileInfo.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testFileInfo.getClientDetails()).isEqualTo(UPDATED_CLIENT_DETAILS);
        assertThat(testFileInfo.getProtocol()).isEqualTo(UPDATED_PROTOCOL);
        assertThat(testFileInfo.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testFileInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingFileInfo() throws Exception {
        int databaseSizeBeforeUpdate = fileInfoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileInfoMockMvc.perform(put("/api/file-infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileInfo)))
            .andExpect(status().isBadRequest());

        // Validate the FileInfo in the database
        List<FileInfo> fileInfoList = fileInfoRepository.findAll();
        assertThat(fileInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFileInfo() throws Exception {
        // Initialize the database
        fileInfoRepository.saveAndFlush(fileInfo);

        int databaseSizeBeforeDelete = fileInfoRepository.findAll().size();

        // Delete the fileInfo
        restFileInfoMockMvc.perform(delete("/api/file-infos/{id}", fileInfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FileInfo> fileInfoList = fileInfoRepository.findAll();
        assertThat(fileInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
