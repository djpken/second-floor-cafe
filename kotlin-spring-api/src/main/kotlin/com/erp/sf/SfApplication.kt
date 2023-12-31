package com.erp.sf

import com.erp.sf.component.RedisComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Indexed

@Indexed
@SpringBootApplication
class SfApplication {
    @Autowired
    private lateinit var redisComponent: RedisComponent
    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun initConnection(): CommandLineRunner {
        return CommandLineRunner {
            redisComponent.cleanRedis()
            logger.info("Redis status : cleaned")
            logger.info("File encoding : " + System.getProperty("file.encoding"))
        }
    }
}

fun main(args: Array<String>) {
    System.setProperty("console.encoding", "UTF-8")
    runApplication<SfApplication>(*args)
}
