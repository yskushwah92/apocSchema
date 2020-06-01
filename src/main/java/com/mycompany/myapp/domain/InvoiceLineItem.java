package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A InvoiceLineItem.
 */
@Entity
@Table(name = "invoice_line_item")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InvoiceLineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "expense_type")
    private String expenseType;

    @Column(name = "distribution_id")
    private String distributionId;

    @Column(name = "line_number")
    private Double lineNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "tax_desc")
    private String taxDesc;

    @Column(name = "tax_amount")
    private Double taxAmount;

    @Column(name = "line_amount")
    private Double lineAmount;

    @Column(name = "gross_line_amount")
    private Double grossLineAmount;

    @Column(name = "confirmer_id")
    private String confirmerId;

    @Column(name = "po_request_by")
    private String poRequestBy;

    @Column(name = "uom")
    private String uom;

    @Column(name = "debit_credit_indicator")
    private String debitCreditIndicator;

    @Column(name = "available_qty")
    private Double availableQty;

    @Column(name = "additional_cost_per_unit")
    private Double additionalCostPerUnit;

    @Column(name = "tax_base_amount")
    private Double taxBaseAmount;

    @Column(name = "invoice_qty")
    private Double invoiceQty;

    @Column(name = "invoice_unit_price")
    private Double invoiceUnitPrice;

    @Column(name = "vat_tax_rate")
    private Double vatTaxRate;

    @Column(name = "confirmer_name")
    private String confirmerName;

    @Column(name = "available_expectedvalue")
    private Double availableExpectedvalue;

    @Column(name = "available_limit_value")
    private String availableLimitValue;

    @Column(name = "expected_value")
    private Double expectedValue;

    @Column(name = "actual_limit")
    private Double actualLimit;

    @Column(name = "overall_limit")
    private Double overallLimit;

    @Column(name = "limit_order_expiry_date")
    private ZonedDateTime limitOrderExpiryDate;

    @Column(name = "limit_order_start_date")
    private ZonedDateTime limitOrderStartDate;

    @Column(name = "vat_tax_code")
    private String vatTaxCode;

    @Column(name = "internal_order_statistical")
    private String internalOrderStatistical;

    @Column(name = "debit_credit_value")
    private Double debitCreditValue;

    @Column(name = "delivery_note")
    private String deliveryNote;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private PurchaseOrderLine purchaseOrderLine;

    @OneToOne
    @JoinColumn(unique = true)
    private GLAccountDetails gLAccountDetails;

    @OneToOne
    @JoinColumn(unique = true)
    private User technicalApprover;

    @OneToOne
    @JoinColumn(unique = true)
    private ShippingLocation shippingLocation;

    @OneToOne
    @JoinColumn(unique = true)
    private Product product;

    @OneToOne
    @JoinColumn(unique = true)
    private GRNDetails gRNDetails;

    @OneToMany(mappedBy = "invoiceLineItem")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CaseStatusDetails> caseStatusDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "invoiceLineItems", allowSetters = true)
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

    public InvoiceLineItem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public InvoiceLineItem expenseType(String expenseType) {
        this.expenseType = expenseType;
        return this;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getDistributionId() {
        return distributionId;
    }

    public InvoiceLineItem distributionId(String distributionId) {
        this.distributionId = distributionId;
        return this;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public Double getLineNumber() {
        return lineNumber;
    }

    public InvoiceLineItem lineNumber(Double lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    public void setLineNumber(Double lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDescription() {
        return description;
    }

    public InvoiceLineItem description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public InvoiceLineItem quantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public InvoiceLineItem unitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public InvoiceLineItem taxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxDesc() {
        return taxDesc;
    }

    public InvoiceLineItem taxDesc(String taxDesc) {
        this.taxDesc = taxDesc;
        return this;
    }

    public void setTaxDesc(String taxDesc) {
        this.taxDesc = taxDesc;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public InvoiceLineItem taxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
        return this;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getLineAmount() {
        return lineAmount;
    }

    public InvoiceLineItem lineAmount(Double lineAmount) {
        this.lineAmount = lineAmount;
        return this;
    }

    public void setLineAmount(Double lineAmount) {
        this.lineAmount = lineAmount;
    }

    public Double getGrossLineAmount() {
        return grossLineAmount;
    }

    public InvoiceLineItem grossLineAmount(Double grossLineAmount) {
        this.grossLineAmount = grossLineAmount;
        return this;
    }

    public void setGrossLineAmount(Double grossLineAmount) {
        this.grossLineAmount = grossLineAmount;
    }

    public String getConfirmerId() {
        return confirmerId;
    }

    public InvoiceLineItem confirmerId(String confirmerId) {
        this.confirmerId = confirmerId;
        return this;
    }

    public void setConfirmerId(String confirmerId) {
        this.confirmerId = confirmerId;
    }

    public String getPoRequestBy() {
        return poRequestBy;
    }

    public InvoiceLineItem poRequestBy(String poRequestBy) {
        this.poRequestBy = poRequestBy;
        return this;
    }

    public void setPoRequestBy(String poRequestBy) {
        this.poRequestBy = poRequestBy;
    }

    public String getUom() {
        return uom;
    }

    public InvoiceLineItem uom(String uom) {
        this.uom = uom;
        return this;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getDebitCreditIndicator() {
        return debitCreditIndicator;
    }

    public InvoiceLineItem debitCreditIndicator(String debitCreditIndicator) {
        this.debitCreditIndicator = debitCreditIndicator;
        return this;
    }

    public void setDebitCreditIndicator(String debitCreditIndicator) {
        this.debitCreditIndicator = debitCreditIndicator;
    }

    public Double getAvailableQty() {
        return availableQty;
    }

    public InvoiceLineItem availableQty(Double availableQty) {
        this.availableQty = availableQty;
        return this;
    }

    public void setAvailableQty(Double availableQty) {
        this.availableQty = availableQty;
    }

    public Double getAdditionalCostPerUnit() {
        return additionalCostPerUnit;
    }

    public InvoiceLineItem additionalCostPerUnit(Double additionalCostPerUnit) {
        this.additionalCostPerUnit = additionalCostPerUnit;
        return this;
    }

    public void setAdditionalCostPerUnit(Double additionalCostPerUnit) {
        this.additionalCostPerUnit = additionalCostPerUnit;
    }

    public Double getTaxBaseAmount() {
        return taxBaseAmount;
    }

    public InvoiceLineItem taxBaseAmount(Double taxBaseAmount) {
        this.taxBaseAmount = taxBaseAmount;
        return this;
    }

    public void setTaxBaseAmount(Double taxBaseAmount) {
        this.taxBaseAmount = taxBaseAmount;
    }

    public Double getInvoiceQty() {
        return invoiceQty;
    }

    public InvoiceLineItem invoiceQty(Double invoiceQty) {
        this.invoiceQty = invoiceQty;
        return this;
    }

    public void setInvoiceQty(Double invoiceQty) {
        this.invoiceQty = invoiceQty;
    }

    public Double getInvoiceUnitPrice() {
        return invoiceUnitPrice;
    }

    public InvoiceLineItem invoiceUnitPrice(Double invoiceUnitPrice) {
        this.invoiceUnitPrice = invoiceUnitPrice;
        return this;
    }

    public void setInvoiceUnitPrice(Double invoiceUnitPrice) {
        this.invoiceUnitPrice = invoiceUnitPrice;
    }

    public Double getVatTaxRate() {
        return vatTaxRate;
    }

    public InvoiceLineItem vatTaxRate(Double vatTaxRate) {
        this.vatTaxRate = vatTaxRate;
        return this;
    }

    public void setVatTaxRate(Double vatTaxRate) {
        this.vatTaxRate = vatTaxRate;
    }

    public String getConfirmerName() {
        return confirmerName;
    }

    public InvoiceLineItem confirmerName(String confirmerName) {
        this.confirmerName = confirmerName;
        return this;
    }

    public void setConfirmerName(String confirmerName) {
        this.confirmerName = confirmerName;
    }

    public Double getAvailableExpectedvalue() {
        return availableExpectedvalue;
    }

    public InvoiceLineItem availableExpectedvalue(Double availableExpectedvalue) {
        this.availableExpectedvalue = availableExpectedvalue;
        return this;
    }

    public void setAvailableExpectedvalue(Double availableExpectedvalue) {
        this.availableExpectedvalue = availableExpectedvalue;
    }

    public String getAvailableLimitValue() {
        return availableLimitValue;
    }

    public InvoiceLineItem availableLimitValue(String availableLimitValue) {
        this.availableLimitValue = availableLimitValue;
        return this;
    }

    public void setAvailableLimitValue(String availableLimitValue) {
        this.availableLimitValue = availableLimitValue;
    }

    public Double getExpectedValue() {
        return expectedValue;
    }

    public InvoiceLineItem expectedValue(Double expectedValue) {
        this.expectedValue = expectedValue;
        return this;
    }

    public void setExpectedValue(Double expectedValue) {
        this.expectedValue = expectedValue;
    }

    public Double getActualLimit() {
        return actualLimit;
    }

    public InvoiceLineItem actualLimit(Double actualLimit) {
        this.actualLimit = actualLimit;
        return this;
    }

    public void setActualLimit(Double actualLimit) {
        this.actualLimit = actualLimit;
    }

    public Double getOverallLimit() {
        return overallLimit;
    }

    public InvoiceLineItem overallLimit(Double overallLimit) {
        this.overallLimit = overallLimit;
        return this;
    }

    public void setOverallLimit(Double overallLimit) {
        this.overallLimit = overallLimit;
    }

    public ZonedDateTime getLimitOrderExpiryDate() {
        return limitOrderExpiryDate;
    }

    public InvoiceLineItem limitOrderExpiryDate(ZonedDateTime limitOrderExpiryDate) {
        this.limitOrderExpiryDate = limitOrderExpiryDate;
        return this;
    }

    public void setLimitOrderExpiryDate(ZonedDateTime limitOrderExpiryDate) {
        this.limitOrderExpiryDate = limitOrderExpiryDate;
    }

    public ZonedDateTime getLimitOrderStartDate() {
        return limitOrderStartDate;
    }

    public InvoiceLineItem limitOrderStartDate(ZonedDateTime limitOrderStartDate) {
        this.limitOrderStartDate = limitOrderStartDate;
        return this;
    }

    public void setLimitOrderStartDate(ZonedDateTime limitOrderStartDate) {
        this.limitOrderStartDate = limitOrderStartDate;
    }

    public String getVatTaxCode() {
        return vatTaxCode;
    }

    public InvoiceLineItem vatTaxCode(String vatTaxCode) {
        this.vatTaxCode = vatTaxCode;
        return this;
    }

    public void setVatTaxCode(String vatTaxCode) {
        this.vatTaxCode = vatTaxCode;
    }

    public String getInternalOrderStatistical() {
        return internalOrderStatistical;
    }

    public InvoiceLineItem internalOrderStatistical(String internalOrderStatistical) {
        this.internalOrderStatistical = internalOrderStatistical;
        return this;
    }

    public void setInternalOrderStatistical(String internalOrderStatistical) {
        this.internalOrderStatistical = internalOrderStatistical;
    }

    public Double getDebitCreditValue() {
        return debitCreditValue;
    }

    public InvoiceLineItem debitCreditValue(Double debitCreditValue) {
        this.debitCreditValue = debitCreditValue;
        return this;
    }

    public void setDebitCreditValue(Double debitCreditValue) {
        this.debitCreditValue = debitCreditValue;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public InvoiceLineItem deliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
        return this;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getReason() {
        return reason;
    }

    public InvoiceLineItem reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public InvoiceLineItem createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public InvoiceLineItem createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public PurchaseOrderLine getPurchaseOrderLine() {
        return purchaseOrderLine;
    }

    public InvoiceLineItem purchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLine = purchaseOrderLine;
        return this;
    }

    public void setPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLine = purchaseOrderLine;
    }

    public GLAccountDetails getGLAccountDetails() {
        return gLAccountDetails;
    }

    public InvoiceLineItem gLAccountDetails(GLAccountDetails gLAccountDetails) {
        this.gLAccountDetails = gLAccountDetails;
        return this;
    }

    public void setGLAccountDetails(GLAccountDetails gLAccountDetails) {
        this.gLAccountDetails = gLAccountDetails;
    }

    public User getTechnicalApprover() {
        return technicalApprover;
    }

    public InvoiceLineItem technicalApprover(User user) {
        this.technicalApprover = user;
        return this;
    }

    public void setTechnicalApprover(User user) {
        this.technicalApprover = user;
    }

    public ShippingLocation getShippingLocation() {
        return shippingLocation;
    }

    public InvoiceLineItem shippingLocation(ShippingLocation shippingLocation) {
        this.shippingLocation = shippingLocation;
        return this;
    }

    public void setShippingLocation(ShippingLocation shippingLocation) {
        this.shippingLocation = shippingLocation;
    }

    public Product getProduct() {
        return product;
    }

    public InvoiceLineItem product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public GRNDetails getGRNDetails() {
        return gRNDetails;
    }

    public InvoiceLineItem gRNDetails(GRNDetails gRNDetails) {
        this.gRNDetails = gRNDetails;
        return this;
    }

    public void setGRNDetails(GRNDetails gRNDetails) {
        this.gRNDetails = gRNDetails;
    }

    public Set<CaseStatusDetails> getCaseStatusDetails() {
        return caseStatusDetails;
    }

    public InvoiceLineItem caseStatusDetails(Set<CaseStatusDetails> caseStatusDetails) {
        this.caseStatusDetails = caseStatusDetails;
        return this;
    }

    public InvoiceLineItem addCaseStatusDetails(CaseStatusDetails caseStatusDetails) {
        this.caseStatusDetails.add(caseStatusDetails);
        caseStatusDetails.setInvoiceLineItem(this);
        return this;
    }

    public InvoiceLineItem removeCaseStatusDetails(CaseStatusDetails caseStatusDetails) {
        this.caseStatusDetails.remove(caseStatusDetails);
        caseStatusDetails.setInvoiceLineItem(null);
        return this;
    }

    public void setCaseStatusDetails(Set<CaseStatusDetails> caseStatusDetails) {
        this.caseStatusDetails = caseStatusDetails;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public InvoiceLineItem invoice(Invoice invoice) {
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
        if (!(o instanceof InvoiceLineItem)) {
            return false;
        }
        return id != null && id.equals(((InvoiceLineItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceLineItem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", expenseType='" + getExpenseType() + "'" +
            ", distributionId='" + getDistributionId() + "'" +
            ", lineNumber=" + getLineNumber() +
            ", description='" + getDescription() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", unitPrice=" + getUnitPrice() +
            ", taxRate=" + getTaxRate() +
            ", taxDesc='" + getTaxDesc() + "'" +
            ", taxAmount=" + getTaxAmount() +
            ", lineAmount=" + getLineAmount() +
            ", grossLineAmount=" + getGrossLineAmount() +
            ", confirmerId='" + getConfirmerId() + "'" +
            ", poRequestBy='" + getPoRequestBy() + "'" +
            ", uom='" + getUom() + "'" +
            ", debitCreditIndicator='" + getDebitCreditIndicator() + "'" +
            ", availableQty=" + getAvailableQty() +
            ", additionalCostPerUnit=" + getAdditionalCostPerUnit() +
            ", taxBaseAmount=" + getTaxBaseAmount() +
            ", invoiceQty=" + getInvoiceQty() +
            ", invoiceUnitPrice=" + getInvoiceUnitPrice() +
            ", vatTaxRate=" + getVatTaxRate() +
            ", confirmerName='" + getConfirmerName() + "'" +
            ", availableExpectedvalue=" + getAvailableExpectedvalue() +
            ", availableLimitValue='" + getAvailableLimitValue() + "'" +
            ", expectedValue=" + getExpectedValue() +
            ", actualLimit=" + getActualLimit() +
            ", overallLimit=" + getOverallLimit() +
            ", limitOrderExpiryDate='" + getLimitOrderExpiryDate() + "'" +
            ", limitOrderStartDate='" + getLimitOrderStartDate() + "'" +
            ", vatTaxCode='" + getVatTaxCode() + "'" +
            ", internalOrderStatistical='" + getInternalOrderStatistical() + "'" +
            ", debitCreditValue=" + getDebitCreditValue() +
            ", deliveryNote='" + getDeliveryNote() + "'" +
            ", reason='" + getReason() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
