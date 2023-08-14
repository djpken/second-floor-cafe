package com.erp.sf.service.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.erp.sf.entity.Score
import com.erp.sf.mapper.ScoreMapper
import com.erp.sf.service.ScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ScoreServiceImpl : ScoreService {
    @Autowired
    private lateinit var scoreMapper: ScoreMapper
    override fun insertScore(score: Score): Score {
        scoreMapper.insert(score)
        return score
    }

    override fun selectScoreByUserId(userId: Long): List<Score> {
        val eq = KtQueryWrapper(Score::class.java).eq(Score::userId, userId)
        return scoreMapper.selectList(eq)
    }
}