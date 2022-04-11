package com.example.pokemonapp.info

import com.example.pokemonapp.info.model.Pokemon
import com.example.pokemonapp.info.service.PokemonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/pokemon")
class PokemonController(private val pokemonService: PokemonService) {

    @PostMapping("/save")
    fun savePokemon(@RequestBody pokemon: Pokemon) {
        pokemonService.save(pokemon)
    }

    @GetMapping("/find")
    fun findPokemon(@RequestParam name: String): Pokemon = pokemonService.findPokemon(name)

    @GetMapping("/all-saved")
    fun getAll(): List<Pokemon> = pokemonService.getAllSaved()

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleException(exception: IllegalArgumentException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
    }
}