package com.jane.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.jane.model.*;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class CommonMethodTestImpl<T, ID extends Serializable> 
	extends SimpleJpaRepository<T, ID> implements CommonMethodTest<T, ID> {

	private EntityManager entityManager;

	public CommonMethodTestImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public CommonMethodTestImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {

		super(entityInformation, em);

		this.entityManager = em;
	}

	@Override
	public Address method() {
		Address address = entityManager.find(Address.class, 1);
		System.out.println("...BASE METHOD TEST...");
		return  address;
	}

}
