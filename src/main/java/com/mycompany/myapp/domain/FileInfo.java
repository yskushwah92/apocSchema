package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.FileSource;

import com.mycompany.myapp.domain.enumeration.FileType;

import com.mycompany.myapp.domain.enumeration.DocumentType;

import com.mycompany.myapp.domain.enumeration.DocumentCategory;

/**
 * A FileInfo.
 */
@Entity
@Table(name = "file_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private FileSource source;

    @Column(name = "source_details")
    private String sourceDetails;

    @Column(name = "sender_mail_id")
    private String senderMailId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "reason")
    private String reason;

    @Column(name = "file_extension")
    private String fileExtension;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FileType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_category")
    private DocumentCategory documentCategory;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_details")
    private String clientDetails;

    @Column(name = "protocol")
    private String protocol;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToMany(mappedBy = "fileInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FileInfo> fileInfos = new HashSet<>();

    @OneToMany(mappedBy = "fileInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FileSourceDetails> fileSourceDetails = new HashSet<>();

    @OneToOne(mappedBy = "fileInfo")
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne
    @JsonIgnoreProperties(value = "fileInfos", allowSetters = true)
    private FileInfo fileInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FileSource getSource() {
        return source;
    }

    public FileInfo source(FileSource source) {
        this.source = source;
        return this;
    }

    public void setSource(FileSource source) {
        this.source = source;
    }

    public String getSourceDetails() {
        return sourceDetails;
    }

    public FileInfo sourceDetails(String sourceDetails) {
        this.sourceDetails = sourceDetails;
        return this;
    }

    public void setSourceDetails(String sourceDetails) {
        this.sourceDetails = sourceDetails;
    }

    public String getSenderMailId() {
        return senderMailId;
    }

    public FileInfo senderMailId(String senderMailId) {
        this.senderMailId = senderMailId;
        return this;
    }

    public void setSenderMailId(String senderMailId) {
        this.senderMailId = senderMailId;
    }

    public String getFilePath() {
        return filePath;
    }

    public FileInfo filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public FileInfo fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getReason() {
        return reason;
    }

    public FileInfo reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public FileInfo fileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public FileType getType() {
        return type;
    }

    public FileInfo type(FileType type) {
        this.type = type;
        return this;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public FileInfo documentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public DocumentCategory getDocumentCategory() {
        return documentCategory;
    }

    public FileInfo documentCategory(DocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
        return this;
    }

    public void setDocumentCategory(DocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
    }

    public String getClientId() {
        return clientId;
    }

    public FileInfo clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientDetails() {
        return clientDetails;
    }

    public FileInfo clientDetails(String clientDetails) {
        this.clientDetails = clientDetails;
        return this;
    }

    public void setClientDetails(String clientDetails) {
        this.clientDetails = clientDetails;
    }

    public String getProtocol() {
        return protocol;
    }

    public FileInfo protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public FileInfo createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public FileInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Set<FileInfo> getFileInfos() {
        return fileInfos;
    }

    public FileInfo fileInfos(Set<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
        return this;
    }

    public FileInfo addFileInfo(FileInfo fileInfo) {
        this.fileInfos.add(fileInfo);
        fileInfo.setFileInfo(this);
        return this;
    }

    public FileInfo removeFileInfo(FileInfo fileInfo) {
        this.fileInfos.remove(fileInfo);
        fileInfo.setFileInfo(null);
        return this;
    }

    public void setFileInfos(Set<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    public Set<FileSourceDetails> getFileSourceDetails() {
        return fileSourceDetails;
    }

    public FileInfo fileSourceDetails(Set<FileSourceDetails> fileSourceDetails) {
        this.fileSourceDetails = fileSourceDetails;
        return this;
    }

    public FileInfo addFileSourceDetails(FileSourceDetails fileSourceDetails) {
        this.fileSourceDetails.add(fileSourceDetails);
        fileSourceDetails.setFileInfo(this);
        return this;
    }

    public FileInfo removeFileSourceDetails(FileSourceDetails fileSourceDetails) {
        this.fileSourceDetails.remove(fileSourceDetails);
        fileSourceDetails.setFileInfo(null);
        return this;
    }

    public void setFileSourceDetails(Set<FileSourceDetails> fileSourceDetails) {
        this.fileSourceDetails = fileSourceDetails;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public FileInfo invoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public FileInfo fileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
        return this;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileInfo)) {
            return false;
        }
        return id != null && id.equals(((FileInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileInfo{" +
            "id=" + getId() +
            ", source='" + getSource() + "'" +
            ", sourceDetails='" + getSourceDetails() + "'" +
            ", senderMailId='" + getSenderMailId() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", reason='" + getReason() + "'" +
            ", fileExtension='" + getFileExtension() + "'" +
            ", type='" + getType() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", documentCategory='" + getDocumentCategory() + "'" +
            ", clientId='" + getClientId() + "'" +
            ", clientDetails='" + getClientDetails() + "'" +
            ", protocol='" + getProtocol() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
