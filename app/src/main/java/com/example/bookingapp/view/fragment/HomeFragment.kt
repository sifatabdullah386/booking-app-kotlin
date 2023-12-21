package com.example.bookingapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bookingapp.databinding.FragmentHomeBinding
import com.example.bookingapp.utilities.SessionManager
import com.example.bookingapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var sessionManager: SessionManager? = null
    private val homeViewModel: HomeViewModel by viewModels()

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        sessionManager = SessionManager(requireContext())

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

//        homeViewModel.data.observe(viewLifecycleOwner) {
//
//        }

        lifecycleScope.launch {
            try {
                val authToken = sessionManager?.getValue("access_token").toString()
                Log.d("Access Token", sessionManager?.getValue("access_token").toString())
                val roomInformation = homeViewModel.getRoomInformation(authToken)
                Log.d("Room Info", roomInformation.toString())

            } catch (e: Exception) {
                Log.d("Room Fetching Error", e.toString())
            }
        }

//        GlobalScope.launch {
//            val result = fetchData()
//            Log.d("Result", result.toString())
//        }

        return root
    }

//    private suspend fun fetchData() {
//        val call = apiServices.getRoomList(sessionManager?.getValue("access_token"))
//        Log.d("Access Token", sessionManager?.getValue("access_token").toString())
//
//        call.enqueue(object : retrofit2.Callback<ArrayList<RoomItems>> {
//            override fun onResponse(call: Call<ArrayList<RoomItems>>, response: Response<ArrayList<RoomItems>>) {
//                Log.d("Url Responded", response.toString())
//                Log.d("Body Responded", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<ArrayList<RoomItems>>, t: Throwable) {
//                Log.d("Error", t.message.toString())
//            }
//        })
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}