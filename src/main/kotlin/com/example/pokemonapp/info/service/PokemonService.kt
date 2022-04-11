package com.example.pokemonapp.info.service

import com.example.pokemonapp.info.model.Pokemon
import org.springframework.stereotype.Service

@Service
class PokemonService(private val pokemonClient: PokemonClient, private val pokemonDao: PokemonDao) {

    fun findPokemon(name: String): Pokemon =
        pokemonClient.findPokemon(name)
            ?.let { Pokemon(name = it.name, weight = it.weight) }
            ?: throw IllegalArgumentException("not found pokemon with name $name")

    fun save(pokemon: Pokemon) {
        pokemonDao.save(pokemon)
    }

    fun getAllSaved(): List<Pokemon> = pokemonDao.getAll()
}