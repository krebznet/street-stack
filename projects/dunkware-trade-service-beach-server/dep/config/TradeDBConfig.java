package com.dunkware.trade.service.beach.server.config;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "tradeEntityManagerFactory", transactionManagerRef = "tradeTransactionManager", basePackages = {
		"com.dunkware.trade.service.beach.server.repository" })
public class TradeDBConfig {

	@Primary
	@Bean(name = "tradeDataSource")
	@ConfigurationProperties(prefix = "spring.trade.datasource")
	public DataSource dataSource() {
		DataSource source = DataSourceBuilder.create().build();
		return source;
	}

	@Primary
	@Bean(name = "tradeEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("tradeDataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		// Dangerous! 
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.dunkware.trade.service.beach.server.repository").persistenceUnit("trade").build();
	}

	@Primary
	@Bean(name = "tradeTransactionManager")
	public PlatformTransactionManager tradeTransactionManager(
			@Qualifier("tradeEntityManagerFactory") EntityManagerFactory tradeEntityManagerFactory) {
		return new JpaTransactionManager(tradeEntityManagerFactory);
	}
}
