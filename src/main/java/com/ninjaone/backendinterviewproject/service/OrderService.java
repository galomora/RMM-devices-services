package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.*;
import com.ninjaone.backendinterviewproject.service.exception.DeviceNotFoundException;
import com.ninjaone.backendinterviewproject.service.exception.OrderNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    /**
     * Creates a new {@link Order}
     * @return
     */
    public Order createOrder ();

    /**
     * add a {@link ServiceOnDevice} to an order
     * @param orderId order ID
     * @param deviceId device ID
     * @param serviceName service ID
     * @return ServiceOnDevice created and added to the order
     *
     */
    public ServiceOnDevice addServiceOnDeviceToOrder (Long orderId, String deviceId, String serviceName) throws OrderNotFoundException;

    /**
     * Gets an {@link Order} with details
     * @param orderId
     * @return instance of {@link Order}
     */
    public Order getOrder (Long orderId) throws OrderNotFoundException;

}
