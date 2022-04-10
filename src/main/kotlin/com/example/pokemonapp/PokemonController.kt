package com.example.pokemonapp

import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.service.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonService: PokemonService) {

    @PostMapping("/save")
    fun savePokemon(@RequestBody pokemon: Pokemon) {
        pokemonService.save(pokemon)
    }

    @GetMapping("/find")
    fun savePokemon(@RequestParam name: String) {
        pokemonService.findPokemon(name)
    }

    @GetMapping("/all-saved")
    fun getAll(): List<Pokemon> = pokemonService.getAllSaved()
}