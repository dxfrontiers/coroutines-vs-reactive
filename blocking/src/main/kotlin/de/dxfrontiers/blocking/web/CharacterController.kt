package de.dxfrontiers.blocking.web

import de.dxfrontiers.blocking.service.CharacterService
import de.dxfrontiers.blocking.exceptions.CharacterNotFoundException
import de.dxfrontiers.blocking.model.persistence.Character
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("imperative/characters")
class CharacterController(private val characterService: CharacterService) {

    @GetMapping
    fun findByName(@RequestParam lastName: String): List<Character> =
        characterService.findByLastName(lastName)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Character? =
        characterService.findById(id)

    @PutMapping
    fun addCharacter(@RequestParam firstName: String, @RequestParam lastName: String): ResponseEntity<String> {
        return try {
            val character = characterService.addCharacter(firstName, lastName)

            character?.let {
                ResponseEntity.status(HttpStatus.CREATED).build()
            } ?: ResponseEntity.ok().build()
        } catch (ex: CharacterNotFoundException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @DeleteMapping
    fun deleteByName(@RequestParam firstName: String, @RequestParam lastName: String) =
        characterService.deleteByName(firstName, lastName)

}
