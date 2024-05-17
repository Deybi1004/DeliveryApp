package com.deybi.kotlindeliveryapp.activities.delivery.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deybi.kotlindeliveryapp.R
import com.deybi.kotlindeliveryapp.databinding.ActivityDeliveryHomeBinding
import com.deybi.kotlindeliveryapp.databinding.ActivitySelectRolesBinding

class DeliveryHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeliveryHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveryHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}