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
public class ServiceForDeviceServiceTest {
    @Mock
    private ServiceForDeviceRepository serviceForDeviceRepository;

    @InjectMocks
    ServiceForDeviceServiceImpl serviceForDeviceService;

    private static final Long SERVICE_ON_DEVICE_ID = 1L;

    @Test
    public void getServiceForDeviceTest() {
        when (serviceForDeviceRepository.findById(SERVICE_ON_DEVICE_ID)).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createServiceOfDeviceWinDeviceService()));
        Optional<ServiceForDevice> optionalServiceOnDevice =
                serviceForDeviceService.getServiceForDevice(SERVICE_ON_DEVICE_ID);
        Assertions.assertFalse(optionalServiceOnDevice.isEmpty());
        verify(serviceForDeviceRepository, times(1)).findById(any());
    }

    @Test
    public void getServiceForDeviceNoResultTest() {
        when (serviceForDeviceRepository.findById(SERVICE_ON_DEVICE_ID)).thenReturn(
                Optional.empty());
        Optional<ServiceForDevice> optionalServiceOnDevice =
                serviceForDeviceService.getServiceForDevice(SERVICE_ON_DEVICE_ID);
        Assertions.assertTrue(optionalServiceOnDevice.isEmpty());
        verify(serviceForDeviceRepository, times(1)).findById(any());
    }

    @Test
    public void deleteServiceForDeviceTest() {
        doNothing().when(serviceForDeviceRepository).deleteById(SERVICE_ON_DEVICE_ID);
        serviceForDeviceService.deleteServiceForDevice(SERVICE_ON_DEVICE_ID);
        verify(serviceForDeviceRepository, times(1)).deleteById(any());
    }

}
