package com.example.catdigital_yardclub.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.catdigital_yardclub.R

class EquipmentMainFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val requestEquipmentFragment = RequestEquipmentFragment()
        val root = inflater.inflate(R.layout.fragment_equipment_main, container, false)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.equipmentContainer, requestEquipmentFragment, RequestEquipmentFragment.TAG)
            ?.commit()
        return root
    }
}