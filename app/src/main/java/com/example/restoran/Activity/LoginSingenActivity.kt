package com.example.restoran.Activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.restoran.Adapter.AdapterFragmet
import com.example.restoran.Fragment.AddUserFragment
import com.example.restoran.Fragment.LoginUserFragment
import com.example.restoran.R
import com.example.restoran.databinding.ActivityLoginSingenBinding


class LoginSingenActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginSingenBinding
    lateinit var fragments:ArrayList<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSingenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fragments = ArrayList<Fragment>()
        fragments.add(LoginUserFragment(this))
        fragments.add(AddUserFragment(this))

        binding.viewpager.adapter = AdapterFragmet(
            supportFragmentManager,
            fragments,
            this
        )
        ConnectToolbariAndViwePager()
    }
    fun ConnectToolbariAndViwePager(){
        binding.viewpager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
                R.id.adduser -> {
                    binding.viewpager.currentItem = 1
                }
                R.id.loginuser -> {
                    binding.viewpager.currentItem = 0
                }
            }

        }
    }
}