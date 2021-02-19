package de.dxfrontiers.nonblocking.reactive.persistence

import de.dxfrontiers.nonblocking.model.persistence.Character
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CharacterRepository : ReactiveCrudRepository<Character, Long> {

    @Query("SELECT * FROM CHARACTERS WHERE LAST_NAME = :familyName")
    fun findByFamilyName(familyName: String): Flux<Character>

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM CHARACTERS WHERE FIRST_NAME = :firstName AND LAST_NAME = :familyName")
    fun exists(firstName: String, familyName: String): Mono<Boolean>

}
