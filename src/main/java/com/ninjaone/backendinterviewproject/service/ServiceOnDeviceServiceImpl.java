package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceOnDeviceRepository;
import com.ninjaone.backendinterviewproject.model.ServiceOnDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceOnDeviceServiceImpl implements ServiceOnDeviceService{

    @Autowired
    private ServiceOnDeviceRepository serviceOnDeviceRepository;

    @Override
    public Optional<ServiceOnDevice> getServiceOnDevice(Long id) {
        return serviceOnDeviceRepository.findById(id);
    }

    @Override
    public void deleteServiceOnDevice(Long id) {
        this.serviceOnDeviceRepository.deleteById(id);
    }
}
