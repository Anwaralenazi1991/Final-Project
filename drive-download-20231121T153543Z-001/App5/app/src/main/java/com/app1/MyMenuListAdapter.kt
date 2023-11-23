package com.app1

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.app1.db.MindOiksDBOpenHelper
import com.app1.db.Items

@Suppress("DEPRECATION")
class MyMenuListAdapter(var mCtx: Context, var resource: Int, var items: List<MenuModel>) :
    ArrayAdapter<MenuModel>(mCtx, resource, items) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)

        val imageView: ImageView = view.findViewById(R.id.menu_icon)
        val addToCart: Button = view.findViewById(R.id.add_to_cart_button)
        val textView: TextView = view.findViewById(R.id.food_name)
        val textView1: TextView = view.findViewById(R.id.food_price)


        val restaurant: MenuModel = items[position]


        imageView. setImageDrawable(mCtx.resources.getDrawable(context.resources
            .getIdentifier(restaurant.photo, "drawable", context.packageName)))

        textView.text = restaurant.name
        textView1.text = restaurant.price

        addToCart.setOnClickListener {
            val dbHandler = MindOiksDBOpenHelper(context, null)
            val item = Items(items[position].name, items[position].price)
            dbHandler.addItem(item)
            Toast.makeText(context, items[position].name + " Added to your cart", Toast.LENGTH_LONG)
                .show()
        }


        return view
    }

}

class MenuModel(val name: String, val price: String, val photo: String)
