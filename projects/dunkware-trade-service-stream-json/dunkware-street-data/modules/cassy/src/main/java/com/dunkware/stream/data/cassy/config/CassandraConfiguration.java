package com.dunkware.stream.data.cassy.config;

import java.net.InetSocketAddress;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.SessionFactory;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.core.AsyncCassandraOperations;
import org.springframework.data.cassandra.core.AsyncCassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.dunkware.stream.data.cassy.support.CustomCassandraOperations;
import com.dunkware.stream.data.cassy.support.CustomCassandraRepositoryImpl;

@Configuration
@EnableCassandraRepositories(repositoryBaseClass = CustomCassandraRepositoryImpl.class, basePackages = "com.dunkware.stream.data.cassy.repository")
class CassandraConfiguration   {

	@Value("${spring.cassandra.keyspace-name}")
	private String keyspace; 
	
	@Value("${spring.cassandra.username}")
	private String username; 
	
	@Value("${spring.cassandra.password}")
	private String password; 
	
	@Value("${spring.cassandra.local-datacenter}")
	private String datacenter; 
	
	@Value("${spring.cassandra.contact-points}")
	private String server; 
	
	@Value("${spring.cassandra.port}")
	private int port; 
	
	
	/**
	 * Based on
	 * https://github.com/spring-projects/spring-boot/blob/2.7.x/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/data/cassandra/CassandraDataAutoConfiguration.java
	 */
	@Bean
	@ConditionalOnMissingBean
	AsyncCassandraTemplate asyncCassandraTemplate(SessionFactory sessionFactory, CassandraConverter converter) {
		return new AsyncCassandraTemplate(sessionFactory, converter);
	}

	@Bean
	CustomCassandraOperations customCassandraOperations(AsyncCassandraOperations asyncCassandraOperations) {
		return new CustomCassandraOperations(asyncCassandraOperations);
	}

	
	@Bean
	public CqlSessionFactoryBean session() {
		CqlSessionFactoryBean session = new CqlSessionFactoryBean();
		InetSocketAddress adr = InetSocketAddress.createUnresolved(server,port);
		session.setContactPoints(Arrays.asList(adr));
		session.setKeyspaceName(keyspace);
		session.setUsername(username);
		session.setPassword(password);
		session.setLocalDatacenter(datacenter);
	
		return session;
	}

	/**
	@ReadingConverter
	class RoleReadingConverter implements Converter<String, Role> {
		@Override
		public Role convert(String source) {
			return Role.parse(source);
		}
	}

	@WritingConverter
	class RoleWritingConverter implements Converter<Role, String> {
		@Override
		public String convert(Role source) {
			return source.toString();
		}
	}

	@Bean
	CassandraCustomConversions cassandraCustomConversions() {
		return new CassandraCustomConversions(Arrays.asList(new RoleReadingConverter(), new RoleWritingConverter()));
	}
	**/

}
