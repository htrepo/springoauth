package com.ht.springsecurity.data.repo;

import com.ht.springsecurity.data.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findAllByCustomerId(long customerId);
}
