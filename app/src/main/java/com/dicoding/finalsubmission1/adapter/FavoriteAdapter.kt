package com.dicoding.finalsubmission1.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.finalsubmission1.DetailActivity
import com.dicoding.finalsubmission1.FavoriteFragment
import com.dicoding.finalsubmission1.data.Event
import com.dicoding.finalsubmission1.databinding.ItemEventBinding

class FavoriteEventAdapter(
    private val fragment: FavoriteFragment
) : ListAdapter<Event, FavoriteEventAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val event = getItem(position)
        holder.bind(event)
    }

    inner class FavoriteViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.eventName.text = event.name


            Glide.with(binding.root.context)
                .load(event.imageLogo)
                .into(binding.eventImage)

            binding.root.setOnClickListener {
                val eventId = event.id
                val intent = Intent(fragment.requireContext(), DetailActivity::class.java)
                intent.putExtra("EVENT_ID", eventId)
                fragment.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem == newItem
            }
        }
    }
}
