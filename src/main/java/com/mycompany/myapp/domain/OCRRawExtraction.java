package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.Language;

import com.mycompany.myapp.domain.enumeration.DocumentType;

/**
 * A OCRRawExtraction.
 */
@Entity
@Table(name = "ocr_raw_extraction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OCRRawExtraction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "capturedfield_value")
    private String capturedfieldValue;

    @Column(name = "actual_field_value")
    private String actualFieldValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "accuracy")
    private String accuracy;

    @Column(name = "extractions")
    private String extractions;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(mappedBy = "oCRRawExtraction")
    @JsonIgnore
    private ModelInfo modelInfo;

    @ManyToOne
    @JsonIgnoreProperties(value = "oCRRawExtractions", allowSetters = true)
    private Invoice invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public OCRRawExtraction name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public OCRRawExtraction fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getCapturedfieldValue() {
        return capturedfieldValue;
    }

    public OCRRawExtraction capturedfieldValue(String capturedfieldValue) {
        this.capturedfieldValue = capturedfieldValue;
        return this;
    }

    public void setCapturedfieldValue(String capturedfieldValue) {
        this.capturedfieldValue = capturedfieldValue;
    }

    public String getActualFieldValue() {
        return actualFieldValue;
    }

    public OCRRawExtraction actualFieldValue(String actualFieldValue) {
        this.actualFieldValue = actualFieldValue;
        return this;
    }

    public void setActualFieldValue(String actualFieldValue) {
        this.actualFieldValue = actualFieldValue;
    }

    public Language getLanguage() {
        return language;
    }

    public OCRRawExtraction language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public OCRRawExtraction documentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public OCRRawExtraction accuracy(String accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getExtractions() {
        return extractions;
    }

    public OCRRawExtraction extractions(String extractions) {
        this.extractions = extractions;
        return this;
    }

    public void setExtractions(String extractions) {
        this.extractions = extractions;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public OCRRawExtraction createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public OCRRawExtraction createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public OCRRawExtraction modelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
        return this;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public OCRRawExtraction invoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OCRRawExtraction)) {
            return false;
        }
        return id != null && id.equals(((OCRRawExtraction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OCRRawExtraction{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fieldName='" + getFieldName() + "'" +
            ", capturedfieldValue='" + getCapturedfieldValue() + "'" +
            ", actualFieldValue='" + getActualFieldValue() + "'" +
            ", language='" + getLanguage() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", accuracy='" + getAccuracy() + "'" +
            ", extractions='" + getExtractions() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
