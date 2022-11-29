package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.Device;

import java.util.Optional;

public interface DeviceService {
    /**
     * Creates a new {@link Device}
     * @param device
     * @return
     */
    public Device createDevice (Device device);

    /**
     *
     * @param id Device ID
     * @return the {@link Device}
     *
     */
    public Optional<Device> getDevice (String id);

    /**
     *
     * @param id Device ID
     *
     */
    public void deleteDevice (String id);
}
