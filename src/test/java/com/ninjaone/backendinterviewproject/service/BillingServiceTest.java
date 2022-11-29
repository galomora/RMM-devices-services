package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.OrderTestFactory;
import com.ninjaone.backendinterviewproject.model.TechServiceTestFactory;
import com.ninjaone.backendinterviewproject.model.billing.Billing;
import com.ninjaone.backendinterviewproject.service.cache.ServiceCache;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.asserts.Assertion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillingServiceTest {

    private static final Long ORDER_ID = 1L;
    @Mock
    private OrderService orderService;

    @Mock
    private ServiceCache serviceCache;

    @InjectMocks
    BillingServiceImpl billingService;

    @Test
    public void getBillingFromOrderNoCacheTest () throws OrderNotFoundException {
        when (orderService.getOrder(ORDER_ID)).thenReturn(
                OrderTestFactory.getInstance().createOrderWinMacTwoServiceTypes());
        Billing billing = billingService.getBillingFromOrder(ORDER_ID, Boolean.FALSE);
        Assertions.assertFalse(billing == null);
        Assertions.assertTrue(billing.getPriceDetails().size() == 2);
        Assertions.assertTrue(billing.getServiceDetails().size() == 2);
    }

    @Test
    public void getBillingFromOrderUsingCacheTest () throws OrderNotFoundException {
        when (orderService.getOrder(ORDER_ID)).thenReturn(
                OrderTestFactory.getInstance().createOrderWinMacTwoServiceTypes());
        when (serviceCache.getAllServices()).thenReturn(
                TechServiceTestFactory.getInstance().createTechServiceMap()
        );
        Billing billing = billingService.getBillingFromOrder(ORDER_ID, Boolean.TRUE);
        Assertions.assertFalse(billing == null);
        Assertions.assertTrue(billing.getPriceDetails().size() == 2);
        Assertions.assertTrue(billing.getServiceDetails().size() == 2);
    }
}
