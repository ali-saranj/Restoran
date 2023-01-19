package com.example.restoran.Fragment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.view.marginTop
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
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
    lateinit var list: List<Restaurant>
    @RequiresApi(Build.VERSION_CODES.M)
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
                list = response.body()!!
                binding.list.adapter = CustomAdapterRestoran(list,activity)
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        binding.list.setOnScrollChangeListener { _, _, _, _, p4 ->
            if (p4<0){
                hideKeyboard(activity)
                binding.cardSerch.animate().translationY(-280f).setDuration(200).start()
            }else{
                binding.cardSerch.animate().translationY(0f).setDuration(200).start()
            }
        }

        binding.editSerch.addTextChangedListener {
            var arrayList = arrayListOf<Restaurant>()
            list.forEach{i->
                if (i.name.contains(it.toString())){
                    arrayList.add(i)
                }
            }
            if (arrayList.size ==1){
                hideKeyboard(activity)
                binding.list.setPadding(0,180,0,0)
            }else{
                binding.list.setPadding(0,0,0,0)
            }
            binding.list.adapter = CustomAdapterRestoran(arrayList,activity)
        }


        return binding.root
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}


