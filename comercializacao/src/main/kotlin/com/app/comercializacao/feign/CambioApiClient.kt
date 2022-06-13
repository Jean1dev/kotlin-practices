package com.app.comercializacao.feign

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(url = "\${api.cambio.url}", name = "cambioApi")
interface CambioApiClient {

    @RequestMapping(
        path = ["/last/USD-BRL"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        method = [RequestMethod.GET]
    )
    fun buscarLimiteDiario(): JsonNode
}