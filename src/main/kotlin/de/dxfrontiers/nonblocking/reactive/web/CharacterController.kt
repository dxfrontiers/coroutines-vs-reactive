package de.dxfrontiers.nonblocking.reactive.web

import de.dxfrontiers.nonblocking.exceptions.CharacterNotFoundException
import de.dxfrontiers.nonblocking.model.persistence.Character
import de.dxfrontiers.nonblocking.reactive.service.CharacterService
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@RestController
@RequestMapping("characters")
class CharacterController(private val characterService: CharacterService) {

    @GetMapping
    fun findByName(@RequestParam lastName: String): Flux<Character> =
        characterService.findByLastName(lastName)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Character> =
        characterService.findById(id)

    @PutMapping
    fun addCharacter(@RequestParam firstName: String, @RequestParam lastName: String, response: ServerHttpResponse): Mono<ResponseEntity<Any>> =
        characterService
            .addCharacter(firstName, lastName)
            .map { ResponseEntity.accepted().build<Any>() }
            .switchIfEmpty { Mono.just(ResponseEntity.ok().build()) }
            .onErrorResume(CharacterNotFoundException::class.java) {
                Mono.just(ResponseEntity.badRequest().body(it.message))
            }
}
