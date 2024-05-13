package com.androidkotlintest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidkotlintest.activity.EditTextActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var editText: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            editText = findViewById(R.id.editText)

            editText.setOnClickListener(this)

            insets
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.editText -> {
                startActivity(Intent(this, EditTextActivity::class.java))
            }
        }

    }
}