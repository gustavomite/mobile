package br.com.recipy.ui.addrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import br.com.recipy.R
import br.com.recipy.data.RecipeList

class AddRecipeFragment3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layoutView = inflater.inflate(R.layout.fragment_add_recipe3, container, false)

        val button = layoutView.findViewById<Button>(R.id.confirmButton)
        button.setOnClickListener {
            (activity as AddRecipeActivity).let {
                val desc = layoutView.findViewById<EditText>(R.id.editDescription)
                it.recipe.description = desc.text.toString()
                RecipeList.add(it.recipe)
                it.finish()
            }
        }

        return layoutView
    }
}