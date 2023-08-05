package com.erp.sf.config

import cn.hutool.core.util.RandomUtil
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springdoc.core.customizers.GlobalOpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun orderGlobalOpenApiCustomizer(): GlobalOpenApiCustomizer {
        return GlobalOpenApiCustomizer { openApi ->
            openApi.tags?.forEach { tag ->
                val map = mutableMapOf<String, Any>()
                map["x-order"] = RandomUtil.randomInt(0, 100)
                tag.extensions = map
            }
            openApi.addExtension("x-test123", "333")
            openApi.paths?.addExtension("x-abb", RandomUtil.randomInt(1, 100))
        }
    }

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("ERP API")
                    .version("1.0")
                    .description(".textVideo")
                    .termsOfService("http://doc.xiaominfo.com")
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("http://doc.xiaominfo.com")
                    )
            )
    }
}