package de.dxfrontiers.nonblocking.reactive.service

import de.dxfrontiers.nonblocking.exceptions.CharacterNotFoundException
import de.dxfrontiers.nonblocking.model.persistence.Character
import de.dxfrontiers.nonblocking.reactive.persistence.ReactiveCharacterRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class ReactiveCharacterService(private val reactiveHouseService: ReactiveHouseService,
                               private val reactiveCharacterRepository: ReactiveCharacterRepository) {

    fun findByLastName(lastName: String): Flux<Character> =
        reactiveCharacterRepository.findByLastName(lastName)

    fun findById(id: Long): Mono<Character> =
        reactiveCharacterRepository.findById(id)

    fun deleteByName(firstName: String, lastName: String): Mono<Void> =
        reactiveCharacterRepository
            .findByFirstNameAndLastName(firstName, lastName)
            .doOnNext {
                reactiveCharacterRepository.deleteById(it.id!!)
            }.then()

    fun addCharacter(firstName: String, lastName: String): Mono<Character> =
        reactiveCharacterRepository
            .existsByFirstNameAndLastName(firstName, lastName)
            .filter { it == false }
            .flatMap {
                reactiveHouseService
                    .findByName(lastName)
                    .switchIfEmpty { Mono.error { CharacterNotFoundException("No valid house found for the character $firstName $lastName!") }}
            }.flatMap { reactiveCharacterRepository.save(Character(firstName = firstName, lastName = lastName, house = it.id!!)) }
}
