package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.mycompany.myapp.domain.enumeration.CommunicationMode;

/**
 * A CommunicationConfiguration.
 */
@Entity
@Table(name = "communication_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommunicationConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "priority")
    private String priority;

    @Column(name = "retries")
    private String retries;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private CommunicationMode mode;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne
    @JoinColumn(unique = true)
    private MailBox mailBox;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public CommunicationConfiguration priority(String priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRetries() {
        return retries;
    }

    public CommunicationConfiguration retries(String retries) {
        this.retries = retries;
        return this;
    }

    public void setRetries(String retries) {
        this.retries = retries;
    }

    public CommunicationMode getMode() {
        return mode;
    }

    public CommunicationConfiguration mode(CommunicationMode mode) {
        this.mode = mode;
        return this;
    }

    public void setMode(CommunicationMode mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public CommunicationConfiguration name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public CommunicationConfiguration createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CommunicationConfiguration createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public MailBox getMailBox() {
        return mailBox;
    }

    public CommunicationConfiguration mailBox(MailBox mailBox) {
        this.mailBox = mailBox;
        return this;
    }

    public void setMailBox(MailBox mailBox) {
        this.mailBox = mailBox;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommunicationConfiguration)) {
            return false;
        }
        return id != null && id.equals(((CommunicationConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommunicationConfiguration{" +
            "id=" + getId() +
            ", priority='" + getPriority() + "'" +
            ", retries='" + getRetries() + "'" +
            ", mode='" + getMode() + "'" +
            ", name='" + getName() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
