package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Vendor.
 */
@Entity
@Table(name = "vendor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "vendor_number")
    private String vendorNumber;

    @Column(name = "payment_terms")
    private String paymentTerms;

    @Column(name = "type")
    private String type;

    @Column(name = "iln")
    private String iln;

    @Column(name = "tax_id_no")
    private String taxIdNO;

    @Column(name = "gst_registration_number")
    private String gstRegistrationNumber;

    @Column(name = "gst_status")
    private String gstStatus;

    @Column(name = "vat_id_no")
    private String vatIdNo;

    @Column(name = "clerk_id")
    private String clerkId;

    @Column(name = "status")
    private String status;

    @Column(name = "vat_registration_number")
    private String vatRegistrationNumber;

    @Column(name = "prefferred_mode_of_communication")
    private String prefferredModeOfCommunication;

    @Column(name = "point_of_contact")
    private String pointOfContact;

    @Column(name = "preferred_mode_of_payment")
    private String preferredModeOfPayment;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private ContactDetails contactDetails;

    @OneToOne
    @JoinColumn(unique = true)
    private Organization organization;

    @OneToOne
    @JoinColumn(unique = true)
    private GLAccountDetails gLAccountDetails;

    @OneToMany(mappedBy = "vendor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Invoice> invoices = new HashSet<>();

    @OneToMany(mappedBy = "vendor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VendorPaymentAccountDetails> vendorPaymentAccountDetails = new HashSet<>();

    @OneToOne(mappedBy = "vendor")
    @JsonIgnore
    private PurchaseOrderLine purchaseOrderLine;

    @ManyToMany(mappedBy = "vendors")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();

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

    public Vendor name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Vendor displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public Vendor vendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
        return this;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public Vendor paymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
        return this;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getType() {
        return type;
    }

    public Vendor type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIln() {
        return iln;
    }

    public Vendor iln(String iln) {
        this.iln = iln;
        return this;
    }

    public void setIln(String iln) {
        this.iln = iln;
    }

    public String getTaxIdNO() {
        return taxIdNO;
    }

    public Vendor taxIdNO(String taxIdNO) {
        this.taxIdNO = taxIdNO;
        return this;
    }

    public void setTaxIdNO(String taxIdNO) {
        this.taxIdNO = taxIdNO;
    }

    public String getGstRegistrationNumber() {
        return gstRegistrationNumber;
    }

    public Vendor gstRegistrationNumber(String gstRegistrationNumber) {
        this.gstRegistrationNumber = gstRegistrationNumber;
        return this;
    }

    public void setGstRegistrationNumber(String gstRegistrationNumber) {
        this.gstRegistrationNumber = gstRegistrationNumber;
    }

    public String getGstStatus() {
        return gstStatus;
    }

    public Vendor gstStatus(String gstStatus) {
        this.gstStatus = gstStatus;
        return this;
    }

    public void setGstStatus(String gstStatus) {
        this.gstStatus = gstStatus;
    }

    public String getVatIdNo() {
        return vatIdNo;
    }

    public Vendor vatIdNo(String vatIdNo) {
        this.vatIdNo = vatIdNo;
        return this;
    }

    public void setVatIdNo(String vatIdNo) {
        this.vatIdNo = vatIdNo;
    }

    public String getClerkId() {
        return clerkId;
    }

    public Vendor clerkId(String clerkId) {
        this.clerkId = clerkId;
        return this;
    }

    public void setClerkId(String clerkId) {
        this.clerkId = clerkId;
    }

    public String getStatus() {
        return status;
    }

    public Vendor status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVatRegistrationNumber() {
        return vatRegistrationNumber;
    }

    public Vendor vatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
        return this;
    }

    public void setVatRegistrationNumber(String vatRegistrationNumber) {
        this.vatRegistrationNumber = vatRegistrationNumber;
    }

    public String getPrefferredModeOfCommunication() {
        return prefferredModeOfCommunication;
    }

    public Vendor prefferredModeOfCommunication(String prefferredModeOfCommunication) {
        this.prefferredModeOfCommunication = prefferredModeOfCommunication;
        return this;
    }

    public void setPrefferredModeOfCommunication(String prefferredModeOfCommunication) {
        this.prefferredModeOfCommunication = prefferredModeOfCommunication;
    }

    public String getPointOfContact() {
        return pointOfContact;
    }

    public Vendor pointOfContact(String pointOfContact) {
        this.pointOfContact = pointOfContact;
        return this;
    }

    public void setPointOfContact(String pointOfContact) {
        this.pointOfContact = pointOfContact;
    }

    public String getPreferredModeOfPayment() {
        return preferredModeOfPayment;
    }

    public Vendor preferredModeOfPayment(String preferredModeOfPayment) {
        this.preferredModeOfPayment = preferredModeOfPayment;
        return this;
    }

    public void setPreferredModeOfPayment(String preferredModeOfPayment) {
        this.preferredModeOfPayment = preferredModeOfPayment;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Vendor createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Vendor createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public Vendor contactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
        return this;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Vendor organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public GLAccountDetails getGLAccountDetails() {
        return gLAccountDetails;
    }

    public Vendor gLAccountDetails(GLAccountDetails gLAccountDetails) {
        this.gLAccountDetails = gLAccountDetails;
        return this;
    }

    public void setGLAccountDetails(GLAccountDetails gLAccountDetails) {
        this.gLAccountDetails = gLAccountDetails;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public Vendor invoices(Set<Invoice> invoices) {
        this.invoices = invoices;
        return this;
    }

    public Vendor addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.setVendor(this);
        return this;
    }

    public Vendor removeInvoice(Invoice invoice) {
        this.invoices.remove(invoice);
        invoice.setVendor(null);
        return this;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<VendorPaymentAccountDetails> getVendorPaymentAccountDetails() {
        return vendorPaymentAccountDetails;
    }

    public Vendor vendorPaymentAccountDetails(Set<VendorPaymentAccountDetails> vendorPaymentAccountDetails) {
        this.vendorPaymentAccountDetails = vendorPaymentAccountDetails;
        return this;
    }

    public Vendor addVendorPaymentAccountDetails(VendorPaymentAccountDetails vendorPaymentAccountDetails) {
        this.vendorPaymentAccountDetails.add(vendorPaymentAccountDetails);
        vendorPaymentAccountDetails.setVendor(this);
        return this;
    }

    public Vendor removeVendorPaymentAccountDetails(VendorPaymentAccountDetails vendorPaymentAccountDetails) {
        this.vendorPaymentAccountDetails.remove(vendorPaymentAccountDetails);
        vendorPaymentAccountDetails.setVendor(null);
        return this;
    }

    public void setVendorPaymentAccountDetails(Set<VendorPaymentAccountDetails> vendorPaymentAccountDetails) {
        this.vendorPaymentAccountDetails = vendorPaymentAccountDetails;
    }

    public PurchaseOrderLine getPurchaseOrderLine() {
        return purchaseOrderLine;
    }

    public Vendor purchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLine = purchaseOrderLine;
        return this;
    }

    public void setPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLine = purchaseOrderLine;
    }

    public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public Vendor purchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
        return this;
    }

    public Vendor addPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrders.add(purchaseOrder);
        purchaseOrder.getVendors().add(this);
        return this;
    }

    public Vendor removePurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrders.remove(purchaseOrder);
        purchaseOrder.getVendors().remove(this);
        return this;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vendor)) {
            return false;
        }
        return id != null && id.equals(((Vendor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vendor{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", vendorNumber='" + getVendorNumber() + "'" +
            ", paymentTerms='" + getPaymentTerms() + "'" +
            ", type='" + getType() + "'" +
            ", iln='" + getIln() + "'" +
            ", taxIdNO='" + getTaxIdNO() + "'" +
            ", gstRegistrationNumber='" + getGstRegistrationNumber() + "'" +
            ", gstStatus='" + getGstStatus() + "'" +
            ", vatIdNo='" + getVatIdNo() + "'" +
            ", clerkId='" + getClerkId() + "'" +
            ", status='" + getStatus() + "'" +
            ", vatRegistrationNumber='" + getVatRegistrationNumber() + "'" +
            ", prefferredModeOfCommunication='" + getPrefferredModeOfCommunication() + "'" +
            ", pointOfContact='" + getPointOfContact() + "'" +
            ", preferredModeOfPayment='" + getPreferredModeOfPayment() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
