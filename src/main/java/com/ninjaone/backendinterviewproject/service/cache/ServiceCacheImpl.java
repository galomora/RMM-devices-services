package com.ninjaone.backendinterviewproject.service.cache;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.model.TechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServiceCacheImpl implements ServiceCache {
    private static Map<String, TechService> allServices;

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceCacheImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
        allServices = new ConcurrentHashMap<>();
    }


    @Scheduled(initialDelay = 3000, fixedRate = 3600000)
    @PostConstruct
    public void init () {
        searchAllServices ();
    }

    private void searchAllServices () {
        allServices.clear();
        this.serviceRepository.findAll().forEach(service ->
                allServices.put(service.getId(), service));
    }

    @Override
    public Map<String, TechService> getAllServices() {
        // PostConstruct or Constructor resolves empty list. Query before commit? Bug??
        if (allServices.isEmpty()) {
            searchAllServices();
        }
        return Map.copyOf(allServices);
    }

    @Override
    public Optional<TechService> getService(String name) {
        return Optional.of(allServices.get(name));
    }

    @Override
    public void refreshCache() {
        searchAllServices ();
    }
}
