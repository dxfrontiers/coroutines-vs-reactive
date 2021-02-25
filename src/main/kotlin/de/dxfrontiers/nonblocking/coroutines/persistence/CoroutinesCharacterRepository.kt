package de.dxfrontiers.nonblocking.coroutines.persistence

import org.springframework.stereotype.Repository

@Repository
interface CoroutinesCharacterRepository {

    fun findByFamilyName(familyName: String)

    fun findByName(firstName: String, familyName: String)

    fun exists(firstName: String, familyName: String)

}
