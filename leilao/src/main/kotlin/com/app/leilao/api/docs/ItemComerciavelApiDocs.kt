package com.app.leilao.api.docs

import com.app.leilao.entities.ItemComerciavel
import com.app.leilao.dtos.ItemComerciavelDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import java.net.HttpURLConnection
import javax.validation.Valid

@Tag(description = "Recurso para gerenciamento dos items no leiao", name = "ItemComerciavelA Api")
interface ItemComerciavelApiDocs {

    @Operation(summary = "Criar items comerciaveis")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = HttpURLConnection.HTTP_OK.toString(),
                description = "Sucesso"
            ),
            ApiResponse(
                responseCode = HttpURLConnection.HTTP_BAD_REQUEST.toString(),
                description = "Operação Rejeitada"
            )
        ]
    )
    fun criarItemComercializacao(@Valid @RequestBody dto: List<ItemComerciavelDto>): ResponseEntity<Flux<List<ItemComerciavel>>>


    @Operation(summary = "Cria um novo leilao a partir dos itens existentes")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = HttpURLConnection.HTTP_OK.toString(),
                description = "Sucesso"
            ),
            ApiResponse(
                responseCode = HttpURLConnection.HTTP_BAD_REQUEST.toString(),
                description = "Operação Rejeitada"
            )
        ]
    )
    fun iniciarLeilao(@PathVariable("id") id: String)

    @Operation(summary = "Recuperar items comerciaveis")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = HttpURLConnection.HTTP_OK.toString(),
                description = "Sucesso"
            ),
            ApiResponse(
                responseCode = HttpURLConnection.HTTP_BAD_REQUEST.toString(),
                description = "Operação Rejeitada"
            )
        ]
    )
    fun getAllItens(): ResponseEntity<Flux<List<ItemComerciavel>>>
}