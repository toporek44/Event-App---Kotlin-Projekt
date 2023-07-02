package com.example.eventapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.MainActivity
import com.example.eventapp.R
import com.example.eventapp.databinding.FragmentDashboardBinding
import com.example.eventapp.models.EventsWrapper
import com.example.eventapp.models.embedded.events.Events
import com.example.eventapp.ui.home.EventListAdapter
import com.google.gson.Gson
import kotlin.math.log

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var recyclerView: RecyclerView
    private var list = arrayListOf<Events>()
    private lateinit var adapter: EventListAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adapter = EventListAdapter(list, context as MainActivity)
        (activity as? MainActivity)?.setDrawerVisible(true)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val response =
            com.example.eventapp.ui.utils.run(
                "https://app.ticketmaster.com/discovery/v2/events.json?apikey=rClsJ88hPEAEBq7CbXw0nDAD3KmP5wdu&size=10&sort=random",
                ::setLayout,
                activity
            )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLayout(responseBody: String?) {
        val gson = Gson()
        val testModel = gson.fromJson(responseBody, EventsWrapper::class.java)
        println("CREATRE")

        list = testModel.Embedded?.events ?: arrayListOf()
    }

    // this function couse problems
    private fun update(responseBody: String?) {
        val gson = Gson()
        val testModel = gson.fromJson(responseBody, EventsWrapper::class.java)
        println("UPDATE")

        list = testModel.Embedded?.events ?: arrayListOf()
    }

    private fun setRecyclerView() {
        recyclerView.adapter = adapter
        println(recyclerView.adapter)

    }

//    private fun setRecyclerView(eventList: ArrayList<Events>) {
//        val adapter = EventListAdapter(eventList, context as MainActivity)
//        recyclerView.adapter = adapter
//    }


    fun filteredData(countries: ArrayList<String>, categroies: ArrayList<String>) {
        println("https://app.ticketmaster.com/discovery/v2/events.json?apikey=rClsJ88hPEAEBq7CbXw0nDAD3KmP5wdu&city=$countries")
        com.example.eventapp.ui.utils.run(
            "https://app.ticketmaster.com/discovery/v2/events.json?apikey=rClsJ88hPEAEBq7CbXw0nDAD3KmP5wdu&city=$countries",
            ::update,
            activity,
        )
    }
}


