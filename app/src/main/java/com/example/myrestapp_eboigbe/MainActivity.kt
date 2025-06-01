package com.example.myrestapp_eboigbe

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {

    private val credentials = listOf("User" to "123")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.mainActivityBtn)
        val userInput = findViewById<EditText>(R.id.mainActivityUser)
        val pswInput = findViewById<EditText>(R.id.mainActivityPass)
        val logoImg = findViewById<ImageView>(R.id.mainActivityImg)
        logoImg.setImageResource(R.drawable.cinema)
        loginButton.setOnClickListener {
            val entUser = userInput.text.toString()
            val entPasw = pswInput.text.toString()
            val isValid = credentials.any { (user, pass) ->
                user == entUser && pass == entPasw
            }
            if (isValid) {
                Toast.makeText(this, R.string.toastAccess, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MenuActivity::class.java))
            } else {
                Toast.makeText(this, R.string.toastDenied, Toast.LENGTH_LONG).show()
                userInput.setText("")
                pswInput.setText("")
            }
        }
    }
}
