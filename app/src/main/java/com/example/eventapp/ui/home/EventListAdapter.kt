package com.example.eventapp.ui.home


import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.R
import com.example.eventapp.models.embedded.events.Events

import java.net.HttpURLConnection
import java.net.URL

class EventListAdapter(private val eventList: ArrayList<Events>) :
    RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_event,
            parent,
            false
        )
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val newWidth = Resources.getSystem().displayMetrics.widthPixels.minus(holder.imageView.width+350)
        holder.eventDataView.getLayoutParams().width = newWidth

        val currentItem = eventList[position]
        val date = currentItem.dates?.start
        val messageString = if(date?.localDate!=null && date.localTime!=null) "${date.localDate} ${date.localTime.toString().substring(0,5)}" else "TBD"

        holder.eventNameTextView.text = currentItem.name
        holder.genre.text = currentItem.classifications.first().genre?.name ?: "TBD when"
        holder.ageRestriction.text =
            if (currentItem.ageRestrictions?.legalAgeEnforced == true) "+18" else ""
        holder.eventDateTextView.text = messageString

        // city
        // country
        // genreId
        // startDateTime
        // keyword

        Thread {
            try {
                val url = URL(currentItem.images.first().url)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                holder.imageView.post {
                    holder.imageView.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    override fun getItemCount() = eventList.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.thumbnail)
        val eventNameTextView: TextView = itemView.findViewById(R.id.eventNameTextView)
        val eventDateTextView: TextView = itemView.findViewById(R.id.eventDateTextView)
        val ageRestriction: TextView = itemView.findViewById(R.id.ageRestriction)
        val genre: TextView = itemView.findViewById(R.id.genre)
        val eventDataView: View = itemView.findViewById(R.id.eventData)
    }
}
