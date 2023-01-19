package com.example.restoran.Adapter

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.Drawable
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
import com.example.restoran.Model.Comment
import com.example.restoran.Model.Restaurant
import com.example.restoran.R
import com.squareup.picasso.Picasso
import kotlin.random.Random

class CustomAdapterComent(private val dataSet: List<Comment>) :
    RecyclerView.Adapter<CustomAdapterComent.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_name: TextView
        var tv_comment: TextView
        var card: CardView


        init {
            // Define click listener for the ViewHolder's View
            tv_name = view.findViewById(R.id.item_tv_name)
            tv_comment = view.findViewById(R.id.item_tv_comment)
            card = view.findViewById(R.id.list_card)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_coment_restoran, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.tv_name.text = dataSet[position].username
        viewHolder.tv_comment.text = dataSet[position].comment
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}