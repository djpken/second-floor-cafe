package com.erp.sf.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@MapperScan("com.erp.sf.mapper")
class MyBatisConfig {
    @Bean
    fun  mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val interceptor:MybatisPlusInterceptor = MybatisPlusInterceptor()
        interceptor.addInnerInterceptor(PaginationInnerInterceptor(DbType.MARIADB))
        return interceptor
    }
}