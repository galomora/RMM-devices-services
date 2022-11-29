package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.billing.Billing;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;

public interface BillingService {
    /**
     * Gets a {@link Billing} obtained from a persisted order
     * @param orderId
     * @param useCache true uses services cache
     * @return {@link Billing} obtained from the order with the id
     * @throws OrderNotFoundException when order not found
     */
    Billing getBillingFromOrder (Long orderId, Boolean useCache) throws OrderNotFoundException;
}
