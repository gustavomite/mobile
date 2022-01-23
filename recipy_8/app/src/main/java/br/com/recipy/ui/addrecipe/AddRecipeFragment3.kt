package br.com.recipy.ui.addrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import br.com.recipy.R
import br.com.recipy.ViewModel.AddRecipeViewModel

class AddRecipeFragment3 : Fragment() {
    lateinit var viewModel: AddRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as AddRecipeActivity).viewModel

        // Inflate the layout for this fragment
        val layoutView = inflater.inflate(R.layout.fragment_add_recipe3, container, false)

        viewModel.done.observe((activity as AddRecipeActivity), {
            if (it == true) {
                (activity as AddRecipeActivity).finish()
            }
        })

        val button = layoutView.findViewById<Button>(R.id.confirmButton)
        button.setOnClickListener {
            (activity as AddRecipeActivity).let {
                val desc = layoutView.findViewById<EditText>(R.id.editDescription)
                viewModel.recipe.description = desc.text.toString()
                viewModel.finilizeAddRecipe()
            }
        }

        return layoutView
    }
}