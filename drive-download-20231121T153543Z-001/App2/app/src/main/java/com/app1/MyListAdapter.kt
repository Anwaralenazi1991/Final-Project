package com.app1

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName

@Suppress("DEPRECATION")
class MyListAdapter(var mCtx: Context, var resource: Int, var items: List<Model>) :
    ArrayAdapter<Model>(mCtx, resource, items) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)

        val imageView: ImageView = view.findViewById(R.id.restaurant_icon)
        val textView: TextView = view.findViewById(R.id.restaurant_name)
        val textView1: TextView = view.findViewById(R.id.restaurant_description)


        val restaurant: Model = items[position]

//        imageView.run {
//            setImageDrawable(mCtx.resources.getDrawable(context.resources
//                .getIdentifier(restaurant.res, "drawable", context.packageName)))
//        }
        Glide.with(context).load(restaurant.res)
            .into(imageView)
        textView.text = restaurant.name
        textView1.text = restaurant.des


        return view
    }

}

class Model(@SerializedName("name") val name: String,
            @SerializedName("branchName") val des: String,
            @SerializedName("image") val res: String,
            val id: Int = 0,
            val deleted: Int? = 0)
