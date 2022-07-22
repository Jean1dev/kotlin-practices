package com.app.leilao.sockets

import com.app.leilao.entities.LanceLeilao
import com.app.leilao.services.ComercializacaoService
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.atomic.AtomicLong

class User(val id: Long, val name: String?)

data class SocketMessage(val msgType: String, val data: Any) {
    companion object {

        fun getItems(data: Any) = SocketMessage(
            "LIST-ITEMS-AVAILABLES",
            data
        )

        fun listagemUser(data: Any) = SocketMessage(
            "LIST-USER-AVAILABLES",
            data
        )

        fun leilaoCreated(data: Any) = SocketMessage(
            msgType = "LEILAO-CREATED",
            data
        )
    }
}

@Component
class LeilaoHandler(
    private val service: ComercializacaoService
) : TextWebSocketHandler() {

    val sessionList = HashMap<WebSocketSession, User?>()
    var uids = AtomicLong(0)

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        sessionList -= session
        broadcastToOthers(session, listagemUsuariosOnline())
    }

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("received connection, total open " + sessionList.size)
        val user = User(uids.getAndIncrement(), "")
        sessionList.put(session!!, user)
        emit(session, SocketMessage.getItems(service.getItems()))
        emit(session, listagemUsuariosOnline())
        broadcastToOthers(session, listagemUsuariosOnline())
    }

    fun leilaoCriadoEvento(data: Any) {
        broadcast(SocketMessage.leilaoCreated(data))
    }

    private fun listagemUsuariosOnline(): SocketMessage {
        val list = sessionList.map { it.value }
        return SocketMessage.listagemUser(list)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        super.handleTextMessage(session, message)
        val json = ObjectMapper().readTree(message?.payload)
        // {type: "join/say", data: "name/msg"}
        when (json.get("type").asText()) {
            "join" -> {
                handleJoinToLeilao(json, session)
            }
            "say" -> {
                broadcast(SocketMessage("say", json.get("data").asText()))
            }
        }
    }

    private fun findUserInSession(session: WebSocketSession): User? {
        return sessionList[session]
    }

    private fun handleJoinToLeilao(json: JsonNode, session: WebSocketSession) {
        val user = findUserInSession(session)

        if (user != null) {
            val lanceLeilao = LanceLeilao(
                idUser = user.id,
                nameUser = user.name,
                valorLance = json.get("valorLance").asDouble()
            )

            service.addLance(json.get("id").asText(), lanceLeilao)
        }
    }

    fun emit(session: WebSocketSession, msg: SocketMessage) =
        session.sendMessage(TextMessage(jacksonObjectMapper().writeValueAsString(msg)))

    fun broadcast(msg: SocketMessage) = sessionList.forEach { emit(it.key, msg) }

    fun broadcastToOthers(me: WebSocketSession, msg: SocketMessage) =
        sessionList.filterNot { it.key == me }.forEach { emit(it.key, msg) }

}
