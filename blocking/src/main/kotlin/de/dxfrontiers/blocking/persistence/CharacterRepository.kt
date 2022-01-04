package de.dxfrontiers.blocking.persistence

import de.dxfrontiers.blocking.model.persistence.Character
import org.springframework.data.jpa.repository.JpaRepository

interface CharacterRepository : JpaRepository<Character, Long> {

    fun findByLastName(lastName: String): List<Character>
    fun findByFirstNameAndLastName(firstName: String, lastName: String): Character?
    fun existsByFirstNameAndLastName(firstName: String, lastName: String): Boolean

}
