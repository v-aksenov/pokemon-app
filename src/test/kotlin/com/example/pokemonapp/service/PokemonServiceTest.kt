package com.example.pokemonapp.service

import com.example.pokemonapp.model.Pokemon
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PokemonServiceTest(private val pokemonService: PokemonService) : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    @MockkBean
    private lateinit var pokemonClient: PokemonClient

    init {
        "find pokemon" {
            every { pokemonClient.findPokemon(any()) } returns Pokemon(name = "test name", weight = 2)

            val pokemon = pokemonService.findPokemon("name")
            pokemon shouldNotBe null
            pokemon!!.name shouldBe "test name"
        }
    }
}