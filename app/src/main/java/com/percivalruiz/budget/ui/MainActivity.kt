package com.percivalruiz.budget.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.percivalruiz.budget.R
import com.percivalruiz.budget.utils.setupWithNavController

class MainActivity : AppCompatActivity() {

  private var currentNavController: LiveData<NavController>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      setUpBottomNavigationBar()
    }
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
    super.onRestoreInstanceState(savedInstanceState)
    // Now that BottomNavigationBar has restored its instance state
    // and its selectedItemId, we can proceed with setting up the
    // BottomNavigationBar with Navigation
    setUpBottomNavigationBar()
  }

  private fun setUpBottomNavigationBar() {
    val navGraphIds = listOf(R.navigation.nav_daily, R.navigation.nav_budget, R.navigation.nav_accounts)
    val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

    val controller = bottomNav.setupWithNavController(
      navGraphIds = navGraphIds,
      fragmentManager = supportFragmentManager,
      containerId = R.id.nav_host_fragment,
      intent = intent
    )

    controller.observe(this, Observer { navController ->
      setupActionBarWithNavController(navController)
    })

    currentNavController = controller
  }

  override fun onSupportNavigateUp(): Boolean {
    return currentNavController?.value?.navigateUp() ?: false
  }
}