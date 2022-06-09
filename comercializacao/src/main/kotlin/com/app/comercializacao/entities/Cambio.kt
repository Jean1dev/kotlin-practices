package com.app.comercializacao.entities

import java.time.LocalDateTime

data class Cambio(
        val valor: Double,
        val lastTimeUpdated: LocalDateTime
)