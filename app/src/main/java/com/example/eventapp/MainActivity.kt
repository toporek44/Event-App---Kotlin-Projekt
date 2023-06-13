package com.example.eventapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.eventapp.ui.dashboard.DashboardFragment
import com.example.eventapp.ui.home.HomeFragment
import com.example.eventapp.ui.notifications.NotificationsFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private var isDrawerVisible: Boolean = false
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        // Set listener for drawer menu item selections
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Handle drawer menu item 3 click
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.navigation_dashboard -> {
                    // Handle drawer menu item 4 click
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.navigation_notifications -> {
                    // Handle drawer menu item 4 click
                    drawerLayout.closeDrawers()
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Replace the content layout with Fragment1
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contentLayout, HomeFragment())
                        .commit()
                    true
                }
                R.id.navigation_dashboard -> {
                    // Replace the content layout with Fragment2
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contentLayout, DashboardFragment())
                        .commit()
                    true
                }
                R.id.navigation_notifications -> {
                    // Replace the content layout with Fragment2
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contentLayout, NotificationsFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // Set the initial fragment
        navigationView.menu.getItem(0).isChecked = true
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentLayout, HomeFragment())
            .commit()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        drawerToggle.isDrawerIndicatorEnabled = this.isDrawerVisible
        return super.onPrepareOptionsMenu(menu)
    }

    fun setDrawerVisible(visible: Boolean) {
        this.isDrawerVisible = visible
        invalidateOptionsMenu()
        drawerLayout.setDrawerLockMode(
            if (visible) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        )
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}