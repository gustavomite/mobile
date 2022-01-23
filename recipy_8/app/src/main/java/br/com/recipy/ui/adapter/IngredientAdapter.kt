package br.com.recipy.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.recipy.R
import br.com.recipy.data.IngredientsList
import br.com.recipy.data.Recipe

class IngredientAdapter(var data: Recipe): RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {
    val selected = mutableMapOf<Int, Boolean>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_ingredient_item, parent, false)

        return ViewHolder(adapterLayout, selected)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkBox.isChecked = false
        val listKeys = data.ingredient.keys.toList()
        val key = listKeys[position]
        val name = IngredientsList.getIngredientByKey(key)
        holder.nameTextView.text = name
        holder.qtyTextView.text = data.ingredient[key]
    }

    override fun getItemCount(): Int {
        return data.ingredient.size
    }

    fun removeSelected() {
        selected.forEach {
            if (it.value) {
                IngredientsList.removeById(it.key)
                data.ingredient.remove(it.key)
            }
        }
    }

    class ViewHolder(val parentView: View, val selected: MutableMap<Int, Boolean>): RecyclerView.ViewHolder(parentView), View.OnClickListener {
        val checkBox = parentView.findViewById<CheckBox>(R.id.checkBox)
        val nameTextView = parentView.findViewById<TextView>(R.id.textView5)
        val qtyTextView = parentView.findViewById<TextView>(R.id.textView6)

        init {
            parentView.setOnClickListener(this)
            checkBox.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if (view !is CheckBox)
                checkBox.isChecked = !checkBox.isChecked
            selected[IngredientsList.getIdByIngredientName(nameTextView.text.toString())] = checkBox.isChecked
        }


    }
}