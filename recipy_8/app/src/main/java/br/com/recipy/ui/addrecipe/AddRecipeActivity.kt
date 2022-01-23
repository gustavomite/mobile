package br.com.recipy.ui.addrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.recipy.R
import br.com.recipy.ViewModel.AddRecipeViewModel

class AddRecipeActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var viewModel: AddRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[AddRecipeViewModel::class.java]

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
    }

}