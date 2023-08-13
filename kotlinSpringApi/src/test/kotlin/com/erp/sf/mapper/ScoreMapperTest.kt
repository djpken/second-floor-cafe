package com.erp.sf.mapper

import com.erp.sf.JunitService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ScoreMapperTest :JunitService(){
    @Autowired
    private lateinit var scoreMapper: ScoreMapper
    @BeforeEach
    override fun beforeEach() {
    }
    @Test
    fun test(){}

}