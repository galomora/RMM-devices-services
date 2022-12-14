package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.TechService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<TechService, String> {
}
