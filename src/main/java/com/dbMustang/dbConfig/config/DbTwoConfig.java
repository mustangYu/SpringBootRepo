package com.dbMustang.dbConfig.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dbMustang.dbConfig.entity.DBTwo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

/**
 * @title:DBTwo配置类
 * @author: mustang.yu
 */

@Configuration
@MapperScan(basePackages = "com.dbMustang.dbMapper.mapper2",sqlSessionTemplateRef = "twoSqlSessionTemplate")
public class DbTwoConfig {
    /**
     * 设置数据源
     * @author: mustang.yu
     */
    @Bean(name = "mustangDBTwo")
    //@primary---------意思是在众多相同的bean中，优先使用用@Primary注解的bean.
    public DataSource setDataSource (DBTwo dbTwo) throws Exception{
        DruidDataSource druidDataSource2 = new DruidDataSource();
        druidDataSource2.setUrl(dbTwo.getUrl());
        druidDataSource2.setUsername(dbTwo.getUsername());
        druidDataSource2.setPassword(dbTwo.getPassword());
        druidDataSource2.setDriverClassName(dbTwo.getDriverClassName());
        return druidDataSource2;
    }

    /**
     * 设置sqlSessionFactory
     * @author: mustang.yu
     */
    @Bean(name = "twoSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("mustangDBTwo")DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/twoMapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 设置sqlSessionTemplate
     * @author: mustang.yu
     */
    @Bean(name = "twoSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate (@Qualifier("twoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
