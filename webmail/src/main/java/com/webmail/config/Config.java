package com.webmail.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({
	"com.webmail.dao.impl",
	"com.webmail.service.impl",
})
public class Config {
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IOException, PropertyVetoException{
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		Properties properties=new Properties();
		properties.setProperty("hibernate.dialect",GlobalPropertie.get("Hibernate.dialect"));
		properties.setProperty("hibernate.connection.isolation",GlobalPropertie.get("Hibernate.connection.isolation"));
		properties.setProperty("hibernate.show_sql",GlobalPropertie.get("Hibernate.show_sql"));
		properties.setProperty("hibernate.format_sql",GlobalPropertie.get("Hibernate.format_sql"));
		properties.setProperty("hibernate.hbm2ddl.auto",GlobalPropertie.get("Hibernate.hbm2ddl.auto"));
		sessionFactory.setHibernateProperties(properties);
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setMappingLocations(new PathMatchingResourcePatternResolver()
					.getResources("classpath:/com/webmail/domain/*.hbm.xml")
				);
		sessionFactory.setPackagesToScan("com.webmail.domain");
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		return new HibernateTransactionManager(sessionFactory);
	}
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException{
		ComboPooledDataSource cpds = new ComboPooledDataSource(); 
		cpds.setDriverClass(GlobalPropertie.get("DataSource.driverClass")); 
		cpds.setJdbcUrl(GlobalPropertie.get("DataSource.jdbcUrl"));
		cpds.setUser(GlobalPropertie.get("DataSource.user")); 
		cpds.setPassword(GlobalPropertie.get("DataSource.password")); 
		cpds.setMinPoolSize(GlobalPropertie.getInteger("DataSource.minPoolSize")); 
		cpds.setMaxPoolSize(GlobalPropertie.getInteger("DataSource.maxPoolSize"));
		cpds.setMaxIdleTime(GlobalPropertie.getInteger("DataSource.maxIdleTime"));
		cpds.setMaxStatements(GlobalPropertie.getInteger("DataSource.maxStatements"));
		return cpds;
	}
}