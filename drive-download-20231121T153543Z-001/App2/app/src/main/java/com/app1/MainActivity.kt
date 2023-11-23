package com.app1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_settings.*

class MainActivity : AppCompatActivity() {

    lateinit var loginButton: Button
    lateinit var registerButton: TextView
    lateinit var username: EditText
    lateinit var editText3: EditText
    lateinit var forgetText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        val userId = prefs.getLong("userId", -1)
        if(userId > -1L){
            val toRestaurantsActivity = Intent(this, RestaurantsActivity::class.java)
            startActivity(toRestaurantsActivity)
            finish()
        }

        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.register_button)
        username = findViewById(R.id.username_value_login_page)
        editText3 = findViewById(R.id.editText3)
        forgetText = findViewById(R.id.textView2)

        forgetText.setOnClickListener {
            val toForgetPasswordActivity = Intent(this, ForgetAcitivty::class.java)
            startActivity(toForgetPasswordActivity)
        }

        loginButton.setOnClickListener {
            if (username.text.isNotEmpty()) {

                val dbHandler = MindOiksDBOpenHelper(this@MainActivity, null)
                val user = dbHandler.getUser(username.text.toString())
                if (user != null) {
                    if (user.password?.equals(editText3.text.toString()) == true) {
                        Toast.makeText(
                            this,
                            "Welcome back " + username.text.toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        val prefs = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
                        prefs.edit().putLong("userId", user.id!!).commit()

                        val toRestaurantsActivity = Intent(this, RestaurantsActivity::class.java)
                        startActivity(toRestaurantsActivity)
                        finish()
                    } else {
                        Toast.makeText(this, "Please enter password correctly", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(this, "No user exist, please register", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            val toRegisterActivity = Intent(this, RegisterActivity::class.java)
            startActivity(toRegisterActivity)
        }

    }


}




