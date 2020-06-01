package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.Currency;

/**
 * A CurrencyExchange.
 */
@Entity
@Table(name = "currency_exchange")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CurrencyExchange implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "country")
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_currency")
    private Currency fromCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_currency")
    private Currency toCurrency;

    @Column(name = "validfrom")
    private ZonedDateTime validfrom;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "exchange_type")
    private String exchangeType;

    @Column(name = "ratio_from")
    private String ratioFrom;

    @Column(name = "ratio_to")
    private String ratioTo;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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

    public String getCountry() {
        return country;
    }

    public CurrencyExchange country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public CurrencyExchange fromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
        return this;
    }

    public void setFromCurrency(Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public CurrencyExchange toCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
        return this;
    }

    public void setToCurrency(Currency toCurrency) {
        this.toCurrency = toCurrency;
    }

    public ZonedDateTime getValidfrom() {
        return validfrom;
    }

    public CurrencyExchange validfrom(ZonedDateTime validfrom) {
        this.validfrom = validfrom;
        return this;
    }

    public void setValidfrom(ZonedDateTime validfrom) {
        this.validfrom = validfrom;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public CurrencyExchange exchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public CurrencyExchange exchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
        return this;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getRatioFrom() {
        return ratioFrom;
    }

    public CurrencyExchange ratioFrom(String ratioFrom) {
        this.ratioFrom = ratioFrom;
        return this;
    }

    public void setRatioFrom(String ratioFrom) {
        this.ratioFrom = ratioFrom;
    }

    public String getRatioTo() {
        return ratioTo;
    }

    public CurrencyExchange ratioTo(String ratioTo) {
        this.ratioTo = ratioTo;
        return this;
    }

    public void setRatioTo(String ratioTo) {
        this.ratioTo = ratioTo;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public CurrencyExchange isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public CurrencyExchange isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public CurrencyExchange createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CurrencyExchange createdBy(String createdBy) {
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
        if (!(o instanceof CurrencyExchange)) {
            return false;
        }
        return id != null && id.equals(((CurrencyExchange) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CurrencyExchange{" +
            "id=" + getId() +
            ", country='" + getCountry() + "'" +
            ", fromCurrency='" + getFromCurrency() + "'" +
            ", toCurrency='" + getToCurrency() + "'" +
            ", validfrom='" + getValidfrom() + "'" +
            ", exchangeRate=" + getExchangeRate() +
            ", exchangeType='" + getExchangeType() + "'" +
            ", ratioFrom='" + getRatioFrom() + "'" +
            ", ratioTo='" + getRatioTo() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
