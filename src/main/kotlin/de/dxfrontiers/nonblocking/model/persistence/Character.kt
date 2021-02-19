package de.dxfrontiers.nonblocking.model.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CHARACTERS")
data class Character(

    @Id
    val id: Long? = null,

    val firstName: String,

    val lastName: String,

    val house: Long

)
