package com.example.catdigital_yardclub.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.catdigital_yardclub.R
import kotlinx.android.synthetic.main.fragment_equipment.*

class RequestEquipmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_equipment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainImage.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_equipment_to_CategoryFragment)
            showCategoryFragment()
        }
    }

    private fun showCategoryFragment() {
        val categoryFragment = CategoryFragment()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.equipmentContainer, categoryFragment, CategoryFragment.TAG)
            ?.addToBackStack(null)?.commit()
    }

    companion object {
        val TAG = RequestEquipmentFragment::class.java.simpleName
    }
}