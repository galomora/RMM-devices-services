package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.database.ServiceTypeRepository;
import com.ninjaone.backendinterviewproject.model.OrderTestFactory;
import com.ninjaone.backendinterviewproject.model.TechService;
import com.ninjaone.backendinterviewproject.model.TechServiceTestFactory;
import com.ninjaone.backendinterviewproject.model.TechServiceType;
import com.ninjaone.backendinterviewproject.service.cache.ServiceCache;
import com.ninjaone.backendinterviewproject.service.cache.ServiceTypeCache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.asserts.Assertion;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TechServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    ServiceCache serviceCache;

    @Mock
    private ServiceTypeRepository serviceTypeRepository;

    @Mock
    private ServiceTypeCache serviceTypeCache;

    @InjectMocks
    private TechServiceServiceImpl techServiceService;

    private static final String SERVICE_TYPE_NAME = "DEVICE SERVICE";
    private static final String SERVICE_NAME = "DEVICE SERVICE";



    @Test
    public void createServiceTest () {
        when (serviceTypeCache.getType(any())).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createTechServiceTypeDeviceService()));
        when (serviceRepository.save(any())).thenReturn(
                OrderTestFactory.getInstance().createServiceOfTypeDeviceService());
        doNothing().when(serviceCache).refreshCache();
        TechService service = techServiceService.createService(
                OrderTestFactory.getInstance().createServiceOfTypeDeviceService(), SERVICE_TYPE_NAME);
        Assertions.assertFalse(Optional.of(service).isEmpty());
        verify(serviceRepository, times(1)).save(any());
    }

    @Test
    public void getServiceTest () {
        when (serviceRepository.findById(any())).thenReturn(
                Optional.of(OrderTestFactory.getInstance().createServiceOfTypeDeviceService())
        );
        Optional <TechService> optionalService = techServiceService.getService(SERVICE_NAME);
        Assertions.assertFalse(optionalService.isEmpty());
        verify(serviceRepository, times(1)).findById(any());
    }

    @Test
    public void deleteServiceTest () {
        doNothing().when(serviceRepository).deleteById(SERVICE_NAME);
        techServiceService.deleteService(SERVICE_NAME);
        Mockito.verify(serviceRepository, times(1)).deleteById(SERVICE_NAME);
    }

    @Test
    public void getAllTypesTest () {
        when (serviceTypeCache.getAllTypes()).thenReturn(
                TechServiceTestFactory.getInstance().createTechServiceTypesMap());
        List<TechServiceType> types = techServiceService.getAllTypes();
        Assertions.assertFalse(types.isEmpty());
        Assertions.assertTrue(types.size() == 2);
    }
}
