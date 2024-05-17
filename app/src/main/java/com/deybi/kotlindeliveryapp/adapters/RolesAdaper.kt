package com.deybi.kotlindeliveryapp.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deybi.kotlindeliveryapp.R
import com.deybi.kotlindeliveryapp.activities.client.home.ClientHomeActivity
import com.deybi.kotlindeliveryapp.activities.delivery.home.DeliveryHomeActivity
import com.deybi.kotlindeliveryapp.activities.restaurant.home.RestaurantHomeActivity
import com.deybi.kotlindeliveryapp.models.Rol
import com.deybi.kotlindeliveryapp.utils.SharedPref

class RolesAdaper(val context: Activity ,val roles: ArrayList<Rol>) : RecyclerView.Adapter<RolesAdaper.RolesViewHolder>() {

    val sharedPref = SharedPref(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RolesViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_roles,parent,false)

        return RolesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roles.size
    }

    override fun onBindViewHolder(holder:RolesViewHolder , position: Int) {
       val rol = roles [position]

        holder.textViewRol.text = rol.name
        Glide.with(context).load(rol.image ).into(holder.imageViewRol)

        holder.itemView.setOnClickListener{
            goToRol(rol)
        }
    }

    class RolesViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewRol : TextView
        val imageViewRol: ImageView

        init {
            textViewRol= view.findViewById(R.id.textview_rol)
            imageViewRol= view.findViewById(R.id.imageview_rol)
        }
    }

    private fun goToRol(rol: Rol){
        if(rol.name  == "RESTAURANTE"){
            sharedPref.save("rol","RESTAURANTE")
            val i = Intent(context, RestaurantHomeActivity::class.java)
            context.startActivity(i)
        } else if(rol.name  == "CLIENTE"){
            sharedPref.save("rol","CLIENTE")
            val i = Intent(context, ClientHomeActivity::class.java)
            context.startActivity(i)
        } else if(rol.name  == "REPARTIDOR"){
            sharedPref.save("rol","REPARTIDOR")
            val i = Intent(context, DeliveryHomeActivity::class.java)
            context.startActivity(i)
        }
    }

}