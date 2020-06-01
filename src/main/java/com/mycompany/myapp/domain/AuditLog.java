package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * A AuditLog.
 */
@Entity
@Table(name = "audit_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "activity_creation_date")
    private ZonedDateTime activityCreationDate;

    @Column(name = "activity_start_time")
    private ZonedDateTime activityStartTime;

    @Column(name = "activity_end_time")
    private ZonedDateTime activityEndTime;

    @Column(name = "assigned_date")
    private ZonedDateTime assignedDate;

    @Column(name = "jhi_user")
    private String user;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @Column(name = "comments")
    private String comments;

    @Column(name = "complete_on")
    private ZonedDateTime completeOn;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToMany(mappedBy = "auditLog")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AuditLogDetails> auditLogDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "auditLogs", allowSetters = true)
    private Invoice invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public AuditLog activityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ZonedDateTime getActivityCreationDate() {
        return activityCreationDate;
    }

    public AuditLog activityCreationDate(ZonedDateTime activityCreationDate) {
        this.activityCreationDate = activityCreationDate;
        return this;
    }

    public void setActivityCreationDate(ZonedDateTime activityCreationDate) {
        this.activityCreationDate = activityCreationDate;
    }

    public ZonedDateTime getActivityStartTime() {
        return activityStartTime;
    }

    public AuditLog activityStartTime(ZonedDateTime activityStartTime) {
        this.activityStartTime = activityStartTime;
        return this;
    }

    public void setActivityStartTime(ZonedDateTime activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public ZonedDateTime getActivityEndTime() {
        return activityEndTime;
    }

    public AuditLog activityEndTime(ZonedDateTime activityEndTime) {
        this.activityEndTime = activityEndTime;
        return this;
    }

    public void setActivityEndTime(ZonedDateTime activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public ZonedDateTime getAssignedDate() {
        return assignedDate;
    }

    public AuditLog assignedDate(ZonedDateTime assignedDate) {
        this.assignedDate = assignedDate;
        return this;
    }

    public void setAssignedDate(ZonedDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getUser() {
        return user;
    }

    public AuditLog user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public AuditLog status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public AuditLog reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getComments() {
        return comments;
    }

    public AuditLog comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ZonedDateTime getCompleteOn() {
        return completeOn;
    }

    public AuditLog completeOn(ZonedDateTime completeOn) {
        this.completeOn = completeOn;
        return this;
    }

    public void setCompleteOn(ZonedDateTime completeOn) {
        this.completeOn = completeOn;
    }

    public Duration getDuration() {
        return duration;
    }

    public AuditLog duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public AuditLog createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuditLog createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Set<AuditLogDetails> getAuditLogDetails() {
        return auditLogDetails;
    }

    public AuditLog auditLogDetails(Set<AuditLogDetails> auditLogDetails) {
        this.auditLogDetails = auditLogDetails;
        return this;
    }

    public AuditLog addAuditLogDetails(AuditLogDetails auditLogDetails) {
        this.auditLogDetails.add(auditLogDetails);
        auditLogDetails.setAuditLog(this);
        return this;
    }

    public AuditLog removeAuditLogDetails(AuditLogDetails auditLogDetails) {
        this.auditLogDetails.remove(auditLogDetails);
        auditLogDetails.setAuditLog(null);
        return this;
    }

    public void setAuditLogDetails(Set<AuditLogDetails> auditLogDetails) {
        this.auditLogDetails = auditLogDetails;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public AuditLog invoice(Invoice invoice) {
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
        if (!(o instanceof AuditLog)) {
            return false;
        }
        return id != null && id.equals(((AuditLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AuditLog{" +
            "id=" + getId() +
            ", activityName='" + getActivityName() + "'" +
            ", activityCreationDate='" + getActivityCreationDate() + "'" +
            ", activityStartTime='" + getActivityStartTime() + "'" +
            ", activityEndTime='" + getActivityEndTime() + "'" +
            ", assignedDate='" + getAssignedDate() + "'" +
            ", user='" + getUser() + "'" +
            ", status='" + getStatus() + "'" +
            ", reason='" + getReason() + "'" +
            ", comments='" + getComments() + "'" +
            ", completeOn='" + getCompleteOn() + "'" +
            ", duration='" + getDuration() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
