package com.example.restoran.Fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restoran.Adapter.CustomAdapterRestoran
import com.example.restoran.Model.Restaurant
import com.example.restoran.WebServes.Client
import com.example.restoran.WebServes.Iclient
import com.example.restoran.databinding.FragmentShowAllRestoranBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowAllRestoranFragment(val activity: Activity) : Fragment() {

    lateinit var binding: FragmentShowAllRestoranBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowAllRestoranBinding.inflate(layoutInflater)

        binding.list.layoutManager = LinearLayoutManager(binding.root.context,
            RecyclerView.VERTICAL,false)
        val Iclient = Client.getClient().create(Iclient::class.java)

        Iclient.restaurants.enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(
                call: Call<List<Restaurant>>,
                response: Response<List<Restaurant>>
            ) {
                binding.list.adapter = response.body()?.let { CustomAdapterRestoran(it,activity) }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return binding.root
    }
}