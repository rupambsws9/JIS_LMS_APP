package com.example.jis_lms

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.jis_lms.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentmanager:FragmentManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this , binding.drawerLayout , binding.toolbar , R.string.nav_open , R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background= null
        binding.bottomNavigation.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.bottom_home->openfragment(homefragment())
                R.id.bottom_profile -> openfragment(profilefragment())
                R.id.bottom_cart -> openfragment(cartfragment())
                R.id.bottom_menu -> openfragment(menufragment())
            }
            true
        }

        fragmentmanager = supportFragmentManager
        openfragment(homefragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_Fees_and_dues -> openfragment(fees_dues_fragment())
            R.id.nav_notice -> openfragment(notice_fragment())
            R.id.nav_visual_labs -> openfragment(visuals_lab_fragment())
            R.id.nav_events -> openfragment(events_fragment())
            R.id.nav_placement -> Toast.makeText(this, "100% placement", Toast.LENGTH_SHORT).show()
            R.id.nav_logout -> Toast.makeText(this, "Log Out Succcessful", Toast.LENGTH_SHORT).show()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun openfragment(fragment: Fragment){
        val fragmentTransaction:FragmentTransaction = fragmentmanager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container , fragment)
        fragmentTransaction.commit()
    }

}