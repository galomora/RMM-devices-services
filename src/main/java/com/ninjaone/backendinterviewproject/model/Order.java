package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "SERVICE_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany (mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<ServiceForDevice> servicesOnDevice;

    public Order () {
        servicesOnDevice = new ArrayList<>();
    }

    public void addServiceOnDevice (ServiceForDevice serviceOnDevice) {
        serviceOnDevice.setOrder(this);
        this.servicesOnDevice.add(serviceOnDevice);
    }

    public void removeServiceOnDevice (ServiceForDevice serviceOnDevice) {
        this.servicesOnDevice.remove(serviceOnDevice);
    }

    @JsonIgnore
    public List<ServiceForDevice> getServicesOnDevice () {
        return this.servicesOnDevice;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Set<OperatingSystem> getDifferentSystems () {
        return this.servicesOnDevice.stream().map(service -> service.getDeviceOS()).collect(Collectors.toSet());
    }

    @JsonIgnore
    public List<ServiceForDevice> getServicesByOS (OperatingSystem system) {
        return this.servicesOnDevice.stream().filter(service -> system.equals(service.getDeviceOS()))
                .collect(Collectors.toList());
    }

    @JsonIgnore
    public Map<OperatingSystem, List<ServiceForDevice>> getAllServicesBySystem () {
        Map map = new HashMap<>();
        getDifferentSystems ().stream().forEach(system ->
                map.put(system, getServicesByOS(system)));
        return map;
    }

    @JsonIgnore
    public Map<TechServiceType, List<ServiceForDevice>> getAllServicesByServiceType () {
        return servicesOnDevice.stream().collect(Collectors.groupingBy(service -> service.getType()));
    }

}
