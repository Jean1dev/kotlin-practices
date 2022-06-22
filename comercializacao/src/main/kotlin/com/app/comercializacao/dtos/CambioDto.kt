package com.app.comercializacao.dtos

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

data class CambioDto(
    @Schema(description = "Valor atual do cambio")
    @NotNull
    val valor: Double,
    val lastTimeUpdated: LocalDateTime,
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
) {
    constructor(valor: Double) : this(
        valor,
        lastTimeUpdated = LocalDateTime.now(),
        code = null,
        codein = null,
        name = null,
        high = null,
        low = null,
        varBid = null,
        pctChange = null,
        bid = null,
        ask = null,
        timestamp = null
    )
}
