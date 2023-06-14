package com.example.eventapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.MainActivity
import com.example.eventapp.R
import com.example.eventapp.databinding.FragmentHomeBinding
import com.example.eventapp.models.EventsWrapper
import com.example.eventapp.models.embedded.events.Events
import com.google.gson.Gson
import com.example.eventapp.ui.utils.run

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var recyclerView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as? MainActivity)?.setDrawerVisible(false)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val response =
            run(
                "https://app.ticketmaster.com/discovery/v2/events.json?apikey=rClsJ88hPEAEBq7CbXw0nDAD3KmP5wdu&size=10&sort=random",
                ::setLayout,
                activity
            )

    }

    private fun setLayout(responseBody: String?) {
        val gson = Gson()
        val testModel = gson.fromJson(responseBody, EventsWrapper::class.java)
        testModel.Embedded?.let { setRecyclerView(it.events) }
    }


    private fun setRecyclerView(eventList: ArrayList<Events>) {
        val adapter = EventListAdapter(eventList)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}