package de.dxfrontiers.blocking.persistence

import de.dxfrontiers.blocking.model.persistence.House
import org.springframework.data.jpa.repository.JpaRepository

interface HouseRepository : JpaRepository<House, Long> {

    fun findByName(name: String): House?

}
