package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import kotlinx.android.synthetic.main.second_activity.*

class Profile : Activity() {

    private val applicationPreferences = "my_settings"
    private val applicationPreferencesActiveEmail = "active_email"
    private val applicationPreferencesActivePassword = "active_password"
    var database = Database()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)
        val pref: SharedPreferences = getSharedPreferences(applicationPreferences, MODE_PRIVATE)
        if (pref.contains(applicationPreferencesActiveEmail)) {
            val activeEmail = pref.getString(applicationPreferencesActiveEmail, "0");
            val activePassword = pref.getString(applicationPreferencesActivePassword,"0")
            if (database.checkUser(activeEmail, activePassword)) {
                val user = database.getUser(activeEmail, activePassword)
                if (user != null) {
                    profileName.text = user.name
                    profileSurname.text = user.surname
                    profileEmail.text = user.email
                }
            }
        }
        profileButton.setOnClickListener {
            val editor = pref.edit()
            editor.putString(applicationPreferencesActiveEmail, "0")
            editor.putString(applicationPreferencesActivePassword, "0")
            editor.apply()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}