package com.app.leilao.listener

import com.app.leilao.entities.ItemLeilao
import com.app.leilao.sockets.LeilaoHandler
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class LeilaoListener(
    private val leilaoHandler: LeilaoHandler
) {

    @EventListener
    fun handle(event: ItemLeilao) {
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        val valueAsString = mapper.writeValueAsString(event)
        leilaoHandler.leilaoCriadoEvento(valueAsString)
    }
}