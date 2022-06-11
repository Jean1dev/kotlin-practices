package com.app.comercializacao.service

import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.entities.Cambio
import com.app.comercializacao.mappers.toDomain
import com.app.comercializacao.repositories.CambioRepository
import org.springframework.stereotype.Service

@Service
class GerenciadorCambioService(
    private val cambioRepository: CambioRepository
) {

    private val cambioHistorico: MutableList<Cambio> = mutableListOf()

    fun upsertCambio(dto: CambioDto): Cambio {
        val domainEntity = dto.toDomain()
        val save = cambioRepository.save(domainEntity)
        cambioHistorico.add(save)
        return save
    }

    fun getHistorico(): MutableList<Cambio> = cambioHistorico

}