package com.example.catdigital_yardclub.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catdigital_yardclub.R
import com.example.catdigital_yardclub.network.NetworkResponse
import com.example.catdigital_yardclub.ui.adapters.ResultAdapter
import com.example.catdigital_yardclub.viewmodel.YardClubViewModel

class ResultDetailsFragment : Fragment() {

    private lateinit var yardClubViewModel: YardClubViewModel
    private var resultAdapter: ResultAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yardClubViewModel = ViewModelProvider(this).get(YardClubViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultAdapter = ResultAdapter(arrayListOf())
        val resultRecyclerView: RecyclerView = view.findViewById(R.id.resultRecycler)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        resultRecyclerView.layoutManager = layoutManager
        resultRecyclerView.adapter = resultAdapter
        displayResults()
    }

    private fun displayResults() {
        yardClubViewModel.categoryResult().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                NetworkResponse.NetworkStatus.SUCCESS -> {
                    if (response.data != null) {
                        response.data.results?.let { result ->
                            resultAdapter?.addAll(result)
                        }
                    }
                }

                NetworkResponse.NetworkStatus.ERROR -> {

                }

                NetworkResponse.NetworkStatus.IN_PROGRESS -> {

                }
            }
        })
    }

    companion object {
        val TAG = ResultDetailsFragment::class.java.simpleName
    }
}

