package com.dunkware.trade.service.data.service.config;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "dataEntityManagerFactory", transactionManagerRef = "dataTransactionManager", basePackages = {
//		"com.dunkware.trade.service.data.service.repository" })
public class MySqlConfig {

	@Value("${spring.data.datasource.jpa.properties.hibernate.hbm2ddl.auto}")
	private String hbm2dll;
	@Primary
	@Bean(name = "dataDataSource")
	@ConfigurationProperties(prefix = "spring.data.datasource")
	public DataSource dataSource() {
		DataSource source = DataSourceBuilder.create().build();
		return source;
	}

	@Primary
	@Bean(name = "dataEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		// Dangerous! 
		properties.put("hibernate.hbm2ddl.auto",hbm2dll);
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.dunkware.trade.service.data.service.repository").persistenceUnit("data").build();
	}

	@Primary
	@Bean(name = "dataTransactionManager")
	public PlatformTransactionManager tradeTransactionManager(
			@Qualifier("dataEntityManagerFactory") EntityManagerFactory tradeEntityManagerFactory) {
		return new JpaTransactionManager(tradeEntityManagerFactory);
	}
}
