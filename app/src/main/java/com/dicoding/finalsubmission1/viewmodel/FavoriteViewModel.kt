package com.dicoding.finalsubmission1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.finalsubmission1.data.Event
import com.dicoding.finalsubmission1.repository.EventRepository


class FavoriteViewModel(repository: EventRepository) : ViewModel() {
    val favoriteEvents: LiveData<List<Event>> = repository.getAllFavorites()
}
