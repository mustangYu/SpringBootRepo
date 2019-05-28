package com.dbMustang.dbConfig.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dbMustang.dbConfig.entity.DBOne;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

/**
 * @title:DBOne配置类(默认数据源)
 * @author: mustang.yu
 */

@Configuration
@MapperScan(basePackages = "com.dbMustang.dbMapper.mapper1",sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class DbOneConfig {
    /**
     * 设置数据源
     * @author: mustang.yu
     */
    @Bean(name = "mustangDBOne")
    @Primary
    //@primary---------意思是在众多相同的bean中，优先使用用@Primary注解的bean.
    public DataSource setDataSource (DBOne dbOne) throws Exception{
        DruidDataSource druidDataSource1 = new DruidDataSource();
        druidDataSource1.setUrl(dbOne.getUrl());
        druidDataSource1.setUsername(dbOne.getUsername());
        druidDataSource1.setPassword(dbOne.getPassword());
        druidDataSource1.setDriverClassName(dbOne.getDriverClassName());
        return druidDataSource1;
    }

    /**
     * 设置sqlSessionFactory
     * @author: mustang.yu
     */
    @Bean(name = "oneSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("mustangDBOne")DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/oneMapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 设置sqlSessionTemplate
     * @author: mustang.yu
     */
    @Bean(name = "oneSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate (@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
