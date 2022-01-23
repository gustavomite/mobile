package br.com.recipy.Util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.preference.PreferenceManager
import br.com.recipy.RecipyApp

object Func {
    fun Activity.startActivity(activity: AppCompatActivity, bundle: Bundle = Bundle(), flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK) {
        startActivity(Intent(this@startActivity, activity.javaClass).apply { putExtras(bundle); addFlags(flags) })
    }

    fun Activity.startActivityWithTransition(activity: AppCompatActivity, view: View, profile: String, bundle: Bundle = Bundle()) =
        run {
            val intent = Intent(this@startActivityWithTransition, activity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@startActivityWithTransition, view, profile
            )
            intent.putExtra("bundle", bundle)

            val bundleWithOptions = options.toBundle()
            startActivity(intent, bundleWithOptions)
        }

    fun getPrefs() = PreferenceManager.getDefaultSharedPreferences(RecipyApp.context)
}