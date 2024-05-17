package com.deybi.kotlindeliveryapp.activities.restaurant.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deybi.kotlindeliveryapp.R
import com.deybi.kotlindeliveryapp.databinding.ActivityDeliveryHomeBinding
import com.deybi.kotlindeliveryapp.databinding.ActivityRestaurantHomeBinding

class RestaurantHomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRestaurantHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}