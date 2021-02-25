package de.dxfrontiers.nonblocking.coroutines.persistence

import de.dxfrontiers.nonblocking.model.persistence.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrDefault
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Repository
import de.dxfrontiers.nonblocking.reactive.persistence.ReactiveCharacterRepository as ReactiveCharacterRepository

@Repository
class CoroutinesCharacterRepository(private val delegate: ReactiveCharacterRepository) {

    fun findByFamilyName(familyName: String): Flow<Character> =
        delegate
            .findByFamilyName(familyName)
            .asFlow()

    suspend fun findByName(firstName: String, familyName: String): Character? =
        delegate
            .findByName(firstName, familyName)
            .awaitFirstOrNull()

    suspend fun findById(id: Long): Character? =
        delegate
            .findById(id)
            .awaitFirstOrNull()

    suspend fun deleteById(id: Long) =
        delegate
            .deleteById(id)
            .awaitFirstOrNull()

    suspend fun exists(firstName: String, familyName: String): Boolean =
        delegate
            .exists(firstName, familyName)
            .awaitFirstOrDefault(false)

    suspend fun save(entity: Character): Character? =
        delegate
            .save(entity)
            .awaitFirstOrNull()

}
