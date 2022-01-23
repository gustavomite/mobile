package br.com.recipy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients_table")
class Ingredient(@ColumnInfo(name = "name") val name: String) {
    @PrimaryKey var id = 0

    init {
        if (IngredientsList.allIngredients().isEmpty()) id = 0
        else {
            if (IngredientsList.toInsert.isEmpty())
                id = IngredientsList.list[IngredientsList.list.size-1].id + 1
            else
                id = IngredientsList.toInsert[IngredientsList.toInsert.size-1].id + 1
        }
    }
}