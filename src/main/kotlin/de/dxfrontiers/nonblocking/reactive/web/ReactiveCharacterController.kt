package de.dxfrontiers.nonblocking.reactive.web

import de.dxfrontiers.nonblocking.exceptions.CharacterNotFoundException
import de.dxfrontiers.nonblocking.model.persistence.Character
import de.dxfrontiers.nonblocking.reactive.service.ReactiveCharacterService
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@RestController
@RequestMapping("reactive/characters")
class ReactiveCharacterController(private val reactiveCharacterService: ReactiveCharacterService) {

    @GetMapping
    fun findByName(@RequestParam lastName: String): Flux<Character> =
        reactiveCharacterService.findByLastName(lastName)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Character> =
        reactiveCharacterService.findById(id)

    @PutMapping
    fun addCharacter(@RequestParam firstName: String, @RequestParam lastName: String, response: ServerHttpResponse): Mono<ResponseEntity<String>> =
        reactiveCharacterService
            .addCharacter(firstName, lastName)
            .map { ResponseEntity.accepted().build<String>() }
            .switchIfEmpty { Mono.just(ResponseEntity.ok().build()) }
            .onErrorResume(CharacterNotFoundException::class.java) {
                Mono.just(ResponseEntity.badRequest().body(it.message))
            }

    @DeleteMapping
    fun deleteByName(@RequestParam firstName: String, @RequestParam lastName: String): Mono<Void> =
        reactiveCharacterService.deleteByName(firstName, lastName)

}