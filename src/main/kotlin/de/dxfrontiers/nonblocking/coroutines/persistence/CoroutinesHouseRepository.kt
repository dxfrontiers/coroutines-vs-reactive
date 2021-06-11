package de.dxfrontiers.nonblocking.coroutines.persistence

import de.dxfrontiers.nonblocking.model.persistence.House
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CoroutinesHouseRepository : CoroutineCrudRepository<House, Long> {

    fun findByName(name: String): House?

}
