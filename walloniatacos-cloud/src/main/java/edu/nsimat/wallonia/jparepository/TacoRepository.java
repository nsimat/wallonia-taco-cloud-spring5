package edu.nsimat.wallonia.jparepository;

import org.springframework.data.repository.CrudRepository;

import edu.nsimat.wallonia.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
