package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.AssignmentMode;

/**
 * A Assignment.
 */
@Entity
@Table(name = "assignment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Assignment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "assignment_date")
    private ZonedDateTime assignmentDate;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "assigned_by")
    private String assignedBy;

    @Column(name = "set_sla")
    private ZonedDateTime setSLA;

    @Column(name = "assignment_status")
    private String assignmentStatus;

    @Column(name = "percentage_completed")
    private Integer percentageCompleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "assignment_mode")
    private AssignmentMode assignmentMode;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne
    @JsonIgnoreProperties(value = "assignments", allowSetters = true)
    private Invoice invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getAssignmentDate() {
        return assignmentDate;
    }

    public Assignment assignmentDate(ZonedDateTime assignmentDate) {
        this.assignmentDate = assignmentDate;
        return this;
    }

    public void setAssignmentDate(ZonedDateTime assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public Assignment assignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        return this;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public Assignment assignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
        return this;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public ZonedDateTime getSetSLA() {
        return setSLA;
    }

    public Assignment setSLA(ZonedDateTime setSLA) {
        this.setSLA = setSLA;
        return this;
    }

    public void setSetSLA(ZonedDateTime setSLA) {
        this.setSLA = setSLA;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public Assignment assignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public Integer getPercentageCompleted() {
        return percentageCompleted;
    }

    public Assignment percentageCompleted(Integer percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
        return this;
    }

    public void setPercentageCompleted(Integer percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
    }

    public AssignmentMode getAssignmentMode() {
        return assignmentMode;
    }

    public Assignment assignmentMode(AssignmentMode assignmentMode) {
        this.assignmentMode = assignmentMode;
        return this;
    }

    public void setAssignmentMode(AssignmentMode assignmentMode) {
        this.assignmentMode = assignmentMode;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Assignment createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Assignment createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Assignment invoice(Invoice invoice) {
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
        if (!(o instanceof Assignment)) {
            return false;
        }
        return id != null && id.equals(((Assignment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Assignment{" +
            "id=" + getId() +
            ", assignmentDate='" + getAssignmentDate() + "'" +
            ", assignedTo='" + getAssignedTo() + "'" +
            ", assignedBy='" + getAssignedBy() + "'" +
            ", setSLA='" + getSetSLA() + "'" +
            ", assignmentStatus='" + getAssignmentStatus() + "'" +
            ", percentageCompleted=" + getPercentageCompleted() +
            ", assignmentMode='" + getAssignmentMode() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
