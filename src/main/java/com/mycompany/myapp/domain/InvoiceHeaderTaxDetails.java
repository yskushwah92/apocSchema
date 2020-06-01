package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A InvoiceHeaderTaxDetails.
 */
@Entity
@Table(name = "invoice_header_tax_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InvoiceHeaderTaxDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "tax_description")
    private String taxDescription;

    @Column(name = "tax_display")
    private String taxDisplay;

    @Column(name = "wht_applicable")
    private String whtApplicable;

    @Column(name = "wht_grp_id")
    private String whtGrpId;

    @Column(name = "wht_type")
    private String whtType;

    @Column(name = "wht_code")
    private String whtCode;

    @Column(name = "wht_amount")
    private Double whtAmount;

    @Column(name = "unplanned_delivery_tax_rate")
    private Double unplannedDeliveryTaxRate;

    @Column(name = "unplanned_delivery_tax_amount")
    private Double unplannedDeliveryTaxAmount;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public InvoiceHeaderTaxDetails taxCode(String taxCode) {
        this.taxCode = taxCode;
        return this;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public InvoiceHeaderTaxDetails taxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public InvoiceHeaderTaxDetails taxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
        return this;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxDescription() {
        return taxDescription;
    }

    public InvoiceHeaderTaxDetails taxDescription(String taxDescription) {
        this.taxDescription = taxDescription;
        return this;
    }

    public void setTaxDescription(String taxDescription) {
        this.taxDescription = taxDescription;
    }

    public String getTaxDisplay() {
        return taxDisplay;
    }

    public InvoiceHeaderTaxDetails taxDisplay(String taxDisplay) {
        this.taxDisplay = taxDisplay;
        return this;
    }

    public void setTaxDisplay(String taxDisplay) {
        this.taxDisplay = taxDisplay;
    }

    public String getWhtApplicable() {
        return whtApplicable;
    }

    public InvoiceHeaderTaxDetails whtApplicable(String whtApplicable) {
        this.whtApplicable = whtApplicable;
        return this;
    }

    public void setWhtApplicable(String whtApplicable) {
        this.whtApplicable = whtApplicable;
    }

    public String getWhtGrpId() {
        return whtGrpId;
    }

    public InvoiceHeaderTaxDetails whtGrpId(String whtGrpId) {
        this.whtGrpId = whtGrpId;
        return this;
    }

    public void setWhtGrpId(String whtGrpId) {
        this.whtGrpId = whtGrpId;
    }

    public String getWhtType() {
        return whtType;
    }

    public InvoiceHeaderTaxDetails whtType(String whtType) {
        this.whtType = whtType;
        return this;
    }

    public void setWhtType(String whtType) {
        this.whtType = whtType;
    }

    public String getWhtCode() {
        return whtCode;
    }

    public InvoiceHeaderTaxDetails whtCode(String whtCode) {
        this.whtCode = whtCode;
        return this;
    }

    public void setWhtCode(String whtCode) {
        this.whtCode = whtCode;
    }

    public Double getWhtAmount() {
        return whtAmount;
    }

    public InvoiceHeaderTaxDetails whtAmount(Double whtAmount) {
        this.whtAmount = whtAmount;
        return this;
    }

    public void setWhtAmount(Double whtAmount) {
        this.whtAmount = whtAmount;
    }

    public Double getUnplannedDeliveryTaxRate() {
        return unplannedDeliveryTaxRate;
    }

    public InvoiceHeaderTaxDetails unplannedDeliveryTaxRate(Double unplannedDeliveryTaxRate) {
        this.unplannedDeliveryTaxRate = unplannedDeliveryTaxRate;
        return this;
    }

    public void setUnplannedDeliveryTaxRate(Double unplannedDeliveryTaxRate) {
        this.unplannedDeliveryTaxRate = unplannedDeliveryTaxRate;
    }

    public Double getUnplannedDeliveryTaxAmount() {
        return unplannedDeliveryTaxAmount;
    }

    public InvoiceHeaderTaxDetails unplannedDeliveryTaxAmount(Double unplannedDeliveryTaxAmount) {
        this.unplannedDeliveryTaxAmount = unplannedDeliveryTaxAmount;
        return this;
    }

    public void setUnplannedDeliveryTaxAmount(Double unplannedDeliveryTaxAmount) {
        this.unplannedDeliveryTaxAmount = unplannedDeliveryTaxAmount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceHeaderTaxDetails)) {
            return false;
        }
        return id != null && id.equals(((InvoiceHeaderTaxDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceHeaderTaxDetails{" +
            "id=" + getId() +
            ", taxCode='" + getTaxCode() + "'" +
            ", taxRate=" + getTaxRate() +
            ", taxAmount=" + getTaxAmount() +
            ", taxDescription='" + getTaxDescription() + "'" +
            ", taxDisplay='" + getTaxDisplay() + "'" +
            ", whtApplicable='" + getWhtApplicable() + "'" +
            ", whtGrpId='" + getWhtGrpId() + "'" +
            ", whtType='" + getWhtType() + "'" +
            ", whtCode='" + getWhtCode() + "'" +
            ", whtAmount=" + getWhtAmount() +
            ", unplannedDeliveryTaxRate=" + getUnplannedDeliveryTaxRate() +
            ", unplannedDeliveryTaxAmount=" + getUnplannedDeliveryTaxAmount() +
            "}";
    }
}
