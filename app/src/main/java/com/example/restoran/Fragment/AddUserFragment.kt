package com.example.restoran.Fragment

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.restoran.Activity.MainActivity
import com.example.restoran.Model.RsaultUser
import com.example.restoran.Model.User
import com.example.restoran.R
import com.example.restoran.WebServes.Client
import com.example.restoran.WebServes.Iclient
import com.example.restoran.databinding.FragmentAddUserBinding
import com.google.android.material.snackbar.Snackbar
import io.ak1.BubbleTabBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddUserFragment(var activity: Activity): Fragment() {

    lateinit var binding: FragmentAddUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUserBinding.inflate(layoutInflater)

        binding.btn.setOnClickListener {
            when{
                binding.name.text.isNullOrEmpty()->{
                    binding.name.error = "نام را وارد کنید"
                }
                binding.family.text.isNullOrEmpty()->{
                    binding.family.error = "نام خانوادگی را وارد کنید"
                }
                binding.userName.text.isNullOrEmpty()->{
                    binding.userName.error = "نام کاربری را وارد کنید"
                }
                binding.pass.text.isNullOrEmpty()->{
                    binding.pass.error = "رمز را وارد کنید"
                }
                binding.phone.text.isNullOrEmpty()->{
                    binding.phone.error = "شماره تلفن را وارد کنید"
                }else->{
                binding.progress.visibility = VISIBLE
                var user = User(
                    0,
                    binding.name.text.toString(),
                    binding.family.text.toString(),
                    binding.userName.text.toString(),
                    binding.pass.text.toString(),
                    binding.phone.text.toString()
                )
                val Iclient = Client.getClient().create(Iclient::class.java)

                Iclient.addUser(user)
                    .enqueue(object : Callback<RsaultUser> {

                        override fun onResponse(
                            call: Call<RsaultUser>,
                            response: Response<RsaultUser>
                        ) {
                            Log.e("re",response.body().toString())
                            if (response.body()?.status.equals("OK")) {
                                binding.progress.visibility = INVISIBLE
                                User.User = response.body()?.any?.User
                                activity.startActivity(Intent(activity, MainActivity::class.java))
                                activity.finish()
                            } else {
                                binding.progress.visibility = INVISIBLE
                               Snackbar.make(binding.root,response.body()?.erorr.toString(),Snackbar.LENGTH_LONG).show()
                                activity.findViewById<BubbleTabBar>(R.id.bubbleTabBar).setSelected(0)
                            }

                        }

                        override fun onFailure(call: Call<RsaultUser>, t: Throwable) {
                            Snackbar.make(binding.root, t.message.toString(),Snackbar.LENGTH_LONG).show()
                            binding.progress.visibility = INVISIBLE

                        }
                    })


            }
            }
        }
        return binding.root
    }

}