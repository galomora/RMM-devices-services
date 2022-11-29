package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceOnDeviceRepository;
import com.ninjaone.backendinterviewproject.model.OrderTestFactory;
import com.ninjaone.backendinterviewproject.model.ServiceForDevice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceOnDeviceServiceTest {
    @Mock
    private ServiceOnDeviceRepository serviceOnDeviceRepository;

    @InjectMocks
    ServiceForDeviceServiceImpl serviceOnDeviceService;

    private static final Long SERVICE_ON_DEVICE_ID = 1L;

    @Test
    public void getServiceOnDeviceTest () {
        when (serviceOnDeviceRepository.findById(SERVICE_ON_DEVICE_ID)).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService()));
        Optional<ServiceForDevice> optionalServiceOnDevice =
                serviceOnDeviceService.getServiceOnDevice(SERVICE_ON_DEVICE_ID);
        Assertions.assertFalse(optionalServiceOnDevice.isEmpty());
        verify(serviceOnDeviceRepository, times(1)).findById(any());
    }

    @Test
    public void getServiceOnDeviceNoResultTest () {
        when (serviceOnDeviceRepository.findById(SERVICE_ON_DEVICE_ID)).thenReturn(
                Optional.empty());
        Optional<ServiceForDevice> optionalServiceOnDevice =
                serviceOnDeviceService.getServiceOnDevice(SERVICE_ON_DEVICE_ID);
        Assertions.assertTrue(optionalServiceOnDevice.isEmpty());
        verify(serviceOnDeviceRepository, times(1)).findById(any());
    }

    @Test
    public void deleteServiceOnDeviceTest () {
        doNothing().when(serviceOnDeviceRepository).deleteById(SERVICE_ON_DEVICE_ID);
        serviceOnDeviceService.deleteServiceOnDevice(SERVICE_ON_DEVICE_ID);
        verify(serviceOnDeviceRepository, times(1)).deleteById(any());
    }

}
