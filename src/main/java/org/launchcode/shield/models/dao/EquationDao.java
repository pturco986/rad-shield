package org.launchcode.shield.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.shield.models.Equation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EquationDao extends CrudRepository<Equation, Integer>{
	
	List<Equation> findByAuthor(int authorId);
	
	//Add method signatures as needed
	List<Equation> findAll();
	
	Equation findByUid(int uid);

}
