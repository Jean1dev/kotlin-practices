package com.app.leilao.listener

import com.app.leilao.entities.ItemLeilao
import com.app.leilao.sockets.LeilaoHandler
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class LeilaoListener(
    private val leilaoHandler: LeilaoHandler
) {

    @EventListener
    fun handle(event: ItemLeilao) {
        leilaoHandler.leilaoCriadoEvento(event)
    }
}