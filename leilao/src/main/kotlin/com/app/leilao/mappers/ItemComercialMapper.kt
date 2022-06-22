package com.app.leilao.mappers

import com.app.leilao.entities.ItemComerciavel
import com.app.leilao.dtos.ItemComerciavelDto
import java.util.*

fun ItemComerciavelDto.toDomain() = ItemComerciavel(
    UUID.randomUUID(),
    animalReferencia = this.animalReferencia,
    sequencial = this.sequencial,
    valorSugerido = this.valorSugerido,
    valorCambio = this.valorCambio,
    tenantId = this.tenantId
)