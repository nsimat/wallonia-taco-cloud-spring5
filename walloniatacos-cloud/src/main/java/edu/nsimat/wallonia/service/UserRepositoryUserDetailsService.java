package edu.nsimat.wallonia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.nsimat.wallonia.domain.User;
import edu.nsimat.wallonia.jparepository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private UserRepository userRepo;

	@Autowired
	public UserRepositoryUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username){

		log.info("-----Loading: " + username + "-----");
		
		User user = userRepo.findByUsername(username);
		if (user != null) {
			log.info("-----User: " + user + "-----");
			return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found!");
	}

}
