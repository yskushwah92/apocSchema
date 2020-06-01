package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ContactDetails.
 */
@Entity
@Table(name = "contact_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContactDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "po_box")
    private String poBox;

    @Column(name = "po_box_code")
    private String poBoxCode;

    @Column(name = "country")
    private String country;

    @Column(name = "telephone_no")
    private String telephoneNo;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(mappedBy = "contactDetails")
    @JsonIgnore
    private Vendor vendor;

    @OneToOne(mappedBy = "contactDetails")
    @JsonIgnore
    private Organization organization;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public ContactDetails street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public ContactDetails zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public ContactDetails city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public ContactDetails district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPoBox() {
        return poBox;
    }

    public ContactDetails poBox(String poBox) {
        this.poBox = poBox;
        return this;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getPoBoxCode() {
        return poBoxCode;
    }

    public ContactDetails poBoxCode(String poBoxCode) {
        this.poBoxCode = poBoxCode;
        return this;
    }

    public void setPoBoxCode(String poBoxCode) {
        this.poBoxCode = poBoxCode;
    }

    public String getCountry() {
        return country;
    }

    public ContactDetails country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public ContactDetails telephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
        return this;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public ContactDetails faxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
        return this;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public ContactDetails email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ContactDetails createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ContactDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public ContactDetails vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Organization getOrganization() {
        return organization;
    }

    public ContactDetails organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContactDetails)) {
            return false;
        }
        return id != null && id.equals(((ContactDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContactDetails{" +
            "id=" + getId() +
            ", street='" + getStreet() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", city='" + getCity() + "'" +
            ", district='" + getDistrict() + "'" +
            ", poBox='" + getPoBox() + "'" +
            ", poBoxCode='" + getPoBoxCode() + "'" +
            ", country='" + getCountry() + "'" +
            ", telephoneNo='" + getTelephoneNo() + "'" +
            ", faxNumber='" + getFaxNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
