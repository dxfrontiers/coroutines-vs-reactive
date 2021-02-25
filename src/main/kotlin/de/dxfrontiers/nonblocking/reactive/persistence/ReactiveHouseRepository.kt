package de.dxfrontiers.nonblocking.reactive.persistence

import de.dxfrontiers.nonblocking.model.persistence.House
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ReactiveHouseRepository : ReactiveCrudRepository<House, Long> {

    @Query("SELECT * FROM HOUSES WHERE NAME = :name")
    fun findByName(name: String): Mono<House>

}
