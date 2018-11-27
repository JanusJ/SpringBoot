package com.jane.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CommonJpaRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {

	public CommonJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	protected RepositoryFactorySupport createRepositoryFactory(
			EntityManager entityManager) {
		return new CommonRepositoryFactory(entityManager);
	}

	private static class CommonRepositoryFactory<T, I extends Serializable>
			extends JpaRepositoryFactory {

		private EntityManager entityManager;

		public CommonRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		protected Object getTargetRepository(JpaMetamodelEntityInformation information) {

			return new CommonMethodTestImpl<T, I>(information,entityManager);
		}
//		protected Object getTargetRepository(RepositoryMetadata metadata) {
//
//			return new CommonMethodTestImpl<T, I>(
//					(Class<T>) metadata.getDomainType(), entityManager);
//		}

		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return CommonMethodTestImpl.class;
		}
//		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
//			return CommonMethodTest.class;
//		}
	}

}
