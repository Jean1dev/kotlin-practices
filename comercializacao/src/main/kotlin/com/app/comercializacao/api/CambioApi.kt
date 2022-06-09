package com.app.comercializacao.api

import com.app.comercializacao.api.docs.CambioApiDocs
import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.entities.Cambio
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/cambio")
class CambioApi: CambioApiDocs {

    lateinit var cambioAtual: Cambio

    @PostMapping
    override fun upsert(dto: CambioDto): ResponseEntity<Cambio> {
        TODO("Not yet implemented")
    }
}