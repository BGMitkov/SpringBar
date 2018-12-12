package bar.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

	@Bean(name = "db-test")
//	@ConfigurationProperties(prefix = "spring.db-test")
	public DataSource createTestDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("51303");
		return dataSource;
//		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTest")
	@Autowired
	public JdbcTemplate createJdbcTemplate_Test(@Qualifier("db-test") DataSource testDS) {
		return new JdbcTemplate(testDS);
	}
}
