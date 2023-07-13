package com.mirodeon.eval_and2_exo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.mirodeon.eval_and2_exo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupNavigation()
        changeOnDestination()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.navHostFragment)
        setSupportActionBar(binding?.toolbarMenu?.root)
        NavigationUI.setupActionBarWithNavController(
            this, navController, AppBarConfiguration(
                setOf(
                    R.id.homeFragment
                )
            )
        )
        binding?.toolbarMenu?.containerAction?.setOnClickListener {
            navController.navigate(R.id.shoppingFragment)
        }
    }

    private fun changeOnDestination() {
        val toolbarTitle = binding?.toolbarMenu?.titleToolbar
        val toShopping = binding?.toolbarMenu?.containerAction

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    toolbarTitle?.text = getString(R.string.shoppinglist_title)
                    toShopping?.visibility = View.VISIBLE
                }

                R.id.shoppingFragment -> {
                    toolbarTitle?.text = getString(R.string.shoppinglist_title)
                    toShopping?.visibility = View.INVISIBLE
                }

                else -> {
                    toolbarTitle?.text = getString(R.string.no_title)
                    toShopping?.visibility = View.VISIBLE
                }
            }
        }
    }
}