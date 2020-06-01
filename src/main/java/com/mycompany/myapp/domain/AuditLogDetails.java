package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A AuditLogDetails.
 */
@Entity
@Table(name = "audit_log_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuditLogDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "assigned_by")
    private String assignedBy;

    @Column(name = "actor")
    private String actor;

    @Column(name = "status")
    private String status;

    @Column(name = "status_code")
    private String statusCode;

    @Column(name = "reason")
    private String reason;

    @Column(name = "reason_code")
    private String reasonCode;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "comments")
    private String comments;

    @Column(name = "delegate")
    private String delegate;

    @Column(name = "delegated_flag")
    private Boolean delegatedFlag;

    @Column(name = "met_sla")
    private String metSLA;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "auditLogDetails", allowSetters = true)
    private AuditLog auditLog;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public AuditLogDetails assignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
        return this;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getActor() {
        return actor;
    }

    public AuditLogDetails actor(String actor) {
        this.actor = actor;
        return this;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getStatus() {
        return status;
    }

    public AuditLogDetails status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public AuditLogDetails statusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return reason;
    }

    public AuditLogDetails reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public AuditLogDetails reasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
        return this;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public ZonedDateTime getCreatedOn() {
        return createdOn;
    }

    public AuditLogDetails createdOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getComments() {
        return comments;
    }

    public AuditLogDetails comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDelegate() {
        return delegate;
    }

    public AuditLogDetails delegate(String delegate) {
        this.delegate = delegate;
        return this;
    }

    public void setDelegate(String delegate) {
        this.delegate = delegate;
    }

    public Boolean isDelegatedFlag() {
        return delegatedFlag;
    }

    public AuditLogDetails delegatedFlag(Boolean delegatedFlag) {
        this.delegatedFlag = delegatedFlag;
        return this;
    }

    public void setDelegatedFlag(Boolean delegatedFlag) {
        this.delegatedFlag = delegatedFlag;
    }

    public String getMetSLA() {
        return metSLA;
    }

    public AuditLogDetails metSLA(String metSLA) {
        this.metSLA = metSLA;
        return this;
    }

    public void setMetSLA(String metSLA) {
        this.metSLA = metSLA;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public AuditLogDetails createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuditLogDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public AuditLog getAuditLog() {
        return auditLog;
    }

    public AuditLogDetails auditLog(AuditLog auditLog) {
        this.auditLog = auditLog;
        return this;
    }

    public void setAuditLog(AuditLog auditLog) {
        this.auditLog = auditLog;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuditLogDetails)) {
            return false;
        }
        return id != null && id.equals(((AuditLogDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AuditLogDetails{" +
            "id=" + getId() +
            ", assignedBy='" + getAssignedBy() + "'" +
            ", actor='" + getActor() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusCode='" + getStatusCode() + "'" +
            ", reason='" + getReason() + "'" +
            ", reasonCode='" + getReasonCode() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", comments='" + getComments() + "'" +
            ", delegate='" + getDelegate() + "'" +
            ", delegatedFlag='" + isDelegatedFlag() + "'" +
            ", metSLA='" + getMetSLA() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
