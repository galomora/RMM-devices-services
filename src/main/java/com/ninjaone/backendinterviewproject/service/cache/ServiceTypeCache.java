package com.ninjaone.backendinterviewproject.service.cache;

import com.ninjaone.backendinterviewproject.model.TechServiceType;

import java.util.Map;
import java.util.Optional;

public interface ServiceTypeCache {
    /**
     * Map of all {@link TechServiceType}
     * @return key is name, value is instance of {@link TechServiceType}
     */
    Map<String, TechServiceType> getAllTypes ();

    Optional<TechServiceType> getType (String name);
}
