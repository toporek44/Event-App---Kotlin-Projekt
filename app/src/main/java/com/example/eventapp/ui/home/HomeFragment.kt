package com.example.eventapp.ui.home

import android.os.Bundle
import android.util.JsonReader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.eventapp.MainActivity
import com.example.eventapp.R
import com.example.eventapp.databinding.FragmentHomeBinding
import com.example.eventapp.models.Events
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var responseData: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as? MainActivity)?.setDrawerVisible(false)

        val textView: TextView = binding.textHome


//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = responseData.text
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the UI element(s)
        responseData = view.findViewById(R.id.text_home)!!

        run("https://app.ticketmaster.com/discovery/v2/events.json?apikey=rClsJ88hPEAEBq7CbXw0nDAD3KmP5wdu&size=5")
    }

    fun run(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()?.string()

                activity?.runOnUiThread {
                    // Update UI with the response data
                    val gson = Gson()
                    var testModel = gson.fromJson(responseBody, Events::class.java)
                    responseData.text = responseBody
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}