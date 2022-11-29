package com.ninjaone.backendinterviewproject.model.billing;

import com.ninjaone.backendinterviewproject.model.OperatingSystem;
import com.ninjaone.backendinterviewproject.model.ServiceForDevice;
import com.ninjaone.backendinterviewproject.model.TechServiceType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingServiceDetail {
    private OperatingSystem operatingSystem;
    private Map<TechServiceType, Integer> serviceQuantity;

    public BillingServiceDetail (OperatingSystem operatingSystem, List<ServiceForDevice> services) {
        this.operatingSystem = operatingSystem;
        serviceQuantity = countServicesByType(services);
    }

    private Map <TechServiceType, Integer> countServicesByType (List<ServiceForDevice> servicesOnDevice) {
        Map <TechServiceType, List<ServiceForDevice>> servicesByType =
                servicesOnDevice.stream().collect(Collectors.groupingBy(service -> service.getType()));
        return servicesByType.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(),
                entry -> entry.getValue().size()));
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public Map<TechServiceType, Integer> getServiceQuantity() {
        return serviceQuantity;
    }
}
