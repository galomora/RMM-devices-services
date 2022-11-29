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
    public void testAddServiceOnDeviceToOrder () {
        Order order = OrderTestFactory.getInstance().createOrder();
        ServiceOnDevice serviceOnDevice = OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService();
        order.addServiceOnDevice(serviceOnDevice);
        Assertions.assertFalse(order.getServicesOnDevice().isEmpty());
        ServiceOnDevice firstService = order.getServicesOnDevice().stream().findFirst().get();
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
        Map<OperatingSystem, List<ServiceOnDevice>> servicesBySystem = order.getAllServicesBySystem();
        Assertions.assertFalse(servicesBySystem.isEmpty());
        Assertions.assertTrue(servicesBySystem.get(OperatingSystem.WINDOWS).size() == 2);
        Assertions.assertTrue(servicesBySystem.get(OperatingSystem.MAC).size() == 1);
    }
}
