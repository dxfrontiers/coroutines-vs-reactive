package de.dxfrontiers.nonblocking.reactive.service

import de.dxfrontiers.nonblocking.exceptions.CharacterNotFoundException
import de.dxfrontiers.nonblocking.model.persistence.Character
import de.dxfrontiers.nonblocking.reactive.persistence.CharacterRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class CharacterService(private val houseService: HouseService,
                       private val characterRepository: CharacterRepository) {

    fun findByLastName(lastName: String): Flux<Character> =
        characterRepository.findByFamilyName(lastName)

    fun findById(id: Long): Mono<Character> =
        characterRepository.findById(id)

    fun addCharacter(firstName: String, lastName: String): Mono<Character> =
        characterRepository
            .exists(firstName, lastName)
            .filter { it == false }
            .flatMap {
                houseService
                    .findByName(lastName)
                    .switchIfEmpty { Mono.error { CharacterNotFoundException("No valid house found for the character $firstName $lastName!") }}
            }.flatMap { characterRepository.save(Character(firstName = firstName, lastName = lastName, house = it.id!!)) }
}
