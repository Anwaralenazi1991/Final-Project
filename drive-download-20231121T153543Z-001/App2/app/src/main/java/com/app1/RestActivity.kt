package com.app1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.app1.db.MindOiksDBOpenHelper

class RestActivity : AppCompatActivity() {

    lateinit var orderNoTv: TextView
    lateinit var totalAmountTV: TextView
    lateinit var regionTV: TextView
    lateinit var streetTV: TextView
    lateinit var itemsOnCart: TextView
    lateinit var itemsOnCartPrice: TextView
    lateinit var doneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest)

        val totalAmount: String = intent.getStringExtra("total_amount")!!
        val addressBundle : Bundle = intent.getBundleExtra("address")
        val id : Int = intent.getIntExtra("order_id", -1)

        val region: String = addressBundle.getString("region", null)
        val street: String = addressBundle.getString("street", null)

        orderNoTv = findViewById(R.id.orderNum)
        totalAmountTV = findViewById(R.id.textView6)
        regionTV = findViewById(R.id.region)
        streetTV = findViewById(R.id.street)
        itemsOnCart = findViewById(R.id.cartListTextView)
        itemsOnCartPrice = findViewById(R.id.cartListPriceTextView)
        doneButton = findViewById(R.id.doneButton)

        orderNoTv.text = "$id"
        totalAmountTV.text = "Total Amount to pay: $totalAmount SAR"
        regionTV.text = region
        streetTV.text = street

        doneButton.setOnClickListener {
            val dbHandler = MindOiksDBOpenHelper(this, null)
            val cursor = dbHandler.deleteAllItems()
            cursor!!.moveToFirst()
            cursor.close()

            finish()
            startActivity(Intent(this@RestActivity, RestaurantsActivity::class.java))
        }


        val dbHandler = MindOiksDBOpenHelper(this, null)
        val cursor = dbHandler.getAllItems()
        cursor!!.moveToFirst()

        if (cursor.count > 0) {
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

        }



    }
}
