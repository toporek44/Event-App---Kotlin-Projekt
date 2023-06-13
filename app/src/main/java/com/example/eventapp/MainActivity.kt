package com.example.eventapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.eventapp.ui.dashboard.DashboardFragment
import com.example.eventapp.ui.home.HomeFragment
import com.example.eventapp.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)

        // Set listener for drawer menu item selections
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_checkbox -> {
                    val checkBox = menuItem.actionView?.findViewById<CheckBox>(R.id.checkBox)
                    checkBox?.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            // Checkbox is checked
                        } else {
                            // Checkbox is unchecked
                        }
                    }
                    true
                }


                R.id.nav_button -> {
                    val button = menuItem.actionView?.findViewById<Button>(R.id.button)
                    button?.setOnClickListener { l ->
                    }
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
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)

                    true
                }

                R.id.navigation_dashboard -> {
                    // Replace the content layout with Fragment2
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contentLayout, DashboardFragment())
                        .commit()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    true
                }

                R.id.navigation_notifications -> {
                    // Replace the content layout with Fragment2
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.contentLayout, NotificationsFragment())
                        .commit()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
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