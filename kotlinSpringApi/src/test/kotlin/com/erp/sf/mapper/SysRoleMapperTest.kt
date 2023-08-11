package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import com.erp.sf.JunitService
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired


class SysRoleMapperTest : JunitService(){
    @Autowired
    private lateinit var sysRoleMapper: SysRoleMapper
    @BeforeEach
    override fun beforeEach() {
        TODO("Not yet implemented")
    }
}