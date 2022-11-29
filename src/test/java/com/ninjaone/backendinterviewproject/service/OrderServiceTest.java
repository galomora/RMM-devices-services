package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.OrderRepository;
import com.ninjaone.backendinterviewproject.database.ServiceOnDeviceRepository;
import com.ninjaone.backendinterviewproject.model.Order;
import com.ninjaone.backendinterviewproject.model.OrderTestFactory;
import com.ninjaone.backendinterviewproject.model.ServiceOnDevice;
import com.ninjaone.backendinterviewproject.service.cache.ServiceCache;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private ServiceOnDeviceRepository serviceOnDeviceRepository;
    @Mock
    private ServiceCache serviceCache;

    @InjectMocks
    private OrderServiceImpl orderService;

    private static Long orderId;
    private static String deviceId;
    private static String serviceName;

    @BeforeAll
    public static void init () {
        orderId = 1L;
        deviceId = "DEV1";
        serviceName = "Device Service";
    }

    @Test
    public void createOrderTest () {
        when (orderRepository.save(any())).thenReturn(OrderTestFactory.getInstance().createOrder());
        Order order = orderService.createOrder();
        Assertions.assertFalse(Optional.of(order).isEmpty());
    }

    @Test
    public void addServiceOnDeviceTest () throws OrderNotFoundException {
        when (orderRepository.findById(orderId)).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createOrderWinWinMacServices()));
        when(serviceCache.getService(serviceName)).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createServiceOfTypeDeviceService()));
        when(deviceRepository.findById(deviceId)).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createDeviceWin())
        );
        when (serviceOnDeviceRepository.save(any())).thenReturn(
                OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService());
        ServiceOnDevice serviceOnDevice = orderService.addServiceOnDeviceToOrder(orderId, deviceId, serviceName);
        Assertions.assertFalse(Optional.of(serviceOnDevice).isEmpty());
        Assertions.assertDoesNotThrow(() -> orderService.addServiceOnDeviceToOrder(orderId, deviceId, serviceName));
    }

    @Test
    public void addServiceOnDeviceNoOrderTest () throws OrderNotFoundException {
        when (orderRepository.findById(orderId)).thenReturn(Optional.empty());
        Assertions.assertThrows(OrderNotFoundException.class,
                () -> orderService.addServiceOnDeviceToOrder(orderId, deviceId, serviceName));
    }

    @Test
    public void getOrderTest() throws OrderNotFoundException {
        when (orderRepository.findById(orderId)).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createOrderWinWinMacServices()));
        Order order = orderService.getOrder(orderId);
        Assertions.assertFalse(order == null);
        Assertions.assertDoesNotThrow(() -> orderService.getOrder(orderId));
    }

    @Test
    public void getOrderTestException() throws OrderNotFoundException {
        when (orderRepository.findById(orderId)).thenReturn(Optional.empty());
        Assertions.assertThrows(OrderNotFoundException.class,
                () -> orderService.getOrder(orderId));
    }


}
