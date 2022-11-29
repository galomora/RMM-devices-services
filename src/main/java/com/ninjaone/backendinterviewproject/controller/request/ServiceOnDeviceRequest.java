package com.ninjaone.backendinterviewproject.controller.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ServiceOnDeviceRequest {
    @NotNull
    private String serviceName;
    @NotNull
    private String deviceId;
    @NotNull
    @Min(1)
    private Long orderId;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
