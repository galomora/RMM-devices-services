package com.ninjaone.backendinterviewproject.service.cache;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.model.OrderTestFactory;
import com.ninjaone.backendinterviewproject.model.TechService;
import com.ninjaone.backendinterviewproject.model.TechServiceTestFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceCacheTest {
    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceCacheImpl serviceCache;

    @Test
    public void getAllServicesTest () {
        when (serviceRepository.findAll()).thenReturn(
                TechServiceTestFactory.getInstance().createTechServiceMap().values());
        Map<String, TechService> servicesMap = serviceCache.getAllServices();
        Assertions.assertFalse(servicesMap.isEmpty());
    }

    @Test
    public void getServiceTest () {
        Optional<TechService> optionalService = serviceCache.getService("SERVICE NAME");
        Assertions.assertTrue(optionalService.isEmpty());
    }

}
