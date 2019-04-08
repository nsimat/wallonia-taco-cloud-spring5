package edu.nsimat.wallonia.jdbcrepository;

import edu.nsimat.wallonia.domain.Order;

public interface OrderRepository {
	
	Order save(Order order);
}
