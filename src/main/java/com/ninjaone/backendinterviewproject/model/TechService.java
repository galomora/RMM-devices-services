package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class TechService implements Persistable<String> {
    @Id
    private String name;
    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OperatingSystem operatingSystem;

    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private TechServiceType type;

    @Transient
    private boolean isNewInstance;

    public TechService() {

    }

    /**
     * Just id
     * @param name ID
     */
    public TechService(String name) {
        this ();
        this.name = name;
    }

    public TechService(String name, BigDecimal price, OperatingSystem operatingSystem, TechServiceType type) {
        this (name);
        this.price = price;
        this.operatingSystem = operatingSystem;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public TechServiceType getType () {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechService that = (TechService) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    @Transient
    @JsonIgnore
    public String getId() {
        return this.name;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return this.isNewInstance;
    }

    public void configureAsNewInstance () {
        this.isNewInstance = true;
    }
}
