package com.sopovs.moradanen.tarantool.spring.data;

import java.io.Serializable;

import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class TarantoolRepositoryFactory extends RepositoryFactorySupport {

	@Override
	public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getTargetRepository(RepositoryInformation metadata) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return SimpleTarantoolRepository.class;
	}

}
