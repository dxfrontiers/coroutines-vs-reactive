package de.dxfrontiers.blocking.service

import de.dxfrontiers.blocking.persistence.HouseRepository
import de.dxfrontiers.blocking.model.persistence.House
import org.springframework.stereotype.Service

@Service
class HouseService(private val houseRepository: HouseRepository) {

    fun findByName(name: String): House? =
        houseRepository.findByName(name)

}
