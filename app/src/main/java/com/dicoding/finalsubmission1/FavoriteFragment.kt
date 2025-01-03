package com.dicoding.finalsubmission1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.finalsubmission1.adapter.FavoriteEventAdapter
import com.dicoding.finalsubmission1.database.AppDatabase
import com.dicoding.finalsubmission1.databinding.FragmentFavoriteBinding
import com.dicoding.finalsubmission1.repository.EventRepository
import com.dicoding.finalsubmission1.viewmodel.FavoriteViewModel
import com.dicoding.finalsubmission1.viewmodel.FavoriteViewModelFactory

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!! // non-null assertion

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteEventAdapter: FavoriteEventAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up ProgressBar
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerViewFavorite.visibility = View.GONE
        binding.emptyStateMessage.visibility = View.GONE

        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(requireContext())
        favoriteEventAdapter = FavoriteEventAdapter(this)
        binding.recyclerViewFavorite.adapter = favoriteEventAdapter

        val eventDao = AppDatabase.getDatabase(requireContext()).eventDao()
        val repository = EventRepository(eventDao)

        favoriteViewModel = ViewModelProvider(this, FavoriteViewModelFactory(repository))[FavoriteViewModel::class.java]

        favoriteViewModel.favoriteEvents.observe(viewLifecycleOwner, Observer { events ->
            binding.progressBar.visibility = View.GONE

            if (events.isEmpty()) {
                binding.emptyStateMessage.visibility = View.VISIBLE
                binding.recyclerViewFavorite.visibility = View.GONE
            } else {
                binding.emptyStateMessage.visibility = View.GONE
                binding.recyclerViewFavorite.visibility = View.VISIBLE
                favoriteEventAdapter.submitList(events)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
