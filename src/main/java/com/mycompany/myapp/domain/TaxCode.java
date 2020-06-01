package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A TaxCode.
 */
@Entity
@Table(name = "tax_code")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TaxCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "country_key")
    private String countryKey;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "description")
    private String description;

    @Column(name = "tax_code_description")
    private String taxCodeDescription;

    @Column(name = "tax_rate")
    private Double taxRate;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

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

    public TaxCode name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public TaxCode country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryKey() {
        return countryKey;
    }

    public TaxCode countryKey(String countryKey) {
        this.countryKey = countryKey;
        return this;
    }

    public void setCountryKey(String countryKey) {
        this.countryKey = countryKey;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public TaxCode taxCode(String taxCode) {
        this.taxCode = taxCode;
        return this;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getDescription() {
        return description;
    }

    public TaxCode description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaxCodeDescription() {
        return taxCodeDescription;
    }

    public TaxCode taxCodeDescription(String taxCodeDescription) {
        this.taxCodeDescription = taxCodeDescription;
        return this;
    }

    public void setTaxCodeDescription(String taxCodeDescription) {
        this.taxCodeDescription = taxCodeDescription;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public TaxCode taxRate(Double taxRate) {
        this.taxRate = taxRate;
        return this;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public TaxCode createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TaxCode createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaxCode)) {
            return false;
        }
        return id != null && id.equals(((TaxCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaxCode{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", country='" + getCountry() + "'" +
            ", countryKey='" + getCountryKey() + "'" +
            ", taxCode='" + getTaxCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", taxCodeDescription='" + getTaxCodeDescription() + "'" +
            ", taxRate=" + getTaxRate() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
