package br.com.recipy

import androidx.lifecycle.*
import br.com.recipy.data.Ingredient
import br.com.recipy.data.IngredientsList
import br.com.recipy.data.Recipe
import br.com.recipy.data.RecipeList
import br.com.recipy.db.IngredientRepository
import br.com.recipy.network.RecipeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class MainViewModel(val repository: IngredientRepository): ViewModel() {
    private val _loadComplete = MutableLiveData(false)
    val loadComplete: LiveData<Boolean>
        get() = _loadComplete

    private val _loadError = MutableLiveData(false)
    val loadError: LiveData<Boolean>
        get() = _loadError

    val allIngredients: LiveData<List<Ingredient>> = repository.allIngredients.asLiveData()

    fun loadIngredientsFromDB(list: List<Ingredient>) {
        IngredientsList.list.clear()
        IngredientsList.list += list
    }

    fun insertIngredientsToDB() {
        insertIngredientList()
    }

    fun insertIngredientList() = viewModelScope.launch {
        repository.insertAll(IngredientsList.toInsert)
        IngredientsList.toInsert.clear()
    }

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

    fun checkSortOrder(): Boolean {
        if (RecipeList.checkSortOrder()) {
            viewModelScope.launch {
                RecipeList.resort()
                _loadComplete.postValue(true)
            }
            return true
        }

        return false
    }
}

class MainActivityViewModelFactory(private val repository: IngredientRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return (MainViewModel(repository) as T)
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}