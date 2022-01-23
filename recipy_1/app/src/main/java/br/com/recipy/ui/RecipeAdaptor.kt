package br.com.recipy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.recipy.R
import br.com.recipy.data.Recipe
import br.com.recipy.data.RecipeList

class RecipeAdaptor() : RecyclerView.Adapter<RecipeAdaptor.ViewHolder>() {
    class ViewHolder(val parentView: View): RecyclerView.ViewHolder(parentView) {
        lateinit var recipe: Recipe
        val textRecipeName = parentView.findViewById<TextView>(R.id.textView3)
        val textRecipeTime = parentView.findViewById<TextView>(R.id.textView4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adaptorLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recipe_item, parent, false)

        return ViewHolder(adaptorLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textRecipeName.text = RecipeList[position].name
        holder.textRecipeTime.text = "Tempo estimado: ${RecipeList[position].time}"
    }

    override fun getItemCount(): Int {
        return RecipeList.size
    }
}