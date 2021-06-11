package de.dxfrontiers.nonblocking.coroutines.persistence

import de.dxfrontiers.nonblocking.model.persistence.Character
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CoroutinesCharacterRepository : CoroutineCrudRepository<Character, Long> {

    fun findByLastName(lastName: String): Flow<Character>
    suspend fun findByFirstNameAndLastName(firstName: String, lastName: String): Character?
    suspend fun existsByFirstNameAndLastName(firstName: String, lastName: String): Boolean

}
