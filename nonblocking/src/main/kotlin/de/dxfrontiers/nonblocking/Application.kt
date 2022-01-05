package de.dxfrontiers.nonblocking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.tools.agent.ReactorDebugAgent

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    ReactorDebugAgent.init()
    runApplication<Application>(*args)
}
