package de.dxfrontiers.nonblocking.reactive.persistence

import de.dxfrontiers.nonblocking.model.persistence.House
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ReactiveHouseRepository : ReactiveCrudRepository<House, Long> {

    fun findByName(name: String): Mono<House>

}
