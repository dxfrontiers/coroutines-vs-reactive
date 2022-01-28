package de.dxfrontiers.nonblocking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    Hooks.onOperatorDebug()
    runApplication<Application>(*args)
}
