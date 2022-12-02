package jp.ac.it_college.std.s21018.android_task

import android.content.res.AssetManager
import com.squareup.moshi.Json
import jp.ac.it_college.std.s21018.android_task.model.Pokedex
import jp.ac.it_college.std.s21018.android_task.model.PokedexJson
import jp.ac.it_college.std.s21018.android_task.model.Pokemon
import jp.ac.it_college.std.s21018.android_task.model.PokemonJson
import java.io.InputStreamReader
import kotlin.reflect.KClass

lateinit var pokedex: List<Pokedex>
lateinit var pokemon: List<Pokemon>

fun initJsonData(assets: AssetManager) {
    pokedex = parseJson(assets, "filtered_pokedex.json", PokedexJson::class).pokedex
    pokemon = parseJson(assets, "ordered_pokemon.json", PokemonJson::class).pokemon
}

private fun <T : Any> parseJson(assets: AssetManager, file: String, c: KClass<T>): T {
    val inputStream = assets.open(file)
    val jsonReader = InputStreamReader(inputStream, "UTF-8").readText()
    inputStream.close()
    return Json().fromJson(jsonReader, c.java)
}