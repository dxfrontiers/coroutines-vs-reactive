package de.dxfrontiers.nonblocking.model.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("HOUSES")
data class House(

    @Id
    val id: Long? = null,

    val name: String,

    val words: String

)
