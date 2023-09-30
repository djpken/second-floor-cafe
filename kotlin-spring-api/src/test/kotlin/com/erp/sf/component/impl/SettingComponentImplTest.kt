package com.erp.sf.component.impl

import com.erp.sf.JunitService
import com.erp.sf.constant.S
import com.erp.sf.component.SettingComponent
import com.erp.sf.util.WatchUtil
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SettingComponentImplTest : JunitService() {

    @BeforeEach
    override fun beforeEach() {
    }

    @Test
    fun get() {
        val watch = WatchUtil()
        println(settingComponent.get(S.INVITE_CODE))
        watch.stop()
        val watch1 = WatchUtil()
        println(settingComponent.get(S.INVITE_CODE))
        watch1.stop()
    }

}