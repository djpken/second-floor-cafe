package com.erp.sf.util

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch

class WatchUtil {
    private val stopWatch: StopWatch = StopWatch()
    private val logging = LoggerFactory.getLogger(javaClass)

    init {
        stopWatch.start()
        logging.info(" Start watch")
    }

    fun stop() {
        stopWatch.stop()
        logging.info("Watch time: ${stopWatch.totalTimeMillis}")
    }
}