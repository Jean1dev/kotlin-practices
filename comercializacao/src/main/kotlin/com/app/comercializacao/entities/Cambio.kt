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
    var lastTimeUpdated: LocalDateTime,
    val code: String?,
    val codein: String?,
    val name: String?,
    val high: Double?,
    val low: Double?,
    val varBid: Double?,
    val pctChange: Double?,
    val bid: Double?,
    val ask: Double?,
    val timestamp: Number?
)