package com.example.bookingapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookingapp.api.MyAPIServices
import com.example.bookingapp.api.RetrofitClientInstance
import com.example.bookingapp.databinding.FragmentHomeBinding
import com.example.bookingapp.model.RoomItems
import com.example.bookingapp.utilities.SessionManager
import com.example.bookingapp.viewmodel.HomeViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var sessionManager: SessionManager? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        sessionManager = SessionManager(requireContext())

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        homeViewModel.data.observe(viewLifecycleOwner) {

        }

        GlobalScope.launch {
            val result = fetchData()
            Log.d("Result", result.toString())
        }

        return root
    }

    private suspend fun fetchData() {
        val myAPIService = RetrofitClientInstance.getRetrofitInstance()!!.create(MyAPIServices::class.java)
        val call = myAPIService.getRoomList(sessionManager?.getValue("access_token"))
        Log.d("Access Token", sessionManager?.getValue("access_token").toString())
        call.enqueue(object : retrofit2.Callback<ArrayList<RoomItems>> {
            override fun onResponse(call: Call<ArrayList<RoomItems>>, response: Response<ArrayList<RoomItems>>) {
                Log.d("Url Responded", response.toString())
                Log.d("Body Responded", response.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<RoomItems>>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}