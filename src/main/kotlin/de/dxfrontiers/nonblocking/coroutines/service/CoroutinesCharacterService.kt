package de.dxfrontiers.nonblocking.coroutines.service

import org.springframework.stereotype.Service

@Service
class CoroutinesCharacterService {

    fun findByLastName(lastName: String): Nothing = TODO()

    suspend fun findById(id: Long): Nothing = TODO()

    suspend fun deleteByName(firstName: String, lastName: String): Nothing = TODO()

    suspend fun addCharacter(firstName: String, lastName: String): Nothing = TODO()

}
