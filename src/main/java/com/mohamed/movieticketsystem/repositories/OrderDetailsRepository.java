package com.mohamed.movieticketsystem.repositories;

import com.mohamed.movieticketsystem.entities.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails,Long> {
}
