package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.Currency;

/**
 * A InvoiceHeader.
 */
@Entity
@Table(name = "invoice_header")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InvoiceHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "company_code")
    private String companyCode;

    @Column(name = "legal_entity_name")
    private String legalEntityName;

    @Column(name = "comments")
    private String comments;

    @Column(name = "gross_amount")
    private String grossAmount;

    @Column(name = "net_amount")
    private String netAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "country")
    private String country;

    @Column(name = "country_key")
    private String countryKey;

    @Column(name = "language_key")
    private String languageKey;

    @Column(name = "erp_reference_number")
    private String erpReferenceNumber;

    @Column(name = "po_box_code")
    private String poBoxCode;

    @Column(name = "scan_date")
    private ZonedDateTime scanDate;

    @Column(name = "received_date")
    private ZonedDateTime receivedDate;

    @Column(name = "case_creation_date")
    private ZonedDateTime caseCreationDate;

    @Column(name = "number_of_scanned_pages")
    private String numberOfScannedPages;

    @Column(name = "sla")
    private String sla;

    @Column(name = "sla_expiration_date")
    private ZonedDateTime slaExpirationDate;

    @Column(name = "trading_partner")
    private String tradingPartner;

    @Column(name = "delivery_date")
    private ZonedDateTime deliveryDate;

    @Column(name = "delivery_note_number")
    private ZonedDateTime deliveryNoteNumber;

    @Column(name = "recepient_email")
    private String recepientEmail;

    @Column(name = "is_case_close")
    private String isCaseClose;

    @Column(name = "ocr_required")
    private Boolean ocrRequired;

    @Column(name = "barcode")
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "functional_currency")
    private Currency functionalCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "reporting_currency")
    private Currency reportingCurrency;

    @Column(name = "approver_required")
    private String approverRequired;

    @Column(name = "site_code")
    private String siteCode;

    @Column(name = "sort_code")
    private String sortCode;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "additional_charges")
    private Double additionalCharges;

    @Column(name = "sum_line_amount")
    private Double sumLineAmount;

    @Column(name = "conversion_rate")
    private Double conversionRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_currency")
    private Currency paymentCurrency;

    @Column(name = "liability_account")
    private String liabilityAccount;

    @Column(name = "posting_date")
    private ZonedDateTime postingDate;

    @Column(name = "term_date")
    private ZonedDateTime termDate;

    @Column(name = "shipping_amount")
    private Double shippingAmount;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private GRNDetails gRNDetails;

    @OneToOne(mappedBy = "invoiceHeader")
    @JsonIgnore
    private Invoice invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public InvoiceHeader companyCode(String companyCode) {
        this.companyCode = companyCode;
        return this;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getLegalEntityName() {
        return legalEntityName;
    }

    public InvoiceHeader legalEntityName(String legalEntityName) {
        this.legalEntityName = legalEntityName;
        return this;
    }

    public void setLegalEntityName(String legalEntityName) {
        this.legalEntityName = legalEntityName;
    }

    public String getComments() {
        return comments;
    }

    public InvoiceHeader comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public InvoiceHeader grossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
        return this;
    }

    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public InvoiceHeader netAmount(String netAmount) {
        this.netAmount = netAmount;
        return this;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public InvoiceHeader currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public InvoiceHeader country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryKey() {
        return countryKey;
    }

    public InvoiceHeader countryKey(String countryKey) {
        this.countryKey = countryKey;
        return this;
    }

    public void setCountryKey(String countryKey) {
        this.countryKey = countryKey;
    }

    public String getLanguageKey() {
        return languageKey;
    }

    public InvoiceHeader languageKey(String languageKey) {
        this.languageKey = languageKey;
        return this;
    }

    public void setLanguageKey(String languageKey) {
        this.languageKey = languageKey;
    }

    public String getErpReferenceNumber() {
        return erpReferenceNumber;
    }

    public InvoiceHeader erpReferenceNumber(String erpReferenceNumber) {
        this.erpReferenceNumber = erpReferenceNumber;
        return this;
    }

    public void setErpReferenceNumber(String erpReferenceNumber) {
        this.erpReferenceNumber = erpReferenceNumber;
    }

    public String getPoBoxCode() {
        return poBoxCode;
    }

    public InvoiceHeader poBoxCode(String poBoxCode) {
        this.poBoxCode = poBoxCode;
        return this;
    }

    public void setPoBoxCode(String poBoxCode) {
        this.poBoxCode = poBoxCode;
    }

    public ZonedDateTime getScanDate() {
        return scanDate;
    }

    public InvoiceHeader scanDate(ZonedDateTime scanDate) {
        this.scanDate = scanDate;
        return this;
    }

    public void setScanDate(ZonedDateTime scanDate) {
        this.scanDate = scanDate;
    }

    public ZonedDateTime getReceivedDate() {
        return receivedDate;
    }

    public InvoiceHeader receivedDate(ZonedDateTime receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public void setReceivedDate(ZonedDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public ZonedDateTime getCaseCreationDate() {
        return caseCreationDate;
    }

    public InvoiceHeader caseCreationDate(ZonedDateTime caseCreationDate) {
        this.caseCreationDate = caseCreationDate;
        return this;
    }

    public void setCaseCreationDate(ZonedDateTime caseCreationDate) {
        this.caseCreationDate = caseCreationDate;
    }

    public String getNumberOfScannedPages() {
        return numberOfScannedPages;
    }

    public InvoiceHeader numberOfScannedPages(String numberOfScannedPages) {
        this.numberOfScannedPages = numberOfScannedPages;
        return this;
    }

    public void setNumberOfScannedPages(String numberOfScannedPages) {
        this.numberOfScannedPages = numberOfScannedPages;
    }

    public String getSla() {
        return sla;
    }

    public InvoiceHeader sla(String sla) {
        this.sla = sla;
        return this;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public ZonedDateTime getSlaExpirationDate() {
        return slaExpirationDate;
    }

    public InvoiceHeader slaExpirationDate(ZonedDateTime slaExpirationDate) {
        this.slaExpirationDate = slaExpirationDate;
        return this;
    }

    public void setSlaExpirationDate(ZonedDateTime slaExpirationDate) {
        this.slaExpirationDate = slaExpirationDate;
    }

    public String getTradingPartner() {
        return tradingPartner;
    }

    public InvoiceHeader tradingPartner(String tradingPartner) {
        this.tradingPartner = tradingPartner;
        return this;
    }

    public void setTradingPartner(String tradingPartner) {
        this.tradingPartner = tradingPartner;
    }

    public ZonedDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public InvoiceHeader deliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public void setDeliveryDate(ZonedDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ZonedDateTime getDeliveryNoteNumber() {
        return deliveryNoteNumber;
    }

    public InvoiceHeader deliveryNoteNumber(ZonedDateTime deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
        return this;
    }

    public void setDeliveryNoteNumber(ZonedDateTime deliveryNoteNumber) {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    public String getRecepientEmail() {
        return recepientEmail;
    }

    public InvoiceHeader recepientEmail(String recepientEmail) {
        this.recepientEmail = recepientEmail;
        return this;
    }

    public void setRecepientEmail(String recepientEmail) {
        this.recepientEmail = recepientEmail;
    }

    public String getIsCaseClose() {
        return isCaseClose;
    }

    public InvoiceHeader isCaseClose(String isCaseClose) {
        this.isCaseClose = isCaseClose;
        return this;
    }

    public void setIsCaseClose(String isCaseClose) {
        this.isCaseClose = isCaseClose;
    }

    public Boolean isOcrRequired() {
        return ocrRequired;
    }

    public InvoiceHeader ocrRequired(Boolean ocrRequired) {
        this.ocrRequired = ocrRequired;
        return this;
    }

    public void setOcrRequired(Boolean ocrRequired) {
        this.ocrRequired = ocrRequired;
    }

    public String getBarcode() {
        return barcode;
    }

    public InvoiceHeader barcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Currency getFunctionalCurrency() {
        return functionalCurrency;
    }

    public InvoiceHeader functionalCurrency(Currency functionalCurrency) {
        this.functionalCurrency = functionalCurrency;
        return this;
    }

    public void setFunctionalCurrency(Currency functionalCurrency) {
        this.functionalCurrency = functionalCurrency;
    }

    public Currency getReportingCurrency() {
        return reportingCurrency;
    }

    public InvoiceHeader reportingCurrency(Currency reportingCurrency) {
        this.reportingCurrency = reportingCurrency;
        return this;
    }

    public void setReportingCurrency(Currency reportingCurrency) {
        this.reportingCurrency = reportingCurrency;
    }

    public String getApproverRequired() {
        return approverRequired;
    }

    public InvoiceHeader approverRequired(String approverRequired) {
        this.approverRequired = approverRequired;
        return this;
    }

    public void setApproverRequired(String approverRequired) {
        this.approverRequired = approverRequired;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public InvoiceHeader siteCode(String siteCode) {
        this.siteCode = siteCode;
        return this;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSortCode() {
        return sortCode;
    }

    public InvoiceHeader sortCode(String sortCode) {
        this.sortCode = sortCode;
        return this;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public Double getDiscount() {
        return discount;
    }

    public InvoiceHeader discount(Double discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getAdditionalCharges() {
        return additionalCharges;
    }

    public InvoiceHeader additionalCharges(Double additionalCharges) {
        this.additionalCharges = additionalCharges;
        return this;
    }

    public void setAdditionalCharges(Double additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    public Double getSumLineAmount() {
        return sumLineAmount;
    }

    public InvoiceHeader sumLineAmount(Double sumLineAmount) {
        this.sumLineAmount = sumLineAmount;
        return this;
    }

    public void setSumLineAmount(Double sumLineAmount) {
        this.sumLineAmount = sumLineAmount;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public InvoiceHeader conversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
        return this;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Currency getPaymentCurrency() {
        return paymentCurrency;
    }

    public InvoiceHeader paymentCurrency(Currency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
        return this;
    }

    public void setPaymentCurrency(Currency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public String getLiabilityAccount() {
        return liabilityAccount;
    }

    public InvoiceHeader liabilityAccount(String liabilityAccount) {
        this.liabilityAccount = liabilityAccount;
        return this;
    }

    public void setLiabilityAccount(String liabilityAccount) {
        this.liabilityAccount = liabilityAccount;
    }

    public ZonedDateTime getPostingDate() {
        return postingDate;
    }

    public InvoiceHeader postingDate(ZonedDateTime postingDate) {
        this.postingDate = postingDate;
        return this;
    }

    public void setPostingDate(ZonedDateTime postingDate) {
        this.postingDate = postingDate;
    }

    public ZonedDateTime getTermDate() {
        return termDate;
    }

    public InvoiceHeader termDate(ZonedDateTime termDate) {
        this.termDate = termDate;
        return this;
    }

    public void setTermDate(ZonedDateTime termDate) {
        this.termDate = termDate;
    }

    public Double getShippingAmount() {
        return shippingAmount;
    }

    public InvoiceHeader shippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
        return this;
    }

    public void setShippingAmount(Double shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public InvoiceHeader createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public InvoiceHeader createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public GRNDetails getGRNDetails() {
        return gRNDetails;
    }

    public InvoiceHeader gRNDetails(GRNDetails gRNDetails) {
        this.gRNDetails = gRNDetails;
        return this;
    }

    public void setGRNDetails(GRNDetails gRNDetails) {
        this.gRNDetails = gRNDetails;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public InvoiceHeader invoice(Invoice invoice) {
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
        if (!(o instanceof InvoiceHeader)) {
            return false;
        }
        return id != null && id.equals(((InvoiceHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InvoiceHeader{" +
            "id=" + getId() +
            ", companyCode='" + getCompanyCode() + "'" +
            ", legalEntityName='" + getLegalEntityName() + "'" +
            ", comments='" + getComments() + "'" +
            ", grossAmount='" + getGrossAmount() + "'" +
            ", netAmount='" + getNetAmount() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", country='" + getCountry() + "'" +
            ", countryKey='" + getCountryKey() + "'" +
            ", languageKey='" + getLanguageKey() + "'" +
            ", erpReferenceNumber='" + getErpReferenceNumber() + "'" +
            ", poBoxCode='" + getPoBoxCode() + "'" +
            ", scanDate='" + getScanDate() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            ", caseCreationDate='" + getCaseCreationDate() + "'" +
            ", numberOfScannedPages='" + getNumberOfScannedPages() + "'" +
            ", sla='" + getSla() + "'" +
            ", slaExpirationDate='" + getSlaExpirationDate() + "'" +
            ", tradingPartner='" + getTradingPartner() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", deliveryNoteNumber='" + getDeliveryNoteNumber() + "'" +
            ", recepientEmail='" + getRecepientEmail() + "'" +
            ", isCaseClose='" + getIsCaseClose() + "'" +
            ", ocrRequired='" + isOcrRequired() + "'" +
            ", barcode='" + getBarcode() + "'" +
            ", functionalCurrency='" + getFunctionalCurrency() + "'" +
            ", reportingCurrency='" + getReportingCurrency() + "'" +
            ", approverRequired='" + getApproverRequired() + "'" +
            ", siteCode='" + getSiteCode() + "'" +
            ", sortCode='" + getSortCode() + "'" +
            ", discount=" + getDiscount() +
            ", additionalCharges=" + getAdditionalCharges() +
            ", sumLineAmount=" + getSumLineAmount() +
            ", conversionRate=" + getConversionRate() +
            ", paymentCurrency='" + getPaymentCurrency() + "'" +
            ", liabilityAccount='" + getLiabilityAccount() + "'" +
            ", postingDate='" + getPostingDate() + "'" +
            ", termDate='" + getTermDate() + "'" +
            ", shippingAmount=" + getShippingAmount() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
