package com.example.restoran.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.restoran.Model.Restaurant
import com.example.restoran.R
import com.squareup.picasso.Picasso

class CustomAdapterRestoran(private val dataSet: List<Restaurant>) :
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

        init {
            // Define click listener for the ViewHolder's View
            tv_titel = view.findViewById(R.id.tv_titel)
            img_titel = view.findViewById(R.id.titel_image)
            tv_Description = view.findViewById(R.id.tv_description)
            ranting = view.findViewById(R.id.ratingBar)
            tv_time_work = view.findViewById(R.id.tv_time_work)
            img_top_bottom = view.findViewById(R.id.img_top_bottom)
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
            if (viewHolder.tv_Description.maxLines==5)
            {
                viewHolder.tv_Description.maxLines = 2
                viewHolder.img_top_bottom.setImageResource(R.drawable.bottom)
            }else{
                viewHolder.tv_Description.maxLines = 5
                viewHolder.img_top_bottom.setImageResource(R.drawable.top)
            }
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}