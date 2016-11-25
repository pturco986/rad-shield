package org.launchcode.shield.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.shield.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	User findByUid(int uid);
	
	List<User> findAll();
	
	//TODO Add method signatures as I go
	User findByUsername(String username);

}
