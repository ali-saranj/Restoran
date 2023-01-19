package com.example.restoran.Activity

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.restoran.Adapter.CustomAdapterComent
import com.example.restoran.Adapter.CustomAdapterImage
import com.example.restoran.Model.Comment
import com.example.restoran.Model.Restaurant
import com.example.restoran.Model.RsaultComment
import com.example.restoran.Model.User
import com.example.restoran.WebServes.Client
import com.example.restoran.WebServes.Iclient
import com.example.restoran.databinding.ActivityRestoranBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestoranActivity : AppCompatActivity() {

    lateinit var binding: ActivityRestoranBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestoranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gson = Gson()
        var restoran = gson.fromJson(intent.getStringExtra("restoran"), Restaurant::class.java)

        setdata(restoran)

        binding.restoranListComent.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        binding.restoranListImage.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)

        getComments(restoran)

        binding.restoranListImage.adapter = CustomAdapterImage(restoran.get_imageUrls(), this)

        binding.restoranAddComent.setOnClickListener {
            var editText = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("ارسال نظر")
                .setView(editText)
                .setNegativeButton("لغو",object :OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0?.dismiss()
                    }
                })
                .setPositiveButton("ارسال",object :OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        val comment = Comment(0,restoran.id.toString(),User.User?.id.toString(),User.User?.name.toString(),editText.text.toString())
                        val Iclient = Client.getClient().create(com.example.restoran.WebServes.Iclient::class.java)
                        Iclient.addComment(comment).enqueue(object :Callback<RsaultComment>{
                            override fun onResponse(
                                call: Call<RsaultComment>,
                                response: Response<RsaultComment>
                            ) {
                                Toast.makeText(this@RestoranActivity, "Ok", Toast.LENGTH_SHORT).show()
                                getComments(restoran)
                            }

                            override fun onFailure(call: Call<RsaultComment>, t: Throwable) {
                                Toast.makeText(this@RestoranActivity,t.message, Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                })
                .create()
                .show()
        }
    }

    fun setdata(restaurant: Restaurant) {
        Picasso.get().load(restaurant.imageTitleUrls).into(binding.restoranImageTitel)
        binding.restoranTvTitel.text = restaurant.name
        binding.restoranTvDescription.text = restaurant.description
        binding.restoranRating.rating = restaurant.rating
        binding.restoranTvTimeWork.text = restaurant.timeWork
    }

    fun getComments(restaurant: Restaurant){
        val Iclient = Client.getClient().create(Iclient::class.java)

        Iclient.getComments(restaurant.id).enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                binding.restoranListComent.adapter = CustomAdapterComent(response.body()!!)
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
            }
        })
    }

}
