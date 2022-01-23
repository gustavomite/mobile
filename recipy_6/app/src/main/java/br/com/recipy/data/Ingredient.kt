package br.com.recipy.data

class Ingredient(val name: String) {
    var id = 0

    init {
        if (IngredientsList.list.size == 0) id = 0
        else id = IngredientsList.list[IngredientsList.list.size-1].id + 1
    }
}