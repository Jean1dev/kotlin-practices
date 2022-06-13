package com.app.comercializacao.api

import com.app.comercializacao.dtos.CambioDto
import com.app.comercializacao.mappers.toDomain
import com.app.comercializacao.service.GerenciadorCambioService
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CambioApi::class)
internal class CambioApiTest @Autowired constructor(
    private val mvc: MockMvc,
    private val mapper: ObjectMapper
) {

    private val uri = "/v1/cambio"

    @MockkBean
    private lateinit var service: GerenciadorCambioService

    @Test
    fun `deve salvar ou atualizar o cambio`() {
        val cambio = CambioDto(valor = 0.5)
        val payload = mapper.writeValueAsString(cambio)

        val cambioDomain = cambio.toDomain()
        every { service.upsertCambio(any()) } answers { cambioDomain }

        mvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.valor").value(cambio.valor))
            .andExpect(jsonPath("$.id").exists())
    }

}