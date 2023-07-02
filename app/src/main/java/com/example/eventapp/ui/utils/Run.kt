package com.example.eventapp.ui.utils

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

fun run(
    url: String,
    setData: (responseBody: String?) -> Unit,
    activity: FragmentActivity?,
) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(url)
        .build()
    val mHandler = Handler(Looper.getMainLooper());
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body()?.string()

            mHandler.post(Runnable {
                println("TEST 2")
                setData(responseBody)
            })

            // Update the UI with the response data


//            val responseBody = response.body()?.string()
//            activity?.runOnUiThread {
//                println("TEST 2")
//                setData(responseBody)
//            }
        }
    })
}