package com.dicoding.finalsubmission1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.finalsubmission1.adapter.FinishAdapter
import com.dicoding.finalsubmission1.api.ApiClient
import com.dicoding.finalsubmission1.data.ListEvent
import com.dicoding.finalsubmission1.data.ListEventsItem
import com.dicoding.finalsubmission1.databinding.FragmentEventsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishedFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private lateinit var finishAdapter: FinishAdapter
    private val eventsList = mutableListOf<ListEventsItem>()
    private var isErrorShown = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        finishAdapter = FinishAdapter(eventsList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = finishAdapter

        fetchEvents()
    }

    private fun fetchEvents() {
        binding.progressBar.visibility = View.VISIBLE
        ApiClient.apiService.getFinished().enqueue(object : Callback<ListEvent> {
            override fun onResponse(call: Call<ListEvent>, response: Response<ListEvent>) {
                if (response.isSuccessful) {
                    eventsList.clear()
                    eventsList.addAll(response.body()?.listEvents ?: listOf())
                    finishAdapter.notifyDataSetChanged()
                    isErrorShown = false
                } else {
                    if (!isErrorShown) {
                        Toast.makeText(
                            context,
                            "Failed to load events",
                            Toast.LENGTH_SHORT
                        ).show()
                        isErrorShown = true
                    }
                }

                binding.progressBar.visibility = View.GONE
            }


            override fun onFailure(call: Call<ListEvent>, t: Throwable) {
                if (!isErrorShown) {
                    Toast.makeText(context, "Error: Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT)
                        .show()
                    isErrorShown = true
                }
                binding.progressBar.visibility = View.VISIBLE
            }
        }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
