package de.dxfrontiers.nonblocking.coroutines.service

import org.springframework.stereotype.Service

@Service
class CoroutinesHouseService {

    suspend fun findByName(name: String): Nothing = TODO()

}
