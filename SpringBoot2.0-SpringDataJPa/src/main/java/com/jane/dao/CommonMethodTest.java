package com.jane.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import com.jane.model.Address;

@NoRepositoryBean
public interface CommonMethodTest<T, ID extends Serializable> extends JpaRepository<T, ID>{

	Address method();
	
}
