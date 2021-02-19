package de.dxfrontiers.nonblocking.reactive.configuration

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
class DatabaseConfig {

    @Bean
    fun connectionFactoryInitializer(connectionFactory: ConnectionFactory) = ConnectionFactoryInitializer().apply {
        setConnectionFactory(connectionFactory)
        setDatabasePopulator(ResourceDatabasePopulator(
            ClassPathResource("schema.sql"),
            ClassPathResource("data.sql")
        ))
    }

}
