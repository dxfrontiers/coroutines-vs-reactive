package de.dxfrontiers.blocking.service

import de.dxfrontiers.blocking.persistence.CharacterRepository
import de.dxfrontiers.blocking.exceptions.CharacterNotFoundException
import de.dxfrontiers.blocking.model.persistence.Character
import org.springframework.stereotype.Service

@Service
class CharacterService(private val houseService: HouseService,
                       private val characterRepository: CharacterRepository) {

    fun findByLastName(lastName: String): List<Character> =
        characterRepository.findByLastName(lastName)

    fun findById(id: Long): Character? =
        characterRepository.findById(id).orElse(null)

    fun deleteByName(firstName: String, lastName: String): Unit? =
        characterRepository
            .findByFirstNameAndLastName(firstName, lastName)
            ?.let { characterRepository.deleteById(it.id!!) }

    fun addCharacter(firstName: String, lastName: String): Character? =
        if (characterRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            null
        } else {
            houseService.findByName(lastName)
                ?.let { characterRepository.save(Character(firstName = firstName, lastName = lastName, house = it.id!!)) }
                ?: throw CharacterNotFoundException("No valid house found for the character $firstName $lastName!")
        }
}
