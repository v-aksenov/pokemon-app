package com.example.pokemonapp.info.service

import com.example.pokemonapp.info.model.Pokemon
import com.example.pokemonapp.info.model.PokemonApiResponse
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PokemonServiceTest(private val pokemonService: PokemonService) : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    @MockkBean
    private lateinit var pokemonClient: PokemonClient

    init {
        "find pokemon unit test" {
            every { pokemonClient.findPokemon(any()) } returns
                    PokemonApiResponse(name = "test name", weight = 2)

            val pokemon = pokemonService.findPokemon("name")
            pokemon shouldBe Pokemon(name = "test name", weight = 2)
        }
    }
}
