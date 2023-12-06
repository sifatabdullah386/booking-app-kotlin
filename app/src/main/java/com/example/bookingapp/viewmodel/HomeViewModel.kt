package com.example.bookingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.model.RoomItems
import com.example.bookingapp.repositories.GetRoomList
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = GetRoomList()
    private val _data = MutableLiveData<ArrayList<RoomItems>>()
    val data: LiveData<ArrayList<RoomItems>> get() = _data

    fun fetchData() {
        viewModelScope.launch {
            val result = repository.fetchData()
//            _data.value = result!!
        }
    }
}