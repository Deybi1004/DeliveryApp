package com.deybi.kotlindeliveryapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.deybi.kotlindeliveryapp.R
import com.deybi.kotlindeliveryapp.adapters.RolesAdaper
import com.deybi.kotlindeliveryapp.databinding.ActivityRegisterBinding
import com.deybi.kotlindeliveryapp.databinding.ActivitySelectRolesBinding
import com.deybi.kotlindeliveryapp.models.User
import com.deybi.kotlindeliveryapp.utils.SharedPref
import com.google.gson.Gson

class SelectRolesActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySelectRolesBinding
    private lateinit var user: User
    private lateinit var adaper: RolesAdaper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectRolesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerviewRoles.layoutManager = LinearLayoutManager(this)
        getUserFromSession()
        adaper = RolesAdaper(this , user.roles!!)
        binding.recyclerviewRoles.adapter = adaper
    }

    private fun getUserFromSession(){
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()) {
            user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            Log.d("ClientHomeActivity","Usuario : $user")
        }
    }
}