package br.com.recipy.ui.addrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.recipy.data.Recipe
import br.com.recipy.data.RecipeList

class AddRecipeViewModel: ViewModel() {
    var recipe = Recipe("")

    private val _done = MutableLiveData(false)
    val done: LiveData<Boolean>
        get() = _done

    fun finilizeAddRecipe() {
        RecipeList.add(recipe)
        _done.postValue(true)
    }
}