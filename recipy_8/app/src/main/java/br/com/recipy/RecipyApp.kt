package br.com.recipy

import android.app.Application
import android.content.Context
import br.com.recipy.db.IngredientRepository
import br.com.recipy.db.IngredientRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RecipyApp: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { IngredientRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { IngredientRepository(database.ingredientDao())}

    companion object {
        private lateinit var instance: RecipyApp
        val context: Context
            get() = instance
    }

    init {
        instance = this
    }
}