package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
