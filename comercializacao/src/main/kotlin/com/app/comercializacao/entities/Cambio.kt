package com.app.comercializacao.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document(collection = "cambio")
data class Cambio(
    @Id
    val id: UUID?,
    var valor: Double,
    var lastTimeUpdated: LocalDateTime
)