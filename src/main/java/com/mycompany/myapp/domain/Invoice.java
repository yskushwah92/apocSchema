package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.mycompany.myapp.domain.enumeration.Priority;

import com.mycompany.myapp.domain.enumeration.InvoiceType;

import com.mycompany.myapp.domain.enumeration.InvoiceStatus;

/**
 * A Invoice.
 */
@Entity
@Table(name = "invoice")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "invoice_no")
    private String invoiceNo;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column(name = "case_no")
    private String caseNo;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private InvoiceType type;

    @Column(name = "date_of_creation")
    private ZonedDateTime dateOfCreation;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "amount_functional")
    private Double amountFunctional;

    @Column(name = "amount_reporting")
    private Double amountReporting;

    @Column(name = "price")
    private String price;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "tax")
    private String tax;

    @Column(name = "inclusive_of_tax")
    private Boolean inclusiveOfTax;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InvoiceStatus status;

    @Column(name = "gstn")
    private String gstn;

    @Column(name = "address")
    private String address;

    @Column(name = "category")
    private String category;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Column(name = "prefferred_mode_of_communication")
    private String prefferredModeOfCommunication;

    @Column(name = "point_of_contact")
    private String pointOfContact;

    @Column(name = "bank_acc_details")
    private String bankAccDetails;

    @Column(name = "upi_details")
    private String upiDetails;

    @Column(name = "preferred_mode_of_payment")
    private String preferredModeOfPayment;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private FileInfo fileInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private ModelInfo modelInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private NotificationInfo notificationInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private PaymentInfo paymentInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private InvoiceHeader invoiceHeader;

    @OneToMany(mappedBy = "invoice")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<OCRRawExtraction> oCRRawExtractions = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Invoice> invoices = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<InvoiceLineItem> invoiceLineItems = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<InvoiceStatusDetails> invoiceStatusDetails = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PaymentInfo> paymentInfos = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AuditLog> auditLogs = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Assignment> assignments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "invoices", allowSetters = true)
    private Vendor vendor;

    @ManyToOne
    @JsonIgnoreProperties(value = "invoices", allowSetters = true)
    private Invoice invoice;

    @ManyToOne
    @JsonIgnoreProperties(value = "invoices", allowSetters = true)
    private PurchaseOrder purchaseOrder;

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

    public Invoice name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public Invoice serialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public Invoice invoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        return this;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public Invoice referenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
        return this;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public Invoice caseNo(String caseNo) {
        this.caseNo = caseNo;
        return this;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getDescription() {
        return description;
    }

    public Invoice description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Invoice priority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public InvoiceType getType() {
        return type;
    }

    public Invoice type(InvoiceType type) {
        this.type = type;
        return this;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public ZonedDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public Invoice dateOfCreation(ZonedDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }

    public void setDateOfCreation(ZonedDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Invoice sellerName(String sellerName) {
        this.sellerName = sellerName;
        return this;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getAmountFunctional() {
        return amountFunctional;
    }

    public Invoice amountFunctional(Double amountFunctional) {
        this.amountFunctional = amountFunctional;
        return this;
    }

    public void setAmountFunctional(Double amountFunctional) {
        this.amountFunctional = amountFunctional;
    }

    public Double getAmountReporting() {
        return amountReporting;
    }

    public Invoice amountReporting(Double amountReporting) {
        this.amountReporting = amountReporting;
        return this;
    }

    public void setAmountReporting(Double amountReporting) {
        this.amountReporting = amountReporting;
    }

    public String getPrice() {
        return price;
    }

    public Invoice price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIssuer() {
        return issuer;
    }

    public Invoice issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getTax() {
        return tax;
    }

    public Invoice tax(String tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Boolean isInclusiveOfTax() {
        return inclusiveOfTax;
    }

    public Invoice inclusiveOfTax(Boolean inclusiveOfTax) {
        this.inclusiveOfTax = inclusiveOfTax;
        return this;
    }

    public void setInclusiveOfTax(Boolean inclusiveOfTax) {
        this.inclusiveOfTax = inclusiveOfTax;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public Invoice status(InvoiceStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public String getGstn() {
        return gstn;
    }

    public Invoice gstn(String gstn) {
        this.gstn = gstn;
        return this;
    }

    public void setGstn(String gstn) {
        this.gstn = gstn;
    }

    public String getAddress() {
        return address;
    }

    public Invoice address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public Invoice category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContactNo() {
        return contactNo;
    }

    public Invoice contactNo(String contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public Invoice email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefferredModeOfCommunication() {
        return prefferredModeOfCommunication;
    }

    public Invoice prefferredModeOfCommunication(String prefferredModeOfCommunication) {
        this.prefferredModeOfCommunication = prefferredModeOfCommunication;
        return this;
    }

    public void setPrefferredModeOfCommunication(String prefferredModeOfCommunication) {
        this.prefferredModeOfCommunication = prefferredModeOfCommunication;
    }

    public String getPointOfContact() {
        return pointOfContact;
    }

    public Invoice pointOfContact(String pointOfContact) {
        this.pointOfContact = pointOfContact;
        return this;
    }

    public void setPointOfContact(String pointOfContact) {
        this.pointOfContact = pointOfContact;
    }

    public String getBankAccDetails() {
        return bankAccDetails;
    }

    public Invoice bankAccDetails(String bankAccDetails) {
        this.bankAccDetails = bankAccDetails;
        return this;
    }

    public void setBankAccDetails(String bankAccDetails) {
        this.bankAccDetails = bankAccDetails;
    }

    public String getUpiDetails() {
        return upiDetails;
    }

    public Invoice upiDetails(String upiDetails) {
        this.upiDetails = upiDetails;
        return this;
    }

    public void setUpiDetails(String upiDetails) {
        this.upiDetails = upiDetails;
    }

    public String getPreferredModeOfPayment() {
        return preferredModeOfPayment;
    }

    public Invoice preferredModeOfPayment(String preferredModeOfPayment) {
        this.preferredModeOfPayment = preferredModeOfPayment;
        return this;
    }

    public void setPreferredModeOfPayment(String preferredModeOfPayment) {
        this.preferredModeOfPayment = preferredModeOfPayment;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Invoice createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Invoice createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public Invoice fileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
        return this;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public ModelInfo getModelInfo() {
        return modelInfo;
    }

    public Invoice modelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
        return this;
    }

    public void setModelInfo(ModelInfo modelInfo) {
        this.modelInfo = modelInfo;
    }

    public NotificationInfo getNotificationInfo() {
        return notificationInfo;
    }

    public Invoice notificationInfo(NotificationInfo notificationInfo) {
        this.notificationInfo = notificationInfo;
        return this;
    }

    public void setNotificationInfo(NotificationInfo notificationInfo) {
        this.notificationInfo = notificationInfo;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public Invoice paymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
        return this;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public Invoice invoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
        return this;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public Set<OCRRawExtraction> getOCRRawExtractions() {
        return oCRRawExtractions;
    }

    public Invoice oCRRawExtractions(Set<OCRRawExtraction> oCRRawExtractions) {
        this.oCRRawExtractions = oCRRawExtractions;
        return this;
    }

    public Invoice addOCRRawExtraction(OCRRawExtraction oCRRawExtraction) {
        this.oCRRawExtractions.add(oCRRawExtraction);
        oCRRawExtraction.setInvoice(this);
        return this;
    }

    public Invoice removeOCRRawExtraction(OCRRawExtraction oCRRawExtraction) {
        this.oCRRawExtractions.remove(oCRRawExtraction);
        oCRRawExtraction.setInvoice(null);
        return this;
    }

    public void setOCRRawExtractions(Set<OCRRawExtraction> oCRRawExtractions) {
        this.oCRRawExtractions = oCRRawExtractions;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public Invoice invoices(Set<Invoice> invoices) {
        this.invoices = invoices;
        return this;
    }

    public Invoice addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.setInvoice(this);
        return this;
    }

    public Invoice removeInvoice(Invoice invoice) {
        this.invoices.remove(invoice);
        invoice.setInvoice(null);
        return this;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<InvoiceLineItem> getInvoiceLineItems() {
        return invoiceLineItems;
    }

    public Invoice invoiceLineItems(Set<InvoiceLineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
        return this;
    }

    public Invoice addInvoiceLineItem(InvoiceLineItem invoiceLineItem) {
        this.invoiceLineItems.add(invoiceLineItem);
        invoiceLineItem.setInvoice(this);
        return this;
    }

    public Invoice removeInvoiceLineItem(InvoiceLineItem invoiceLineItem) {
        this.invoiceLineItems.remove(invoiceLineItem);
        invoiceLineItem.setInvoice(null);
        return this;
    }

    public void setInvoiceLineItems(Set<InvoiceLineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
    }

    public Set<InvoiceStatusDetails> getInvoiceStatusDetails() {
        return invoiceStatusDetails;
    }

    public Invoice invoiceStatusDetails(Set<InvoiceStatusDetails> invoiceStatusDetails) {
        this.invoiceStatusDetails = invoiceStatusDetails;
        return this;
    }

    public Invoice addInvoiceStatusDetails(InvoiceStatusDetails invoiceStatusDetails) {
        this.invoiceStatusDetails.add(invoiceStatusDetails);
        invoiceStatusDetails.setInvoice(this);
        return this;
    }

    public Invoice removeInvoiceStatusDetails(InvoiceStatusDetails invoiceStatusDetails) {
        this.invoiceStatusDetails.remove(invoiceStatusDetails);
        invoiceStatusDetails.setInvoice(null);
        return this;
    }

    public void setInvoiceStatusDetails(Set<InvoiceStatusDetails> invoiceStatusDetails) {
        this.invoiceStatusDetails = invoiceStatusDetails;
    }

    public Set<PaymentInfo> getPaymentInfos() {
        return paymentInfos;
    }

    public Invoice paymentInfos(Set<PaymentInfo> paymentInfos) {
        this.paymentInfos = paymentInfos;
        return this;
    }

    public Invoice addPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfos.add(paymentInfo);
        paymentInfo.setInvoice(this);
        return this;
    }

    public Invoice removePaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfos.remove(paymentInfo);
        paymentInfo.setInvoice(null);
        return this;
    }

    public void setPaymentInfos(Set<PaymentInfo> paymentInfos) {
        this.paymentInfos = paymentInfos;
    }

    public Set<AuditLog> getAuditLogs() {
        return auditLogs;
    }

    public Invoice auditLogs(Set<AuditLog> auditLogs) {
        this.auditLogs = auditLogs;
        return this;
    }

    public Invoice addAuditLog(AuditLog auditLog) {
        this.auditLogs.add(auditLog);
        auditLog.setInvoice(this);
        return this;
    }

    public Invoice removeAuditLog(AuditLog auditLog) {
        this.auditLogs.remove(auditLog);
        auditLog.setInvoice(null);
        return this;
    }

    public void setAuditLogs(Set<AuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public Invoice assignments(Set<Assignment> assignments) {
        this.assignments = assignments;
        return this;
    }

    public Invoice addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
        assignment.setInvoice(this);
        return this;
    }

    public Invoice removeAssignment(Assignment assignment) {
        this.assignments.remove(assignment);
        assignment.setInvoice(null);
        return this;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Invoice vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Invoice invoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public Invoice purchaseOrder(PurchaseOrder purchaseOrder) {
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
        if (!(o instanceof Invoice)) {
            return false;
        }
        return id != null && id.equals(((Invoice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serialNo='" + getSerialNo() + "'" +
            ", invoiceNo='" + getInvoiceNo() + "'" +
            ", referenceNo='" + getReferenceNo() + "'" +
            ", caseNo='" + getCaseNo() + "'" +
            ", description='" + getDescription() + "'" +
            ", priority='" + getPriority() + "'" +
            ", type='" + getType() + "'" +
            ", dateOfCreation='" + getDateOfCreation() + "'" +
            ", sellerName='" + getSellerName() + "'" +
            ", amountFunctional=" + getAmountFunctional() +
            ", amountReporting=" + getAmountReporting() +
            ", price='" + getPrice() + "'" +
            ", issuer='" + getIssuer() + "'" +
            ", tax='" + getTax() + "'" +
            ", inclusiveOfTax='" + isInclusiveOfTax() + "'" +
            ", status='" + getStatus() + "'" +
            ", gstn='" + getGstn() + "'" +
            ", address='" + getAddress() + "'" +
            ", category='" + getCategory() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", prefferredModeOfCommunication='" + getPrefferredModeOfCommunication() + "'" +
            ", pointOfContact='" + getPointOfContact() + "'" +
            ", bankAccDetails='" + getBankAccDetails() + "'" +
            ", upiDetails='" + getUpiDetails() + "'" +
            ", preferredModeOfPayment='" + getPreferredModeOfPayment() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
