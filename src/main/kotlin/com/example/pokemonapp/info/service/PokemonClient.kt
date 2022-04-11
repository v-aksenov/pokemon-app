package com.example.pokemonapp.info.service

import com.example.pokemonapp.info.model.PokemonApiResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Service
class PokemonClient(private val restTemplate: RestTemplate = RestTemplate()) {

    fun findPokemon(name: String): PokemonApiResponse? =
        try {
            restTemplate.getForEntity<PokemonApiResponse>("$FIND_POKEMON_URL$name").body
        } catch (e: Exception) {
            null
        }

}

private const val FIND_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/"
