package br.com.recipy.ui.addrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import br.com.recipy.R

class AddRecipeFragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layoutView = inflater.inflate(R.layout.fragment_add_recipe2, container, false)

        val button = layoutView.findViewById<Button>(R.id.buttonDescriptionFragment)
        button.setOnClickListener {
            val myActivity = activity as AddRecipeActivity
            myActivity.navController.navigate(AddRecipeFragment2Directions.actionAddRecipeFragment2ToAddRecipeFragment3())
        }

        return layoutView
    }
}