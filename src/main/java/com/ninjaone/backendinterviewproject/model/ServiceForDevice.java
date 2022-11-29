
package com.ninjaone.backendinterviewproject.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class ServiceForDevice {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (cascade = {}, fetch = FetchType.EAGER, optional = false)
    private TechService techService;
    @ManyToOne (cascade = {}, fetch = FetchType.EAGER, optional = false)
    private Device device;

    @ManyToOne (cascade = {}, fetch = FetchType.EAGER, optional = false)
    private Order order;
    @Column(nullable = false, precision = 9, scale = 2)
    private BigDecimal priceApplied;

    public ServiceForDevice() {

    }

    public ServiceForDevice(TechService techService, Device device) {
        this.techService = techService;
        this.device = device;
        this.priceApplied = this.techService.getPrice();
    }

    public ServiceForDevice(TechService techService, Device device, BigDecimal price) {
        this (techService, device);
        this.priceApplied = price;
    }

    public ServiceForDevice(TechService techService, Device device, Order order) {
        this (techService, device);
        this.order = order;
    }

    public BigDecimal getPriceApplied() {
        return priceApplied;
    }

    public OperatingSystem getDeviceOS () {
        return this.device.getOperatingSystem();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public TechServiceType getType () {
        return this.techService == null ? null : this.techService.getType();
    }

    public String getServiceName () {
        return this.techService == null ? null : this.techService.getName();
    }

    public String getDeviceId () { return this.device == null ? null : this.device.getId(); }

    public Long getOrderId () {
        return this.order == null ? null : this.order.getId();
    }

    public Long getId() {
        return id;
    }
}
