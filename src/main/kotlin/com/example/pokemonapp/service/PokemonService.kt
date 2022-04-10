package com.example.pokemonapp.service

import com.example.pokemonapp.model.Pokemon
import org.springframework.stereotype.Service

@Service
class PokemonService(private val pokemonClient: PokemonClient, private val pokemonDao: PokemonDao) {

    fun findPokemon(name: String): Pokemon? = pokemonClient.findPokemon(name)

    fun save(pokemon: Pokemon) {
        pokemonDao.save(pokemon)
    }

    fun getAllSaved(): List<Pokemon> = pokemonDao.getAll()
}