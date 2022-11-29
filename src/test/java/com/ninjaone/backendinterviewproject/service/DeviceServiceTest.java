package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.OrderTestFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    DeviceServiceImpl deviceService;

    private Device device1;
    private String deviceId = "DEV1";

    @BeforeEach
    public void init () {
        device1 = OrderTestFactory.getInstance().createDeviceMac();
    }

    @Test
    public void createDeviceTest () {
        when (deviceRepository.save(any())).thenReturn(device1);
        Device saved = deviceService.createDevice(device1);
        Assertions.assertFalse(Optional.of(saved).isEmpty());
        verify(deviceRepository, times(1)).save(any());
    }

    @Test
    public void getDeviceTest () {
        when (deviceRepository.findById(deviceId)).thenReturn(Optional.of(device1));
        Optional<Device> optionalDevice = deviceService.getDevice(deviceId);
        Assertions.assertFalse(optionalDevice.isEmpty());
    }

    @Test
    public void getDeviceNoResultTest () {
        when (deviceRepository.findById(deviceId)).thenReturn(Optional.empty());
        Optional<Device> optionalDevice = deviceService.getDevice(deviceId);
        Assertions.assertTrue(optionalDevice.isEmpty());
    }

    @Test
    public void deleteDeviceTest () {
        doNothing().when(deviceRepository).deleteById(deviceId);
        deviceService.deleteDevice(deviceId);
        Mockito.verify(deviceRepository, times(1)).deleteById(deviceId);
    }


}
