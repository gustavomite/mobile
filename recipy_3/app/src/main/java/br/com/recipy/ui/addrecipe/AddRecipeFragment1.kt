package br.com.recipy.ui.addrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import br.com.recipy.R
import com.google.android.material.slider.Slider

class AddRecipeFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layoutView = inflater.inflate(R.layout.fragment_add_recipe1, container, false)

        val button = layoutView.findViewById<Button>(R.id.buttonIngredientsFragment)
        button.setOnClickListener {
            val name = layoutView.findViewById<TextView>(R.id.textProduct)
            val time = layoutView.findViewById<Slider>(R.id.sliderDuration)

            (activity as AddRecipeActivity).let {
                it.recipe.name = name.text.toString()
                it.recipe.time = time.value.toInt()
            }


            val myActivity = activity as AddRecipeActivity
            myActivity.navController.navigate(AddRecipeFragment1Directions.actionAddRecipeFragment1ToAddRecipeFragment2())

        }

        return layoutView
    }

}