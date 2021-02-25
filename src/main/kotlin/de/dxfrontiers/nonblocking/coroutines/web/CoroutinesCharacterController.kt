package de.dxfrontiers.nonblocking.coroutines.web

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("coroutines/characters")
class CoroutinesCharacterController {

    @GetMapping
    fun findByName(@RequestParam lastName: String): Nothing = TODO()

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): Nothing = TODO()

    @PutMapping
    suspend fun addCharacter(@RequestParam firstName: String, @RequestParam lastName: String): Nothing = TODO()

    @DeleteMapping
    suspend fun deleteByName(@RequestParam firstName: String, @RequestParam lastName: String): Nothing = TODO()

}
