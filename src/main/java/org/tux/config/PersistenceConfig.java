package org.tux.config;

import java.util.Properties;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
//@PropertySource("file://${catalina.base}/conf/smgs/persistence.properties")
@EnableJpaRepositories("org.tux.dao")
@EnableTransactionManagement
public class PersistenceConfig {

	static final String FALSE = "false";

	private Logger logger = Logger.getLogger(PersistenceConfig.class);
	
@Bean(name="dataSource")
public DriverManagerDataSource dataSource(){
	DriverManagerDataSource dataSource= new DriverManagerDataSource();
	dataSource.setDriverClassName("org.postgresql.Driver");
	dataSource.setUrl("jdbc:postgresql://localhost:5432/articles");
	dataSource.setUsername("admin");
	dataSource.setPassword("admin");	
	return dataSource;
}

@Bean
public JpaVendorAdapter jpaVendorAdapter(){
	return new HibernateJpaVendorAdapter();
}

@Bean (name="transactionManager")
public PlatformTransactionManager transactionManager(){
	JpaTransactionManager transactionManager =new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	return transactionManager;
}

@Bean(name="entityManagerFactory") 
@Singleton
public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
	
	logger.info("****************************************************************");
	LocalContainerEntityManagerFactoryBean entityMangerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	entityMangerFactoryBean.setDataSource(dataSource());
	entityMangerFactoryBean.setPackagesToScan(new String[] {"org.tux.entites"});
	entityMangerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	entityMangerFactoryBean.setJpaProperties(hibernateProperties());
	
	return entityMangerFactoryBean;
}

Properties hibernateProperties(){
	Properties properties = new Properties();
	properties.setProperty("hibernate.hbm2ddl.auto", "create");
	properties.setProperty("hibernate.show_sql", "true");
	properties.setProperty("hibernate.format_sql","true");
	properties.setProperty("hibernate.use_sql_comments","true");
	properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	return properties;
}


/* Job luncher*/
//@Bean(name="jobRepository")
//public JobRepository jobRepository(){
//	
//	JobRepository jobRepository = null;
//	try {
//		jobRepository = (new MapJobRepositoryFactoryBean(transactionManager())).getObject();
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
//	return jobRepository;
//}

}
