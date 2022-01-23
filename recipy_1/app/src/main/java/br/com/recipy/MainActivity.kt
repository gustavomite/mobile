package br.com.recipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import br.com.recipy.data.Recipe
import br.com.recipy.data.RecipeList
import br.com.recipy.ui.RecipeAdaptor

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rec1 = Recipe("Receita1")
        val rec2 = Recipe("Receita2")
        RecipeList.add(rec1)
        RecipeList.add(rec2)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = RecipeAdaptor()

        recyclerView.setHasFixedSize(true)
    }
}