package br.com.recipy.data

import br.com.recipy.R
import br.com.recipy.RecipyApp
import br.com.recipy.Util.Func.getPrefs
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class CompareRecipe : Comparator<Recipe> {
    override fun compare(p0: Recipe?, p1: Recipe?): Int {
        return when (RecipeList.sortOrder) {
            "time_asc" -> p0!!.time - p1!!.time
            "time_desc" -> p1!!.time - p0!!.time
            "name_desc" -> p1!!.name.compareTo(p0!!.name)
            else -> p0!!.name.compareTo(p1!!.name)
        }
    }

}
object RecipeList: ArrayList<Recipe>() {
    var lastSortOrder = RecipyApp.context.resources.getStringArray(R.array.sort_values)[0]
    var sortOrder = ""
        get() = getPrefs().getString("sort", RecipyApp.context.resources.getStringArray(R.array.sort_values)[0]).toString()

    override fun add(element: Recipe): Boolean {
        super.add(element)
        Collections.sort(this, CompareRecipe())
        return true
    }

    override fun addAll(elements: Collection<Recipe>): Boolean {
        super.addAll(elements)
        Collections.sort(this, CompareRecipe())
        return true
    }

    fun resort() {
        val tmpArray = ArrayList<Recipe>(this)
        clear()
        addAll(tmpArray)
        lastSortOrder = sortOrder
    }

    fun checkSortOrder(): Boolean {
        return sortOrder.compareTo(lastSortOrder) != 0
    }
}