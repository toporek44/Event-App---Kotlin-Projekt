package com.example.eventapp.ui.eventSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.MainActivity
import com.example.eventapp.R
import com.example.eventapp.databinding.FragmentEventSearchBinding
import com.example.eventapp.models.EventsWrapper
import com.example.eventapp.models.embedded.events.Events
import com.example.eventapp.ui.home.EventListAdapter
import com.google.gson.Gson

class EventSearchFragment : Fragment() {

    private var _binding: FragmentEventSearchBinding? = null
    private lateinit var recyclerView: RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val eventSearchViewModel =
            ViewModelProvider(this).get(EventSearchViewModel::class.java)

        _binding = FragmentEventSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (activity as? MainActivity)?.setDrawerVisible(true)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val emptyList = arrayListOf<String>()
        filteredData(emptyList, emptyList, "")

        com.example.eventapp.ui.utils.run(
            "https://app.ticketmaster.com/discovery/v2/events.json?apikey=rClsJ88hPEAEBq7CbXw0nDAD3KmP5wdu&sort=random",
            ::update,
            activity,
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLayout(responseBody: String?) {
        val gson = Gson()
        val testModel = gson.fromJson(responseBody, EventsWrapper::class.java)
        testModel.Embedded?.let { setRecyclerView(it.events) }
    }

    private fun update(responseBody: String?) {
        setLayout(responseBody)
        recyclerView.adapter?.notifyDataSetChanged()
    }


    private fun setRecyclerView(eventList: ArrayList<Events>) {
        val adapter = EventListAdapter(eventList, context as MainActivity)
        recyclerView.adapter = adapter
    }

    fun parseQueryData(string: String): String {
        return string.replace(Regex("[\\[\\]]"), "")
    }

    fun filteredData(
        countries: ArrayList<String>,
        categories: ArrayList<String>,
        searchString: String
    ) {
        val parsedCategories = parseQueryData(categories.toString())
        val parsedCountries = parseQueryData(countries.toString())

        val catogoriesParam = if (categories.size > 0) "&segmentId=$parsedCategories" else ""
        val countriesParam = if (countries.size > 0) "&city=$parsedCountries" else ""
        val keywordParam = if (searchString.isNotEmpty()) "&keyword=$searchString" else ""
        com.example.eventapp.ui.utils.run(
            "https://app.ticketmaster.com/discovery/v2/events.json?apikey=rClsJ88hPEAEBq7CbXw0nDAD3KmP5wdu$countriesParam$catogoriesParam$keywordParam&sort=random",
            ::update,
            activity,
        )
    }
}


