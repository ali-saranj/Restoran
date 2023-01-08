package com.example.restoran.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.restoran.Activity.MainActivity
import com.example.restoran.Model.RsaultUser
import com.example.restoran.R
import com.example.restoran.WebServes.Client
import com.example.restoran.WebServes.Iclient
import com.example.restoran.databinding.FragmentLoginUserBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginUserFragment(var activity: Activity) : Fragment() {
    lateinit var binding: FragmentLoginUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginUserBinding.inflate(layoutInflater)

        binding.btn.setOnClickListener {
            when {
                binding.userName.text.isNullOrEmpty() -> {
                    binding.userName.error = "نام کاربری را وارد کنید"
                }
                binding.pass.text.isNullOrEmpty() -> {
                    binding.pass.error = "رمز را وارد کنید"
                }
                binding.phone.text.isNullOrEmpty() -> {
                    binding.phone.error = "شماره تلفن را وارد کنید"
                }
                else -> {
                    var Iclient = Client.getClient().create(Iclient::class.java)
                    binding.progress.visibility=View.VISIBLE
                    Iclient.loginUser(
                        binding.userName.text.toString(),
                        binding.pass.text.toString()
                    )
                        .enqueue(object : Callback<RsaultUser> {
                            override fun onResponse(
                                call: Call<RsaultUser>,
                                response: Response<RsaultUser>
                            ) {
                                if (response.body()?.status.equals("OK")) {
                                    binding.progress.visibility=View.INVISIBLE
                                    activity.startActivity(Intent(activity, MainActivity::class.java))
                                    activity.finish()
                                } else if (response.body()?.erorr.equals("user is not exist")) {
                                    binding.progress.visibility=View.INVISIBLE
                                    activity.findViewById<ViewPager>(R.id.viewpager).currentItem = 1
                                } else if (response.body()?.erorr.equals("Password is false")) {
                                    binding.progress.visibility=View.INVISIBLE
                                    var snackbar = Snackbar.make(binding.root,"رمز اشتباه است",Snackbar.LENGTH_LONG);
                                    snackbar.setAction("دریافت رمز عبور") {
                                        Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show()
                                    }
                                    snackbar.show()
                                }
                            }

                            override fun onFailure(call: Call<RsaultUser>, t: Throwable) {
                                Snackbar.make(
                                    binding.root,
                                    t.message.toString(),
                                    Snackbar.LENGTH_LONG
                                ).show()
                                binding.progress.visibility=View.INVISIBLE
                            }
                        })
                }
            }

        }
        return binding.root
    }
}