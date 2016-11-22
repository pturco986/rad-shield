package org.launchcode.shield.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.shield.models.Machine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface MachineDao extends CrudRepository<Machine, Integer>{
	
	List<Machine> findByAuthor (int authorId);
	
	//Add additional signatures as needed
	List<Machine> findAll();
	
	Machine findByUid(int uid);
	
}
