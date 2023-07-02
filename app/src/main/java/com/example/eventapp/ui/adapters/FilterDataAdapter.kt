package com.example.eventapp.ui.adapters


import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.R
import com.example.eventapp.models.shared.FilterItemData
import com.example.eventapp.ui.utils.getCheckedItems
import com.example.eventapp.ui.utils.saveCheckedItems
import com.google.gson.Gson


class FilterDataAdapter(
    private val filtersData: MutableList<FilterItemData>, private val key: String
) :
    RecyclerView.Adapter<FilterDataAdapter.ExpendableViewHolder>() {
    private var checkedItems = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpendableViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.widget_check,
            parent,
            false
        )
        checkedItems = getCheckedItems(parent.context, key)

        return ExpendableViewHolder(itemView)
    }


    override fun getItemCount() = filtersData.size

    override fun onBindViewHolder(holder: ExpendableViewHolder, position: Int) {
        val currentItem: FilterItemData = filtersData[position]
        val context = holder.checkbox.context
        val key = currentItem.key
        val value = currentItem.value
        holder.checkbox.text = currentItem.name.toString()
        holder.checkbox.isChecked = checkedItems.contains(value)

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (value != null) {
                if (isChecked) {
                    checkedItems.add(value)
                } else {
                    checkedItems.remove(value)
                }
            }
            saveCheckedItems(context, checkedItems, key)
        }
    }


    inner class ExpendableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkbox: CheckBox = itemView.findViewById(R.id.checkBox)
    }


}
