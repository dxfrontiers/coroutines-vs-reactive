package de.dxfrontiers.blocking.model.persistence

import javax.persistence.*

@Entity
@Table(name = "CHARACTERS")
data class Character(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val firstName: String,

    val lastName: String,

    val house: Long

)
