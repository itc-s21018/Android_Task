package jp.ac.it_college.std.s21018.android_task.model

data class PokemonJson (
    val pokemon: List<Pokemon>
)

data class Pokemon(
    val id: Int,
    val name: String
)
