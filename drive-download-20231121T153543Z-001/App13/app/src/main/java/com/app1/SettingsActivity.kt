@file:Suppress("DEPRECATION")

package com.app1

import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper
import com.app1.db.User

class SettingsActivity : AppCompatActivity() {

    lateinit var saveButton: Button
    lateinit var cancelButton: Button
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var email: EditText
    lateinit var phonenumber: EditText
    lateinit var region: EditText
    lateinit var street: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this@SettingsActivity)
        val userId = prefs.getLong("userId", -1)

        val dbHandler = MindOiksDBOpenHelper(this@SettingsActivity, null)
        val user = dbHandler.getUser(userId)

        this.saveButton = findViewById(R.id.Save)
        this.cancelButton = findViewById(R.id.Cancel)
        this.username = findViewById(R.id.username)
        this.password = findViewById(R.id.passward)
        this.email = findViewById(R.id.email)
        this.phonenumber = findViewById(R.id.phonenumber)
        this.region = findViewById(R.id.region)
        this.street = findViewById(R.id.street)

        this.username.setText(user!!.userName)
        this.password.setText(user.password)

        if(user.email!=null) {
            this.email.setText(user.email)
        }
        if(user.phoneNum!=null) {
            this.phonenumber.setText(user.phoneNum)
        }

        if(user.region!=null) {
            this.region.setText(user.region)
        }

        if(user.street!=null) {
            this.street.setText(user.street)
        }

        saveButton.setOnClickListener {

            if (username.text.isEmpty()) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()

            } else if (password.text.isEmpty()) {
                Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show()

            }

            val newUser = User(
                id = user.id,
                userName = username.text.toString(), password = password.text.toString(),
                email = email.text.toString(), phoneNum = phonenumber.text.toString(),
                region = region.text.toString(), street = street.text.toString()
            )

            dbHandler.updateUser(newUser)

            finish()
        }


        cancelButton.setOnClickListener {
            finish()
        }
    }
}


