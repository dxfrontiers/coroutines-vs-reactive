package de.dxfrontiers.blocking.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import javax.sql.DataSource

@Configuration
class DatabaseConfig {

    @Bean
    fun dataSourceInitializer(dataSource: DataSource) = DataSourceInitializer().apply {
        setDataSource(dataSource)
        setDatabasePopulator(ResourceDatabasePopulator(
            ClassPathResource("schema.sql"),
            ClassPathResource("data.sql")
        ))
    }

}
