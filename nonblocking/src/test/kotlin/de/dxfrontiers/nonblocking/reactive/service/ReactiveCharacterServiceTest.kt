package de.dxfrontiers.nonblocking.reactive.service

import de.dxfrontiers.nonblocking.model.persistence.Character
import de.dxfrontiers.nonblocking.model.persistence.House
import de.dxfrontiers.nonblocking.reactive.persistence.ReactiveCharacterRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.dao.OptimisticLockingFailureException
import reactor.core.publisher.Mono

@ExtendWith(MockitoExtension::class)
class ReactiveCharacterServiceTest {

    @InjectMocks
    private lateinit var cut: ReactiveCharacterService

    @Mock
    private lateinit var reactiveHouseService: ReactiveHouseService

    @Mock
    private lateinit var reactiveCharacterRepository: ReactiveCharacterRepository

    @Test
    fun `testing stuff`() {
        `when`(reactiveCharacterRepository.existsByFirstNameAndLastName("meow", "meowmeow")).thenReturn(Mono.just(false))
        `when`(reactiveHouseService.findByName("meowmeow")).thenReturn(Mono.just(House(42, "meowmeow", "Meow!")))
        `when`(reactiveCharacterRepository.save(any(Character::class.java))).thenThrow(OptimisticLockingFailureException("something failed"))

        cut.addCharacter("meow", "meowmeow").subscribe()
    }
}
