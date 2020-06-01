package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.Currency;

/**
 * A PurchaseOrderLine.
 */
@Entity
@Table(name = "purchase_order_line")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PurchaseOrderLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "article_no")
    private String articleNo;

    @Column(name = "qty")
    private Double qty;

    @Column(name = "received_qty")
    private Double receivedQty;

    @Column(name = "billed_qty")
    private Double billedQty;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "line_amount")
    private Double lineAmount;

    @Column(name = "line_amount_excl_tax")
    private Double lineAmountExclTax;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "cost_centre")
    private String costCentre;

    @Column(name = "cost_centreid")
    private String costCentreid;

    @Column(name = "article_description")
    private String articleDescription;

    @Column(name = "delivery_receipt_no")
    private String deliveryReceiptNo;

    @Column(name = "delivery_date")
    private ZonedDateTime deliveryDate;

    @Column(name = "hsn_code")
    private String hsnCode;

    @Column(name = "tolerance")
    private String tolerance;

    @Column(name = "tolerance_price")
    private Double tolerancePrice;

    @Column(name = "receipt_reqd")
    private Boolean receiptReqd;

    @Column(name = "accepted_qty")
    private Double acceptedQty;

    @Column(name = "amount_received")
    private Double amountReceived;

    @Column(name = "available_qty")
    private Double availableQty;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private Organization organization;

    @OneToOne
    @JoinColumn(unique = true)
    private Vendor vendor;

    @OneToOne
    @JoinColumn(unique = true)
    private GLAccountDetails gLAccountDetails;

    @OneToOne(mappedBy = "purchaseOrderLine")
    @JsonIgnore
    private InvoiceLineItem invoiceLineItem;

    @ManyToOne
    @JsonIgnoreProperties(value = "purchaseOrderLines", allowSetters = true)
    private PurchaseOrder purchaseOrder;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public PurchaseOrderLine country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArticleNo() {
        return articleNo;
    }

    public PurchaseOrderLine articleNo(String articleNo) {
        this.articleNo = articleNo;
        return this;
    }

    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo;
    }

    public Double getQty() {
        return qty;
    }

    public PurchaseOrderLine qty(Double qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getReceivedQty() {
        return receivedQty;
    }

    public PurchaseOrderLine receivedQty(Double receivedQty) {
        this.receivedQty = receivedQty;
        return this;
    }

    public void setReceivedQty(Double receivedQty) {
        this.receivedQty = receivedQty;
    }

    public Double getBilledQty() {
        return billedQty;
    }

    public PurchaseOrderLine billedQty(Double billedQty) {
        this.billedQty = billedQty;
        return this;
    }

    public void setBilledQty(Double billedQty) {
        this.billedQty = billedQty;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public PurchaseOrderLine unitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
        return this;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Currency getCurrency() {
        return currency;
    }

    public PurchaseOrderLine currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getLineAmount() {
        return lineAmount;
    }

    public PurchaseOrderLine lineAmount(Double lineAmount) {
        this.lineAmount = lineAmount;
        return this;
    }

    public void setLineAmount(Double lineAmount) {
        this.lineAmount = lineAmount;
    }

    public Double getLineAmountExclTax() {
        return lineAmountExclTax;
    }

    public PurchaseOrderLine lineAmountExclTax(Double lineAmountExclTax) {
        this.lineAmountExclTax = lineAmountExclTax;
        return this;
    }

    public void setLineAmountExclTax(Double lineAmountExclTax) {
        this.lineAmountExclTax = lineAmountExclTax;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public PurchaseOrderLine unitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCostCentre() {
        return costCentre;
    }

    public PurchaseOrderLine costCentre(String costCentre) {
        this.costCentre = costCentre;
        return this;
    }

    public void setCostCentre(String costCentre) {
        this.costCentre = costCentre;
    }

    public String getCostCentreid() {
        return costCentreid;
    }

    public PurchaseOrderLine costCentreid(String costCentreid) {
        this.costCentreid = costCentreid;
        return this;
    }

    public void setCostCentreid(String costCentreid) {
        this.costCentreid = costCentreid;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public PurchaseOrderLine articleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
        return this;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getDeliveryReceiptNo() {
        return deliveryReceiptNo;
    }

    public PurchaseOrderLine deliveryReceiptNo(String deliveryReceiptNo) {
        this.deliveryReceiptNo = deliveryReceiptNo;
        return this;
    }

    public void setDeliveryReceiptNo(String deliveryReceiptNo) {
        this.deliveryReceiptNo = deliveryReceiptNo;
    }

    public ZonedDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public PurchaseOrderLine deliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public void setDeliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public PurchaseOrderLine hsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
        return this;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getTolerance() {
        return tolerance;
    }

    public PurchaseOrderLine tolerance(String tolerance) {
        this.tolerance = tolerance;
        return this;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    public Double getTolerancePrice() {
        return tolerancePrice;
    }

    public PurchaseOrderLine tolerancePrice(Double tolerancePrice) {
        this.tolerancePrice = tolerancePrice;
        return this;
    }

    public void setTolerancePrice(Double tolerancePrice) {
        this.tolerancePrice = tolerancePrice;
    }

    public Boolean isReceiptReqd() {
        return receiptReqd;
    }

    public PurchaseOrderLine receiptReqd(Boolean receiptReqd) {
        this.receiptReqd = receiptReqd;
        return this;
    }

    public void setReceiptReqd(Boolean receiptReqd) {
        this.receiptReqd = receiptReqd;
    }

    public Double getAcceptedQty() {
        return acceptedQty;
    }

    public PurchaseOrderLine acceptedQty(Double acceptedQty) {
        this.acceptedQty = acceptedQty;
        return this;
    }

    public void setAcceptedQty(Double acceptedQty) {
        this.acceptedQty = acceptedQty;
    }

    public Double getAmountReceived() {
        return amountReceived;
    }

    public PurchaseOrderLine amountReceived(Double amountReceived) {
        this.amountReceived = amountReceived;
        return this;
    }

    public void setAmountReceived(Double amountReceived) {
        this.amountReceived = amountReceived;
    }

    public Double getAvailableQty() {
        return availableQty;
    }

    public PurchaseOrderLine availableQty(Double availableQty) {
        this.availableQty = availableQty;
        return this;
    }

    public void setAvailableQty(Double availableQty) {
        this.availableQty = availableQty;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public PurchaseOrderLine createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PurchaseOrderLine createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Organization getOrganization() {
        return organization;
    }

    public PurchaseOrderLine organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public PurchaseOrderLine vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public GLAccountDetails getGLAccountDetails() {
        return gLAccountDetails;
    }

    public PurchaseOrderLine gLAccountDetails(GLAccountDetails gLAccountDetails) {
        this.gLAccountDetails = gLAccountDetails;
        return this;
    }

    public void setGLAccountDetails(GLAccountDetails gLAccountDetails) {
        this.gLAccountDetails = gLAccountDetails;
    }

    public InvoiceLineItem getInvoiceLineItem() {
        return invoiceLineItem;
    }

    public PurchaseOrderLine invoiceLineItem(InvoiceLineItem invoiceLineItem) {
        this.invoiceLineItem = invoiceLineItem;
        return this;
    }

    public void setInvoiceLineItem(InvoiceLineItem invoiceLineItem) {
        this.invoiceLineItem = invoiceLineItem;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public PurchaseOrderLine purchaseOrder(PurchaseOrder purchaseOrder) {
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
        if (!(o instanceof PurchaseOrderLine)) {
            return false;
        }
        return id != null && id.equals(((PurchaseOrderLine) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderLine{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", articleNo='" + getArticleNo() + "'" +
            ", qty=" + getQty() +
            ", receivedQty=" + getReceivedQty() +
            ", billedQty=" + getBilledQty() +
            ", unitOfMeasurement='" + getUnitOfMeasurement() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", lineAmount=" + getLineAmount() +
            ", lineAmountExclTax=" + getLineAmountExclTax() +
            ", unitPrice=" + getUnitPrice() +
            ", costCentre='" + getCostCentre() + "'" +
            ", costCentreid='" + getCostCentreid() + "'" +
            ", articleDescription='" + getArticleDescription() + "'" +
            ", deliveryReceiptNo='" + getDeliveryReceiptNo() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", hsnCode='" + getHsnCode() + "'" +
            ", tolerance='" + getTolerance() + "'" +
            ", tolerancePrice=" + getTolerancePrice() +
            ", receiptReqd='" + isReceiptReqd() + "'" +
            ", acceptedQty=" + getAcceptedQty() +
            ", amountReceived=" + getAmountReceived() +
            ", availableQty=" + getAvailableQty() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
