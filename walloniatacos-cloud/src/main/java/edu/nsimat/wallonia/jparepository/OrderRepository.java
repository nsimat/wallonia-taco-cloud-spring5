package edu.nsimat.wallonia.jparepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import edu.nsimat.wallonia.domain.Order;
import edu.nsimat.wallonia.domain.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
