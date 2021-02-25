package de.dxfrontiers.nonblocking.reactive.web

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("reactive/characters")
class ReactiveCharacterController {

    @GetMapping
    fun findByName(@RequestParam lastName: String): Nothing = TODO()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Nothing = TODO()

    @PutMapping
    fun addCharacter(@RequestParam firstName: String, @RequestParam lastName: String): Nothing = TODO()

    @DeleteMapping
    fun deleteByName(@RequestParam firstName: String, @RequestParam lastName: String): Nothing = TODO()

}
