package com.erp.sf

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest
import com.erp.sf.entity.SysMenu
import com.erp.sf.mapper.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.transaction.annotation.Transactional


@Transactional
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class JunitBase {
}