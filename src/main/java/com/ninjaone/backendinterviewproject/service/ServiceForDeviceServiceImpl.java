package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceOnDeviceRepository;
import com.ninjaone.backendinterviewproject.model.ServiceForDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceForDeviceServiceImpl implements ServiceForDeviceService {

    @Autowired
    private ServiceOnDeviceRepository serviceOnDeviceRepository;

    @Override
    public Optional<ServiceForDevice> getServiceOnDevice(Long id) {
        return serviceOnDeviceRepository.findById(id);
    }

    @Override
    public void deleteServiceOnDevice(Long id) {
        this.serviceOnDeviceRepository.deleteById(id);
    }
}
