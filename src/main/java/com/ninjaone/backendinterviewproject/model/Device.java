package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Device implements Persistable<String> {
    @Id
    private String id;
    @Column(nullable = true)
    private String systemName;
    private String type;

    @Transient
    private boolean isNewInstance;

    @Enumerated (EnumType.STRING)
    private OperatingSystem operatingSystem;

    public Device () {

    }

    /**
     * Just the id
     * @param id ID
     */
    public Device (String id) {
        this ();
        this.id = id;
    }

    public Device (String id, String systemName, String type, OperatingSystem operatingSystem) {
        this (id);
        this.systemName = systemName;
        this.type = type;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public String getId() {
        return id;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return this.isNewInstance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void configureAsNewInstance () {
        this.isNewInstance = true;
    }

    public String getSystemName() {
        return systemName;
    }

    public String getType() {
        return type;
    }
}
