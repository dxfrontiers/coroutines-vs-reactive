package de.dxfrontiers.nonblocking.coroutines.service

import de.dxfrontiers.nonblocking.coroutines.persistence.CoroutinesCharacterRepository
import de.dxfrontiers.nonblocking.exceptions.CharacterNotFoundException
import de.dxfrontiers.nonblocking.model.persistence.Character
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class CoroutinesCharacterService(private val coroutinesHouseService: CoroutinesHouseService,
                                 private val coroutinesCharacterRepository: CoroutinesCharacterRepository) {

    fun findByLastName(lastName: String): Flow<Character> =
        coroutinesCharacterRepository.findByLastName(lastName)

    suspend fun findById(id: Long): Character? =
        coroutinesCharacterRepository.findById(id)

    suspend fun deleteByName(firstName: String, lastName: String) =
        coroutinesCharacterRepository
            .findByFirstNameAndLastName(firstName, lastName)
            ?.let {
                coroutinesCharacterRepository.deleteById(it.id!!)
            }

    suspend fun addCharacter(firstName: String, lastName: String): Character? =
        if (coroutinesCharacterRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            null
        } else {
            coroutinesHouseService.findByName(lastName)
                    ?.let { coroutinesCharacterRepository.save(Character(firstName = firstName, lastName = lastName, house = it.id!!)) }
                    ?: run { throw CharacterNotFoundException("No valid house found for the character $firstName $lastName!") }
        }
}
