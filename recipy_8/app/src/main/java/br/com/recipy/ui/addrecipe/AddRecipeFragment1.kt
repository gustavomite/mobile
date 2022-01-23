package br.com.recipy.ui.addrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import br.com.recipy.R
import br.com.recipy.ViewModel.AddRecipeViewModel
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar

class AddRecipeFragment1 : Fragment() {
    lateinit var viewModel: AddRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as AddRecipeActivity).viewModel

        // Inflate the layout for this fragment
        val layoutView = inflater.inflate(R.layout.fragment_add_recipe1, container, false)

        val button = layoutView.findViewById<Button>(R.id.buttonIngredientsFragment)
        val time = layoutView.findViewById<Slider>(R.id.sliderDuration)
        val timeText = layoutView.findViewById<TextView>(R.id.textViewSelectedTime)
        
        time.addOnChangeListener { _, value, _ ->
            timeText.text = String.format(getString(R.string.placeholder_minutos), "$value")
        }

        time.value = 5F
        
        button.setOnClickListener {
            val name = layoutView.findViewById<TextView>(R.id.textProduct)


            if (name.text.isNullOrBlank()) {
                Snackbar.make(
                    name,
                    getString(R.string.erro_nome_vazio),
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("Ok") {}
                    .show()
            } else {

                viewModel.let {
                    it.recipe.name = name.text.toString()
                    it.recipe.time = time.value.toInt()
                }


                val myActivity = activity as AddRecipeActivity
                myActivity.navController.navigate(AddRecipeFragment1Directions.actionAddRecipeFragment1ToAddRecipeFragment2())
            }

        }

        return layoutView
    }

}