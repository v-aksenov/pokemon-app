package com.example.pokemonapp.info

import com.example.pokemonapp.info.model.Pokemon
import com.example.pokemonapp.info.service.PokemonDao
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTest(
    private val mockMvc: MockMvc,
    private val pokemonDao: PokemonDao,
    private val objectMapper: ObjectMapper
) : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        "save pokemon - success" {
            mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/pokemon")
                    .content(objectMapper.writeValueAsString(Pokemon(name = "saved pokemon name", weight = 1)))
                    .contentType(MediaType.APPLICATION_JSON)
            )

            pokemonDao.getAll().first { it.name == "saved pokemon name" } should {
                it.weight shouldBe 1
                it.id shouldNotBe null
            }
        }
        "save pokemon - too long name" {
            mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/pokemon")
                    .content(objectMapper.writeValueAsString(
                        Pokemon(name = "saved pokemon name - just too long name to save", weight = 1))
                    )
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
        }
        "get all pokemons" {
            val pokemons = mockMvc
                .get("/pokemon")
                .andReturn()
                .response
                .contentAsString
                .let { objectMapper.readValue(it, List::class.java) }
                .map { objectMapper.convertValue(it, Pokemon::class.java) }

            pokemons.first {it.name == "test-pokemon"} should {
                it.weight shouldBe 45
                it.id shouldNotBe null
            }
        }
        "get all pokemons - not found" {
            val pokemons = mockMvc
                .get("/pokemon")
                .andReturn()
                .response
                .contentAsString
                .let { objectMapper.readValue(it, List::class.java) }
                .map { objectMapper.convertValue(it, Pokemon::class.java) }

            pokemons.firstOrNull { it.name == "test-pokemon not found"} shouldBe null
        }
        "find pokemon" {
            val pokemon = mockMvc
                .get("/pokemon-api/find?name=ditto")
                .andReturn()
                .response
                .contentAsString
                .let { objectMapper.readValue(it, Pokemon::class.java) }

            pokemon shouldBe Pokemon(name = "ditto", weight = 40)
        }
        "find unexisted pokemon" {
            val response = mockMvc
                .get("/pokemon-api/find?name=pokemonunknown")
                .andReturn()
                .response

            response.status shouldBe 400
            response.contentAsString shouldBe "not found pokemon with name pokemonunknown"
        }
    }
}