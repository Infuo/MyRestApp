package com.example.myrestapp_eboigbe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val btnS = findViewById<Button>(R.id.menuActivityBtnS)
        val btnW = findViewById<Button>(R.id.menuActivityBtnW)

        btnS.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        btnW.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }
    }
}
