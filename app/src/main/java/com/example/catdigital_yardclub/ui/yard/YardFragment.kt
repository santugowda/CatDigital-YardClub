package com.example.catdigital_yardclub.ui.yard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.catdigital_yardclub.R

class YardFragment : Fragment() {

    private lateinit var yardViewModel: YardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        yardViewModel =
                ViewModelProviders.of(this).get(YardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_yard, container, false)
        val textView: TextView = root.findViewById(R.id.text_myyard)
        yardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}