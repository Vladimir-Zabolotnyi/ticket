package org.hillel.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource("database.properties")
@EnableTransactionManagement
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setPassword(environment.getProperty("database.password"));
        config.setUsername(environment.getProperty("database.username"));
        config.setJdbcUrl(environment.getProperty("database.url"));
        config.addDataSourceProperty("databaseName", environment.getProperty("database.name"));
        config.setDataSourceClassName(PGSimpleDataSource.class.getName());
        config.setMinimumIdle(Integer.parseInt(environment.getProperty("database.minIdle")));
        config.setMaximumPoolSize(Integer.parseInt(environment.getProperty("database.maxPoolSize")));
        HikariDataSource dataSource = new HikariDataSource(config);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("org.hillel.persistence.entity");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.put("hibernate.dialect", PostgreSQL10Dialect.class.getName());
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");
        properties.put("javax.persistence.query.timeout", 1000 * 60 * 5);/*TimeUnit.MINUTES.toMillis(5L);IllegalArgumentException*/
        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        jpaTransactionManager.setDataSource(dataSource());
        return jpaTransactionManager;
    }
}
