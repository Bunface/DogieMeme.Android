package hu.bme.mobillabor.dogiememe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class MainActivity() : AppCompatActivity( R.layout.activity_main){

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setUpNavigation()
        }
    }

    private val navController: NavController by lazy { findNavController(R.id.fragment_navigator) }

    private fun setUpNavigation() {
        navController.addOnDestinationChangedListener { _, dest, _ ->
           //todo
        }
    }
}