package com.app1

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper

class AddressActivity : AppCompatActivity() {

    lateinit var continueButton: Button
    lateinit var regionTextField: EditText
    lateinit var streetTextField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        continueButton = findViewById(R.id.proceed_button)
        regionTextField = findViewById(R.id.region)
        streetTextField = findViewById(R.id.street)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this@AddressActivity)
        val userId = prefs.getLong("userId", -1)

        val dbHandler = MindOiksDBOpenHelper(this@AddressActivity, null)
        val user = dbHandler.getUser(userId)

        regionTextField.setText(user!!.region)
        streetTextField.setText(user.street)

        continueButton.setOnClickListener {
            val region = regionTextField.text.toString();
            val street = streetTextField.text.toString();

            if(region.isEmpty() || street.isEmpty()){
                Toast.makeText(this@AddressActivity, "Please enter your address", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent()
                val extras = Bundle()

                extras.putString("region", region)
                extras.putString("street", street)

                intent.putExtras(extras)

                setResult(RESULT_OK, intent)
                finish()
            }

        }

    }


}
