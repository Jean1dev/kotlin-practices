package com.app.comercializacao.api

import com.app.comercializacao.api.docs.CambioApiDocs
import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.entities.Cambio
import com.app.comercializacao.service.GerenciadorCambioService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/cambio")
class CambioApi(
    private val service: GerenciadorCambioService
) : CambioApiDocs {

    private val logger = LoggerFactory.getLogger(CambioApi::class.java)

    @PostMapping
    override fun upsert(@Valid dto: CambioDto): ResponseEntity<Cambio> {
        logger.info("POST CAMBIO")
        val cambio = service.upsertCambio(dto)
        return ResponseEntity.ok(cambio)
    }

    @GetMapping
    override fun getHistorico(): ResponseEntity<MutableList<Cambio>> {
        return ResponseEntity.ok(service.getHistorico())
    }
}