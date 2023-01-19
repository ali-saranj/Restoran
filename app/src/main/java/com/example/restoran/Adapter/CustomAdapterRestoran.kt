package com.example.restoran.Adapter

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.restoran.Activity.RestoranActivity
import com.example.restoran.Fragment.ShowAllRestoranFragment
import com.example.restoran.Model.Restaurant
import com.example.restoran.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class CustomAdapterRestoran(private val dataSet: List<Restaurant>, val activity: Activity) :
    RecyclerView.Adapter<CustomAdapterRestoran.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
          var tv_titel: TextView
          var img_titel: ImageView
          var tv_Description: TextView
          var tv_time_work: TextView
          var ranting: RatingBar
          var img_top_bottom: ImageView
          var btn_call: CardView
          var card_restoran: CardView


        init {
            // Define click listener for the ViewHolder's View
            tv_titel = view.findViewById(R.id.tv_titel)
            img_titel = view.findViewById(R.id.titel_image)
            tv_Description = view.findViewById(R.id.tv_description)
            ranting = view.findViewById(R.id.ratingBar)
            tv_time_work = view.findViewById(R.id.tv_time_work)
            img_top_bottom = view.findViewById(R.id.img_top_bottom)
            btn_call = view.findViewById(R.id.btn_call)
            card_restoran = view.findViewById(R.id.card_restoran)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_restoran, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.ranting.rating = dataSet[position].rating.toFloat()
        viewHolder.tv_titel.text = dataSet[position].name
        viewHolder.tv_Description.text = dataSet[position].description
        viewHolder.tv_time_work.text = dataSet[position].timeWork
        Picasso.get().load(dataSet[position].imageTitleUrls).into(viewHolder.img_titel);

        viewHolder.img_top_bottom.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(it.context,android.R.anim.fade_in))
            if (viewHolder.tv_Description.maxLines==10)
            {
                viewHolder.tv_Description.maxLines = 2
                viewHolder.img_top_bottom.setImageResource(R.drawable.bottom)
            }else{
                viewHolder.tv_Description.maxLines = 10
                viewHolder.img_top_bottom.setImageResource(R.drawable.top)
            }
        }

        viewHolder.btn_call.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(it.context,android.R.anim.fade_in))
            if (ContextCompat.checkSelfPermission(it.context,android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.CALL_PHONE), 1)
            } else {
                it.context.startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:${dataSet[position].phone}")))
            }
        }

        viewHolder.card_restoran.setOnClickListener {
            var gson = Gson()
            activity.startActivity(Intent(viewHolder.card_restoran.context,RestoranActivity::class.java).putExtra("restoran",gson.toJson(dataSet[position])))
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}