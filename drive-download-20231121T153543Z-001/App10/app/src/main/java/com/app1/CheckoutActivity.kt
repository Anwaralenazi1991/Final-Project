package com.app1

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper
import kotlinx.android.synthetic.main.checkout_activity.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutActivity : AppCompatActivity() {

    lateinit var totalAmountTV: TextView
    private lateinit var itemsOnCart: TextView
    private lateinit var itemsOnCartPrice: TextView
    private lateinit var cartEmpty: TextView
    private lateinit var payButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.checkout_activity)

        val totalAmount: String = intent.getStringExtra("total_amount")!!
        val addressBundle: Bundle = intent.getBundleExtra("address")

        totalAmountTV = findViewById(R.id.textView6)
        itemsOnCart = findViewById(R.id.cartListTextView)
        itemsOnCartPrice = findViewById(R.id.cartListPriceTextView)
        cartEmpty = findViewById(R.id.cartEmpty)
        payButton = findViewById(R.id.button)

        totalAmountTV.text = "Total Amount to pay: $totalAmount SAR"

        val prefs = PreferenceManager.getDefaultSharedPreferences(this@CheckoutActivity)
        val userId = prefs.getLong("userId", -1)

        val dbHandler = MindOiksDBOpenHelper(this, null)
        val user = dbHandler.getUser(userId)
        val cursor = dbHandler.getAllItems()
        cursor!!.moveToFirst()

        val list = java.util.ArrayList<MenuModel>()

        if (cursor.count > 0) {
            cartEmpty.visibility = View.GONE

            val item = MenuModel(
                id = cursor.getInt(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PROD_ID)),
                name = cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_NAME)),
                price = cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE)),
                photo = ""
            )


            list.add(item)
            itemsOnCart.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_NAME))))
            itemsOnCartPrice.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE))))

            while (cursor.moveToNext()) {
                itemsOnCart.append("\n")
                itemsOnCart.append("\n")
                itemsOnCart.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_NAME))))

                itemsOnCartPrice.append("\n")
                itemsOnCartPrice.append("\n")
                itemsOnCartPrice.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE))))


                val item = MenuModel(
                    id = cursor.getInt(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PROD_ID)),
                    name = cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_NAME)),
                    price = cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE)),
                    photo = ""
                )

                list.add(item)
            }
            cursor.close()

        } else {
            cartEmpty.visibility = View.VISIBLE
        }


        payButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            val address =
                addressBundle.getString("region") + " " + addressBundle.getString("street")
            Injection.getInstance().menuAPIs.addOrder(user!!.userName, address,
                totalAmount.toDouble(), getString(R.string.app_name), list).enqueue(
                object : Callback<Order> {
                    override fun onResponse(
                        call: Call<Order>,
                        response: Response<Order>
                    ) {
                        progressBar.visibility = View.GONE
                        if (response.isSuccessful && response.body() != null) {
                            val toRestActivity = Intent(this@CheckoutActivity, RestActivity::class.java)
                            toRestActivity.putExtra("total_amount", totalAmount)
                            toRestActivity.putExtra("order_id", response.body()!!.id)
                            toRestActivity.putExtra("address", addressBundle)
                            startActivity(toRestActivity)
                        }
                    }

                    override fun onFailure(call: Call<Order>, t: Throwable) {
                        progressBar.visibility = View.GONE
                    }
                });


        }
    }
}