package com.example.eventapp

import android.annotation.SuppressLint
import android.os.Build
import android.content.Context

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.eventapp.ui.dashboard.DashboardFragment
import com.example.eventapp.ui.home.HomeFragment
import com.example.eventapp.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import android.widget.Button;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.eventapp.models.embedded.events.Events

import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.models.CountriesWrapper
import com.example.eventapp.models.EventsWrapper
import com.example.eventapp.models.GenresWrapper
import com.example.eventapp.models.shared.FilterItem
import com.example.eventapp.models.shared.FilterItemData
import com.example.eventapp.ui.adapters.ExpandableAdapter
import com.example.eventapp.ui.home.EventListAdapter
import com.example.eventapp.ui.utils.getCheckedItems
import com.example.eventapp.ui.utils.saveCheckedItems
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    val gson = Gson()
    private var isDrawerVisible: Boolean = false
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
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
        val context = this
        val menu = navigationView.getMenu()
        val filterItemMenu: MenuItem? = menu.findItem(R.id.filter_button)
        val clearFiltersItemMenu: MenuItem? = menu.findItem(R.id.clear_filters)
        val filterButton: Button? = filterItemMenu?.actionView?.findViewById<Button>(R.id.button)
        val clearFiltersButton: Button? =
            clearFiltersItemMenu?.actionView?.findViewById<Button>(R.id.button)
        filterButton?.apply {
            text = "Filtruj"
            setOnClickListener { l ->
                val countries = getCheckedItems(context, "countries")
                val categories = getCheckedItems(context, "categories")
                println(countries)
//                setDrawerVisible(false)
                DashboardFragment().filteredData(countries, categories)
            }
        }
        val filters = arrayListOf(
            FilterItem("Cities", getCountries()),
            FilterItem("Categories", getCategories()),
        )


        clearFiltersButton?.apply {
            text = "Wyczysc Filtry"
            setOnClickListener { l ->
                val emptyList = arrayListOf<String>()
                saveCheckedItems(context, emptyList, "countries")
                saveCheckedItems(context, emptyList, "categories")
                setRecyclerView(filters)
            }
        }


        val recyclerViewContainer = menu.findItem(R.id.filters)
        recyclerView =
            recyclerViewContainer?.actionView?.findViewById<RecyclerView>(R.id.filterContent)!!
        (recyclerView.itemAnimator as SimpleItemAnimator?)!!.supportsChangeAnimations = false
        recyclerView.layoutManager = LinearLayoutManager(this)

        drawerLayout.addDrawerListener(drawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)

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


        setRecyclerView(filters)

    }

    fun saveEvent(event: Events) {
        getPreferences(Context.MODE_PRIVATE).edit().putString("${event.id}", event.toString())
            .apply()
    }

    fun deleteEvent(event: Events) {
        getPreferences(Context.MODE_PRIVATE).edit().remove(event.id).apply()
    }

    fun checkForFav(event: Events): Boolean {
        return readEvents(event) != ""
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


    fun readAllEvents(): ArrayList<Events> {
        val jsonToProcess = getPreferences(Context.MODE_PRIVATE).all.values.toString()
        val arrayOfEmbeddedEvents: Array<Events> =
            gson.fromJson(jsonToProcess, Array<Events>::class.java)
        return arrayListOf(*arrayOfEmbeddedEvents)
    }

    private fun readEvents(event: Events): String? {
        return getPreferences(Context.MODE_PRIVATE).getString("${event.id}", "")
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

    private fun getCountries(): MutableList<FilterItemData> {

        val gson = Gson()
        val countries =
            assets.open("countries.json").bufferedReader().use { it.readText() }

        val countriesArray = gson.fromJson(countries, CountriesWrapper::class.java).countries
        val mappedCountries = countriesArray.map { country ->
            FilterItemData(country.name, country.name, "countries")
        }.toMutableList()

        return mappedCountries
    }

    private fun getCategories(): MutableList<FilterItemData> {

        val gson = Gson()
        val genres =
            assets.open("genres.json").bufferedReader().use { it.readText() }

        val genresArray = gson.fromJson(genres, GenresWrapper::class.java).genres
        val mappedGenres = genresArray.map { genre ->
            FilterItemData(genre.name, genre.id, "categories")
        }.toMutableList()

        return mappedGenres
    }


    private fun setRecyclerView(filters: ArrayList<FilterItem>) {
        val adapter = ExpandableAdapter(this, filters)
        recyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)

    }

}