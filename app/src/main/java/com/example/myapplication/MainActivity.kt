package com.example.myapplication

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val applicationPreferences = "my_settings"
    private val applicationPreferencesActiveEmail = "active_email"
    private val regexEmail = Regex(pattern = "^([a-z0-9-]+)*[a-z0-9\\-]+@[a-z0-9-]+([.a-z0-9\\-]+)\\.[a-z]{2,6}\$")
    private val applicationPreferencesActivePassword = "active_password"
    private val regexPassword = Regex(pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        val database = Database()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref: SharedPreferences = getSharedPreferences(applicationPreferences, MODE_PRIVATE)
        if (pref.contains(applicationPreferencesActiveEmail)) {
            val emailFromDoc = pref.getString(applicationPreferencesActiveEmail, "0");
            if (!emailFromDoc.equals("0")) {
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
            }
        }
        toComeIn.setOnClickListener {
            val email = Email.text.toString()
            val password = Password.text.toString()
            if (regexEmail.containsMatchIn(email) && regexPassword.containsMatchIn(password)) {
                if (database.checkUser(email, password)) {
                    val i = Intent(this, Profile::class.java)
                    val editor = pref.edit()
                    editor.putString(applicationPreferencesActiveEmail, email)
                    editor.putString(applicationPreferencesActivePassword, password)
                    editor.apply()
                    startActivity(i)
                } else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "",
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }
            else if (!email.matches(regexEmail)) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Введенный email имеет неверный формат!",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
            else {
                val toast = Toast.makeText(
                    applicationContext,
                    "Введенный пароль имеет неверный формат!" +
                            "Пароль должен состоять из >= 6 символов и содержать хотя" +
                            " бы одну заглавную букву, строчную букву и цифру",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
    }
}
