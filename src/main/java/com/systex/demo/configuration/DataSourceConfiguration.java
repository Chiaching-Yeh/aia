package manage.config;

import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.jackson2.Jackson2Plugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfiguration {

    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource") 
    @Primary
    public DataSource driverManagerDataSource() {
    	return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "datasource-2")
    @ConfigurationProperties(prefix = "spring.datasource-2")
    public DataSource driverManagerDataSourceEIP() {
    	return DataSourceBuilder.create().build();
    }

    @Bean(name="tm1")
    @Autowired
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier ("datasource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
    
    @Bean(name="tm2")
    @Autowired
    public DataSourceTransactionManager dataSourceTransactionManagerEIP(@Qualifier ("datasource-2") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
    
    @Bean("jdbi")
    public Jdbi jdbi(@Qualifier ("datasource") DataSource dataSource) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(proxy);

        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new Jackson2Plugin());
        return jdbi;
    }
    
    @Bean("jdbi-2")
    public Jdbi jdbiEIP(@Qualifier ("datasource-2") DataSource dataSource) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(proxy);

        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new Jackson2Plugin());
        return jdbi;
    }

}