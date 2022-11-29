package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.OrderRepository;
import com.ninjaone.backendinterviewproject.database.ServiceOnDeviceRepository;
import com.ninjaone.backendinterviewproject.model.*;
import com.ninjaone.backendinterviewproject.service.cache.ServiceCache;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ServiceOnDeviceRepository serviceOnDeviceRepository;
    @Autowired
    private ServiceCache serviceCache;

    @Transactional
    public Order createOrder () {
        return this.orderRepository.save(new Order ());
    }

    @Override
    @Transactional
    public ServiceOnDevice addServiceOnDeviceToOrder(Long orderId, String deviceId, String serviceName) throws OrderNotFoundException {
        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        Order order = optionalOrder.orElseThrow(() -> new OrderNotFoundException("Not found order with id " + orderId));
        TechService service = this.serviceCache.getService(serviceName).orElseThrow();
        Device device = this.deviceRepository.findById(deviceId).orElseThrow();
        ServiceOnDevice serviceOnDeviceToSave = new ServiceOnDevice(service, device, order );
        return serviceOnDeviceRepository.save(serviceOnDeviceToSave);
    }

    @Override
    @Transactional
    public Order getOrder(Long orderId) throws OrderNotFoundException {
        Order order = this.orderRepository.findById(orderId).orElseThrow(
                () -> new OrderNotFoundException("Not found order with id " + orderId));
        order.getServicesOnDevice().size();
        return order;
    }

}
