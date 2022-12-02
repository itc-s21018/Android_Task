package jp.ac.it_college.std.s21018.android_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import com.squareup.picasso.Picasso
import jp.ac.it_college.std.s21018.android_task.databinding.FragmentQuizBinding
import jp.ac.it_college.std.s21018.android_task.service.PokemonService
//import jp.ac.it_college.std.s21018.android_task.json.PokemonInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://pokeapi.co/api/v2/"

class quiz : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val args: quiz by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        binding.gen.text = getString(R.string.gen_select, args.num)
        return binding.root

    }

    @UiThread
    private fun showpokeimg(id: Int) {
        lifecycleScope.launch {
            val info = getPokemonInfo(id)
            setPokemonInfo(info)
        }
    }

    @WorkerThread
    private suspend fun getPokemonInfo(id: Int): Json {
        return withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder().apply {
                baseUrl(BASE_URL)
                addConverterFactory(MoshiConverterFactory.create(moshi))
            }.build()

            val service: PokemonService = retrofit.create(PokemonService::class.java)
            try {
                service.getPokemon(id).execute().body() ?: throw IllegalStateException("ポケモンの情報が取れません")
            } catch (e: Exception) {
                throw IllegalStateException("なにか例外が発生しました。", e)
            }
        }
    }
    @UiThread
    private fun setPokemonInfo(info: PokemonInfo) {
        val IMG_URL = info.sprites.other.officialArtwork.frontDefault
        Picasso.get().load(IMG_URL).into(binding.viPokemon)
    }
}