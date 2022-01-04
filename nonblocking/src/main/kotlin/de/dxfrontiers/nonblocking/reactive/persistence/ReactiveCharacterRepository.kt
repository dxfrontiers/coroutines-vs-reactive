package de.dxfrontiers.nonblocking.reactive.persistence

import de.dxfrontiers.nonblocking.model.persistence.Character
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ReactiveCharacterRepository : ReactiveCrudRepository<Character, Long> {

    fun findByLastName(lastName: String): Flux<Character>
    fun findByFirstNameAndLastName(firstName: String, lastName: String): Mono<Character>
    fun existsByFirstNameAndLastName(firstName: String, lastName: String): Mono<Boolean>

}
