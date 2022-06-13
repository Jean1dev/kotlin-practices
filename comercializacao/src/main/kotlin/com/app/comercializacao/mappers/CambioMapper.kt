package com.app.comercializacao.mappers

import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.entities.Cambio
import java.time.LocalDateTime
import java.util.*

fun CambioDto.toDomain() = Cambio(
    UUID.randomUUID(),
    valor = this.valor,
    lastTimeUpdated = LocalDateTime.now(),
    code = this.code,
    codein = this.codein,
    name = this.name,
    high = this.high,
    low = this.low,
    varBid = this.varBid,
    pctChange = this.pctChange,
    bid = this.bid,
    ask = this.ask,
    timestamp = this.timestamp
)