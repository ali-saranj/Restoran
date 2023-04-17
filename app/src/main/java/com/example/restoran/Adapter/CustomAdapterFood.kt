package com.example.restoran.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.findViewTreeOnBackPressedDispatcherOwner
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.restoran.Activity.MainActivity
import com.example.restoran.Activity.RestoranActivity
import com.example.restoran.Model.Comment
import com.example.restoran.Model.Food
import com.example.restoran.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider

class CustomAdapterFood(private val dataSet: List<Food>,var activity: Activity,var tv_restoran_many: TextView) :
    RecyclerView.Adapter<CustomAdapterFood.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_name: TextView
        var tv_many: TextView
        var tv_cunter: TextView
        var image: ImageView
        var img_add: ImageView
        var img_remove: ImageView


        init {
            // Define click listener for the ViewHolder's View
            tv_name = view.findViewById(R.id.tv_food_name)
            tv_many = view.findViewById(R.id.tv_food_many)
            tv_cunter = view.findViewById(R.id.tv_cunter)
            image = view.findViewById(R.id.img)
            img_add = view.findViewById(R.id.img_add)
            img_remove = view.findViewById(R.id.img_remove)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_food, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var i = 0
        viewHolder.tv_name.text = dataSet[position].name
        viewHolder.tv_many.text = " تومان ${dataSet[position].many}"
        Picasso.get().load(dataSet[position].image).into(viewHolder.image)

        viewHolder.img_add.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(activity,android.R.anim.fade_in))
            i = viewHolder.tv_cunter.text.toString().toInt()+1
            RestoranActivity.many += dataSet[position].many
            tv_restoran_many.text = RestoranActivity.many.toString()
            viewHolder.tv_cunter.text = i.toString()
        }

        viewHolder.img_remove.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(activity,android.R.anim.fade_in))
            i = viewHolder.tv_cunter.text.toString().toInt()-1
            if(i >= 0){
                viewHolder.tv_cunter.text = i.toString()
                RestoranActivity.many -= dataSet[position].many
                tv_restoran_many.text = RestoranActivity.many.toString()
            }else{
                i=0
            }

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}