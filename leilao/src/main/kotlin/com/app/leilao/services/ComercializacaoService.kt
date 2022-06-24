package com.app.leilao.services

import com.app.leilao.entities.ItemComerciavel
import com.app.leilao.repository.ItemComerciavelRepository
import com.app.leilao.dtos.ItemComerciavelDto
import com.app.leilao.mappers.toDomain
import com.app.leilao.sockets.LeilaoHandler
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration

@Service
class ComercializacaoService(
    private val repository: ItemComerciavelRepository,
    private val websocketClient: LeilaoHandler
) {

    private var leiloes: MutableList<ItemComerciavel> = mutableListOf()

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
        val itemComerciavel = repository.findById(ObjectId(id)).orElseThrow()
        leiloes.add(itemComerciavel)
    }

}