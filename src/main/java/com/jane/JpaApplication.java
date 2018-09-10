package com.jane;

import com.jane.dao.CommonJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages={"com.jane.model"})
@EnableJpaRepositories(basePackages = {"com.jane.dao"},repositoryFactoryBeanClass = CommonJpaRepositoryFactoryBean.class)
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
}
