package de.dxfrontiers.nonblocking.coroutines.service

import de.dxfrontiers.nonblocking.model.persistence.House
import de.dxfrontiers.nonblocking.coroutines.persistence.CoroutinesHouseRepository
import org.springframework.stereotype.Service

@Service
class CoroutinesHouseService(private val coroutinesHouseRepository: CoroutinesHouseRepository) {

    suspend fun findByName(name: String): House? =
        coroutinesHouseRepository.findByName(name)

}
