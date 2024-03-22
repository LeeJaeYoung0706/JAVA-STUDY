package com.keti.iam.idthub.config;

import com.keti.iam.idthub.aop.interceptor.MyBatisInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.keti.iam.idthub.mapper" , sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {

    private final String PATH;
    public DataSourceConfig(@Value("${mybatis.mapper-locations}") String PATH) {
        this.PATH = PATH;
    }

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dataSource") DataSource DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(DataSource);
        sqlSessionFactoryBean.setPlugins(new MyBatisInterceptor());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(PATH));  // resources 에서의 위치
        sqlSessionFactoryBean.setTypeAliasesPackage("com.keti.iam.idthub.dto");
       // sqlSessionFactoryBean.setTypeHandlers(new AuthoritiesEnum.TypeHandler());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "SessionTemplate")
    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("SqlSessionFactory") SqlSessionFactory firstSqlSessionFactory) {
        return new SqlSessionTemplate(firstSqlSessionFactory);
    }
}
