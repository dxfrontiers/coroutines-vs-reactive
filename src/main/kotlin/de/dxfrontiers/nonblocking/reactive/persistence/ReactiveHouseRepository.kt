package de.dxfrontiers.nonblocking.reactive.persistence

import org.springframework.stereotype.Repository

@Repository
interface ReactiveHouseRepository {

    fun findByName(name: String)

}
