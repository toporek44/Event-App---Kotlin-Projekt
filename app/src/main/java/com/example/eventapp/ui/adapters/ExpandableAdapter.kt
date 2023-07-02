package com.example.eventapp.ui.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventapp.R
import com.example.eventapp.models.shared.FilterItem
import com.example.eventapp.models.shared.FilterItemData


class ExpandableAdapter(
    var context: Context,
    private val filters: ArrayList<FilterItem>
) :
    RecyclerView.Adapter<ExpandableAdapter.ExpendableViewHolder>() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpendableViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.expandable,
            parent,
            false
        )

        return ExpendableViewHolder(itemView)
    }


    override fun getItemCount() = filters.size


    override fun onBindViewHolder(holder: ExpendableViewHolder, position: Int) {
        val currentItem: FilterItem = filters[position]
        holder.title.text = currentItem.name
        recyclerView = holder.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.isNestedScrollingEnabled = false;

        currentItem.data?.let {
            currentItem.name?.let { it1 ->
                setRecyclerView(
                    it,
                    it1.toLowerCase()
                )
            }
        }
        holder.subItem.visibility = if (currentItem.expanded) View.VISIBLE else View.GONE
        holder.arrow.setImageResource(if (currentItem.expanded) R.drawable.ic_arrow_up_foreground else R.drawable.ic_arrow_down_foreground)

        holder.title.setOnClickListener { v ->
            val layoutParams = holder.subItem.layoutParams
            holder.subItem.layoutParams = layoutParams
            val expanded: Boolean = currentItem.expanded
            currentItem.expanded = !expanded
            layoutParams.height = (currentItem.data?.size?.times(110)!!) + 50
            holder.subItem.visibility = if (expanded) View.VISIBLE else View.GONE
            holder.arrow.setImageResource(if (expanded) R.drawable.ic_arrow_up_foreground else R.drawable.ic_arrow_down_foreground)
            notifyItemChanged(position)

        }
    }


    inner class ExpendableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.item_title)

        val recyclerView: RecyclerView = itemView.findViewById(R.id.filterData)

        val subItem: LinearLayout = itemView.findViewById(R.id.sub_item)
        val arrow: ImageView = itemView.findViewById(R.id.arrow)
    }

    private fun setRecyclerView(filterDataList: MutableList<FilterItemData>, key: String) {
        val adapter = FilterDataAdapter(filterDataList, key)
        recyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
    }

}
