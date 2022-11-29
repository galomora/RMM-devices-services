package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.database.ServiceTypeRepository;
import com.ninjaone.backendinterviewproject.model.TechService;
import com.ninjaone.backendinterviewproject.model.TechServiceType;
import com.ninjaone.backendinterviewproject.service.cache.ServiceCache;
import com.ninjaone.backendinterviewproject.service.cache.ServiceTypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TechServiceServiceImpl implements TechServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    ServiceCache serviceCache;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ServiceTypeCache serviceTypeCache;

    @Transactional
    public TechService createService (TechService service, String serviceTypeName) {
        TechServiceType type = this.serviceTypeCache.getType(serviceTypeName)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("Service type not found " + serviceTypeName);
                });
        TechService serviceToSave = new TechService(service.getName(), service.getPrice(),
                service.getOperatingSystem(), type);
        serviceToSave.configureAsNewInstance();
        TechService persisted = this.serviceRepository.save(serviceToSave);
        this.serviceCache.refreshCache();
        return persisted;
    }

    @Override
    public Optional<TechService> getService(String id) {
        return this.serviceRepository.findById(id);
    }

    @Override
    public void deleteService(String id) {
        this.serviceRepository.deleteById(id);
    }

    @Override
    public List<TechServiceType> getAllTypes() {
        return List.copyOf(this.serviceTypeCache.getAllTypes().values());
    }
}
