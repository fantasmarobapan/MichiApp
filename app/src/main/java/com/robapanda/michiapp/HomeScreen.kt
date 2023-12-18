package com.robapanda.michiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class HomeScreen : AppCompatActivity() {

    lateinit var buttonStart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        buttonStart=findViewById(R.id.startButton)
        buttonStart.setOnClickListener {

            val intent = Intent(this@HomeScreen, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.AbAcercade -> {
                val intent = Intent(this@HomeScreen, AboutScreen::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}