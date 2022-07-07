package com.app.leilao.api

import com.app.leilao.api.docs.ItemComerciavelApiDocs
import com.app.leilao.dtos.ItemComerciavelDto
import com.app.leilao.entities.ItemComerciavel
import com.app.leilao.services.ComercializacaoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("v1/comercializacao")
class ItemComerciavelApi(
    private val service: ComercializacaoService
) : ItemComerciavelApiDocs {

    @PostMapping
    override fun criarItemComercializacao(dto: List<ItemComerciavelDto>): ResponseEntity<Flux<List<ItemComerciavel>>> {
        return ResponseEntity.ok(service.registrarItems(dto))
    }

    @PostMapping("iniciar-leilao/{id}")
    override fun iniciarLeilao(@PathVariable("id") id: String) {
        service.iniciarLeilao(id)
    }

    @GetMapping
    override fun getAllItens(): ResponseEntity<Flux<List<ItemComerciavel>>> {
        return ResponseEntity.ok(service.getItemsFlux())
    }
}