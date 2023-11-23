package com.app1

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgetAcitivty : AppCompatActivity() {

    lateinit var forgetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forget_acitivity)

        forgetButton = findViewById(R.id.forget_button)

        forgetButton.setOnClickListener {
            Toast.makeText(
                this,
                "Check you email please, the new password sent",
                Toast.LENGTH_SHORT
            ).show();
        }

    }
}
