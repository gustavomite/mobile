package br.com.recipy.db

import androidx.annotation.WorkerThread
import br.com.recipy.data.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientRepository(private val ingredientDao: IngredientDao) {
    val allIngredients: Flow<List<Ingredient>> = ingredientDao.getIngredients()

    @WorkerThread
    suspend fun insert(ingredient: Ingredient) {
        ingredientDao.insert(ingredient)
    }

    @WorkerThread
    suspend fun insertAll(ingredients: List<Ingredient>) {
        ingredientDao.insertList(ingredients)
    }
}