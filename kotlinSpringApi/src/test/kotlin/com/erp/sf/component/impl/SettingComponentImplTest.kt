package com.erp.sf.component.impl

import com.erp.sf.JunitService
import com.erp.sf.constant.S
import com.erp.sf.component.SettingComponent
import com.erp.sf.util.Watch
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SettingComponentImplTest : JunitService() {
    @Autowired
    private lateinit var settingComponent: SettingComponent

    @BeforeEach
    override fun beforeEach() {
    }

    @Test
    fun get() {
        val watch = Watch()
        println(settingComponent.get(S.INVITE_CODE))
        watch.stop()
        val watch1 = Watch()
        println(settingComponent.get(S.INVITE_CODE))
        watch1.stop()
    }

}