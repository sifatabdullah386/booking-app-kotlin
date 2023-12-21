package com.example.bookingapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bookingapp.api.MyAPIServices
import com.example.bookingapp.model.RoomItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//class HomeViewModel : ViewModel() {
//    private val repository = GetRoomList()
//    private val _data = MutableLiveData<ArrayList<RoomItems>>()
//    val data: LiveData<ArrayList<RoomItems>> get() = _data
//
////    fun fetchData() {
////        viewModelScope.launch {
////            val result = repository.fetchData()
//////            _data.value = result!!
////        }
////    }
//}

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService: MyAPIServices) : ViewModel() {

    suspend fun getRoomInformation(token: String): ArrayList<RoomItems> = withContext(Dispatchers.IO) {
        return@withContext apiService.getRoomList(token)
    }
}