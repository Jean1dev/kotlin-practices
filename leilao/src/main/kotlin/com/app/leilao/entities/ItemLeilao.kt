package com.app.leilao.entities

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document(collection = "item_leilao")
data class ItemLeilao(
    @Id
    val id: UUID?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val dataHoraInicio: LocalDateTime,
    val itemComerciavel: ItemComerciavel
)
