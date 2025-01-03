package com.dicoding.finalsubmission1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.finalsubmission1.DetailActivity
import com.dicoding.finalsubmission1.EventsFragment
import com.dicoding.finalsubmission1.data.ListEventsItem
import com.dicoding.finalsubmission1.databinding.ItemEventBinding

class EventAdapter(private val events: List<ListEventsItem>, private val fragment: EventsFragment) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: ListEventsItem) {
            binding.eventName.text = event.name
            Glide.with(fragment)
                .load(event.imageLogo)
                .into(binding.eventImage)

            binding.root.setOnClickListener {
                val eventId = event.id
                val intent = Intent(fragment.context, DetailActivity::class.java)
                intent.putExtra("EVENT_ID", eventId)
                fragment.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }
}
