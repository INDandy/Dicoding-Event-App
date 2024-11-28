package com.dicoding.finalsubmission1.repository

import androidx.lifecycle.LiveData
import com.dicoding.finalsubmission1.dao.EventDao
import com.dicoding.finalsubmission1.data.Event

class EventRepository(private val eventDao: EventDao) {
    fun getAllFavorites(): LiveData<List<Event>> {
        return eventDao.getAllFavorites()
    }
}
