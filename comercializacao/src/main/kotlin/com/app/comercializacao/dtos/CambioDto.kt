package com.app.comercializacao.dtos

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty

data class CambioDto(
        @Schema(description = "Valor atual do cambio")
        @field:NotEmpty
        val valor: Double,

        val lastTimeUpdated: LocalDateTime
)
