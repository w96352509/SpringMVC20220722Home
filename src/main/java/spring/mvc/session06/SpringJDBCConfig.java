package spring.mvc.session06;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan
@PropertySource(value = {"classpath:db.properties"})
public class SpringJDBCConfig {

	@Autowired
	protected Environment env; // 用於取得外部檔案配置內容
	
	@Bean
	public DataSource dataSource() {
		
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			// Basic
			ds.setDriverClass(env.getProperty("jdbc.driver"));
		    ds.setJdbcUrl(env.getProperty("jdbc.url"));
		    ds.setUser(env.getProperty("jdbc.username"));
			ds.setPassword(env.getProperty("jdbc.password"));
			// Options
			ds.setMinPoolSize(10);
			ds.setMaxPoolSize(100);
			ds.setInitialPoolSize(10);
			ds.setMaxIdleTime(1800);
			ds.setMaxStatements(100);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
