package com.example.pokemonapp.info

import com.example.pokemonapp.info.model.Pokemon
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


@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerTest(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
) : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        "save/getAll pokemon" {
            mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/pokemon/save")
                    .content(objectMapper.writeValueAsString(Pokemon(name = "name", weight = 1)))
                    .contentType(MediaType.APPLICATION_JSON)
            )

            val pokemons = mockMvc
                .get("/pokemon/all-saved")
                .andReturn()
                .response
                .contentAsString
                .let { objectMapper.readValue(it, List::class.java) }
                .map { objectMapper.convertValue(it, Pokemon::class.java) }

            pokemons.first() should {
                it.name shouldBe "name"
                it.weight shouldBe 1
                it.id shouldNotBe null
            }
        }
        "find pokemon" {
            val pokemon = mockMvc
                .get("/pokemon/find?name=ditto")
                .andReturn()
                .response
                .contentAsString
                .let { objectMapper.readValue(it, Pokemon::class.java) }

            pokemon shouldBe Pokemon(name = "ditto", weight = 40)
        }
        "find unexisted pokemon" {
            val response = mockMvc
                .get("/pokemon/find?name=pokemonunknown")
                .andReturn()
                .response

            response.status shouldBe 400
            response.contentAsString shouldBe "not found pokemon with name pokemonunknown"
        }
    }
}