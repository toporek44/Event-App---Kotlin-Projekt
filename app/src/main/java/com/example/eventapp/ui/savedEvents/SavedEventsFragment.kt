package com.example.eventapp.ui.savedEvents

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
import com.example.eventapp.models.embedded.events.Events
import com.example.eventapp.ui.home.EventListAdapter
import com.example.eventapp.ui.utils.readFavorites
import java.util.ArrayList

class SavedEventsFragment : Fragment() {

    private var _binding: FragmentEventSearchBinding? = null
    private lateinit var recyclerView: RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val savedEventsViewModel =
            ViewModelProvider(this).get(SavedEventsViewModel::class.java)

        _binding = FragmentEventSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as? MainActivity)?.setDrawerVisible(false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val allSavedEvents: ArrayList<Events> = readFavorites(requireContext())
        setRecyclerView(allSavedEvents)
    }

    private fun setRecyclerView(eventList: ArrayList<Events>?) {
        val adapter = eventList?.let { EventListAdapter(it, context as MainActivity) }
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}