package br.com.recipy.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.recipy.data.Ingredient
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients_table ORDER BY id ASC")
    fun getIngredients(): Flow<List<Ingredient>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ingredient: Ingredient)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(ingredientList: List<Ingredient>)

    @Query("DELETE FROM ingredients_table")
    suspend fun deleteAll()
}