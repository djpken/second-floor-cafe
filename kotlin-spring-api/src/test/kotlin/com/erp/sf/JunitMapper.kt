package com.erp.sf

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.transaction.annotation.Transactional


@Transactional
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class JunitMapper :BeforeEach{
}