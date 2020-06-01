package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.POStatus;

/**
 * A PurchaseOrderHeader.
 */
@Entity
@Table(name = "purchase_order_header")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PurchaseOrderHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "type")
    private String type;

    @Column(name = "requested_by")
    private String requestedBy;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "receipt_required")
    private Boolean receiptRequired;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private POStatus status;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(mappedBy = "purchaseOrderHeader")
    @JsonIgnore
    private PurchaseOrder purchaseOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public PurchaseOrderHeader serialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Double getAmount() {
        return amount;
    }

    public PurchaseOrderHeader amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public PurchaseOrderHeader type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public PurchaseOrderHeader requestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
        return this;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public PurchaseOrderHeader creationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean isReceiptRequired() {
        return receiptRequired;
    }

    public PurchaseOrderHeader receiptRequired(Boolean receiptRequired) {
        this.receiptRequired = receiptRequired;
        return this;
    }

    public void setReceiptRequired(Boolean receiptRequired) {
        this.receiptRequired = receiptRequired;
    }

    public POStatus getStatus() {
        return status;
    }

    public PurchaseOrderHeader status(POStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(POStatus status) {
        this.status = status;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public PurchaseOrderHeader companyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public PurchaseOrderHeader currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public PurchaseOrderHeader createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PurchaseOrderHeader createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public PurchaseOrderHeader purchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        return this;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderHeader)) {
            return false;
        }
        return id != null && id.equals(((PurchaseOrderHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderHeader{" +
            "id=" + getId() +
            ", serialNo='" + getSerialNo() + "'" +
            ", amount=" + getAmount() +
            ", type='" + getType() + "'" +
            ", requestedBy='" + getRequestedBy() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", receiptRequired='" + isReceiptRequired() + "'" +
            ", status='" + getStatus() + "'" +
            ", companyCode='" + getCompanyCode() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
