package com.example.pokemonapp.info

import com.example.pokemonapp.info.model.Pokemon
import com.example.pokemonapp.info.service.PokemonDao
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonDao: PokemonDao) {

    @PostMapping
    fun savePokemon(@RequestBody pokemon: Pokemon) {
        pokemonDao.save(pokemon)
    }

    @GetMapping
    fun getAll(): List<Pokemon> = pokemonDao.getAll()
}