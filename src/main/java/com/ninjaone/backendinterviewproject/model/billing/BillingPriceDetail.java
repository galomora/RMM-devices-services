package com.ninjaone.backendinterviewproject.model.billing;

import com.ninjaone.backendinterviewproject.model.ServiceOnDevice;
import com.ninjaone.backendinterviewproject.model.TechService;
import com.ninjaone.backendinterviewproject.model.TechServiceType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BillingPriceDetail {
    private TechServiceType serviceType;
    private BigDecimal price;


    /**
     * Computes prices using a list of services provided, each service including its price
     * @param type Service type
     * @param servicesOnDevice services applied on devices
     * @param servicesWithPrice list of services including prices
     */
    public BillingPriceDetail(TechServiceType type, List<ServiceOnDevice> servicesOnDevice,
                              List<TechService> servicesWithPrice) {
        this.serviceType = type;
        this.price = computePrice (servicesOnDevice, servicesWithPrice);
    }

    /**
     * Computes prices using the price included in {@link ServiceOnDevice}
     * @param type Service type
     * @param servicesOnDevice services applied on devices, each one contains the price applied
     */
    public BillingPriceDetail(TechServiceType type, List<ServiceOnDevice> servicesOnDevice) {
        this.serviceType = type;
        this.price = computePrice (servicesOnDevice);
    }

    private BigDecimal computePrice(List<ServiceOnDevice> servicesOnDevice) {
        BigDecimal total = BigDecimal.ZERO;
        for (ServiceOnDevice service : servicesOnDevice) {
            total = total.add(service.getPriceApplied());
        }
        return total;
    }

    private BigDecimal computePrice(List<ServiceOnDevice> servicesOnDevice, List<TechService> servicesWithPrice) {
        BigDecimal total = BigDecimal.ZERO;
        Map<String, TechService> servicesByName = Billing.mapServicesByName(servicesWithPrice);
        for (ServiceOnDevice service : servicesOnDevice) {
            String serviceName = service.getServiceName();
            total = total.add(servicesByName.get(serviceName).getPrice());
        }
        return total;
    }

    public String getTypeName () {
        return this.serviceType == null ? null : serviceType.getName();
    }

    public BigDecimal getPrice() {
        return price;
    }
}
