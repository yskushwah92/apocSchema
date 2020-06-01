package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.mycompany.myapp.domain.enumeration.GRNType;

/**
 * A GRNDetails.
 */
@Entity
@Table(name = "grn_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GRNDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "grn_number")
    private String grnNumber;

    @Column(name = "grn_line_number")
    private String grnLineNumber;

    @Column(name = "grn_quantity")
    private Double grnQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "grn_type")
    private GRNType grnType;

    @Column(name = "delivery_note")
    private String deliveryNote;

    @Column(name = "is_open")
    private Boolean isOpen;

    @OneToOne(mappedBy = "gRNDetails")
    @JsonIgnore
    private InvoiceHeader invoiceHeader;

    @OneToOne(mappedBy = "gRNDetails")
    @JsonIgnore
    private PurchaseOrder purchaseOrder;

    @OneToOne(mappedBy = "gRNDetails")
    @JsonIgnore
    private InvoiceLineItem invoiceLineItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrnNumber() {
        return grnNumber;
    }

    public GRNDetails grnNumber(String grnNumber) {
        this.grnNumber = grnNumber;
        return this;
    }

    public void setGrnNumber(String grnNumber) {
        this.grnNumber = grnNumber;
    }

    public String getGrnLineNumber() {
        return grnLineNumber;
    }

    public GRNDetails grnLineNumber(String grnLineNumber) {
        this.grnLineNumber = grnLineNumber;
        return this;
    }

    public void setGrnLineNumber(String grnLineNumber) {
        this.grnLineNumber = grnLineNumber;
    }

    public Double getGrnQuantity() {
        return grnQuantity;
    }

    public GRNDetails grnQuantity(Double grnQuantity) {
        this.grnQuantity = grnQuantity;
        return this;
    }

    public void setGrnQuantity(Double grnQuantity) {
        this.grnQuantity = grnQuantity;
    }

    public GRNType getGrnType() {
        return grnType;
    }

    public GRNDetails grnType(GRNType grnType) {
        this.grnType = grnType;
        return this;
    }

    public void setGrnType(GRNType grnType) {
        this.grnType = grnType;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public GRNDetails deliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
        return this;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public Boolean isIsOpen() {
        return isOpen;
    }

    public GRNDetails isOpen(Boolean isOpen) {
        this.isOpen = isOpen;
        return this;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public GRNDetails invoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
        return this;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public GRNDetails purchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        return this;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public InvoiceLineItem getInvoiceLineItem() {
        return invoiceLineItem;
    }

    public GRNDetails invoiceLineItem(InvoiceLineItem invoiceLineItem) {
        this.invoiceLineItem = invoiceLineItem;
        return this;
    }

    public void setInvoiceLineItem(InvoiceLineItem invoiceLineItem) {
        this.invoiceLineItem = invoiceLineItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GRNDetails)) {
            return false;
        }
        return id != null && id.equals(((GRNDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GRNDetails{" +
            "id=" + getId() +
            ", grnNumber='" + getGrnNumber() + "'" +
            ", grnLineNumber='" + getGrnLineNumber() + "'" +
            ", grnQuantity=" + getGrnQuantity() +
            ", grnType='" + getGrnType() + "'" +
            ", deliveryNote='" + getDeliveryNote() + "'" +
            ", isOpen='" + isIsOpen() + "'" +
            "}";
    }
}
