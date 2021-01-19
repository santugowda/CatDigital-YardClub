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
import com.example.catdigital_yardclub.model.Catalog
import com.example.catdigital_yardclub.network.NetworkResponse
import com.example.catdigital_yardclub.ui.adapters.CatalogAdapter
import com.example.catdigital_yardclub.viewmodel.YardClubViewModel
import kotlinx.android.synthetic.main.fragment_category.*


class CatalogSharedViewModel : ViewModel() {
    val selectedId = MutableLiveData<Catalog>()

    fun catalogSelected(catalog: Catalog) {
        selectedId.value = catalog
    }
}

class CategoryFragment : Fragment(), CatalogAdapter.OnCatalogEventListener {

    private lateinit var yardClubViewModel: YardClubViewModel
    private lateinit var catalogSharedViewModel: CatalogSharedViewModel
    private var catalogAdapter: CatalogAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yardClubViewModel = ViewModelProvider(this).get(YardClubViewModel::class.java)
        catalogSharedViewModel =
            ViewModelProvider(requireActivity()).get(CatalogSharedViewModel::class.java)
        catalogAdapter = CatalogAdapter(arrayListOf(), this)
        val catalogRecyclerView: RecyclerView = view.findViewById(R.id.catalogRecycler)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        catalogRecyclerView.layoutManager = layoutManager
        catalogRecyclerView.adapter = catalogAdapter

        yardClubViewModel.catalogNames().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                NetworkResponse.NetworkStatus.SUCCESS -> {
                    mainProgress.visibility = View.GONE
                    if (it.data != null) {
                        catalogAdapter?.addAll(it.data)
                    }
                }

                NetworkResponse.NetworkStatus.ERROR -> {

                }
                NetworkResponse.NetworkStatus.IN_PROGRESS -> {
                    mainProgress.visibility = View.VISIBLE

                }
            }
        })
    }

    override fun onCatalogSelected(catalog: Catalog) {
        catalogSharedViewModel.catalogSelected(catalog)
    }

    companion object {
        val TAG = CategoryFragment::class.java.simpleName
    }
}