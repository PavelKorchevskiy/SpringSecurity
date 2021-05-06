package org.school.app.configs;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "org.school.app")
@EnableWebMvc
@EnableWebSecurity
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "org.school.app.repository")
@EnableTransactionManagement
public class AppConfig extends AbstractSecurityWebApplicationInitializer implements WebMvcConfigurer {

  @Bean
  public InternalResourceViewResolver internalResourceViewResolver(@Autowired ApplicationContext ctx) {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setApplicationContext(ctx);
    resolver.setPrefix("/pages/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  private static final String URL = "jdbc:postgresql://localhost:5432/pasha";
  private static final String USER = "pasha";
  private static final String PASSWORD = "qwe";
  private static final String DRIVER_NAME = "org.postgresql.Driver";

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(DRIVER_NAME);
    dataSource.setUrl(URL);
    dataSource.setUsername(USER);
    dataSource.setPassword(PASSWORD);
    return dataSource;
  }

  @Bean
  public Properties jpaProperties() {
    Properties props = new Properties();
    props.setProperty("connection.pool_size", "20");
    props.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
    props.setProperty("current_session_context_class", "thread");
    props.setProperty("hbm2ddl.auto", "none");
    return props;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(dataSource());
    emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    emf.setPackagesToScan("org.school.app.model");
    emf.setJpaProperties(jpaProperties());
    return emf;
  }


  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return jpaTransactionManager;
  }

  @Bean
  public OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor() {
    OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor = new OpenEntityManagerInViewInterceptor();
    openEntityManagerInViewInterceptor.setEntityManagerFactory(entityManagerFactory().getObject());
    return openEntityManagerInViewInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor());
  }
}
