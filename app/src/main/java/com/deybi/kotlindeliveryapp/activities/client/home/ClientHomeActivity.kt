package com.deybi.kotlindeliveryapp.activities.client.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deybi.kotlindeliveryapp.R
import com.deybi.kotlindeliveryapp.activities.MainActivity
import com.deybi.kotlindeliveryapp.databinding.ActivityClientHomeBinding
import com.deybi.kotlindeliveryapp.databinding.ActivityMainBinding
import com.deybi.kotlindeliveryapp.models.User
import com.deybi.kotlindeliveryapp.utils.SharedPref
import com.google.gson.Gson

class ClientHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClientHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getUserFromSession()
        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun getUserFromSession(){
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()) {
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            Log.d("ClientHomeActivity","Usuario : $user")
        }
    }

    private fun logout(){
        val sharedPref = SharedPref(this)
        sharedPref.remove("user")
        sharedPref.remove("rol")
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
    }
}