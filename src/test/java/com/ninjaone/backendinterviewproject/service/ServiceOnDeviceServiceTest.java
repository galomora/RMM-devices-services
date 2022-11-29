package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceForDeviceRepository;
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
    private ServiceForDeviceRepository serviceForDeviceRepository;

    @InjectMocks
    ServiceForDeviceServiceImpl serviceOnDeviceService;

    private static final Long SERVICE_ON_DEVICE_ID = 1L;

    @Test
    public void getServiceOnDeviceTest () {
        when (serviceForDeviceRepository.findById(SERVICE_ON_DEVICE_ID)).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService()));
        Optional<ServiceForDevice> optionalServiceOnDevice =
                serviceOnDeviceService.getServiceOnDevice(SERVICE_ON_DEVICE_ID);
        Assertions.assertFalse(optionalServiceOnDevice.isEmpty());
        verify(serviceForDeviceRepository, times(1)).findById(any());
    }

    @Test
    public void getServiceOnDeviceNoResultTest () {
        when (serviceForDeviceRepository.findById(SERVICE_ON_DEVICE_ID)).thenReturn(
                Optional.empty());
        Optional<ServiceForDevice> optionalServiceOnDevice =
                serviceOnDeviceService.getServiceOnDevice(SERVICE_ON_DEVICE_ID);
        Assertions.assertTrue(optionalServiceOnDevice.isEmpty());
        verify(serviceForDeviceRepository, times(1)).findById(any());
    }

    @Test
    public void deleteServiceOnDeviceTest () {
        doNothing().when(serviceForDeviceRepository).deleteById(SERVICE_ON_DEVICE_ID);
        serviceOnDeviceService.deleteServiceOnDevice(SERVICE_ON_DEVICE_ID);
        verify(serviceForDeviceRepository, times(1)).deleteById(any());
    }

}
