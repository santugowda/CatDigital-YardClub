package com.example.catdigital_yardclub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.catdigital_yardclub.ui.activerental.ActiveRentalFragment
import com.example.catdigital_yardclub.ui.equipment.EquipmentMainFragment
import com.example.catdigital_yardclub.ui.openrequests.OpenRequestsFragment
import com.example.catdigital_yardclub.ui.yard.YardFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {

    private val equipmentFragment: Fragment = EquipmentMainFragment()
    private val rentalFragment: Fragment = ActiveRentalFragment()
    private val requestFragment: Fragment = OpenRequestsFragment()
    private val yardFragment: Fragment = YardFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager
    var active: Fragment = equipmentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, yardFragment, "4").hide(
            yardFragment
        ).commit()
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, requestFragment, "3").hide(
            requestFragment
        ).commit()
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, rentalFragment, "2").hide(
            rentalFragment
        ).commit()
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, equipmentFragment, "1").commit()
    }

        private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_equipment -> {
                    fragmentManager.beginTransaction().hide(active).show(equipmentFragment).commit()
                    active = equipmentFragment
                    customToolbarTitle.text = getString(R.string.title_request_equipment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_rental -> {
                    fragmentManager.beginTransaction().hide(active).show(rentalFragment).commit()
                    active = rentalFragment
                    customToolbarTitle.text = getString(R.string.title_active_rental)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_requests -> {
                    fragmentManager.beginTransaction().hide(active).show(requestFragment).commit()
                    active = requestFragment
                    customToolbarTitle.text = getString(R.string.title_open_requests)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_yard -> {
                    fragmentManager.beginTransaction().hide(active).show(yardFragment).commit()
                    active = yardFragment
                    customToolbarTitle.text = getString(R.string.title_yard)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}