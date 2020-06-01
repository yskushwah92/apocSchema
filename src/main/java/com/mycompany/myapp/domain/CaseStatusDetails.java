package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A CaseStatusDetails.
 */
@Entity
@Table(name = "case_status_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CaseStatusDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "comments")
    private String comments;

    @Column(name = "time_elapased_in_current_status")
    private String timeElapasedInCurrentStatus;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "caseStatusDetails", allowSetters = true)
    private InvoiceLineItem invoiceLineItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public CaseStatusDetails status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public CaseStatusDetails assignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getComments() {
        return comments;
    }

    public CaseStatusDetails comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTimeElapasedInCurrentStatus() {
        return timeElapasedInCurrentStatus;
    }

    public CaseStatusDetails timeElapasedInCurrentStatus(String timeElapasedInCurrentStatus) {
        this.timeElapasedInCurrentStatus = timeElapasedInCurrentStatus;
        return this;
    }

    public void setTimeElapasedInCurrentStatus(String timeElapasedInCurrentStatus) {
        this.timeElapasedInCurrentStatus = timeElapasedInCurrentStatus;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public CaseStatusDetails createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CaseStatusDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public InvoiceLineItem getInvoiceLineItem() {
        return invoiceLineItem;
    }

    public CaseStatusDetails invoiceLineItem(InvoiceLineItem invoiceLineItem) {
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
        if (!(o instanceof CaseStatusDetails)) {
            return false;
        }
        return id != null && id.equals(((CaseStatusDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaseStatusDetails{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", assignee='" + getAssignee() + "'" +
            ", comments='" + getComments() + "'" +
            ", timeElapasedInCurrentStatus='" + getTimeElapasedInCurrentStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
