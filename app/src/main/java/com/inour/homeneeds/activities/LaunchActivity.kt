package com.inour.homeneeds.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inour.homeneeds.MainActivity
import com.inour.homeneeds.R

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val sharedPref = this.getSharedPreferences(getString(R.string.shared_file), Context.MODE_PRIVATE)
        val user = sharedPref.getString("user","") ?: ""
        val group = sharedPref.getString("group","") ?: ""
        val intent = if (group == "") {
            Intent(this, GroupsList::class.java)
        } else if(user == "") {
            Intent(this, UsersList::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }
        startActivity(intent)
    }
}