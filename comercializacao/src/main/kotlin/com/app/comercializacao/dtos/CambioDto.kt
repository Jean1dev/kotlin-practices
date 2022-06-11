package com.app.comercializacao.dtos

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

data class CambioDto(
        @Schema(description = "Valor atual do cambio")
        @NotNull
        val valor: Double,

        val lastTimeUpdated: LocalDateTime
)
