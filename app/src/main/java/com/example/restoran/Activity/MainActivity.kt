package com.example.restoran.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.restoran.Adapter.AdapterFragmet
import com.example.restoran.Fragment.MapsFragment
import com.example.restoran.Fragment.ShowAllRestoranFragment
import com.example.restoran.R
import com.example.restoran.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fragments = ArrayList<Fragment>()
        fragments.add(MapsFragment(this))
        fragments.add(ShowAllRestoranFragment(this))

        binding.viewpager.adapter = AdapterFragmet(
            supportFragmentManager,
            fragments,
            this
        )
        ConnectToolbariAndViwePager()

    }

    fun ConnectToolbariAndViwePager(){
        binding.viewpager.setOnPageChangeListener(object :OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.bubbleTabBar.setSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        binding.bubbleTabBar.addBubbleListener { id ->
            when (id) {
                R.id.restoran -> {
                    binding.viewpager.currentItem = 1
                }
                R.id.gps -> {
                    binding.viewpager.currentItem = 0
                }
            }
            Log.e("onBubbleClick: ", id.toString())
        }
    }
}



