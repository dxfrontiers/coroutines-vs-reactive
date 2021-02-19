package de.dxfrontiers.nonblocking.reactive.service

import de.dxfrontiers.nonblocking.model.persistence.House
import de.dxfrontiers.nonblocking.reactive.persistence.HouseRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class HouseService(private val houseRepository: HouseRepository) {

    fun findByName(name: String): Mono<House> =
        houseRepository.findByName(name)

}
