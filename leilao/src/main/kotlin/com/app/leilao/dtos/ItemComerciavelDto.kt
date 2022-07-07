package com.app.leilao.dtos

import javax.validation.constraints.NotNull

data class ItemComerciavelDto(
    @NotNull
    val animalReferencia: Number,
    @NotNull
    val sequencial: Number,
    @NotNull
    val valorSugerido: Number,
    @NotNull
    val valorCambio: Number,
    @NotNull
    val tenantId: Number
)
