package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.ServiceOnDevice;

import java.util.Optional;

public interface ServiceOnDeviceService {
    /**
     * get a {@link ServiceOnDevice} by id
     * @param id
     * @return
     */
    Optional<ServiceOnDevice> getServiceOnDevice (Long id);

    /**
     * Deletes a {@link ServiceOnDevice}
     * @param id
     */
    void deleteServiceOnDevice (Long id);
}
