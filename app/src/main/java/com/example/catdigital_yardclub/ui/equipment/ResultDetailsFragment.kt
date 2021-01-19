package com.example.catdigital_yardclub.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.catdigital.ui.adapters.FeaturedImagesPagerAdapter
import com.example.catdigital_yardclub.R
import com.example.catdigital_yardclub.network.NetworkResponse
import com.example.catdigital_yardclub.ui.adapters.ResultAdapter
import com.example.catdigital_yardclub.viewmodel.YardClubViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ResultDetailsFragment : Fragment() {

    private var featuredImagesPagerAdapter: FeaturedImagesPagerAdapter? = null
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
        val viewPager = view.findViewById(R.id.featuredViewpager) as ViewPager2
        val tabLayout = view.findViewById(R.id.tabLayout) as TabLayout
        featuredImagesPagerAdapter = FeaturedImagesPagerAdapter(requireContext(), arrayListOf())
        viewPager.adapter = featuredImagesPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        }.attach()

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

                        val featureImagesList = response.data.featuredPhotos
                        val nameMap: List<String>
                        if(featureImagesList != null) {
                            nameMap = featureImagesList.map { it.url.toString() }
                            featuredImagesPagerAdapter?.setImageList(ArrayList(nameMap))
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

