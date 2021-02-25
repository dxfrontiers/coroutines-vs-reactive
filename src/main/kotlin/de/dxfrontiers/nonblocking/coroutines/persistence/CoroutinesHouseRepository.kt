package de.dxfrontiers.nonblocking.coroutines.persistence

import de.dxfrontiers.nonblocking.model.persistence.House
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Repository
import de.dxfrontiers.nonblocking.reactive.persistence.ReactiveHouseRepository as ReactiveHouseRepository

@Repository
class CoroutinesHouseRepository(private val delegate: ReactiveHouseRepository) {

    suspend fun findByName(name: String): House? =
        delegate
            .findByName(name)
            .awaitFirstOrNull()

}
