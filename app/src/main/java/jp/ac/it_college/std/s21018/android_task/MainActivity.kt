package jp.ac.it_college.std.s21018.android_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import jp.ac.it_college.std.s21018.android_task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//    private var pokemonList: List<Poke>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initJsonData(assets)



        //画面遷移をわかりやすくするため
        val navController =
            binding.fragmentContainerView.getFragment<NavHostFragment>().navController
        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(navController.graph)
        )
    }
    override fun onBackPressed() {
    }
}