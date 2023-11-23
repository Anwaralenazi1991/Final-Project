package com.app1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper


class CartActivity : AppCompatActivity() {

    private lateinit var itemsOnCart: TextView
    private lateinit var itemsOnCartPrice: TextView
    private lateinit var cartEmpty: TextView
    private lateinit var checkoutButton: Button
    private lateinit var deleteButton: TextView

    private var totalAmount = 0

    private val ADDRESS_REQUEST_CODE = 0x01
    private var addressBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_activity)

        itemsOnCart = findViewById(R.id.cartListTextView)
        itemsOnCartPrice = findViewById(R.id.cartListPriceTextView)
        cartEmpty = findViewById(R.id.cartEmpty)
        checkoutButton = findViewById(R.id.checkout_button)
        deleteButton = findViewById(R.id.textView8)

        checkoutButton.setOnClickListener {
            if (addressBundle?.isEmpty == false) {
                val toCheckoutActivity = Intent(this, CheckoutActivity::class.java)
                toCheckoutActivity.putExtra("total_amount", totalAmount.toString())
                toCheckoutActivity.putExtra("address", addressBundle)
                startActivity(toCheckoutActivity)
            } else {

                val toAddressActivity = Intent(this, AddressActivity::class.java)
                startActivityForResult(toAddressActivity, ADDRESS_REQUEST_CODE)
            }
        }

        deleteButton.setOnClickListener {
            Toast.makeText(this, "Your cart is clean", Toast.LENGTH_SHORT).show()
            val dbHandler = MindOiksDBOpenHelper(this, null)
            val cursor = dbHandler.deleteAllItems()
            cursor!!.moveToFirst()
            cursor.close()
            onBackPressed()
        }

        val dbHandler = MindOiksDBOpenHelper(this, null)
        val cursor = dbHandler.getAllItems()
        cursor!!.moveToFirst()

        if (cursor.count > 0) {
            cartEmpty.visibility = View.GONE
            deleteButton.visibility = View.VISIBLE
            checkoutButton.visibility = View.VISIBLE

            itemsOnCart.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_NAME))))
            itemsOnCartPrice.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE))))

            val firstDigit =
                cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE))
                    .toString().replace("$", "").toInt()

            while (cursor.moveToNext()) {
                itemsOnCart.append("\n")
                itemsOnCart.append("\n")
                itemsOnCart.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_NAME))))

                itemsOnCartPrice.append("\n")
                itemsOnCartPrice.append("\n")
                itemsOnCartPrice.append((cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE))))


                totalAmount += cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_PRICE)).toString().replace(
                    "$",
                    ""
                ).toIntOrNull()!!
            }
            cursor.close()

            totalAmount += firstDigit

        } else {
            cartEmpty.visibility = View.VISIBLE
            deleteButton.visibility = View.VISIBLE
            checkoutButton.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADDRESS_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val extras = data?.extras

                if (extras != null) {
                    addressBundle = extras
                }
            }
        }

    }
}