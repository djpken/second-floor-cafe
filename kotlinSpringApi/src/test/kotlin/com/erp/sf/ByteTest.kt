package com.erp.sf

import com.erp.sf.component.ObjectByteComponent
import com.erp.sf.entity.Score
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ByteTest : JunitService() {
    @Autowired
    private lateinit var objectByteComponent: ObjectByteComponent

    @BeforeEach
    override fun beforeEach() {
    }

    @Test
    fun test() {
        val score = Score(1, 2, 3)
        val objectToByte = objectByteComponent.objectToByte(score)
        val objectFromByte = objectByteComponent.byteToObject<Score>(objectToByte)
    }
}