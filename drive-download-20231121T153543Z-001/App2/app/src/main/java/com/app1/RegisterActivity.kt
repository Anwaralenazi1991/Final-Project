package com.app1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper
import com.app1.db.User

class RegisterActivity : AppCompatActivity() {


    lateinit var loginButton: TextView
    lateinit var registerButton: Button
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var email: EditText
    lateinit var phoneNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_acitivty)

        loginButton = findViewById(R.id.login_reg_page)
        registerButton = findViewById(R.id.register_button_reg_page)
        username = findViewById(R.id.username_value)
        password = findViewById(R.id.editText3)
        email = findViewById(R.id.editText4)
        phoneNumber = findViewById(R.id.editText5)

        loginButton.setOnClickListener {
            val toLoginActivity = Intent(this, MainActivity::class.java)
            startActivity(toLoginActivity)
        }

        registerButton.setOnClickListener {
            if (username.text.isEmpty()) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()

            } else {

                val dbHandler = MindOiksDBOpenHelper(this@RegisterActivity, null)
                val user = User(
                    userName = username.text.toString(), password = password.text.toString(),
                    email = email.text.toString(), phoneNum = phoneNumber.text.toString()
                )
                dbHandler.addUser(user)
                Toast.makeText(
                    this,
                    "Welcome " + username.text.toString() + " , please Login",
                    Toast.LENGTH_SHORT
                )
                    .show()
                finish()
            }
        }


    }
}
