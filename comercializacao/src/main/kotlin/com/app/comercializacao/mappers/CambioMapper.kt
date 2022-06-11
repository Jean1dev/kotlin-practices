package com.app.comercializacao.mappers

import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.entities.Cambio
import java.time.LocalDateTime
import java.util.*

fun CambioDto.toDomain() = Cambio(
    UUID.randomUUID(),
    valor = this.valor,
    lastTimeUpdated = LocalDateTime.now()
)