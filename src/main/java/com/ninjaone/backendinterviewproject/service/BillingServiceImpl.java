package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.Order;
import com.ninjaone.backendinterviewproject.model.billing.Billing;
import com.ninjaone.backendinterviewproject.service.cache.ServiceCache;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ServiceCache serviceCache;

    @Override
    @Transactional
    public Billing getBillingFromOrder(Long orderId, Boolean useCache) throws OrderNotFoundException {
        Order order = this.orderService.getOrder(orderId);
        return createBilling(order, useCache);
    }

    private Billing createBilling (Order order, Boolean useCache) {
        Billing billing = null;
        if (useCache) {
            billing = new Billing(order, new ArrayList<>(serviceCache.getAllServices().values()));
        } else {
            billing = new Billing(order);
        }
        return billing;
    }
}
