package de.dxfrontiers.blocking.model.persistence

import javax.persistence.*

@Entity
@Table(name = "HOUSES")
data class House(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val words: String

)
