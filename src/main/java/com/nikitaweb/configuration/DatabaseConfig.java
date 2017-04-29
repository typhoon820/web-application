package com.nikitaweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String DB_DRIVER;
    @Value("${spring.datasource.data-password}")
    private String DB_PASSWORD;
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.data-username}")
    private String DB_USERNAME;
    @Value("${spring.jpa.properties.dialect}")
    private String HIBERNATE_DIALECT;
    @Value("${spring.jpa.show-sql}")
    private String HIBERNATE_SHOW_SQL;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String HIBERNATE_HBM2DDL_AUTO;
    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;
    @Value("${spring.jpa.hibernate.use-new-id-generator-mappings}")
    private String HIBERNATE_MAPPINGS;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("spring.jpa.properties.dialect", HIBERNATE_DIALECT);
        hibernateProperties.put("spring.jpa.show-sql", HIBERNATE_SHOW_SQL);
        hibernateProperties.put("spring.jpa.hibernate.ddl-auto", HIBERNATE_HBM2DDL_AUTO);
        hibernateProperties.put("spring.jpa.hibernate.use-new-id-generator-mappings", HIBERNATE_MAPPINGS);
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        return sessionFactoryBean;


    }
    @Autowired
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
