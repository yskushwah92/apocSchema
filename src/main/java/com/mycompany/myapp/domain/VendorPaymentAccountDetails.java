package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.PaymentAccountType;

import com.mycompany.myapp.domain.enumeration.PaymentMethod;

/**
 * A VendorPaymentAccountDetails.
 */
@Entity
@Table(name = "vendor_payment_account_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VendorPaymentAccountDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PaymentAccountType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private PaymentMethod method;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_code")
    private String accountCode;

    @Column(name = "account_key")
    private String accountKey;

    @Column(name = "bank_account_type")
    private String bankAccountType;

    @Column(name = "iban")
    private String iban;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "vendorPaymentAccountDetails", allowSetters = true)
    private Vendor vendor;

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

    public VendorPaymentAccountDetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentAccountType getType() {
        return type;
    }

    public VendorPaymentAccountDetails type(PaymentAccountType type) {
        this.type = type;
        return this;
    }

    public void setType(PaymentAccountType type) {
        this.type = type;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public VendorPaymentAccountDetails method(PaymentMethod method) {
        this.method = method;
        return this;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public VendorPaymentAccountDetails accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public VendorPaymentAccountDetails accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public VendorPaymentAccountDetails accountCode(String accountCode) {
        this.accountCode = accountCode;
        return this;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public VendorPaymentAccountDetails accountKey(String accountKey) {
        this.accountKey = accountKey;
        return this;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public VendorPaymentAccountDetails bankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
        return this;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getIban() {
        return iban;
    }

    public VendorPaymentAccountDetails iban(String iban) {
        this.iban = iban;
        return this;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public VendorPaymentAccountDetails isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public VendorPaymentAccountDetails createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorPaymentAccountDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public VendorPaymentAccountDetails vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendorPaymentAccountDetails)) {
            return false;
        }
        return id != null && id.equals(((VendorPaymentAccountDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VendorPaymentAccountDetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", method='" + getMethod() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", accountCode='" + getAccountCode() + "'" +
            ", accountKey='" + getAccountKey() + "'" +
            ", bankAccountType='" + getBankAccountType() + "'" +
            ", iban='" + getIban() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
