package com.study.backend.config.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//해당 위치에 있는 @Mapper가 붙은 인터페이스를 스캔하고 실제 SQL문과 연결되게 해줌
@MapperScan(basePackages = {"com.study.backend.*.mapper"})
public class dbConfig {
	

	   @Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource);
	        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/**/*.xml"));
	        return sessionFactory.getObject();
	    }
	 
	    @Bean
	    public PlatformTransactionManager transactionManager(DataSource dataSource) {
	    	DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
	    	transactionManager.setGlobalRollbackOnParticipationFailure(false);
	        return transactionManager;
	    }
}