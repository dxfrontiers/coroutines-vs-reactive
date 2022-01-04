package de.dxfrontiers.nonblocking.reactive.service

import de.dxfrontiers.nonblocking.model.persistence.House
import de.dxfrontiers.nonblocking.reactive.persistence.ReactiveHouseRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ReactiveHouseService(private val reactiveHouseRepository: ReactiveHouseRepository) {

    fun findByName(name: String): Mono<House> =
        reactiveHouseRepository.findByName(name)

}
