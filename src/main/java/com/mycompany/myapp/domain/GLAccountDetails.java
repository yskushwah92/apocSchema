package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A GLAccountDetails.
 */
@Entity
@Table(name = "gl_account_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GLAccountDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "country")
    private String country;

    @Column(name = "chart_of_accounts")
    private String chartOfAccounts;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "is_balance_sheet")
    private Boolean isBalanceSheet;

    @Column(name = "text_for_criterion")
    private String textForCriterion;

    @Column(name = "some_text")
    private String someText;

    @Column(name = "tax_cat_tax_code")
    private String taxCatTaxCode;

    @Column(name = "currencykey")
    private String currencykey;

    @Column(name = "active_coa")
    private String activeCOA;

    @Column(name = "active_co_code")
    private String activeCoCode;

    @Column(name = "posting_without_tax_allowed")
    private String postingWithoutTaxAllowed;

    @Column(name = "posting_block")
    private String postingBlock;

    @Column(name = "post_at_profit_centre")
    private String postAtProfitCentre;

    @Column(name = "post_at_cost_centre")
    private String postAtCostCentre;

    @Column(name = "post_at_segment")
    private String postAtSegment;

    @Column(name = "post_at_internal_order")
    private String postAtInternalOrder;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(mappedBy = "gLAccountDetails")
    @JsonIgnore
    private Vendor vendor;

    @OneToOne(mappedBy = "gLAccountDetails")
    @JsonIgnore
    private PurchaseOrderLine purchaseOrderLine;

    @OneToOne(mappedBy = "gLAccountDetails")
    @JsonIgnore
    private InvoiceLineItem invoiceLineItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public GLAccountDetails accountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCountry() {
        return country;
    }

    public GLAccountDetails country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getChartOfAccounts() {
        return chartOfAccounts;
    }

    public GLAccountDetails chartOfAccounts(String chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
        return this;
    }

    public void setChartOfAccounts(String chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public GLAccountDetails categoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAccountType() {
        return accountType;
    }

    public GLAccountDetails accountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean isIsBalanceSheet() {
        return isBalanceSheet;
    }

    public GLAccountDetails isBalanceSheet(Boolean isBalanceSheet) {
        this.isBalanceSheet = isBalanceSheet;
        return this;
    }

    public void setIsBalanceSheet(Boolean isBalanceSheet) {
        this.isBalanceSheet = isBalanceSheet;
    }

    public String getTextForCriterion() {
        return textForCriterion;
    }

    public GLAccountDetails textForCriterion(String textForCriterion) {
        this.textForCriterion = textForCriterion;
        return this;
    }

    public void setTextForCriterion(String textForCriterion) {
        this.textForCriterion = textForCriterion;
    }

    public String getSomeText() {
        return someText;
    }

    public GLAccountDetails someText(String someText) {
        this.someText = someText;
        return this;
    }

    public void setSomeText(String someText) {
        this.someText = someText;
    }

    public String getTaxCatTaxCode() {
        return taxCatTaxCode;
    }

    public GLAccountDetails taxCatTaxCode(String taxCatTaxCode) {
        this.taxCatTaxCode = taxCatTaxCode;
        return this;
    }

    public void setTaxCatTaxCode(String taxCatTaxCode) {
        this.taxCatTaxCode = taxCatTaxCode;
    }

    public String getCurrencykey() {
        return currencykey;
    }

    public GLAccountDetails currencykey(String currencykey) {
        this.currencykey = currencykey;
        return this;
    }

    public void setCurrencykey(String currencykey) {
        this.currencykey = currencykey;
    }

    public String getActiveCOA() {
        return activeCOA;
    }

    public GLAccountDetails activeCOA(String activeCOA) {
        this.activeCOA = activeCOA;
        return this;
    }

    public void setActiveCOA(String activeCOA) {
        this.activeCOA = activeCOA;
    }

    public String getActiveCoCode() {
        return activeCoCode;
    }

    public GLAccountDetails activeCoCode(String activeCoCode) {
        this.activeCoCode = activeCoCode;
        return this;
    }

    public void setActiveCoCode(String activeCoCode) {
        this.activeCoCode = activeCoCode;
    }

    public String getPostingWithoutTaxAllowed() {
        return postingWithoutTaxAllowed;
    }

    public GLAccountDetails postingWithoutTaxAllowed(String postingWithoutTaxAllowed) {
        this.postingWithoutTaxAllowed = postingWithoutTaxAllowed;
        return this;
    }

    public void setPostingWithoutTaxAllowed(String postingWithoutTaxAllowed) {
        this.postingWithoutTaxAllowed = postingWithoutTaxAllowed;
    }

    public String getPostingBlock() {
        return postingBlock;
    }

    public GLAccountDetails postingBlock(String postingBlock) {
        this.postingBlock = postingBlock;
        return this;
    }

    public void setPostingBlock(String postingBlock) {
        this.postingBlock = postingBlock;
    }

    public String getPostAtProfitCentre() {
        return postAtProfitCentre;
    }

    public GLAccountDetails postAtProfitCentre(String postAtProfitCentre) {
        this.postAtProfitCentre = postAtProfitCentre;
        return this;
    }

    public void setPostAtProfitCentre(String postAtProfitCentre) {
        this.postAtProfitCentre = postAtProfitCentre;
    }

    public String getPostAtCostCentre() {
        return postAtCostCentre;
    }

    public GLAccountDetails postAtCostCentre(String postAtCostCentre) {
        this.postAtCostCentre = postAtCostCentre;
        return this;
    }

    public void setPostAtCostCentre(String postAtCostCentre) {
        this.postAtCostCentre = postAtCostCentre;
    }

    public String getPostAtSegment() {
        return postAtSegment;
    }

    public GLAccountDetails postAtSegment(String postAtSegment) {
        this.postAtSegment = postAtSegment;
        return this;
    }

    public void setPostAtSegment(String postAtSegment) {
        this.postAtSegment = postAtSegment;
    }

    public String getPostAtInternalOrder() {
        return postAtInternalOrder;
    }

    public GLAccountDetails postAtInternalOrder(String postAtInternalOrder) {
        this.postAtInternalOrder = postAtInternalOrder;
        return this;
    }

    public void setPostAtInternalOrder(String postAtInternalOrder) {
        this.postAtInternalOrder = postAtInternalOrder;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public GLAccountDetails isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public GLAccountDetails createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public GLAccountDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public GLAccountDetails vendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public PurchaseOrderLine getPurchaseOrderLine() {
        return purchaseOrderLine;
    }

    public GLAccountDetails purchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLine = purchaseOrderLine;
        return this;
    }

    public void setPurchaseOrderLine(PurchaseOrderLine purchaseOrderLine) {
        this.purchaseOrderLine = purchaseOrderLine;
    }

    public InvoiceLineItem getInvoiceLineItem() {
        return invoiceLineItem;
    }

    public GLAccountDetails invoiceLineItem(InvoiceLineItem invoiceLineItem) {
        this.invoiceLineItem = invoiceLineItem;
        return this;
    }

    public void setInvoiceLineItem(InvoiceLineItem invoiceLineItem) {
        this.invoiceLineItem = invoiceLineItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GLAccountDetails)) {
            return false;
        }
        return id != null && id.equals(((GLAccountDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GLAccountDetails{" +
            "id=" + getId() +
            ", accountNo='" + getAccountNo() + "'" +
            ", country='" + getCountry() + "'" +
            ", chartOfAccounts='" + getChartOfAccounts() + "'" +
            ", categoryId='" + getCategoryId() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", isBalanceSheet='" + isIsBalanceSheet() + "'" +
            ", textForCriterion='" + getTextForCriterion() + "'" +
            ", someText='" + getSomeText() + "'" +
            ", taxCatTaxCode='" + getTaxCatTaxCode() + "'" +
            ", currencykey='" + getCurrencykey() + "'" +
            ", activeCOA='" + getActiveCOA() + "'" +
            ", activeCoCode='" + getActiveCoCode() + "'" +
            ", postingWithoutTaxAllowed='" + getPostingWithoutTaxAllowed() + "'" +
            ", postingBlock='" + getPostingBlock() + "'" +
            ", postAtProfitCentre='" + getPostAtProfitCentre() + "'" +
            ", postAtCostCentre='" + getPostAtCostCentre() + "'" +
            ", postAtSegment='" + getPostAtSegment() + "'" +
            ", postAtInternalOrder='" + getPostAtInternalOrder() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
