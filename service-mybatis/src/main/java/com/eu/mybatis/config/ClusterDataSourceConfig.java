package com.eu.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mysql从库配置类
 * @author yuanjie
 * @date 2019/1/7 14:09
 */
@Configuration
@MapperScan(basePackages = "com.eu.mybatis.mapper.cluster", sqlSessionTemplateRef = "clusterSqlSessionTemplate")
public class ClusterDataSourceConfig {

    /**
     * 创建数据源
     * @return
     */
    @Bean(name = "clusterDataSource")
    @ConfigurationProperties(prefix = "spring.datasouce.cluster")
    public DataSource clusterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建工厂
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/cluster/*.xml"));
        return bean.getObject();
    }

    /**
     * 创建事务
     * @param dataSource
     * @return
     */
    @Bean(name = "clusterTransationManager")
    public DataSourceTransactionManager clusterDataSourceTransactionManager(@Qualifier("clusterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建模板
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "clusterSqlSessionTemplate")
    public SqlSessionTemplate clusterSqlSessionTemplate(@Qualifier("clusterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
