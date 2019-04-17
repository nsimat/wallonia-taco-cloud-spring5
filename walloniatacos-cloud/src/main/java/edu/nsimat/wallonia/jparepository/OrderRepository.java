package edu.nsimat.wallonia.jparepository;

import org.springframework.data.repository.CrudRepository;

import edu.nsimat.wallonia.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
