package com.deybi.kotlindeliveryapp.providers

import com.deybi.kotlindeliveryapp.api.ApiRoutes
import com.deybi.kotlindeliveryapp.models.ResponseHttp
import com.deybi.kotlindeliveryapp.models.User
import com.deybi.kotlindeliveryapp.routes.UsersRoutes
import retrofit2.Call

class UsersProvider {

    private var usersRoutes: UsersRoutes? = null

    init {
        val api = ApiRoutes()
        usersRoutes = api.getUsersRoutes()
    }

    fun register(user: User): Call<ResponseHttp>?{
        return usersRoutes?.register(user)
    }

    fun login(email: String, password: String): Call<ResponseHttp>?{
        return usersRoutes?.login(email, password)
    }
}