package br.com.recipy.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.recipy.R
import br.com.recipy.data.Recipe
import br.com.recipy.data.RecipeList

class RecipeAdapter(val onClickCallback: OnClickCallback) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    interface OnClickCallback {
        fun onClick(data: Recipe)
        fun onLongClick(data: Recipe)
    }

    class ViewHolder(val parentView: View, val onClickCallback: OnClickCallback): RecyclerView.ViewHolder(parentView) {
        lateinit var recipe: Recipe
        val textRecipeName = parentView.findViewById<TextView>(R.id.textView3)
        val textRecipeTime = parentView.findViewById<TextView>(R.id.textView4)

        init {
            parentView.setOnClickListener {
                onClickCallback.onClick(recipe)
            }

            parentView.setOnLongClickListener {
                onClickCallback.onLongClick(recipe)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adaptorLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recipe_item, parent, false)

        return ViewHolder(adaptorLayout, onClickCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textRecipeName.text = RecipeList[position].name
        holder.textRecipeTime.text = "Tempo estimado: ${RecipeList[position].time}"
        holder.recipe = RecipeList[position]
    }

    override fun getItemCount(): Int {
        return RecipeList.size
    }
}