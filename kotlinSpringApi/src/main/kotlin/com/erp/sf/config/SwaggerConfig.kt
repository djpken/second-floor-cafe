package com.erp.sf.config

import cn.hutool.core.util.RandomUtil
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springdoc.core.customizers.GlobalOpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        val name = "Ken Hsu"
        val url = "https://github.com/djpken"
        val email = "djpkendjpken@gmail.com"
        val contact = Contact().name(name).url(url).email(email)
        return OpenAPI().info(
            Info().title("Second Floor Cafe API").contact(contact).version("1.0")
                .description("My side project for Second Floor Cafe")
        )
    }
}