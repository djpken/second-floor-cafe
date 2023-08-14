package com.erp.sf.service

import com.erp.sf.entity.Score

interface ScoreService {
    fun insertScore(score: Score):Score
    fun selectScoreByUserId(userId: Long): List<Score>
}