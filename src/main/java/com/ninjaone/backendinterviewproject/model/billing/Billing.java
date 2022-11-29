package com.ninjaone.backendinterviewproject.model.billing;

import com.ninjaone.backendinterviewproject.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Billing {
    private List<BillingServiceDetail> serviceDetails;
    private List<BillingPriceDetail> priceDetails;
    private Order order;
    private BigDecimal total;

    private Billing () {
        serviceDetails = new ArrayList<>();
        priceDetails = new ArrayList<>();
        total = BigDecimal.ZERO;
    }

    public Billing (Order order) {
        this();
        this.order = order;
        computeServiceDetails();
        computePriceDetails ();
        computeTotal ();
    }
    public Billing (Order order, List<TechService> servicesWithPrice) {
        this();
        this.order = order;
        computeServiceDetails();
        computePriceDetails (servicesWithPrice);
        computeTotal (servicesWithPrice);
    }

    public void computeTotal() {
        BigDecimal result = BigDecimal.ZERO;
        for (ServiceOnDevice service : order.getServicesOnDevice()) {
            result = result.add(service.getPriceApplied());
        }
        total = BigDecimal.ZERO.add(result);
    }

    public void computeTotal(List<TechService> servicesWithPrice) {
        BigDecimal result = BigDecimal.ZERO;
        for (ServiceOnDevice serviceOnDevice : order.getServicesOnDevice()) {
            TechService currentService = mapServicesByName(servicesWithPrice)
                    .get(serviceOnDevice.getServiceName());
            result = result.add(currentService.getPrice());
        }
        total = BigDecimal.ZERO.add(result);
    }

    public void computeServiceDetails () {
        Map<OperatingSystem, List<ServiceOnDevice>> servicesBySystem = order.getAllServicesBySystem();
        servicesBySystem.entrySet().stream().forEach(entry -> {
            serviceDetails.add(new BillingServiceDetail(entry.getKey(), entry.getValue()));
        });
    }

    public void computePriceDetails () {
        order.getAllServicesByServiceType().entrySet().forEach(entry -> {
            priceDetails.add(new BillingPriceDetail(entry.getKey(), entry.getValue()));
        });
    }

    public void computePriceDetails (List<TechService> servicesWithPrice) {
        order.getAllServicesByServiceType().entrySet().forEach(entry -> {
            priceDetails.add(new BillingPriceDetail(entry.getKey(), entry.getValue(), servicesWithPrice));
        });
    }

    public List<BillingServiceDetail> getServiceDetails() {
        return serviceDetails;
    }

    public List<BillingPriceDetail> getPriceDetails() {
        return priceDetails;
    }

    public BigDecimal getTotal() {
        return total;
    }

    protected static Map<String, TechService> mapServicesByName (List<TechService> servicesWithPrice) {
        return servicesWithPrice.stream().collect(Collectors.toMap(service -> service.getName(), service -> service));
    }
}
