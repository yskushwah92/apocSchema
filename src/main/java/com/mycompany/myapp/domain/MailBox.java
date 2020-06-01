package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A MailBox.
 */
@Entity
@Table(name = "mail_box")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MailBox implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "server")
    private String server;

    @Column(name = "address")
    private String address;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(mappedBy = "mailBox")
    @JsonIgnore
    private CommunicationConfiguration communicationConfiguration;

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

    public MailBox name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public MailBox server(String server) {
        this.server = server;
        return this;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getAddress() {
        return address;
    }

    public MailBox address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisplayName() {
        return displayName;
    }

    public MailBox displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public MailBox createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MailBox createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public CommunicationConfiguration getCommunicationConfiguration() {
        return communicationConfiguration;
    }

    public MailBox communicationConfiguration(CommunicationConfiguration communicationConfiguration) {
        this.communicationConfiguration = communicationConfiguration;
        return this;
    }

    public void setCommunicationConfiguration(CommunicationConfiguration communicationConfiguration) {
        this.communicationConfiguration = communicationConfiguration;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MailBox)) {
            return false;
        }
        return id != null && id.equals(((MailBox) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MailBox{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", server='" + getServer() + "'" +
            ", address='" + getAddress() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            "}";
    }
}
