package com.example.pokemonapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokemonAppApplication

fun main(args: Array<String>) {
    runApplication<PokemonAppApplication>(*args)
}
