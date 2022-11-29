package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.TechService;
import com.ninjaone.backendinterviewproject.model.TechServiceType;

import java.util.List;
import java.util.Optional;

public interface TechServiceService {
    /**
     * creates a new {@link TechService}
     * @param service
     * @param serviceTypeName related {@link TechServiceType} name
     * @return
     */
    public TechService createService (TechService service, String serviceTypeName);
    /**
     *
     * @param id {@link TechService} ID = name
     * @return the {@link TechService}
     *
     */
    public Optional<TechService> getService (String id);

    /**
     *
     * @param id {@link TechService} ID = name
     *
     */
    public void deleteService (String id);

    /**
     * A list of all {@link TechServiceType}
     * @return
     */
    public List<TechServiceType> getAllTypes ();
}
