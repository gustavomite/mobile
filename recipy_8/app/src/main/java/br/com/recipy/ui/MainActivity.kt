package br.com.recipy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import br.com.recipy.*
import br.com.recipy.data.Recipe
import br.com.recipy.ui.adapter.RecipeAdapter
import br.com.recipy.Util.Func.startActivity
import br.com.recipy.Util.Func.startActivityWithTransition
import br.com.recipy.ui.addrecipe.AddRecipeActivity
import br.com.recipy.ui.dialogs.RemoveRecipeConfirmDialog
import br.com.recipy.ui.settings.SettingsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), RecipeAdapter.OnClickCallback {
    lateinit var recyclerView: RecyclerView
    val viewModel: MainViewModel by viewModels {
        MainActivityViewModelFactory((application as RecipyApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        val textView = findViewById<TextView>(R.id.textView2)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        textView.visibility = View.INVISIBLE

        viewModel.loadComplete.observe(this, {
            if (it == true) {
                recyclerView.adapter!!.notifyDataSetChanged()
                textView.visibility = View.INVISIBLE
                progressBar.visibility = View.INVISIBLE
            }
        })

        viewModel.loadError.observe(this, {
            if (it == true) {
                textView.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }
        })

        viewModel.getDataFromNetwork()

        viewModel.allIngredients.observe(this, { ingredientList ->
            viewModel.loadIngredientsFromDB(ingredientList)
        })

        linearLayout.setOnClickListener {
            startActivity(SettingsActivity())
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = RecipeAdapter(this)

        recyclerView.setHasFixedSize(true)

        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            startActivity(AddRecipeActivity())
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.insertIngredientsToDB()

        if (viewModel.checkSortOrder()) {
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            progressBar.visibility = View.VISIBLE
        }

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