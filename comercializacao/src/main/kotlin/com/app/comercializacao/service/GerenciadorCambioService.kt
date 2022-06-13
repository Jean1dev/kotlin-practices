package com.app.comercializacao.service

import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.entities.Cambio
import com.app.comercializacao.feign.CambioApiClient
import com.app.comercializacao.mappers.toDomain
import com.app.comercializacao.repositories.CambioRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.annotation.PostConstruct

@Service
class GerenciadorCambioService(
    private val cambioRepository: CambioRepository,
    private val cambioApiClient: CambioApiClient
) {

    private val cambioHistorico: MutableList<Cambio> = mutableListOf()

    fun upsertCambio(dto: CambioDto): Cambio {
        val domainEntity = dto.toDomain()
        val save = cambioRepository.save(domainEntity)
        cambioHistorico.add(save)
        return save
    }

    fun getHistorico(): MutableList<Cambio> = cambioHistorico

    @PostConstruct
    fun getIndiceAtual() {
        val node = cambioApiClient.buscarLimiteDiario();
        val jsonNode = node.get("USDBRL")

        val cambiodto = CambioDto(
            valor = jsonNode.get("bid").asDouble(),
            lastTimeUpdated = LocalDateTime.now(),
            code = jsonNode.get("code").asText(),
            codein = jsonNode.get("codein").asText(),
            name = jsonNode.get("name").asText(),
            high = jsonNode.get("high").asDouble(),
            low = jsonNode.get("low").asDouble(),
            varBid = jsonNode.get("varBid").asDouble(),
            pctChange = jsonNode.get("pctChange").asDouble(),
            bid = jsonNode.get("bid").asDouble(),
            ask = jsonNode.get("ask").asDouble(),
            timestamp = jsonNode.get("timestamp").asLong()
        )

        upsertCambio(cambiodto)
        println(cambiodto)
    }


}