package com.cendekia.githubapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cendekia.githubapp.R
import com.cendekia.githubapp.repositories.models.Event

class ListEventAdapter(private val listEvent: ArrayList<Event>) :
    RecyclerView.Adapter<ListEventAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Event)
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvEventnames: TextView = itemView.findViewById(R.id.tv_name_events)
        var tvEventlocation: TextView = itemView.findViewById(R.id.tv_location_events)
        var ivEventimg: ImageView = itemView.findViewById(R.id.iv_img_events)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_carousell_events, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val event = listEvent[position]
        Glide.with(holder.itemView.context)
            .load(event.photo_events)
            .apply(RequestOptions().override(666, 374))
            .into(holder.ivEventimg)
        holder.tvEventnames.text = event.name_events
        holder.tvEventlocation.text = event.location_events

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listEvent[holder.adapterPosition]) }
    }
}