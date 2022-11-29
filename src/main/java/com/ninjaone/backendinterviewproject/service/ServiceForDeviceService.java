package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.ServiceForDevice;

import java.util.Optional;

public interface ServiceForDeviceService {
    /**
     * get a {@link ServiceForDevice} by id
     * @param id
     * @return
     */
    Optional<ServiceForDevice> getServiceForDevice(Long id);

    /**
     * Deletes a {@link ServiceForDevice}
     * @param id
     */
    void deleteServiceForDevice(Long id);
}
