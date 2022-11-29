package com.ninjaone.backendinterviewproject.controller.request;

import javax.validation.constraints.NotNull;

public class DeviceRequest {
    @NotNull
    private String id;

    private String systemName;
    @NotNull
    private String type;
    @NotNull
    private String operatingSystem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
