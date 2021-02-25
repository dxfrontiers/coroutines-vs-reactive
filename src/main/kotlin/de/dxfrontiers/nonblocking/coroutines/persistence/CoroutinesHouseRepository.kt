package de.dxfrontiers.nonblocking.coroutines.persistence

import org.springframework.stereotype.Repository

@Repository
interface CoroutinesHouseRepository {

    fun findByName(name: String)

}
