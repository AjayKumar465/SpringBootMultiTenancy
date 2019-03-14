package com.poc.multitenancy;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.poc.multitenancy.core.ThreadLocalStorage;
import com.poc.multitenancy.routing.TenantAwareRoutingSource;

@SpringBootApplication
@EnableTransactionManagement
public class SampleJerseyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SampleJerseyApplication()
				.configure(new SpringApplicationBuilder(SampleJerseyApplication.class))
				.properties(getDefaultProperties())
				.run(args);
	}


	@Bean
	public DataSource dataSource() {

		AbstractRoutingDataSource dataSource = new TenantAwareRoutingSource();

		Map<Object,Object> targetDataSources = new HashMap<>();

		targetDataSources.put("TenantOne", tenantOne());
		targetDataSources.put("TenantTwo", tenantTwo());

		dataSource.setTargetDataSources(targetDataSources);

		dataSource.afterPropertiesSet();
		ThreadLocalStorage.setTenantName("TenantTwo");
		return dataSource;
	}

	public DataSource tenantOne() {


		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://pushlive-dev-env.crrslh60fv0r.us-west-1.rds.amazonaws.com:3306/TenantOne");
		basicDataSource.setUsername("pushlivedev");
		basicDataSource.setPassword("pushlivedev");
		// Below field required for jtds in MS SQL database


		return basicDataSource;
	}

	public DataSource tenantTwo() {

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://pushlive-dev-env.crrslh60fv0r.us-west-1.rds.amazonaws.com:3306/TenantTwo");
		basicDataSource.setUsername("pushlivedev");
		basicDataSource.setPassword("pushlivedev");
		// Below field required for jtds in MS SQL database


		return basicDataSource;
	}

	private static Properties getDefaultProperties() {

		Properties defaultProperties = new Properties();

		// Set sane Spring Hibernate properties:
		defaultProperties.put("spring.jpa.show-sql", "true");
		defaultProperties.put("spring.jpa.hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
		defaultProperties.put("spring.datasource.initialize", "false");

		// Prevent JPA from trying to Auto Detect the Database:
	//	defaultProperties.put("spring.jpa.database", "postgresql");

		// Prevent Hibernate from Automatic Changes to the DDL Schema:
		defaultProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		defaultProperties.put("spring.jpa.hibernate.ddl-auto", "none");

		return defaultProperties;
	}

}