package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.Currency;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "organization_code")
    private String organizationCode;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "country_key")
    private String countryKey;

    @Column(name = "finance_system")
    private String financeSystem;

    @Column(name = "vat_registration_no")
    private String vatRegistrationNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "language")
    private String language;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private ContactDetails contactDetails;

    @OneToOne(mappedBy = "organization")
    @JsonIgnore
    private Vendor vendor;

    @OneToOne(mappedBy = "organization")
    @JsonIgnore
    private PurchaseOrderLine purchaseOrderLine;

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

    public Organization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public Organization organizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
        return this;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public Organization orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCountryKey() {
        return countryKey;
    }

    public Organization countryKey(String countryKey) {
        this.countryKey = countryKey;
        return this;
    }

    public void setCountryKey(String countryKey) {
        this.countryKey = countryKey;
    }

    public String getFinanceSystem() {
        return financeSystem;
    }

    public Organization financeSystem(String financeSystem) {
        this.financeSystem = financeSystem;
        return this;
    }

    public void setFinanceSystem(String financeSystem) {
        this.financeSystem = financeSystem;
    }

    public String getVatRegistrationNo() {
        return vatRegistrationNo;
    }

    public Organization vatRegistrationNo(String vatRegistrationNo) {
        this.vatRegistrationNo = vatRegistrationNo;
        return this;
    }

    public void setVatRegistrationNo(String vatRegistrationNo) {
        this.vatRegistrationNo = vatRegistrationNo;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Organization currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getLanguage() {
        return language;
    }

    public Organization language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Organization createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Organization createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public Organization contactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
        return this;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Organization vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public PurchaseOrderLine getPurchaseOrderLine() {
        return purchaseOrderLine;
    }

    public Organization purchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLine = purchaseOrderLine;
        return this;
    }

    public void setPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLine = purchaseOrderLine;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return id != null && id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organization{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", organizationCode='" + getOrganizationCode() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", countryKey='" + getCountryKey() + "'" +
            ", financeSystem='" + getFinanceSystem() + "'" +
            ", vatRegistrationNo='" + getVatRegistrationNo() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", language='" + getLanguage() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
