package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.TechServiceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends CrudRepository<TechServiceType, String> {
}
