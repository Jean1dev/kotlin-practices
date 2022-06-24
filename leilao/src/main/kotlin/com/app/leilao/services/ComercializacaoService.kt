package com.app.leilao.services

import com.app.leilao.dtos.ItemComerciavelDto
import com.app.leilao.entities.ItemComerciavel
import com.app.leilao.entities.ItemLeilao
import com.app.leilao.mappers.toDomain
import com.app.leilao.repository.ItemComerciavelRepository
import com.app.leilao.repository.ItemLeilaoRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@Service
class ComercializacaoService(
    private val repository: ItemComerciavelRepository,
    private val leilaoRepository: ItemLeilaoRepository,
    private val publisher: ApplicationEventPublisher,
) {

    fun registrarItems(items: List<ItemComerciavelDto>): Flux<List<ItemComerciavel>> {
        return Flux.fromIterable(repository.saveAll(items.map { it.toDomain() }))
            .cache(Duration.ofSeconds(2))
            .buffer(200)
    }

    fun getItemsFlux(): Flux<List<ItemComerciavel>> {
        return Flux.fromIterable(repository.findAll())
            .cache(Duration.ofSeconds(2))
            .buffer(200)
    }

    fun getItems(): List<ItemComerciavel> {
        return repository.findAll()
    }

    fun iniciarLeilao(id: String) {
        val itemComerciavel = repository.findById(UUID.fromString(id)).orElseThrow()
        val itemLeilao = leilaoRepository.save(
            ItemLeilao(
                UUID.randomUUID(),
                LocalDateTime.now(),
                itemComerciavel
            )
        )
        publisher.publishEvent(itemLeilao)
    }

}