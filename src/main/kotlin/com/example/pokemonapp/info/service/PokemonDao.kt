package com.example.pokemonapp.info.service

import com.example.pokemonapp.info.model.Pokemon
import com.fasterxml.jackson.databind.ObjectMapper
import org.intellij.lang.annotations.Language
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class PokemonDao(private val jdbcTemplate: JdbcTemplate, private val objectMapper: ObjectMapper) {

    fun save(pokemon: Pokemon) {
        jdbcTemplate.update(SAVE_POKEMON, pokemon.name, pokemon.weight)
    }

    fun getAll(): List<Pokemon> =
        jdbcTemplate.queryForList(SELECT_ALL_POKEMONS)
            .map { objectMapper.readValue(objectMapper.writeValueAsString(it), Pokemon::class.java) }
}

@Language("Sql")
private const val SAVE_POKEMON = "insert into pokemon values(default, ?, ?)"

@Language("Sql")
private const val SELECT_ALL_POKEMONS = "select * from pokemon"
