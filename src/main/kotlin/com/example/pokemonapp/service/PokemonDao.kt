package com.example.pokemonapp.service

import com.example.pokemonapp.model.Pokemon
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class PokemonDao(private val jdbcTemplate: JdbcTemplate, private val objectMapper: ObjectMapper) {

    fun save(pokemon: Pokemon) {
        jdbcTemplate.update("insert into pokemon values(default, ?, ?)", pokemon.name, pokemon.weight)
    }

    fun getAll(): List<Pokemon> =
        jdbcTemplate.queryForList("select * from pokemon")
            .map { objectMapper.readValue(objectMapper.writeValueAsString(it), Pokemon::class.java) }
}