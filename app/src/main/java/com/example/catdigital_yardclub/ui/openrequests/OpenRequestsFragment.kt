package com.example.catdigital_yardclub.ui.openrequests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.catdigital_yardclub.R

class OpenRequestsFragment : Fragment() {

    private lateinit var openRequestsViewModel: OpenRequestsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        openRequestsViewModel =
                ViewModelProviders.of(this).get(OpenRequestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_open_request, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        openRequestsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}