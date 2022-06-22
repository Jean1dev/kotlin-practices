package com.app.leilao.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "item_comerciavel")
data class ItemComerciavel(
    @Id
    val id: UUID?,
    val animalReferencia: Number,
    val sequencial: Number,
    val valorSugerido: Number,
    val valorCambio: Number,
    val tenantId: Number
)
