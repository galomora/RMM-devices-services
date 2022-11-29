package com.ninjaone.backendinterviewproject.service.cache;

import com.ninjaone.backendinterviewproject.model.TechService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public interface ServiceCache {
    /**
     * Get all {@link TechService}
     * @return map with all {@link TechService}
     * key is service name
     * value is {@link TechService} instance
     */
    Map<String, TechService> getAllServices ();

    Optional<TechService> getService(String name);

    /**
     * Refresh the cache
     */
    void refreshCache ();
}
