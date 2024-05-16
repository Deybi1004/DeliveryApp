package com.deybi.kotlindeliveryapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deybi.kotlindeliveryapp.R
import com.deybi.kotlindeliveryapp.activities.client.home.ClientHomeActivity
import com.deybi.kotlindeliveryapp.databinding.ActivityMainBinding
import com.deybi.kotlindeliveryapp.models.ResponseHttp
import com.deybi.kotlindeliveryapp.models.User
import com.deybi.kotlindeliveryapp.providers.UsersProvider
import com.deybi.kotlindeliveryapp.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imageviewGoToRegister.setOnClickListener {
            goToRegister()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }

        getUserFromSession()
    }

    private fun login(){
        val email = binding.edittextEmail.text.toString()
        val password = binding.edittextPassword.text.toString()
        if (isValidateForm(email,password)){
            usersProvider.login(email,password)?.enqueue(object: Callback<ResponseHttp>{
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                    if(response.body()?.isSuccess == true){
                        Toast.makeText(this@MainActivity, response.body()?.message, Toast.LENGTH_SHORT).show()

                        saveUserInSession(response.body()?.data.toString())
                        goToClientHome()
                    } else {
                        Toast.makeText(this@MainActivity, "Los datos no son correctos", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(p0: Call<ResponseHttp>, p1: Throwable) {
                    Toast.makeText(this@MainActivity, "Hubo un error $p1", Toast.LENGTH_SHORT).show()
                }

            })
            Toast.makeText(this,"El formulario es válido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"El formulario NO es válido", Toast.LENGTH_SHORT).show()
        }


    }

    private fun String.isEmailValidForm():Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidateForm(email: String, password: String) : Boolean{
        return email.isNotBlank() && password.isNotBlank() && email.isEmailValidForm()
    }

    private fun goToRegister() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    private fun saveUserInSession(data: String){
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    private fun goToClientHome(){
        val i = Intent(this,ClientHomeActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){
        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()) {
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)
            goToClientHome()
            Log.d("ClientHomeActivity","Usuario : $user")
        }
    }
}