package org.tux.config;

import java.util.Properties;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@PropertySource("file://${catalina.base}/conf/documentation/persistence.properties")
@EnableJpaRepositories("org.tux.dao")
@EnableTransactionManagement
public class PersistenceConfig {

	static final String FALSE = "false";

	private Logger logger = Logger.getLogger(PersistenceConfig.class);
	
@Bean(name="dataSource")
public DriverManagerDataSource dataSource(Environment env){
	DriverManagerDataSource dataSource= new DriverManagerDataSource();
	dataSource.setDriverClassName(env.getProperty("database.driver"));
	dataSource.setUrl(env.getProperty("database.url"));
	dataSource.setUsername(env.getProperty("database.username"));
	dataSource.setPassword(env.getProperty("database.password"));	
	return dataSource;
}

@Bean
public JpaVendorAdapter jpaVendorAdapter(){
	return new HibernateJpaVendorAdapter();
}

@Bean (name="transactionManager")
public PlatformTransactionManager transactionManager(Environment env){
	JpaTransactionManager transactionManager =new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(entityManagerFactory(env).getObject());
	return transactionManager;
}

@Bean(name="entityManagerFactory") 
@Singleton
public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env){
	
	logger.info("****************************************************************");
	LocalContainerEntityManagerFactoryBean entityMangerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	entityMangerFactoryBean.setDataSource(dataSource(env));
	entityMangerFactoryBean.setPackagesToScan("org.tux.entites");
	entityMangerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	entityMangerFactoryBean.setJpaProperties(hibernateProperties(env));
	
	return entityMangerFactoryBean;
}

Properties hibernateProperties(Environment env){
	Properties properties = new Properties();
	properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
	properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
	properties.setProperty("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
	properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
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
