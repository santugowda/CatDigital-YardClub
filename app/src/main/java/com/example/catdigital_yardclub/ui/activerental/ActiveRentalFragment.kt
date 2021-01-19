package com.example.catdigital_yardclub.ui.activerental

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.catdigital_yardclub.R

class ActiveRentalFragment : Fragment() {

    private lateinit var activeRentalViewModel: ActiveRentalViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        activeRentalViewModel =
                ViewModelProviders.of(this).get(ActiveRentalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_active_rental, container, false)
        val textView: TextView = root.findViewById(R.id.text_active_rental)
        activeRentalViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}