package edu.nsimat.wallonia.jparepository;

import org.springframework.data.repository.CrudRepository;

import edu.nsimat.wallonia.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
