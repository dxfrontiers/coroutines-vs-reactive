package de.dxfrontiers.nonblocking.reactive.persistence

import org.springframework.stereotype.Repository

@Repository
interface ReactiveCharacterRepository {

    fun findByFamilyName(familyName: String)

    fun findByName(firstName: String, familyName: String)

    fun exists(firstName: String, familyName: String)

}
