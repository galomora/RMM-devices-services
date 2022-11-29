package com.ninjaone.backendinterviewproject.model;

import com.ninjaone.backendinterviewproject.model.billing.Billing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BillingTest {


    @Test
    public void testCreateBilling () {
        Order order = OrderTestFactory.getInstance().createOrderWinWinMacServices();
        Billing billing = new Billing(order);
        Assertions.assertFalse(billing.getServiceDetails().isEmpty());
        // 2 different OS
        Assertions.assertTrue(billing.getServiceDetails().size() == 2);
        Assertions.assertFalse(billing.getPriceDetails().isEmpty());
        // 3 services of the same type, so details = 1
        Assertions.assertTrue(billing.getPriceDetails().size() == 1);
        // 3 services "Device Service" 4+4+4=12
        Assertions.assertEquals(new BigDecimal(12), billing.getTotal());
    }

    @Test
    public void testCreateBillingTwoTypes () {
        Order order = OrderTestFactory.getInstance().createOrderWinMacTwoServiceTypes();
        Billing billing = new Billing(order);
        Assertions.assertFalse(billing.getServiceDetails().isEmpty());
        // 2 different OS
        Assertions.assertTrue(billing.getServiceDetails().size() == 2);
        Assertions.assertFalse(billing.getPriceDetails().isEmpty());
        // 4 services of 2 types, so details = 2
        Assertions.assertTrue(billing.getPriceDetails().size() == 2);
        // 4 services 4+4+5+5=18
        Assertions.assertEquals(new BigDecimal(18), billing.getTotal());
    }
}
