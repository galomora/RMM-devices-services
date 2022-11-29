package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "TECH_SERVICE_TYPE")
public class TechServiceType {
    @Id
    private String name;
    private String description;

    public TechServiceType () {

    }

    public TechServiceType (String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechServiceType that = (TechServiceType) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TechServiceType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
