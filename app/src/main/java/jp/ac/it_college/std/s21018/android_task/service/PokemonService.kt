package jp.ac.it_college.std.s21018.android_task.service

import android.telecom.Call
import jp.ac.it_college.std.s21018.android_task.model.Pokedex
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("https://pokeapi.co/api/v2/{id}")
    fun getPokemon(
        @Path("id")id: Int
    ): Call<Pokedex>
}