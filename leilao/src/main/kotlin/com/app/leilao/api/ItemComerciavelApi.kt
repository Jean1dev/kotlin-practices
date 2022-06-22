package com.app.leilao.api

import com.app.leilao.api.docs.ItemComerciavelApiDocs
import com.app.leilao.entities.ItemComerciavel
import com.app.leilao.dtos.ItemComerciavelDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import com.app.leilao.services.ComercializacaoService

@RestController
@RequestMapping("v1/comercializacao")
class ItemComerciavelApi(
    private val service: ComercializacaoService
) : ItemComerciavelApiDocs {

    @PostMapping
    override fun criarItemComercializacao(dto: List<ItemComerciavelDto>): ResponseEntity<Flux<List<ItemComerciavel>>> {
        return ResponseEntity.ok(service.registrarItems(dto))
    }
}