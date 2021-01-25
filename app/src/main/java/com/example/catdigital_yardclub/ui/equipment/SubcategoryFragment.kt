package com.example.catdigital_yardclub.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.catdigital_yardclub.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catdigital_yardclub.model.CatalogItems
import com.example.catdigital_yardclub.network.NetworkResponse
import com.example.catdigital_yardclub.ui.adapters.SubcategoriesAdapter
import com.example.catdigital_yardclub.viewmodel.YardClubViewModel
import kotlinx.android.synthetic.main.fragment_subcategory.*

class SubcategoryFragment : Fragment(), SubcategoriesAdapter.OnSubcategoryEventListener {

    private lateinit var yardClubViewModel: YardClubViewModel
    private lateinit var catalogSharedViewModel: CatalogSharedViewModel
    private var subcategoriesAdapter: SubcategoriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subcategory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yardClubViewModel = ViewModelProvider(this).get(YardClubViewModel::class.java)
        catalogSharedViewModel =
            ViewModelProvider(requireActivity()).get(CatalogSharedViewModel::class.java)
        subcategoriesAdapter = SubcategoriesAdapter(arrayListOf(), this)
        val catalogRecyclerView: RecyclerView = view.findViewById(R.id.subcategoryRecycler)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        catalogRecyclerView.layoutManager = layoutManager
        catalogRecyclerView.adapter = subcategoriesAdapter
        displaySubcategoryView()
    }

    private fun displaySubcategoryView() {
        catalogSharedViewModel.selectedId.value?.id?.let {
            yardClubViewModel.catalogItems(it)
                .observe(viewLifecycleOwner, Observer { catalogItems ->
                    when (catalogItems.status) {
                        NetworkResponse.NetworkStatus.SUCCESS -> {
                            subcategoryProgress.visibility = View.GONE
                            if (catalogItems.data != null) {
                                subcategoriesAdapter?.addAll(catalogItems.data)
                            }
                        }

                        NetworkResponse.NetworkStatus.ERROR -> {

                        }

                        NetworkResponse.NetworkStatus.IN_PROGRESS -> {
                            subcategoryProgress.visibility = View.VISIBLE

                        }
                    }
                })
        }
    }

    override fun onSubcategorySelected(catalogItems: CatalogItems) {
        showResultFragment()
    }

    private fun showResultFragment() {
        val resultDetailsFragment = ResultDetailsFragment()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.equipmentContainer, resultDetailsFragment, ResultDetailsFragment.TAG)
            ?.addToBackStack(null)?.commit()
    }

    companion object {
        val TAG = SubcategoryFragment::class.java.simpleName
    }
}