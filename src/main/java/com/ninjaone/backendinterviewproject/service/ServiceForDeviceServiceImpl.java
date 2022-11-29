package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceForDeviceRepository;
import com.ninjaone.backendinterviewproject.model.ServiceForDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceForDeviceServiceImpl implements ServiceForDeviceService {

    @Autowired
    private ServiceForDeviceRepository serviceForDeviceRepository;

    @Override
    public Optional<ServiceForDevice> getServiceOnDevice(Long id) {
        return serviceForDeviceRepository.findById(id);
    }

    @Override
    public void deleteServiceOnDevice(Long id) {
        this.serviceForDeviceRepository.deleteById(id);
    }
}
