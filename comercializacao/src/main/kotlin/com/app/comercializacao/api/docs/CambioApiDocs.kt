package com.app.comercializacao.api.docs

import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.entities.Cambio
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import java.net.HttpURLConnection
import javax.validation.Valid

@Tag(description = "Recurso para atualização e recuperação do Cambio atual", name = "Cambio Api")
interface CambioApiDocs {

    @Operation(summary = "Criar ou atualiza o cambio")
    @ApiResponses(
            value = [
                ApiResponse(
                        responseCode = HttpURLConnection.HTTP_OK.toString(),
                        description = "Cambio atualizado"
                ),
                ApiResponse(
                        responseCode = HttpURLConnection.HTTP_BAD_REQUEST.toString(),
                        description = "Não foi possivel completar a operação"
                )
            ]
    )
    fun upsert(@Valid @RequestBody dto: CambioDto): ResponseEntity<Cambio>
}
