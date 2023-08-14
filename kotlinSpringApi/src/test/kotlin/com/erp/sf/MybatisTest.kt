package com.erp.sf

import com.erp.sf.entity.Score
import com.erp.sf.mapper.ScoreMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MybatisTest : JunitService() {
    @Autowired
    private lateinit var scoreMapper: ScoreMapper
    override fun beforeEach() {
    }

    @Test
    fun test() {
        val scores = List(3) { Score(0, 2, 3) }

        scoreMapper.insertBatch(scores)
        scores.drop(1).forEachIndexed { index, score ->
            score.id = scores[0].id!! + index + 1
        }

        println(scores)
    }

}