package br.com.recipy.data

object IngredientsList {
    val list = mutableListOf<Ingredient>()

    fun add(name: String): Int {
        val ingr = list.filter {
            it.name == name
        }

        if (ingr.isNotEmpty())
            return ingr[0].id
        else {
            val newIngr = Ingredient(name)
            list.add(newIngr)
            return newIngr.id
        }
    }

    fun getIngredientByKey(key: Int): String {
        val ingr = list.filter {
            it.id == key
        }
        return if (ingr.isNotEmpty())
            ingr[0].name
        else
            "N/A"
    }

    fun getIdByIngredientName(name: String): Int {
        val ingr = list.filter {
            it.name == name
        }

        return if (ingr.isNotEmpty())
            ingr[0].id
        else
            -1
    }

    fun removeById(id: Int) {
        val ingr = list.filter {
            it.id == id
        }

        if (ingr.isNotEmpty())
            list.remove(ingr[0])
    }

    fun getNames(): List<String> {
        return list.flatMap { listOf(it.name) }
    }
}