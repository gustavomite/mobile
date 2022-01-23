package br.com.recipy

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import br.com.recipy.data.Recipe
import br.com.recipy.data.RecipeList
import java.lang.IllegalStateException

class RemoveRecipeConfirmDialog(var recyclerView: RecyclerView, var data: Recipe): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(getString(R.string.remover_receita))
                .setPositiveButton(
                    getString(R.string.ok),
                    DialogInterface.OnClickListener { dialog, id ->
                        RecipeList.remove(data)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                )
                .setNegativeButton(
                    getString(R.string.cancel),
                    DialogInterface.OnClickListener { dialog, id ->

                    }
                )
            builder.create()
        } ?: throw IllegalStateException(getString(R.string.null_activity))
    }
}