package br.com.recipy.ui.addrecipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import br.com.recipy.R
import br.com.recipy.data.IngredientsList
import br.com.recipy.ui.IngredientAdapter
import com.google.android.material.snackbar.Snackbar

class AddRecipeFragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layoutView = inflater.inflate(R.layout.fragment_add_recipe2, container, false)
        val myActivity = activity as AddRecipeActivity

        val button = layoutView.findViewById<Button>(R.id.buttonDescriptionFragment)
        button.setOnClickListener {
            myActivity.navController.navigate(AddRecipeFragment2Directions.actionAddRecipeFragment2ToAddRecipeFragment3())
        }

        val ingrName = layoutView.findViewById<AutoCompleteTextView>(R.id.editIngredientName)
        val ingrQty = layoutView.findViewById<EditText>(R.id.editIngredientQty)
        val ingrList = layoutView.findViewById<RecyclerView>(R.id.ingredientsRecyclerView)
        val buttonAdd = layoutView.findViewById<Button>(R.id.buttonAddIngredient)
        val buttonRem = layoutView.findViewById<Button>(R.id.buttonRemoveIngredient)

        ingrList.adapter = IngredientAdapter(myActivity.recipe)

        ingrName.setAdapter(ArrayAdapter(requireContext(), R.layout.autocomplete_ingredient_item, IngredientsList.getNames()))

        buttonAdd.setOnClickListener {
            if (ingrName.text.isNullOrBlank() || ingrQty.text.isNullOrBlank()) {
                Snackbar.make(ingrList, getString(R.string.err_ingredient), Snackbar.LENGTH_SHORT)
                    .setAction("Ok") {}
                    .show()
            } else {
                val id = IngredientsList.add(ingrName.text.toString())
                myActivity.recipe.ingredient[id] = ingrQty.text.toString()

                ingrList.adapter!!.notifyDataSetChanged()

                ingrName.text.clear()
                ingrQty.text.clear()

                ingrName.requestFocus()
            }
        }

        buttonRem.setOnClickListener {
            (ingrList.adapter as IngredientAdapter).removeSelected()

            ingrList.adapter!!.notifyDataSetChanged()
        }

        return layoutView
    }
}