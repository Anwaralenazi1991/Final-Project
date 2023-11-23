package com.app1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper
import kotlinx.android.synthetic.main.checkout_activity.*

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

        totalAmountTV.text = "Total Amount to pay: $totalAmount $"

        val dbHandler = MindOiksDBOpenHelper(this, null)
        val cursor = dbHandler.getAllItems()
        cursor!!.moveToFirst()

        if (cursor.count > 0) {
            cartEmpty.visibility = View.GONE

            itemsOnCart.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_NAME))))
            itemsOnCartPrice.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE))))

          while (cursor.moveToNext()) {
                itemsOnCart.append("\n")
                itemsOnCart.append("\n")
                itemsOnCart.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_NAME))))

                itemsOnCartPrice.append("\n")
                itemsOnCartPrice.append("\n")
                itemsOnCartPrice.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE))))


            }
            cursor.close()

        } else {
            cartEmpty.visibility = View.VISIBLE
        }


        payButton.setOnClickListener {
            val toRestActivity = Intent(this, RestActivity::class.java)
            toRestActivity.putExtra("total_amount", totalAmount)
            toRestActivity.putExtra("address", addressBundle)
            startActivity(toRestActivity)
        }
    }
}