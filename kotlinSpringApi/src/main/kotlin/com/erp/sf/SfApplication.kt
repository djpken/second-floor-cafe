package com.erp.sf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Indexed

@Indexed
@SpringBootApplication
class SfApplication

fun main(args: Array<String>) {
    runApplication<SfApplication>(*args)
}
