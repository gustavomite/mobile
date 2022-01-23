package br.com.recipy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import br.com.recipy.data.Recipe
import br.com.recipy.data.RecipeList
import br.com.recipy.ui.RecipeAdaptor
import br.com.recipy.Util.Func.startActivity
import br.com.recipy.Util.Func.startActivityWithTransition
import br.com.recipy.ui.addrecipe.AddRecipeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), RecipeAdaptor.OnClickCallback {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rec1 = Recipe("Receita1")
        val rec2 = Recipe("Receita2")
        rec2.description = "Adicione os ovos\n" +
                "Adicione a farinha\n" +
                "Mexa\nAdicione os ovos\n" +
                "Adicione a farinha\n" +
                "Mexa\nAdicione os ovos\nAdicione a farinha\nMexa\nAdicione os ovos\n" +
                "Adicione a farinha\n" +
                "MexaAdicione os ovos\n" +
                "Adicione a farinha\n" +
                "Mexa\nAdicione Leite\nMexa por Mais trinta minutos\nLeve ao forno\nSirva"
        RecipeList.add(rec1)
        RecipeList.add(rec2)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = RecipeAdaptor(this)

        recyclerView.setHasFixedSize(true)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            startActivity(AddRecipeActivity())
        }
    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onClick(data: Recipe) {
        val bundle = Bundle()
        bundle.putParcelable("data", data)
        startActivityWithTransition(RecipeDetails(), recyclerView, "profile_recipe", bundle)
    }

    override fun onLongClick(data: Recipe) {
        val dialog = RemoveRecipeConfirmDialog(recyclerView, data)
        dialog.show(supportFragmentManager, "RemoveRecipeConfirmDialog")
    }
}