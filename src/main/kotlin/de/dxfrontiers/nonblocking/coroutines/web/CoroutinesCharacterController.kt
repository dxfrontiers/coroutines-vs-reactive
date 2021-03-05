package de.dxfrontiers.nonblocking.coroutines.web

import de.dxfrontiers.nonblocking.coroutines.service.CoroutinesCharacterService
import de.dxfrontiers.nonblocking.exceptions.CharacterNotFoundException
import de.dxfrontiers.nonblocking.model.persistence.Character
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("coroutines/characters")
class CoroutinesCharacterController(private val coroutinesCharacterService: CoroutinesCharacterService) {

    @GetMapping
    fun findByName(@RequestParam lastName: String): Flow<Character> =
        coroutinesCharacterService.findByLastName(lastName)

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): Character? =
        coroutinesCharacterService.findById(id)

    @PutMapping
    suspend fun addCharacter(@RequestParam firstName: String, @RequestParam lastName: String): ResponseEntity<String> {
        return try {
            val character = coroutinesCharacterService.addCharacter(firstName, lastName)

            character?.let {
                ResponseEntity.accepted().build()
            } ?: run { ResponseEntity.ok().build() }
        } catch (ex: CharacterNotFoundException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @DeleteMapping
    suspend fun deleteByName(@RequestParam firstName: String, @RequestParam lastName: String) =
        coroutinesCharacterService.deleteByName(firstName, lastName)

}
