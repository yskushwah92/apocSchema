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
 * A PurchaseOrder.
 */
@Entity
@Table(name = "purchase_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "header")
    private String header;

    @Column(name = "unit")
    private String unit;

    @Column(name = "issuer")
    private String issuer;

    @Column(name = "issuer_details")
    private String issuerDetails;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "price")
    private String price;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private PurchaseOrderHeader purchaseOrderHeader;

    @OneToOne
    @JoinColumn(unique = true)
    private GRNDetails gRNDetails;

    @OneToMany(mappedBy = "purchaseOrder")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PurchaseOrderLine> purchaseOrderLines = new HashSet<>();

    @OneToMany(mappedBy = "purchaseOrder")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Invoice> invoices = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "purchase_order_vendor",
               joinColumns = @JoinColumn(name = "purchase_order_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "vendor_id", referencedColumnName = "id"))
    private Set<Vendor> vendors = new HashSet<>();

    @OneToOne(mappedBy = "purchaseOrder")
    @JsonIgnore
    private NotificationInfo notificationInfo;

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

    public PurchaseOrder serialNo(String serialNo) {
        this.serialNo = serialNo;
        return this;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getHeader() {
        return header;
    }

    public PurchaseOrder header(String header) {
        this.header = header;
        return this;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getUnit() {
        return unit;
    }

    public PurchaseOrder unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIssuer() {
        return issuer;
    }

    public PurchaseOrder issuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuerDetails() {
        return issuerDetails;
    }

    public PurchaseOrder issuerDetails(String issuerDetails) {
        this.issuerDetails = issuerDetails;
        return this;
    }

    public void setIssuerDetails(String issuerDetails) {
        this.issuerDetails = issuerDetails;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public PurchaseOrder creationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getPrice() {
        return price;
    }

    public PurchaseOrder price(String price) {
        this.price = price;
        return this;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public PurchaseOrder status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PurchaseOrder createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public PurchaseOrderHeader getPurchaseOrderHeader() {
        return purchaseOrderHeader;
    }

    public PurchaseOrder purchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
        this.purchaseOrderHeader = purchaseOrderHeader;
        return this;
    }

    public void setPurchaseOrderHeader(PurchaseOrderHeader purchaseOrderHeader) {
        this.purchaseOrderHeader = purchaseOrderHeader;
    }

    public GRNDetails getGRNDetails() {
        return gRNDetails;
    }

    public PurchaseOrder gRNDetails(GRNDetails gRNDetails) {
        this.gRNDetails = gRNDetails;
        return this;
    }

    public void setGRNDetails(GRNDetails gRNDetails) {
        this.gRNDetails = gRNDetails;
    }

    public Set<PurchaseOrderLine> getPurchaseOrderLines() {
        return purchaseOrderLines;
    }

    public PurchaseOrder purchaseOrderLines(Set<PurchaseOrderLine> purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
        return this;
    }

    public PurchaseOrder addPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLines.add(purchaseOrderLine);
        purchaseOrderLine.setPurchaseOrder(this);
        return this;
    }

    public PurchaseOrder removePurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLines.remove(purchaseOrderLine);
        purchaseOrderLine.setPurchaseOrder(null);
        return this;
    }

    public void setPurchaseOrderLines(Set<PurchaseOrderLine> purchaseOrderLines) {
        this.purchaseOrderLines = purchaseOrderLines;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public PurchaseOrder invoices(Set<Invoice> invoices) {
        this.invoices = invoices;
        return this;
    }

    public PurchaseOrder addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
        invoice.setPurchaseOrder(this);
        return this;
    }

    public PurchaseOrder removeInvoice(Invoice invoice) {
        this.invoices.remove(invoice);
        invoice.setPurchaseOrder(null);
        return this;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<Vendor> getVendors() {
        return vendors;
    }

    public PurchaseOrder vendors(Set<Vendor> vendors) {
        this.vendors = vendors;
        return this;
    }

    public PurchaseOrder addVendor(Vendor vendor) {
        this.vendors.add(vendor);
        vendor.getPurchaseOrders().add(this);
        return this;
    }

    public PurchaseOrder removeVendor(Vendor vendor) {
        this.vendors.remove(vendor);
        vendor.getPurchaseOrders().remove(this);
        return this;
    }

    public void setVendors(Set<Vendor> vendors) {
        this.vendors = vendors;
    }

    public NotificationInfo getNotificationInfo() {
        return notificationInfo;
    }

    public PurchaseOrder notificationInfo(NotificationInfo notificationInfo) {
        this.notificationInfo = notificationInfo;
        return this;
    }

    public void setNotificationInfo(NotificationInfo notificationInfo) {
        this.notificationInfo = notificationInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrder)) {
            return false;
        }
        return id != null && id.equals(((PurchaseOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrder{" +
            "id=" + getId() +
            ", serialNo='" + getSerialNo() + "'" +
            ", header='" + getHeader() + "'" +
            ", unit='" + getUnit() + "'" +
            ", issuer='" + getIssuer() + "'" +
            ", issuerDetails='" + getIssuerDetails() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", price='" + getPrice() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
