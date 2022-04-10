package com.example.pokemonapp

import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.service.PokemonDao
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTest(
    private val pokemonDao: PokemonDao,
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        "test" {
            mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/pokemon/save")
                    .content(objectMapper.writeValueAsString(Pokemon(name = "name", weight = 1)))
                    .contentType(MediaType.APPLICATION_JSON)
            )

            pokemonDao.getAll().firstOrNull { it.name == "name" } shouldNotBe null
        }
    }
}