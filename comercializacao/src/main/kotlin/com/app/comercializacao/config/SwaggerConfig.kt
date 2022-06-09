package com.app.comercializacao.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Schema
import org.springdoc.core.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Configuration
@OpenAPIDefinition
class SwaggerConfig {

    @Bean
    fun configureInlineLocalTime(): SpringDocUtils {
        val schema = Schema<LocalTime>()
        val localTimeFormatted = schema.example(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        return SpringDocUtils.getConfig().replaceWithSchema(LocalTime::class.java, localTimeFormatted)
    }

    @Bean
    fun api(): OpenAPI = OpenAPI().info(apiInfo())

    private fun apiInfo(): Info = Info()
            .version("1.0")
            .description("Comercialização API")
            .description("temp")
            .contact(Contact().name("Jeanluca F Pereira").url("").email("jeanlucafp@gmail.com"))
}