package br.com.recipy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.recipy.data.Recipe
import br.com.recipy.data.RecipeList
import br.com.recipy.network.RecipeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {
    private val _loadComplete = MutableLiveData(false)
    val loadComplete: LiveData<Boolean>
        get() = _loadComplete

    private val _loadError = MutableLiveData(false)
    val loadError: LiveData<Boolean>
        get() = _loadError

    fun getDataFromNetwork() {
        viewModelScope.launch {
            try {
                val listResult = RecipeApi.retrofitService.getPosts()
                listResult.forEach {
                    val newRecipe = Recipe("Receita ${it.name}")
                    newRecipe.time = it.time
                    newRecipe.description = it.description
                    val word = it.listIngredients.split(' ')
                    word.forEach {
                        newRecipe.ingredient[it[0].toChar().code - 97] = it.length.toString()
                    }

                    RecipeList.add(newRecipe)
                }

                _loadComplete.postValue(true)
            } catch (e: Exception) {
                _loadError.postValue(true)
            }
        }
    }
}