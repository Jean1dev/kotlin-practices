package com.app.leilao.services

import com.app.leilao.entities.ItemComerciavel
import com.app.leilao.repository.ItemComerciavelRepository
import com.app.leilao.dtos.ItemComerciavelDto
import com.app.leilao.mappers.toDomain
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration

@Service
class ComercializacaoService(
    private val repository: ItemComerciavelRepository
) {

    fun registrarItems(items: List<ItemComerciavelDto>): Flux<List<ItemComerciavel>> {
        return Flux.fromIterable(repository.saveAll(items.map { it.toDomain() }))
            .cache(Duration.ofSeconds(2))
            .buffer(200)
    }

    fun getItems(): Flux<List<ItemComerciavel>> {
        return Flux.fromIterable(repository.findAll())
            .cache(Duration.ofSeconds(2))
            .buffer(200)
    }

}