package br.com.recipy.data

object IngredientsList {
    val list = mutableListOf<Ingredient>()
    val toInsert = mutableListOf<Ingredient>()

    fun allIngredients() = list + toInsert

    fun add(name: String): Int {
        val ingr = allIngredients().filter {
            it.name == name
        }

        if (ingr.isNotEmpty())
            return ingr[0].id
        else {
            val newIngr = Ingredient(name)
            toInsert.add(newIngr)
            return newIngr.id
        }
    }

    fun getIngredientByKey(key: Int): String {
        val ingr = allIngredients().filter {
            it.id == key
        }
        return if (ingr.isNotEmpty())
            ingr[0].name
        else
            "N/A"
    }

    fun getIdByIngredientName(name: String): Int {
        val ingr = allIngredients().filter {
            it.name == name
        }

        return if (ingr.isNotEmpty())
            ingr[0].id
        else
            -1
    }

    fun removeById(id: Int) {
        val ingr = allIngredients().filter {
            it.id == id
        }

        if (ingr.isNotEmpty()) {
            list.remove(ingr[0])
            toInsert.remove(ingr[0])
        }
    }

    fun getNames(): List<String> {
        return allIngredients().flatMap { listOf(it.name) }
    }
}