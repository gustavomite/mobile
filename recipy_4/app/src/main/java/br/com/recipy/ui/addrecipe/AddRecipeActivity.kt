package br.com.recipy.ui.addrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.recipy.R
import br.com.recipy.data.Recipe

class AddRecipeActivity : AppCompatActivity() {
    lateinit var navController: NavController
    var recipe = Recipe("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
    }

}