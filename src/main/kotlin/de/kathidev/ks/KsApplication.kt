package de.kathidev.ks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KsApplication

fun main(args: Array<String>) {
    runApplication<KsApplication>(*args)
}
