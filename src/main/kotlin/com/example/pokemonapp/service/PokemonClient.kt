package com.example.pokemonapp.service

import com.example.pokemonapp.model.Pokemon
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Service
class PokemonClient(private val restTemplate: RestTemplate = RestTemplate()) {

    fun findPokemon(name: String): Pokemon? =
        restTemplate.getForEntity<Pokemon>("https://pokeapi.co/api/v2/pokemon/$name").body
}