package org.tux.dao;

import org.springframework.data.repository.CrudRepository;
import org.tux.entites.User;

public interface UserRepository extends CrudRepository<User, String> {
	
	User findByEmail (String email);

}
