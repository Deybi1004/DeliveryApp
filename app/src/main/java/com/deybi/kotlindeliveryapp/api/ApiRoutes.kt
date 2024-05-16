package com.deybi.kotlindeliveryapp.api

import com.deybi.kotlindeliveryapp.routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://192.168.100.4:3000/api/"
    val retrofit = RetrofitClient()

    fun getUsersRoutes(): UsersRoutes{
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

}