package br.com.recipy.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.recipy.data.Ingredient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Ingredient::class), version = 1, exportSchema = false)
abstract class IngredientRoomDatabase: RoomDatabase() {

    abstract fun ingredientDao(): IngredientDao

    private class IngredientDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.ingredientDao())
                }
            }
        }

        fun populateDatabase(ingredientDao: IngredientDao) {

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: IngredientRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): IngredientRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    IngredientRoomDatabase::class.java,
                    "recipes_database"
                )
                    .addCallback(IngredientDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}