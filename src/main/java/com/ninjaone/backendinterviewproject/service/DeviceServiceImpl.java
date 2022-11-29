package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Transactional
    public Device createDevice (Device device) {
        device.configureAsNewInstance();
        return this.deviceRepository.save(device);
    }

    @Override
    @Transactional
    public Optional<Device> getDevice(String id){
        return this.deviceRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteDevice(String id) {
        this.deviceRepository.deleteById(id);
    }
}
