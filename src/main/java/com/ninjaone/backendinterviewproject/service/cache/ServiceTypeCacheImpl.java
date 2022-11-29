package com.ninjaone.backendinterviewproject.service.cache;

import com.ninjaone.backendinterviewproject.database.ServiceTypeRepository;
import com.ninjaone.backendinterviewproject.model.TechServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ServiceTypeCacheImpl implements ServiceTypeCache {

    private static Map<String, TechServiceType> allTypes;

    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public ServiceTypeCacheImpl(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        allTypes = new ConcurrentHashMap<>();

    }

    @PostConstruct
    @Scheduled(initialDelay = 3000, fixedDelay = 3600000)
    public void init () {
        searchAllTypes ();
    }

    private void searchAllTypes () {
        allTypes.clear();
        System.out.println("to search all types ");
        this.serviceTypeRepository.findAll().forEach(service ->
                allTypes.put(service.getName(), service));
        System.out.println("hw many types " + allTypes.size());
    }

    @Override
    public Map<String, TechServiceType> getAllTypes() {
       if (allTypes.isEmpty()) {
           searchAllTypes();
       }
        return Map.copyOf(allTypes);
    }

    @Override
    public Optional<TechServiceType> getType(String name) {
        return Optional.of(allTypes.get(name));
    }


}
