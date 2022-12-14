package com.ninjaone.backendinterviewproject.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderTest {

    @Test
    public void testCreateOrder () {
        Order order = OrderTestFactory.getInstance().createOrder();
        Assertions.assertTrue(order.getServicesOnDevice().isEmpty());
    }

    @Test
    public void testAddServiceForDeviceToOrder() {
        Order order = OrderTestFactory.getInstance().createOrder();
        ServiceForDevice serviceForDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService();
        order.addServiceForDevice(serviceForDevice);
        Assertions.assertFalse(order.getServicesOnDevice().isEmpty());
        ServiceForDevice firstService = order.getServicesOnDevice().stream().findFirst().get();
        TechService service = OrderTestFactory.getInstance().createServiceOfTypeDeviceService();
        Assertions.assertEquals(
                firstService.getPriceApplied(), service.getPrice());
    }

    @Test
    public void testGetSystems () {
        Order order = OrderTestFactory.getInstance().createOrderWinWinMacServices();
        Set<OperatingSystem> systems = order.getDifferentSystems();
        Assertions.assertFalse (systems.isEmpty());
        Assertions.assertTrue(systems.size() == 2);
    }

    @Test
    public void testGetAllServicesBySystem () {
        Order order = OrderTestFactory.getInstance().createOrderWinWinMacServices();
        Map<OperatingSystem, List<ServiceForDevice>> servicesBySystem = order.getAllServicesBySystem();
        Assertions.assertFalse(servicesBySystem.isEmpty());
        Assertions.assertTrue(servicesBySystem.get(OperatingSystem.WINDOWS).size() == 2);
        Assertions.assertTrue(servicesBySystem.get(OperatingSystem.MAC).size() == 1);
    }
}
