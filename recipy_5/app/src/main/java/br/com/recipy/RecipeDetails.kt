package br.com.recipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import br.com.recipy.data.IngredientsList
import br.com.recipy.data.Recipe

class RecipeDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val bundle = intent.extras
        val recipe = bundle!!.getBundle("bundle")!!.getParcelable<Recipe>("data")

        recipe?.let {
            val titleTextView = findViewById<TextView>(R.id.textView12)
            val timeTextView = findViewById<TextView>(R.id.textView14)
            val ingredientsTextView = findViewById<TextView>(R.id.textView16)
            val detailsTextView = findViewById<TextView>(R.id.textView18)

            titleTextView.text = it.name
            timeTextView.text = String.format(getString(R.string.x_minutes), "${it.time}")
            var ingredients = String()
            it.ingredient.forEach {
                ingredients += "- ${IngredientsList.getIngredientByKey(it.key)} (${it.value})\n"
            }
            ingredientsTextView.text = ingredients
            detailsTextView.text = it.description
        }
    }
}