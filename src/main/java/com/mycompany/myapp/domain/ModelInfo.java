package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ModelInfo.
 */
@Entity
@Table(name = "model_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModelInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "version")
    private String version;

    @Column(name = "location")
    private String location;

    @Column(name = "execution_script")
    private String executionScript;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private OCRRawExtraction oCRRawExtraction;

    @OneToOne(mappedBy = "modelInfo")
    @JsonIgnore
    private Invoice invoice;

    @OneToOne(mappedBy = "modelInfo")
    @JsonIgnore
    private OCRExtractionEngineInfo oCRExtractionEngineInfo;

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

    public ModelInfo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public ModelInfo version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLocation() {
        return location;
    }

    public ModelInfo location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExecutionScript() {
        return executionScript;
    }

    public ModelInfo executionScript(String executionScript) {
        this.executionScript = executionScript;
        return this;
    }

    public void setExecutionScript(String executionScript) {
        this.executionScript = executionScript;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ModelInfo createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ModelInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public OCRRawExtraction getOCRRawExtraction() {
        return oCRRawExtraction;
    }

    public ModelInfo oCRRawExtraction(OCRRawExtraction oCRRawExtraction) {
        this.oCRRawExtraction = oCRRawExtraction;
        return this;
    }

    public void setOCRRawExtraction(OCRRawExtraction oCRRawExtraction) {
        this.oCRRawExtraction = oCRRawExtraction;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public ModelInfo invoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public OCRExtractionEngineInfo getOCRExtractionEngineInfo() {
        return oCRExtractionEngineInfo;
    }

    public ModelInfo oCRExtractionEngineInfo(OCRExtractionEngineInfo oCRExtractionEngineInfo) {
        this.oCRExtractionEngineInfo = oCRExtractionEngineInfo;
        return this;
    }

    public void setOCRExtractionEngineInfo(OCRExtractionEngineInfo oCRExtractionEngineInfo) {
        this.oCRExtractionEngineInfo = oCRExtractionEngineInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModelInfo)) {
            return false;
        }
        return id != null && id.equals(((ModelInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModelInfo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", version='" + getVersion() + "'" +
            ", location='" + getLocation() + "'" +
            ", executionScript='" + getExecutionScript() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
