package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.PaymentMode;

import com.mycompany.myapp.domain.enumeration.PaymentStatus;

import com.mycompany.myapp.domain.enumeration.PaymentType;

import com.mycompany.myapp.domain.enumeration.ApprovalStatus;

import com.mycompany.myapp.domain.enumeration.Currency;

/**
 * A PaymentInfo.
 */
@Entity
@Table(name = "payment_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "terms")
    private String terms;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private PaymentMode mode;

    @Column(name = "due_date")
    private ZonedDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "payment_channel")
    private String paymentChannel;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PaymentType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus;

    @Column(name = "date_of_approval")
    private ZonedDateTime dateOfApproval;

    @Column(name = "date_of_payment")
    private ZonedDateTime dateOfPayment;

    @Column(name = "payment_reference_number")
    private String paymentReferenceNumber;

    @Column(name = "check_date")
    private ZonedDateTime checkDate;

    @Column(name = "check_number")
    private String checkNumber;

    @Column(name = "check_amount")
    private Double checkAmount;

    @Column(name = "early_payment_date")
    private ZonedDateTime earlyPaymentDate;

    @Column(name = "early_payment_discount")
    private String earlyPaymentDiscount;

    @Column(name = "early_payment_amount")
    private Double earlyPaymentAmount;

    @Column(name = "early_payment_remarks")
    private String earlyPaymentRemarks;

    @Column(name = "payment_doc_date")
    private ZonedDateTime paymentDocDate;

    @Column(name = "payment_doc_no")
    private String paymentDocNo;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "discount_taken")
    private Double discountTaken;

    @Column(name = "discount_lost")
    private Double discountLost;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_currency")
    private Currency paymentCurrency;

    @Column(name = "invoice_base_amount")
    private Double invoiceBaseAmount;

    @Column(name = "cleared_date")
    private ZonedDateTime clearedDate;

    @Column(name = "cleared_amount")
    private Double clearedAmount;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private PaymentInfo paymentInfo;

    @OneToOne(mappedBy = "paymentInfo")
    @JsonIgnore
    private Invoice invoice;

    @OneToOne(mappedBy = "paymentInfo")
    @JsonIgnore
    private PaymentInfo paymentInfo;

    @ManyToOne
    @JsonIgnoreProperties(value = "paymentInfos", allowSetters = true)
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

    public PaymentInfo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerms() {
        return terms;
    }

    public PaymentInfo terms(String terms) {
        this.terms = terms;
        return this;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public PaymentMode getMode() {
        return mode;
    }

    public PaymentInfo mode(PaymentMode mode) {
        this.mode = mode;
        return this;
    }

    public void setMode(PaymentMode mode) {
        this.mode = mode;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public PaymentInfo dueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(ZonedDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public PaymentInfo status(PaymentStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public PaymentInfo paymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
        return this;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public PaymentType getType() {
        return type;
    }

    public PaymentInfo type(PaymentType type) {
        this.type = type;
        return this;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public PaymentInfo approvalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
        return this;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public ZonedDateTime getDateOfApproval() {
        return dateOfApproval;
    }

    public PaymentInfo dateOfApproval(ZonedDateTime dateOfApproval) {
        this.dateOfApproval = dateOfApproval;
        return this;
    }

    public void setDateOfApproval(ZonedDateTime dateOfApproval) {
        this.dateOfApproval = dateOfApproval;
    }

    public ZonedDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    public PaymentInfo dateOfPayment(ZonedDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
        return this;
    }

    public void setDateOfPayment(ZonedDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getPaymentReferenceNumber() {
        return paymentReferenceNumber;
    }

    public PaymentInfo paymentReferenceNumber(String paymentReferenceNumber) {
        this.paymentReferenceNumber = paymentReferenceNumber;
        return this;
    }

    public void setPaymentReferenceNumber(String paymentReferenceNumber) {
        this.paymentReferenceNumber = paymentReferenceNumber;
    }

    public ZonedDateTime getCheckDate() {
        return checkDate;
    }

    public PaymentInfo checkDate(ZonedDateTime checkDate) {
        this.checkDate = checkDate;
        return this;
    }

    public void setCheckDate(ZonedDateTime checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public PaymentInfo checkNumber(String checkNumber) {
        this.checkNumber = checkNumber;
        return this;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Double getCheckAmount() {
        return checkAmount;
    }

    public PaymentInfo checkAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
        return this;
    }

    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public ZonedDateTime getEarlyPaymentDate() {
        return earlyPaymentDate;
    }

    public PaymentInfo earlyPaymentDate(ZonedDateTime earlyPaymentDate) {
        this.earlyPaymentDate = earlyPaymentDate;
        return this;
    }

    public void setEarlyPaymentDate(ZonedDateTime earlyPaymentDate) {
        this.earlyPaymentDate = earlyPaymentDate;
    }

    public String getEarlyPaymentDiscount() {
        return earlyPaymentDiscount;
    }

    public PaymentInfo earlyPaymentDiscount(String earlyPaymentDiscount) {
        this.earlyPaymentDiscount = earlyPaymentDiscount;
        return this;
    }

    public void setEarlyPaymentDiscount(String earlyPaymentDiscount) {
        this.earlyPaymentDiscount = earlyPaymentDiscount;
    }

    public Double getEarlyPaymentAmount() {
        return earlyPaymentAmount;
    }

    public PaymentInfo earlyPaymentAmount(Double earlyPaymentAmount) {
        this.earlyPaymentAmount = earlyPaymentAmount;
        return this;
    }

    public void setEarlyPaymentAmount(Double earlyPaymentAmount) {
        this.earlyPaymentAmount = earlyPaymentAmount;
    }

    public String getEarlyPaymentRemarks() {
        return earlyPaymentRemarks;
    }

    public PaymentInfo earlyPaymentRemarks(String earlyPaymentRemarks) {
        this.earlyPaymentRemarks = earlyPaymentRemarks;
        return this;
    }

    public void setEarlyPaymentRemarks(String earlyPaymentRemarks) {
        this.earlyPaymentRemarks = earlyPaymentRemarks;
    }

    public ZonedDateTime getPaymentDocDate() {
        return paymentDocDate;
    }

    public PaymentInfo paymentDocDate(ZonedDateTime paymentDocDate) {
        this.paymentDocDate = paymentDocDate;
        return this;
    }

    public void setPaymentDocDate(ZonedDateTime paymentDocDate) {
        this.paymentDocDate = paymentDocDate;
    }

    public String getPaymentDocNo() {
        return paymentDocNo;
    }

    public PaymentInfo paymentDocNo(String paymentDocNo) {
        this.paymentDocNo = paymentDocNo;
        return this;
    }

    public void setPaymentDocNo(String paymentDocNo) {
        this.paymentDocNo = paymentDocNo;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentInfo paymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Double getDiscountTaken() {
        return discountTaken;
    }

    public PaymentInfo discountTaken(Double discountTaken) {
        this.discountTaken = discountTaken;
        return this;
    }

    public void setDiscountTaken(Double discountTaken) {
        this.discountTaken = discountTaken;
    }

    public Double getDiscountLost() {
        return discountLost;
    }

    public PaymentInfo discountLost(Double discountLost) {
        this.discountLost = discountLost;
        return this;
    }

    public void setDiscountLost(Double discountLost) {
        this.discountLost = discountLost;
    }

    public Currency getPaymentCurrency() {
        return paymentCurrency;
    }

    public PaymentInfo paymentCurrency(Currency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
        return this;
    }

    public void setPaymentCurrency(Currency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public Double getInvoiceBaseAmount() {
        return invoiceBaseAmount;
    }

    public PaymentInfo invoiceBaseAmount(Double invoiceBaseAmount) {
        this.invoiceBaseAmount = invoiceBaseAmount;
        return this;
    }

    public void setInvoiceBaseAmount(Double invoiceBaseAmount) {
        this.invoiceBaseAmount = invoiceBaseAmount;
    }

    public ZonedDateTime getClearedDate() {
        return clearedDate;
    }

    public PaymentInfo clearedDate(ZonedDateTime clearedDate) {
        this.clearedDate = clearedDate;
        return this;
    }

    public void setClearedDate(ZonedDateTime clearedDate) {
        this.clearedDate = clearedDate;
    }

    public Double getClearedAmount() {
        return clearedAmount;
    }

    public PaymentInfo clearedAmount(Double clearedAmount) {
        this.clearedAmount = clearedAmount;
        return this;
    }

    public void setClearedAmount(Double clearedAmount) {
        this.clearedAmount = clearedAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentInfo transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public PaymentInfo createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PaymentInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public PaymentInfo paymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
        return this;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public PaymentInfo invoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public PaymentInfo paymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
        return this;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public PaymentInfo invoice(Invoice invoice) {
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
        if (!(o instanceof PaymentInfo)) {
            return false;
        }
        return id != null && id.equals(((PaymentInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentInfo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", terms='" + getTerms() + "'" +
            ", mode='" + getMode() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", paymentChannel='" + getPaymentChannel() + "'" +
            ", type='" + getType() + "'" +
            ", approvalStatus='" + getApprovalStatus() + "'" +
            ", dateOfApproval='" + getDateOfApproval() + "'" +
            ", dateOfPayment='" + getDateOfPayment() + "'" +
            ", paymentReferenceNumber='" + getPaymentReferenceNumber() + "'" +
            ", checkDate='" + getCheckDate() + "'" +
            ", checkNumber='" + getCheckNumber() + "'" +
            ", checkAmount=" + getCheckAmount() +
            ", earlyPaymentDate='" + getEarlyPaymentDate() + "'" +
            ", earlyPaymentDiscount='" + getEarlyPaymentDiscount() + "'" +
            ", earlyPaymentAmount=" + getEarlyPaymentAmount() +
            ", earlyPaymentRemarks='" + getEarlyPaymentRemarks() + "'" +
            ", paymentDocDate='" + getPaymentDocDate() + "'" +
            ", paymentDocNo='" + getPaymentDocNo() + "'" +
            ", paymentAmount=" + getPaymentAmount() +
            ", discountTaken=" + getDiscountTaken() +
            ", discountLost=" + getDiscountLost() +
            ", paymentCurrency='" + getPaymentCurrency() + "'" +
            ", invoiceBaseAmount=" + getInvoiceBaseAmount() +
            ", clearedDate='" + getClearedDate() + "'" +
            ", clearedAmount=" + getClearedAmount() +
            ", transactionId='" + getTransactionId() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
