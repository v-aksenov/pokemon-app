package com.example.pokemonapp.info

import com.example.pokemonapp.info.model.Pokemon
import com.example.pokemonapp.info.service.PokemonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pokemon-api")
class PokemonApiController(private val pokemonService: PokemonService) {

    @GetMapping("/find")
    fun findPokemon(@RequestParam name: String): Pokemon = pokemonService.findPokemon(name)

    @ExceptionHandler
    fun handleException(exception: IllegalArgumentException): ResponseEntity<*> {
        return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
    }
}
